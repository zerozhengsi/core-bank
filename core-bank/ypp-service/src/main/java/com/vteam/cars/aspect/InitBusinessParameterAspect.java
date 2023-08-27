package com.vteam.cars.aspect;

import com.vteam.cars.common.RequestStore;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.api.ReqEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 分页和其他用户参数初始化切面 .<br>
 *
 * @author andy.sher
 * @date 2018/7/25 13:24
 */
@Aspect
@Component
@Order(6)
public class InitBusinessParameterAspect {

    /**
     * 初始化业务参数 .<br>
     *
     * @param joinPoint 切入点
     * @return void
     * @author andy.sher
     * @date 2018/7/25 13:29
     */
    @Around("execution(* com.vteam.cars.api.controller..*(..))")
    public Object initBaseParam(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        // 设置客户端类型
        if (ArrayUtils.isNotEmpty(args)) {
            if (args[0] instanceof ReqEntity) {
                ReqEntity reqEntity = (ReqEntity) args[0];
                if (StringUtils.isNotBlank(reqEntity.getClient())) {
                    RequestStore.setClient(reqEntity.getClient());
                }
            }
        }

        return joinPoint.proceed(args);
    }

    /**
     * 初始化分页参数 .<br>
     *
     * @param joinPoint 切入点
     * @return void
     * @author andy.sher
     * @date 2018/7/25 13:29
     */
    @Around("execution(* com.vteam.cars.api.controller..find*Page(..))")
    public Object initPageParam(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (CollectionUtils.isNotEmpty(Arrays.asList(args))) {
            if (args[0] instanceof ReqEntity) {
                ReqEntity reqEntity = (ReqEntity) args[0];
                // 初始化分页参数
                RequestStore.initPageParam(reqEntity);
            }
        }
        return joinPoint.proceed(args);
    }

}
