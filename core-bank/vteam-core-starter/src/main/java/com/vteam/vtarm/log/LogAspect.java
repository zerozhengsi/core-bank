package com.vteam.vtarm.log;

import com.vteam.vtarm.status.GlobalStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 接口调用日志记录切面 .<br>
 *
 * @author andy.sher
 * @date 2018/7/16 10:52
 */
@Slf4j
@Aspect
@Component
@Order(99999)
public class LogAspect {

    /**
     * 记录每个接口的调用日志 .<br>
     *
     * @param joinPoint 切入点
     * @return java.lang.Object 接口返回值
     * @author andy.sher
     * @date 2018/7/16 11:05
     */
    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        GlobalStatus.setBusinessStartTime(System.currentTimeMillis());
        Object obj = joinPoint.proceed();
        GlobalStatus.setBusinessEndTime(System.currentTimeMillis());
        return obj;
    }

}
