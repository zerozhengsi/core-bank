 package com.vteam.cars.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.annotation.ServiceAPILog;
import com.vteam.cars.api.entity.ServiceEntity;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.vo.FlogApiMVo;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.service.flog.FlogApiMService;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 接口调用日志记录切面 .<br>
 *
 * @author andy.sher
 * @date 2018/7/16 10:52
 */
@Slf4j
@Aspect
@Component
public class ServiceAPILogAspect {

    private static final String PARAM = "param";

    @Resource
    private FlogApiMService flogApiMService;

    @Resource(name = "smeTaskExecutor")
    protected TaskExecutor taskExecutor;

    /**
     * 记录每个对外服务API接口的调用日志 .<br>
     *
     * @param joinPoint 切入点
     * @author andy.sher
     * @date 2019/9/29 11:05
     */
    @Before("@annotation(com.vteam.cars.annotation.ServiceAPILog)")
    public void logBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ServiceAPILog serviceAPILog = methodSignature.getMethod().getDeclaredAnnotation(ServiceAPILog.class);

        // 忽略的参数下标（不参与接口记录）
        int[] ignoreArray = serviceAPILog.ignore();
        Set<Integer> ignoreIndexSet = new HashSet<>();
        if (null != ignoreArray) {
            for (int index : ignoreArray) {
                ignoreIndexSet.add(index);
            }
        }

        // 组装请求报文
        Object[] args = joinPoint.getArgs();
        JSONObject requestBodyJSON = new JSONObject();
        if (ArrayUtils.isNotEmpty(args)) {
            for (int i = 0, len = args.length; i < len; i++) {
                if (null != args[i]) {
                    if (ignoreIndexSet.contains(i)) {
                        requestBodyJSON.put(PARAM + i, GlobalConstants.Flag.IGNORE);
                    } else {
                        if (args[i] instanceof String || args[i] instanceof Integer) {
                            if(len == 1){
                                requestBodyJSON = JSONObject.parseObject(args[i].toString());
                            }else {
                                requestBodyJSON.put(PARAM + i, args[i].toString());
                            }
                        } else if (args[i] instanceof ServiceEntity || args[i] instanceof ReqEntity) {
                            if(len == 1){
                                requestBodyJSON = (JSONObject)JSONObject.toJSON(args[i]);
                            }else{
                                requestBodyJSON.put(PARAM + i, JSONObject.toJSONString(args[i]));
                            }
                        }
                    }
                } else {
                    requestBodyJSON.put(PARAM + i, GlobalConstants.Flag.NULL);
                }
            }
            // 请求报文 无用代码
//            RequestStore.getApiLog().setRequestBody(requestBodyJSON.toJSONString());
        }

        // 记录请求参数
        this.saveRequestParams(serviceAPILog.route(), serviceAPILog.name(), requestBodyJSON.isEmpty() ? null: requestBodyJSON.toJSONString());
    }

    /**
     * 记录接口请求参数
     * @param apiId
     * @param apiName
     * @param requestBody
     * @author oscar.yu
     * @date 2019/12/9 15:20
     */
    public void saveRequestParams(String apiId, String apiName, String requestBody) {
        // 接口ID
        RequestStore.getApiLog().setApiId(apiId);
        // 接口名称
        RequestStore.getApiLog().setApiName(apiName);
        // 请求报文
        RequestStore.getApiLog().setRequestBody(requestBody);
        // 请求时间
        RequestStore.getApiLog().setRequestTime(LocalDateTime.now());
    }

    /**
     * 记录每个对外服务API接口的调用日志 .<br>
     *
     * @param joinPoint 切入点
     * @author andy.sher
     * @date 2019/9/29 11:05
     */
    @AfterReturning(value = "@annotation(com.vteam.cars.annotation.ServiceAPILog)", returning = "respEntity")
    public void logAfterReturning(JoinPoint joinPoint, RespEntity respEntity) throws Throwable {
        saveSuccessLog(JSONObject.toJSONString(respEntity));
    }

    /**
     * 记录接口请求成功日志
     * @param responseBody
     * @author oscar.yu
     * @date 2019/12/9 15:17
     */
    public void saveSuccessLog(String responseBody) {
        RequestStore.getApiLog().setResultCode(Integer.valueOf(HttpStatus.OK.value()).toString());
        RequestStore.getApiLog().setResponseBody(responseBody);
        RequestStore.getApiLog().setResponseTime(LocalDateTime.now());
        log.info(JSONObject.toJSONString(RequestStore.getApiLog()));
        this.saveApiLog();
    }

    /**
     * 记录每个对外服务API接口的调用日志 .<br>
     *
     * @param joinPoint 切入点
     * @return java.lang.Object 接口返回值
     * @author andy.sher
     * @date 2019/9/29 11:05
     */
    @AfterThrowing(value = "@annotation(com.vteam.cars.annotation.ServiceAPILog)", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ServiceAPILog serviceAPILog = methodSignature.getMethod().getDeclaredAnnotation(ServiceAPILog.class);
        // 是否记录错误日志
        if(serviceAPILog.errorLog()) {
            saveFailLog(ex);
        }
    }

    /**
     * 记录接口失败日志
     * @param ex
     * @author oscar.yu
     * @date 2019/12/9 15:18
     */
    public void saveFailLog(Throwable ex) {
        RequestStore.getApiLog().setResultCode(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).toString());
        RequestStore.getApiLog().setResponseBody(JSON.toJSONString(RespEntity.error(ex.getMessage())));
        RequestStore.getApiLog().setResponseTime(LocalDateTime.now());
        log.error(JSONObject.toJSONString(RequestStore.getApiLog()));
        log.error(String.format("%s -> %s", RequestStore.getApiLog().getApiId(), ex));
        this.saveApiLog();
    }

    /**
     * 记录日志对象 .
     *
     * @author andy.sher
     * @date 2019/9/29 13:06
     */
    private void saveApiLog() {
        // 记录应用程序接口请求记录
        FlogApiMVo flogApiMVo = RequestStore.getApiLog();
        SipaBurMVo sipaBurMVo = RequestStore.getLoginUser();
        if (sipaBurMVo != null && StringUtils.isNotBlank(sipaBurMVo.getUserid())) {
            flogApiMVo.setCreateUser(sipaBurMVo.getUserid());
            flogApiMVo.setLastModUser(sipaBurMVo.getUserid());
        }
        // 20230224 toby "do*"开头的方法平台架构会托管事物，为避免业务处理失败后，日志表记录失败，单开线程进行处理
        taskExecutor.execute(() -> flogApiMService.save(flogApiMVo));
//        flogApiMService.save(RequestStore.getApiLog());
    }

}
