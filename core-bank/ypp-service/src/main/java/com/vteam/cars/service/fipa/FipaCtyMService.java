package com.vteam.cars.service.fipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FipaCtyM;
import com.vteam.cars.entity.vo.FipaCtyMVo;

import java.util.List;

/**
 * <p>
 * 国家别信息表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-09-14
 */
public interface FipaCtyMService extends IService<FipaCtyM> {

    /**
     * 获取国家别列表 .
     *
     * @param
     * @return java.util.List<com.vteam.sme.entity.vo.FipaCtyMVo> 国家别列表
     * @author andy.sher
     * @date 2018/9/14 11:45
     */
    List<FipaCtyMVo> listCountry();

    /**
     * 根据国家别代号获取国家名称
     *
     * @param ctyid
     * @return
     * @author zhuang.shao
     * @date 2018年9月17日 下午1:58:25
     */
    FipaCtyMVo getCtyDescByCtyid(String ctyid);
}
