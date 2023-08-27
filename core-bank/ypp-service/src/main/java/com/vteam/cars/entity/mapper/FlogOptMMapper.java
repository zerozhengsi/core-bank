package com.vteam.cars.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vteam.cars.entity.model.FlogOptM;
import com.vteam.cars.entity.vo.FlogOptMVo;

import java.util.List;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FlogOptMMapper extends BaseMapper<FlogOptM> {

    /**
     * 获取日志列表
     *
     * @param flogOptMVo
     * @return java.util.List<com.vteam.sme.entity.vo.FlogOptMVo>
     * @author zack.yin
     * @date 2018/7/26 10:37
     */
    List<FlogOptMVo> listOperationLogByCondition(FlogOptMVo flogOptMVo);

}
