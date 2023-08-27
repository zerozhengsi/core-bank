package com.vteam.vtarm.codec;

import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.utils.AesUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 控制层数据安全切面 .<br>
 *
 * @author andy.sher
 * @date 2018/7/16 16:41
 */
@Aspect
@Order(2)
public class DataSecurityAspect {

    @Resource
    private CodecProperties codecProperties;

    /**
     * 是否已初始化配置
     */
    private boolean isInitConfig;

    /**
     * 是否已开启接口数据编解码
     */
    private boolean isEnable;

    /**
     * 忽略的资源路径
     */
    private String[] ignoreUrl;

    /**
     * 处理数据加解密
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object dataHandler(ProceedingJoinPoint joinPoint) throws Throwable {

        if (Boolean.FALSE.equals(this.isInitConfig)) {
            this.isEnable = codecProperties.isApiEnable();
            this.ignoreUrl = codecProperties.getIgnoreUrl();
            this.isInitConfig = Boolean.TRUE;
        }

        Object[] args = joinPoint.getArgs();
        // 解密
        if (CollectionUtils.isNotEmpty(Arrays.asList(args))) {
            decrypt(args);
        }
        // 处理业务逻辑
        Object obj = joinPoint.proceed(args);
        // 加密
        if (null != obj) {
            encrypt(obj);
        }

        return obj;
    }

    /**
     * 解密参数 .<br>
     *
     * @param args 请求对象的参数数组
     * @return void
     * @author andy.sher
     * @date 2018/7/16 16:55
     */
    private void decrypt(Object[] args) {
        if (args[0] instanceof ReqEntity && !isIgnore()) {
            ReqEntity reqEntity = (ReqEntity) args[0];
            if (this.isEnable) {
                reqEntity.setData(AesUtils.decrypt(reqEntity.getData(), AesUtils.Mode.API));
            }
        }
    }

    /**
     * 加密参数 .<br>
     *
     * @param obj 响应对象
     * @return void
     * @author andy.sher
     * @date 2018/7/16 16:55
     */
    private void encrypt(Object obj) {
        if (obj instanceof RespEntity && !isIgnore()) {
            RespEntity respEntity = (RespEntity) obj;
            if (StringUtils.isBlank(respEntity.getData()) || CommonConstants.NULL.equals(respEntity.getData())) {
                respEntity.setData(null);
            } else if (this.isEnable) {
                respEntity.setData(AesUtils.encrypt(respEntity.getData(), AesUtils.Mode.API));
            }
        }
    }

    /**
     * 是否忽略加解密 .
     *
     * @param
     * @return boolean 是否忽略
     * @author andy.sher
     * @date 2019/5/6 17:25
     */
    private boolean isIgnore() {
        boolean isIgnore = false;
        if (ArrayUtils.isNotEmpty(this.ignoreUrl)) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            for (int i = 0, len = this.ignoreUrl.length; i < len; i++) {
                if (PatternMatchUtils.simpleMatch(this.ignoreUrl[i], request.getRequestURI())) {
                    isIgnore = true;
                    break;
                }
            }
        }
        return isIgnore;
    }

}
