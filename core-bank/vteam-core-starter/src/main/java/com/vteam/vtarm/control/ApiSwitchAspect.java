package com.vteam.vtarm.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.utils.SpringContextUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

/**
 * 接口开关总阀 .<br>
 *
 * @author andy.sher
 * @date 2019/9/11 11:08
 */
@Aspect
@Order(5)
public class ApiSwitchAspect {

    /**
     * 切入所有使用了注解（@ApiSwitch）的接口 .
     *
     * @param joinPoint 切入点
     * @return java.lang.Object
     * @author andy.sher
     * @date 2019/9/11 11:09
     */
    @Around("@annotation(com.vteam.vtarm.control.ApiSwitch)")
    public Object initBaseParam(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ApiSwitch apiSwitch = methodSignature.getMethod().getDeclaredAnnotation(ApiSwitch.class);

        if (ArrayUtils.isNotEmpty(apiSwitch.controls())) {
            boolean pass = true;
            for (int i = 0; i < apiSwitch.controls().length; i++) {
                ApiSwitchControl apiSwitchControl = SpringContextUtils.getBean(apiSwitch.controls()[i]);
                if (apiSwitchControl.isOpen()) {
                    continue;
                } else {
                    pass = false;
                    break;
                }
            }
            if (pass) {
                return joinPoint.proceed();
            } else {
                JSONObject data = new JSONObject();
                if (apiSwitch.isArray()) {
                    data.put(apiSwitch.root(), new JSONArray());
                } else {
                    if (StringUtils.isNotBlank(apiSwitch.root())) {
                        data.put(apiSwitch.root(), null);
                    }
                }
                return RespEntity.ok(data);
            }
        } else {
            return joinPoint.proceed();
        }
    }

}
