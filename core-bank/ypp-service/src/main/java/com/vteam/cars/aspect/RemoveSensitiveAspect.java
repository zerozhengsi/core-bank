package com.vteam.cars.aspect;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.annotation.ResponseRemoveSensitive;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.api.RespEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 响应内容脱敏切面 .<br>
 *
 * @author andy.sher
 * @date 2019/9/9 14:47
 */
@Slf4j
@Aspect
@Component
public class RemoveSensitiveAspect {

    /**
     * 拦截需要脱敏的接口，将响应内容字段进行脱敏 .
     *
     * @param joinPoint  切入点
     * @param respEntity 响应内容
     * @author andy.sher
     * @date 2019/9/9 14:52
     */
    @AfterReturning(pointcut = "@annotation(com.vteam.cars.annotation.ResponseRemoveSensitive)", returning = "respEntity")
    public void flowAdvice(JoinPoint joinPoint, RespEntity respEntity) throws Throwable {
        String responseData = respEntity.getData();
        if (StringUtils.isNotBlank(responseData)) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            ResponseRemoveSensitive responseRemoveSensitive = methodSignature.getMethod().getDeclaredAnnotation(ResponseRemoveSensitive.class);
            if (responseRemoveSensitive.multiple()) {
                JSONObject target = JSONObject.parseObject(responseData);
                if (StringUtils.isBlank(responseRemoveSensitive.target())) {
                    return;
                }
                List<?> dataList = JSONObject.parseArray(target.getString(responseRemoveSensitive.target()), responseRemoveSensitive.type());
                dataList.forEach(StringUtils::removeSensitive);
                target.put(responseRemoveSensitive.target(), dataList);
                respEntity.setData(target.toJSONString());
            } else {
                Object data;
                if (StringUtils.isNotBlank(responseRemoveSensitive.target())) {
                    JSONObject target = JSONObject.parseObject(responseData);
                    data = JSONObject.parseObject(target.getString(responseRemoveSensitive.target()), responseRemoveSensitive.type());
                } else {
                    data = JSONObject.parseObject(responseData, responseRemoveSensitive.type());
                }
                StringUtils.removeSensitive(data);
                respEntity.setData(JSONObject.toJSONString(data));
            }
            log.info(String.format("脱敏内容：%s", respEntity.getData()));
        }
    }

}
