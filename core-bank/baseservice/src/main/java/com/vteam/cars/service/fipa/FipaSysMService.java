package com.vteam.cars.service.fipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FipaSysM;
import com.vteam.cars.entity.vo.FipaSysMVo;

import java.util.List;

/**
 * <p>
 * 系统参数设定表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FipaSysMService extends IService<FipaSysM> {

    /**
     * 获取配置列表
     *
     * @return java.util.List<com.vteam.sme.entity.vo.FipaSysMVo> 配置列表
     * @author andy.sher
     * @date 2018/8/7 13:43
     */
    List<FipaSysMVo> listSystemConfig();

}
