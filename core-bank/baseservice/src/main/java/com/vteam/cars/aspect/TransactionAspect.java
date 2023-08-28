package com.vteam.cars.aspect;

import com.vteam.vtarm.transaction.TransactionProperties;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jakarta.annotation.Resource;

/**
 * 事务管理切面 .<br>
 *
 * @author andy.sher
 * @date 2019/5/16 11:14
 */
@Slf4j
@Aspect
@Component
@Order(99997)
public class TransactionAspect {

    @Resource(name = "dataSourceTransactionManager")
    private DataSourceTransactionManager dataSourceTransactionManager;

    private static DefaultTransactionDefinition def = new DefaultTransactionDefinition();

    static {
        // 创建事务隔离策略
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 设置超时时间
        TransactionProperties transactionProperties = SpringContextUtils.getBean(TransactionProperties.class);
        def.setTimeout(transactionProperties.getTimeout());
    }

    /**
     * 对每个do接口进行事务管理 .<br>
     *
     * @param joinPoint 切入点
     * @return java.lang.Object 接口返回值
     * @author andy.sher
     * @date 2018/7/16 11:05
     */
    @Around("execution(* com.vteam.cars.api.controller..do*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(def);

        boolean result = false;
        Object obj;
        try {
            obj = joinPoint.proceed();
            dataSourceTransactionManager.commit(transactionStatus);
            result = true;
        } catch (Throwable throwable) {
            dataSourceTransactionManager.rollback(transactionStatus);
            throw throwable;
        } finally {
            log.info("transaction:{} -> {}", joinPoint.toString(), result);
        }
        return obj;
    }

}
