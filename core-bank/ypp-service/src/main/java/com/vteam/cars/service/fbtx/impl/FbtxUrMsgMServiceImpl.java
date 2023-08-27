package com.vteam.cars.service.fbtx.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.entity.mapper.FbtxUrMsgMMapper;
import com.vteam.cars.entity.model.FbtxUrMsgM;
import com.vteam.cars.entity.vo.FbtxUrMsgMVo;
import com.vteam.cars.service.fbtx.FbtxUrMsgMService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 未读即时消息表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
@Service
public class FbtxUrMsgMServiceImpl extends ServiceImpl<FbtxUrMsgMMapper, FbtxUrMsgM> implements FbtxUrMsgMService {

    @Resource(type = FbtxUrMsgMMapper.class)
    private FbtxUrMsgMMapper fbtxUrMsgMMapper;

    @Override
    public List<FbtxUrMsgM> listUnreadMessageByCondition(FbtxUrMsgM condition) {
        return fbtxUrMsgMMapper.listUnreadMessageByCondition(condition);
    }

    @Override
    public void saveUnreadMsg(FbtxUrMsgMVo fbtxUrMsgMVo) {
        super.save(fbtxUrMsgMVo);
    }
}
