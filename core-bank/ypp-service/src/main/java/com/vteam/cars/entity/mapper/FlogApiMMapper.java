package com.vteam.cars.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vteam.cars.entity.model.FlogApiM;
import com.vteam.cars.entity.vo.FlogApiMVo;

import java.util.List;

/**
 * <p>
 * 应用程序接口记录表 Mapper 接口
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FlogApiMMapper extends BaseMapper<FlogApiM> {

    /**
     * 查询接口日志列表 .
     *
     * @param flogApiMVo
     * @return java.util.List<com.vteam.mssme.entity.vo.FlogApiMVo>
     * @author fu.tong
     * @date 2019/6/4 16:14
     */
    List<FlogApiMVo> listApiLogByCondition(FlogApiMVo flogApiMVo);

}
