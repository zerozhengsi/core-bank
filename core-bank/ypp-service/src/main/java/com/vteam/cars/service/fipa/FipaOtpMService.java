package com.vteam.cars.service.fipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FipaOtpM;
import com.vteam.cars.entity.vo.FipaOtpMVo;

import java.util.List;

/**
 * <p>
 * 行业类别表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FipaOtpMService extends IService<FipaOtpM> {

    /**
     * 根据条件获取行业类别
     *
     * @param fipaOtpMVo 行业类别扩展
     * @return java.util.List<com.vteam.cars.entity.model.FipaOtpM>
     * @author jiangming.huang
     * @date 2019/4/28 16:15
     */
    List<FipaOtpM> listFipaOtpMByParams(FipaOtpMVo fipaOtpMVo);

}
