package com.vteam.cars.plugin.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.model.FipaNumM;
import com.vteam.cars.entity.model.FspaSeqM;
import com.vteam.cars.plugin.mybatisplus.handler.BeforePersistenceHandler;
import com.vteam.cars.service.fipa.FipaNumMService;
import com.vteam.cars.service.fspa.FspaSeqMService;
import com.vteam.vtarm.cache.SequenceContainer;
import com.vteam.vtarm.runner.Runner;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 同步编号序列 .<br>
 *
 * @author andy.sher
 * @date 2019/6/20 13:44
 */
@Component
public class SynchronizeSequenceRunner implements Runner {

    @Resource
    private FipaNumMService fipaNumMService;

    @Resource
    private FspaSeqMService fspaSeqMService;

    @Resource
    private SequenceContainer sequenceContainer;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Override
    public int order() {
        return 6;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void run() throws Exception {
        BeforePersistenceHandler.skipFill();
        // 初始化系统编号信息表
        this.initFipaNum();
        // 初始化数据库序列信息表
        this.initFspaSeq();
//        final String dbType = smeConfiguration.getDbType();
//        // mysql下使用redis序列，Oracle使用自己的序列
//        if (GlobalConstants.DbType.SYSTEM_DB_MYSQL.equals(dbType)
//                || GlobalConstants.DbType.SYSTEM_DB_INSPUR_KDB.equals(dbType)
//                || GlobalConstants.DbType.SYSTEM_DB_DB2.equals(dbType)) {
//            this.initFspaSeq();
//        }
        BeforePersistenceHandler.resetFill();
    }

    /**
     * 初始化系统编号信息表
     */
    private void initFipaNum() {
        List<FipaNumM> fipaNumMList = fipaNumMService.list();
        if (CollectionUtils.isNotEmpty(fipaNumMList)) {
            String key;
            String first = GlobalConstants.ArabicNumeral.N1;
            for (FipaNumM fipaNumM : fipaNumMList) {
                final Integer currentValue = fipaNumM.getCurrSeqnum();
                key = fipaNumMService.getSequenceKey(String.format("%s-%s", String.valueOf(fipaNumM.getNumType()), fipaNumM.getNumValue()));
                long currentSeq = sequenceContainer.incr(key);
                if (first.equals(Long.toString(currentSeq))) {
                    // 值为1，首次同步至Redis
                    sequenceContainer.set(key, String.valueOf(currentValue));
                } else if ((int) currentSeq > currentValue) {
                    // Redis值大于数据库，同步至数据库
                    fipaNumM.setCurrSeqnum((int) currentSeq);
                    fipaNumMService.updateById(fipaNumM);
                } else if ((int) currentSeq < currentValue) {
                    // Redis值小于数据库，同步至Redis
                    sequenceContainer.set(key, String.valueOf(currentValue));
                }
            }
        }
    }

    /**
     * 初始化数据库序列信息表
     */
    private void initFspaSeq() {
        List<FspaSeqM> fspaSeqMList = fspaSeqMService.list();
        if (CollectionUtils.isNotEmpty(fspaSeqMList)) {
            String key;
            String first = GlobalConstants.ArabicNumeral.N1;
            for (FspaSeqM fspaSeqM : fspaSeqMList) {
                final Integer currentValue = fspaSeqM.getCurrentValue();
                key = fspaSeqMService.getSequenceKey(fspaSeqM.getName());
                long currentSeq = sequenceContainer.incr(key);
                if (first.equals(Long.toString(currentSeq))) {
                    // Redis值为1，首次同步至Redis
                    sequenceContainer.setIfAbsent(key, String.valueOf(currentValue));
                } else if ((int) currentSeq > currentValue) {
                    // Redis值大于数据库，同步至数据库
                    fspaSeqM.setCurrentValue((int) currentSeq);
                    fspaSeqMService.updateById(fspaSeqM);
                } else if ((int) currentSeq < currentValue) {
                    // Redis值小于数据库，同步至Redis
                    sequenceContainer.set(key, String.valueOf(currentValue));
                }
            }
        }
    }

}
