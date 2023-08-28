package com.vteam.cars.aspect;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.util.SmeAssert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 分页切面 .<br>
 *
 * @author andy.sher
 * @date 2018/7/27 15:57
 */
@Aspect
@Component
@Order(8)
public class PageAspect {

    private static final String TOTAL_COUNT = "totalCount";

    /**
     * 分页处理器 .<br>
     *
     * @param joinPoint 可放行的方法切入点
     * @return java.lang.Object 方法返回值
     * @author andy.sher
     * @date 2018/7/30 14:49
     */
    @Around("execution(* com.vteam.cars.api.controller..find*Page(..))")
    public Object pageHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        SmeAssert.notNull(RequestStore.getPageNum(), "页码不能为空。");
        SmeAssert.notNull(RequestStore.getPageSize(), "每页大小不能为空。");
        Page<Object> page = PageHelper.startPage(RequestStore.getPageNum(), RequestStore.getPageSize());
        RequestStore.setPage(page);
        // 处理业务逻辑
        Object object = joinPoint.proceed(args);
        // 设置总记录数
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        // 此处page分页对象不从方法变量获取，用于兼容分页方法中有多个查询的写法
        beanWrapper.setPropertyValue(TOTAL_COUNT, RequestStore.getPage().getTotal());
        return object;
    }

}
