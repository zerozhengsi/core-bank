package com.vteam.cars.service.flog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FlogOptM;
import com.vteam.cars.entity.vo.FlogOptMVo;
import com.vteam.cars.entity.vo.SipaBurMVo;

import java.util.List;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
public interface FlogOptMService extends IService<FlogOptM> {

    /**
     * 获取符合条件的操作日志列表 .
     *
     * @param condition
     * @return java.util.List<com.vteam.sme.entity.vo.FlogOptMVo>
     * @author zack.yin
     * @date 2018/7/27 13:15
     */
    List<FlogOptMVo> listOperationLogByCondition(FlogOptMVo condition);
    /**
     * 记录用户登出日志 .<br>
     *
     * @param sipaBurMVo 用户信息
     * @return void
     * @author andy.sher
     * @date 2018/8/6 11:03
     */
    void saveLogoutInfo(SipaBurMVo sipaBurMVo);

    /**
     * 记录用户登录日志 .<br>
     *
     * @param sipaBurMVo 用户信息
     * @return void
     * @author andy.sher
     * @date 2018/8/6 12:55
     */
    void saveLoginInfo(SipaBurMVo sipaBurMVo);

    /**
     * 添加操作日志 .
     *
     * @param edtid
     * @param edtDesc
     * @return void
     * @author zack.yin
     * @date 2019/6/5 11:48
     */
    void saveLogInfo(String edtid, String edtDesc);

    /**
     * 添加操作日志(用于兼容接口请求，单独传入用户信息) .
     *
     * @param sipaBurMVo
     * @param edtid
     * @param edtDesc
     * @author oscar.yu
     * @date 2020/5/9 14:11
     */
    void saveLogInfo(SipaBurMVo sipaBurMVo, String edtid, String edtDesc);
}
