package com.vteam.cars.plugin.tasks;

import com.vteam.cars.entity.model.FspaSeqM;
import com.vteam.cars.service.fipa.FipaNumMService;
import com.vteam.cars.service.fspa.FspaSeqMService;
import com.vteam.vtarm.cache.SequenceContainer;
import com.vteam.vtarm.cluster.TaskClusterLock;
import com.vteam.vtarm.task.Task;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 同步序列信息到数据库 .<br>
 *
 * @author andy.sher
 * @date 2019/6/20 14:57
 */
@Component
public class SynchronizeSequenceJob extends Task {

    @Resource
    private FipaNumMService fipaNumMService;

    @Resource
    private FspaSeqMService fspaSeqMService;

    @Resource
    private SequenceContainer sequenceContainer;

    @Override
    protected boolean active() {
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @TaskClusterLock
    @Override
    public Integer execute() {
        // 同步系统编号信息表
        this.saveFipaNum();
        // 同步数据库序列信息表
        this.saveFspaSeq();
        return null;
    }

    /**
     * 同步系统编号信息表
     */
    private void saveFipaNum() {

    }

    /**
     * 同步数据库序列信息表
     */
    private void saveFspaSeq() {
        List<FspaSeqM> fspaSeqMList = fspaSeqMService.list();
        if (CollectionUtils.isNotEmpty(fspaSeqMList)) {
            String key;
            int first = 1;
            for (FspaSeqM fspaSeqM : fspaSeqMList) {
                final Integer currentValue = fspaSeqM.getCurrentValue();
                key = fspaSeqMService.getSequenceKey(fspaSeqM.getName());
                long currentSeq = sequenceContainer.incr(key);
                if (Integer.toString(first).equals(Long.toString(currentSeq))) {
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
