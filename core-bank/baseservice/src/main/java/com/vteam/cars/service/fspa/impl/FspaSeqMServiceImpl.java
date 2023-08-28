package com.vteam.cars.service.fspa.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.mapper.FspaSeqMMapper;
import com.vteam.cars.entity.model.FspaSeqM;
import com.vteam.cars.service.fspa.FspaSeqMService;
import com.vteam.vtarm.cache.SequenceContainer;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * <p>
 * 数据库序列表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
@Service
public class FspaSeqMServiceImpl extends ServiceImpl<FspaSeqMMapper, FspaSeqM> implements FspaSeqMService {

    @Resource
    private SequenceContainer sequenceContainer;

    @Override
    public String getSequenceKey(String key) {
        return FspaSeqMServiceImpl.class.getSimpleName() + GlobalConstants.Symbol.SEMICOLON + "sequence" + GlobalConstants.Symbol.SEMICOLON + key;
    }

    @Override
    public int nextval(String sequenceName) {
        return (int) sequenceContainer.incr(getSequenceKey(sequenceName));
    }

}
