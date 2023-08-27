package com.vteam.cars.service.fipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FipaCcyM;
import com.vteam.cars.entity.vo.FipaCcyMVo;

import java.util.List;

/**
 * <p>
 * 货币别信息表 服务类
 * </p>
 *
 * @author vteam-generator
 *
 * @since 2018-07-20
 */
public interface FipaCcyMService extends IService<FipaCcyM> {

    /**
     * 获取货币别列表 .
     *
     * @param
     * @return java.util.List<com.vteam.sme.entity.vo.FipaCcyMVo>
     * @author andy.sher
     * @date 2018/9/14 15:03
     */
    List<FipaCcyMVo> listCurrency();
    
    /**
     * 根据条件获取币种信息
     * 
     * @return
     * @author zhuang.shao
     * @date 2019年5月15日 下午5:53:08
     */
    List<FipaCcyMVo> listCurrencyByCondition(FipaCcyMVo fipaCcyMVo);
}
