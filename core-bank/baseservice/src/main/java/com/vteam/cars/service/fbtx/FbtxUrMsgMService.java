package com.vteam.cars.service.fbtx;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FbtxUrMsgM;
import com.vteam.cars.entity.vo.FbtxUrMsgMVo;

import java.util.List;

/**
 * <p>
 * 未读即时消息表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FbtxUrMsgMService extends IService<FbtxUrMsgM> {

    /**
     * 按条件查询未读即时消息列表 .
     *
     * @param condition 查询条件
     * @return java.util.List<com.vteam.sme.entity.model.FbtxUrMsgM>
     * @author andy.sher
     * @date 2018/9/29 19:48
     */
    List<FbtxUrMsgM> listUnreadMessageByCondition(FbtxUrMsgM condition);

    /**
     * 保存未读信息 .
     *
     * @param fbtxUrMsgMVo 未读信息
     * @return void
     * @author andy.sher
     * @date 2018/9/30 10:15
     */
    void saveUnreadMsg(FbtxUrMsgMVo fbtxUrMsgMVo);
}
