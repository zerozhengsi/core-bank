package com.vteam.cars.aspect;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.TimeoutConstants;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.exception.MSSmeBusinessException;
import com.vteam.cars.util.ListUtils;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.VtarmProperties;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.security.CheckTemporarilyCode;
import com.vteam.vtarm.security.DefaultUrlAuthFilter;
import com.vteam.vtarm.security.GrantAuthorization;
import com.vteam.vtarm.security.LoginRequest;
import com.vteam.vtarm.status.GlobalStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;

/**
 * 认证控制切面 .<br>
 *
 * @author andy.sher
 * @date 2019/4/18 9:48
 */
@Aspect
@Component
@Slf4j
public class SystemAspect {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    @Resource
    private VtarmProperties vtarmProperties;

    @Resource
    private MapContainer mapContainer;

    /** 登录注册表 */
    private static final String LOGIN_REGISTRY = "login-registry";

    /**
     * 临时授权码使用次数
     */
    private static final int TEMP_AUTH_CODE_COUNT = 50;

    /**
     * 正常登录后装载用户认证授权信息 .
     *
     * @author andy.sher
     * @date 2019/4/19 8:57
     */
    @Around("@annotation(com.vteam.vtarm.security.LoginRequest)")
    public Object loginAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取登录用户
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LoginRequest loginRequest = methodSignature.getMethod().getDeclaredAnnotation(LoginRequest.class);
        // 校验登录凭证(临时授权码)
        Object[] args = joinPoint.getArgs();
        if (ListUtils.isNotEmpty(args) && args[0] instanceof ReqEntity) {
            ReqEntity reqEntity = (ReqEntity) args[0];
            if (loginRequest.needCertificate() && GlobalConstants.Flag.PC.equals(reqEntity.getClient()) && !vtarmProperties.isDeveloperMode()) {
                JSONObject reqData = JSONObject.parseObject(reqEntity.getData());
                final String keyUuid = reqData.getString(GlobalConstants.Flag.KEY);
                // 根据验证码类型，获取临时授权码键值，默认为图形验证码
                String codeType = reqData.getString(GlobalConstants.Flag.CODE_TYPE);
                // 临时授权码键值
                String temporarilyCodeKey = keyUuid;
                if (StringUtils.isNotBlank(codeType) && GlobalConstants.Flag.CODE_TYPE_PHONE.equals(codeType)) {
                    String loginId = reqData.getString(GlobalConstants.Flag.LOGIN_ID);
                    temporarilyCodeKey = getTemporarilyCodeKey(loginId, keyUuid);
                }
                // 临时授权码对应值
                String temporarilyCode = stringValueContainer.get(temporarilyCodeKey);
                // 校验临时校验码
                this.doCheckTemporarilyCode(keyUuid, temporarilyCodeKey, temporarilyCode);
            }

            // 执行业务
            Object respObj = joinPoint.proceed();

            // 构造token
            if (respObj instanceof RespEntity) {
                RespEntity respEntity = (RespEntity) respObj;
                // 转换为json对象
                JSONObject data = JSONObject.parseObject(respEntity.getData());

                // 获取用户信息
                JSONObject loginUser = data.getJSONObject(loginRequest.value());
                SipaBurMVo sipaBurMVo = JSONObject.parseObject(loginUser.toJSONString(), SipaBurMVo.class);

                // 校验用户是否可用
                SmeAssert.eq(FieldConstant.SipaBurM.USE_FLAG_ENABLE, sipaBurMVo.getUseFlag(), "该用户已被禁用。");

                // 计算过期时间
                LocalDateTime exTime = LocalDateTime.now().plusSeconds(smeConfiguration.getSecurityTimeToLive());

                // 设置认证信息
                GlobalStatus.setAnonymous(false);
                GlobalStatus.getAuthenticationInfo().setExpiresIn(exTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                GlobalStatus.getAuthenticationInfo().setAdditional(JSONObject.toJSONString(sipaBurMVo), GlobalStatus.getAuthenticationInfo());

                // 记录注册表
                mapContainer.put(LOGIN_REGISTRY, sipaBurMVo.getUserid(), GlobalStatus.getAuthenticationInfo().getAccessToken());

                data.put(DefaultUrlAuthFilter.TOKEN, GlobalStatus.getAuthenticationInfo().getAccessToken());

                respEntity.setData(data.toJSONString());
            }
            return respObj;
        } else {
            return joinPoint.proceed();
        }
    }

    /**
     * 用户授权
     *
     * @author andy.sher
     * @date 2019/4/19 8:57
     */
    @AfterReturning(pointcut = "@annotation(com.vteam.vtarm.security.GrantAuthorization)", returning = "respEntity")
    public void grantAuthorizationAfter(JoinPoint joinPoint, RespEntity respEntity) {
        // 获取用户信息
        JSONObject data = JSONObject.parseObject(respEntity.getData());
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        GrantAuthorization grantAuthorization = methodSignature.getMethod().getDeclaredAnnotation(GrantAuthorization.class);

        // 临时授权，token只能授权一次
        if (grantAuthorization.temporarily()) {
            // 存储临时令牌并返回给前端，用作后续业务接口调用合法性校验
            if (null == data) {
                data = new JSONObject();
            }
            String scope;
            String key;
            String uuid = UUID.randomUUID().toString();
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            if (!data.isEmpty() && StringUtils.isNotBlank(grantAuthorization.scope())) {
                scope = data.getString(grantAuthorization.scope());
                if (StringUtils.isNotBlank(scope)) {
                    key = getTemporarilyCodeKey(scope, uuid);
                } else {
                    key = uuid;
                }
            } else {
                key = uuid;
            }
            stringValueContainer.set(key, uuid + request.getRequestedSessionId(), TimeoutConstants.VERIFICATION_CODE_TIMEOUT);
            data.put(GlobalConstants.Flag.UUID, uuid);
            respEntity.setData(data.toJSONString());
        }
        // 限时授权,token有效期内可重复续费，若客户端超过15分钟未进行操作则授权过期。
        else {
            JSONObject loginUserJSON = data.getJSONObject(grantAuthorization.value());
            SipaBurMVo loginUser = JSONObject.parseObject(loginUserJSON.toJSONString(), SipaBurMVo.class);

            GlobalStatus.getAuthenticationInfo().setAdditional(JSONObject.toJSONString(loginUser), GlobalStatus.getAuthenticationInfo());
        }
    }

    /**
     * 校验临时授权码的合法性 .
     *
     * @author oscar.yu
     * @date 2019/12/10 19:12
     */
    @Before("@annotation(com.vteam.vtarm.security.CheckTemporarilyCode)")
    public void checkTemporarilyCodeBefore(JoinPoint joinPoint) {
        // 增加判断验证码类型，若配置为图片验证码则跳过合法性验证控制，验证码类型[1=短信/0=图形]
        if (GlobalConstants.Flag.CODE_TYPE_IMAGE.equals(smeConfiguration.getVerificationFlag())) {
            return;
        }
        // 获取配置信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        CheckTemporarilyCode checkConfig = methodSignature.getMethod().getDeclaredAnnotation(CheckTemporarilyCode.class);
        // 获取参数信息
        Object[] args = joinPoint.getArgs();
        ReqEntity reqEntity = null;
        if (ArrayUtils.isNotEmpty(args)) {
            if (args[0] instanceof ReqEntity) {
                reqEntity = (ReqEntity) args[0];
            }
        }
        // 校验请求数据合法性
        if (null != reqEntity && StringUtils.isNotBlank(reqEntity.getData())) {
            JSONObject data = JSONObject.parseObject(reqEntity.getData());
            final String uuid = data.getString(checkConfig.uuid());
            final String scope = data.getString(checkConfig.scope());
            SmeAssert.notBlank(uuid, "验证码为空。");
            SmeAssert.notBlank(scope, "验证码对应业务信息为空。");
            final String temporarilyCodeKey = getTemporarilyCodeKey(scope, uuid);
            final String temporarilyCode = stringValueContainer.get(temporarilyCodeKey);
            // 校验临时校验码
            this.doCheckTemporarilyCode(uuid, temporarilyCodeKey, temporarilyCode);
        }
    }

    /**
     * 获取临时授权包含业务信息的缓存键值
     *
     * @param scope 业务信息
     * @param uuid  授权码
     * @author oscar.yu
     * @date 2019/12/10 18:41
     */
    public static String getTemporarilyCodeKey(String scope, String uuid) {
        return String.format("%s:%s", scope, uuid);
    }

    /**
     * 校验临时校验码
     *
     * @author oscar.yu
     * @date 2021/9/13 14:52
     */
    private void doCheckTemporarilyCode(String keyUuid, String temporarilyCodeKey, String temporarilyCode) {
        SmeAssert.notBlank(temporarilyCode, "验证码非法或已失效。");
        SmeAssert.eq(keyUuid + GlobalStatus.getRequest().getRequestedSessionId(), temporarilyCode,
                MSSmeBusinessException.DEFAULT);
        // 检查临时授权码的使用次数，超过50次则删除，此种处理是兼容授权码认证通过后业务抛错的情况
        String countStr = this.stringValueContainer.get(temporarilyCode);
        if (StringUtils.isBlank(countStr)) {
            stringValueContainer.set(temporarilyCode, GlobalConstants.ArabicNumeral.N1, TimeoutConstants.VERIFICATION_CODE_TIMEOUT);
        } else {
            int count = Integer.parseInt(countStr) + 1;
            if (count >= TEMP_AUTH_CODE_COUNT) {
                // 删除临时授权码
                stringValueContainer.delete(temporarilyCodeKey);
                stringValueContainer.delete(temporarilyCode);
            }
            stringValueContainer.set(temporarilyCode, Integer.toString(count), TimeoutConstants.VERIFICATION_CODE_TIMEOUT);
        }
    }
}
