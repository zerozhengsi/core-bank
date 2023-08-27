package com.vteam.cars.service.fipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FipaAreM;
import com.vteam.cars.entity.vo.FipaAreMVo;

import java.util.List;

/**
 * <p>
 * 地区信息表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FipaAreMService extends IService<FipaAreM> {

    /**
     * 根据条件获取地区信息
     *
     * @param fipaAreMVo 地区信息扩展
     * @return List<FipaAreMVo>
     * @author zhuang.shao
     * @date 2018年9月14日 上午10:30:50
     */
    List<FipaAreMVo> listAreByCondition(FipaAreMVo fipaAreMVo);

}
