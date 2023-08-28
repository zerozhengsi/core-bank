package com.vteam.cars.service.flog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FlogApiM;
import com.vteam.cars.entity.vo.FlogApiMVo;

import java.util.List;

/**
 * <p>
 * 应用程序接口记录表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2019-02-18
 */
public interface FlogApiMService extends IService<FlogApiM> {

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
