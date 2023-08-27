package com.vteam.vtarm.security;

import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.status.GlobalStatus;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.AntPathMatcher;

/**
 * 指定网段服务切面 .<br>
 *
 * @author andy.sher
 * @date 2019/12/23 10:32
 */
@Aspect
public class InternalServiceAspect {

    private AntPathMatcher antPathMatcher = new AntPathMatcher(CommonConstants.Symbol.SPOT);

    /**
     * 拦截请求，需要满足指定网段才能访问 .
     *
     * @param joinPoint 切入点
     * @return void
     * @author andy.sher
     * @date 2019/4/22 13:31
     */
    @Before("@annotation(com.vteam.vtarm.security.InternalService)")
    public void requiredAuthorized(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        InternalService internalService = methodSignature.getMethod().getDeclaredAnnotation(InternalService.class);

        String remoteAddr = GlobalStatus.getRequest().getRemoteAddr();
        String segment = internalService.segment();

        if (!antPathMatcher.match(segment, remoteAddr)) {
            throw new UnauthorizedException();
        }
    }

}
