package com.vteam.cars.aspect;

import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.util.StringUtils;
import com.vteam.cars.exception.MSSmeBusinessException;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.status.GlobalStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 用于限制用户导出文件操作，当用户在第一次导出文件进行中的时候，拦截用户第1+N次请求 .<br>
 *
 * @author andy.sher
 * @date 2019/5/13 10:58
 */
@Aspect
@Component
public class ExportFileAspect {

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    /**
     * 运营端请求
     */
    private static String PATTERN = "/operation/**";

    /**
     * 超时时间
     */
    private static int TIMEOUT = 10;

    /**
     * 缓存key
     */
    public static String BASE_CACHE_KEY = ExportFileAspect.class.getSimpleName() + GlobalConstants.Symbol.SEMICOLON;

    /**
     * 用于限制用户导出文件操作，当用户在第一次导出文件进行中的时候，拦截用户第1+N次请求
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.vteam.cars.api.controller..doExport*(..))")
    public Object exportFileAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        if (StringUtils.isNotBlank(GlobalStatus.getAuthenticationInfo().getAccessToken())) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 只处理运营端接口
            if (PatternMatchUtils.simpleMatch(PATTERN, request.getRequestURI())) {
                String key = BASE_CACHE_KEY + RequestStore.getLoginUser().getUserid();
                if (!stringValueContainer.setIfAbsent(key, key, TIMEOUT)) {
                    throw new MSSmeBusinessException("导出动作正在进行中，请耐心等待。");
                }
            }
        }
        return joinPoint.proceed();
    }

}
