package com.vteam.cars.api.controller.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.TimeoutConstants;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.service.flog.FlogOptMService;
import com.vteam.cars.service.sipa.SipaBurMService;
import com.vteam.cars.util.DateUtils;
import com.vteam.cars.util.MD5Utils;
import com.vteam.cars.util.SmeAssert;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.security.LoginRequest;
import com.vteam.vtarm.security.LogoutRequest;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证控制器 .
 *
 * @author andy.sher
 * @date 2019/4/19 9:25
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    @Resource(type = SipaBurMService.class)
    private SipaBurMService sipaBurMService;

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Resource(type = FlogOptMService.class)
    private FlogOptMService flogOptMService;

    private static final String LOGIN_TRY_COUNT = "login-try-count-%s-%s";

    private static final String LOCKED_USER = "locked-user-%s-%s";

    /**
     * 用户登录 .<br>
     *
     * @param reqEntity 请求对象
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/24 18:16
     */
    @PostMapping("/login")
    @LoginRequest(value = "loginUser", needCertificate = true)
    public RespEntity login(@RequestBody @NotNull ReqEntity reqEntity) {
        SipaBurMVo loginUser = JSON.parseObject(reqEntity.getData(), SipaBurMVo.class);
        JSONObject data = new JSONObject();
        final String loginId = loginUser.getLoginId();
        SmeAssert.notBlank(loginId, "登录账号为空。");

        // 运营端用户名登录
        SipaBurMVo sipaBurMVo = sipaBurMService.getUserInfoByUserid(loginId, FieldConstant.PublicFieldValue.SYSTEM_TYPE_MANAGE);
        SmeAssert.notNull(sipaBurMVo, "用户名或密码错误。");

        // 尝试次数
        String tryCountKey = String.format(LOGIN_TRY_COUNT, loginId, FieldConstant.PublicFieldValue.SYSTEM_TYPE_MANAGE);
        String lockedKey = String.format(LOCKED_USER, loginId, FieldConstant.PublicFieldValue.SYSTEM_TYPE_MANAGE);
        int tryCount = 0;
        // 初始密码为空
        if (!MD5Utils.encryptPwd(loginUser.getPassword()).equals(sipaBurMVo.getPassword()) && !GlobalConstants.ArabicNumeral.N1.equals(sipaBurMVo.getChgpwdFlag())) {

            String count = stringValueContainer.get(tryCountKey);
            if (StringUtils.isNotBlank(count)) {
                tryCount = Integer.parseInt(count);
            }
            tryCount++;
            stringValueContainer.set(tryCountKey, Integer.toString(tryCount), TimeoutConstants.DEFAULT_TIMEOUT);

            Map<String, String> model = new HashMap<>(1);
            // 用户登录最大尝试次数
            final Integer securityMaxRetryTotal = smeConfiguration.getSecurityMaxRetryTotal();
            if (securityMaxRetryTotal.equals(tryCount) || tryCount > securityMaxRetryTotal) {
                // 是否已设置锁定时间
                if (StringUtils.isBlank(stringValueContainer.get(lockedKey))) {
                    // 设置锁定时间
                    stringValueContainer.set(lockedKey, LocalDateTime.now().plusHours(smeConfiguration.getSecurityLockedHours()).format(DateUtils.FMT_SECOND));
                }
            } else {
                model.put("count", Integer.toString(securityMaxRetryTotal - tryCount));
                SmeAssert.eq(sipaBurMVo.getPassword(), loginUser.getPassword(), "用户名或密码错误，剩余尝试次数${count}次。", model);
            }
        }

        // 用户已锁定
        if (StringUtils.isNotBlank(stringValueContainer.get(lockedKey))) {
            // 获取锁定时间
            LocalDateTime lockedTime = LocalDateTime.parse(stringValueContainer.get(lockedKey), DateUtils.FMT_SECOND);

            // 锁定未过期
            if (!LocalDateTime.now().isAfter(LocalDateTime.parse(stringValueContainer.get(lockedKey), DateUtils.FMT_SECOND))) {
                Map<String, String> model = new HashMap<>(1);
                model.put("time", lockedTime.format(DateUtils.FMT_SECOND));
                SmeAssert.isNull(lockedTime, "为了您的账户安全，账号将锁定至${time}。", model);
            }
        }

        // 清空锁定状态
        stringValueContainer.delete(lockedKey);
        stringValueContainer.delete(tryCountKey);

        // 记录用户登陆信息
        sipaBurMVo.setLastLoginDate(LocalDateTime.now());
        sipaBurMService.updateById(sipaBurMVo);

        // 如果是后台用户则用户登录日志
        if (FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION.equals(sipaBurMVo.getSystemType())) {
            flogOptMService.saveLoginInfo(sipaBurMVo);
        }
        data.put("loginUser", sipaBurMVo);
        return RespEntity.ok(data);
    }

    /**
     * 重置密码
     *
     * @param reqEntity 请求加密参数
     * @return com.vteam.sme.api.entity.RespEntity
     * @author fu.tong
     * @date 2018/7/24 16:35
     */
    @PostMapping(value = "/doResetPassword")
    public RespEntity doResetPassword(@RequestBody ReqEntity reqEntity) {
        SipaBurMVo sipaBurMVo = JSON.parseObject(reqEntity.getData(), SipaBurMVo.class);
        SmeAssert.notBlank(sipaBurMVo.getLoginId(), "登录账号为空。");
        JSONObject data = new JSONObject();
        boolean resultFlag = false;
        if (sipaBurMVo.getConfirmPassword().equals(sipaBurMVo.getPassword())) {
            resultFlag = sipaBurMService.updatePassword(sipaBurMVo);
        }
        // 结果[1=成功/0=失败]
        data.put("result", resultFlag ? GlobalConstants.ArabicNumeral.N1 : GlobalConstants.ArabicNumeral.N0);
        return RespEntity.ok(data);
    }

    /**
     * 用户退出系统 .<br>
     *
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2019/4/16 11:29
     */
    @GetMapping("/logout")
    @LogoutRequest
    public RespEntity logout() {
        // 获取用户信息
        SipaBurMVo sipaBurMVo = RequestStore.getLoginUser();

        if (StringUtils.isNotBlank(sipaBurMVo.getUserid())) {
            // 用户登出日志
            flogOptMService.saveLogoutInfo(sipaBurMVo);
        }

        return RespEntity.ok();
    }

}
