package com.vteam.cars.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vteam.cars.entity.model.FbtxUrMsgM;

import java.util.List;

/**
 * <p>
 * 未读即时消息表 Mapper 接口
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FbtxUrMsgMMapper extends BaseMapper<FbtxUrMsgM> {

    /**
     * 按条件查询未读消息列表 .
     *
     * @param condition 查询条件
     * @return java.util.List<com.vteam.sme.entity.model.FbtxUrMsgM>
     * @author andy.sher
     * @date 2018/9/29 19:49
     */
    List<FbtxUrMsgM> listUnreadMessageByCondition(FbtxUrMsgM condition);

}
