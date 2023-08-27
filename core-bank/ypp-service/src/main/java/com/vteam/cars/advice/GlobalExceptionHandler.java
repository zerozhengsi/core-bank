package com.vteam.cars.advice;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.exception.DataNotFoundException;
import com.vteam.cars.exception.MSSmeBusinessException;
import com.vteam.cars.util.I18NUtils;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.api.RespCodeEnum;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.security.TokenExpiredException;
import com.vteam.vtarm.security.UnauthenticatedException;
import com.vteam.vtarm.security.UnauthorizedException;
import com.vteam.vtarm.security.UnbindWechatException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.RejectedExecutionException;

/**
 * 统一异常处理器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/10 17:18
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
        log.info("成功初始化统一异常处理器");
    }

    /**
     * 获取栈异常信息 .<br>
     *
     * @param throwable 异常对象
     * @return java.lang.String
     * @author andy.sher
     * @date 2018/8/1 10:38
     */
    private String getStackTrace(Throwable throwable) {
        String errorInfo = StringUtils.EMPTY;
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            // 将出错的栈信息输出到printWriter中
            throwable.printStackTrace(pw);
            pw.flush();
            sw.flush();
            errorInfo = sw.toString();
        } catch (IOException e) {
            log.error(GlobalExceptionHandler.class.getName(), e);
        }
        return errorInfo;
    }

    /**
     * 默认异常处理器 .<br>
     *
     * @param e 异常对象
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/11 14:38
     */
    @ExceptionHandler(value = Exception.class)
    public RespEntity defaultErrorHandler(Exception e) {
        log.error(getStackTrace(e));
        return new RespEntity(RespCodeEnum.FAILD);
    }

    /**
     * 认证失败异常 .
     *
     * @param e
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/8/15 11:04
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public RespEntity unauthenticatedExceptionHandler(UnauthenticatedException e) {
        log.error(getStackTrace(e));
        RespEntity respEntity = RespEntity.unauthenticated();
        respEntity.setMsg(I18NUtils.getI18N(e.getMessage(), e.getModel()));
        return respEntity;
    }

    /**
     * 授权失败异常 .
     *
     * @param e
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/8/15 11:04
     */
    @ExceptionHandler(UnauthorizedException.class)
    public RespEntity unauthorizedExceptionHandler(UnauthorizedException e) {
        log.error(getStackTrace(e));
        RespEntity respEntity = RespEntity.unauthorized();
        respEntity.setMsg(I18NUtils.getI18N(e.getMessage(), e.getModel()));
        return respEntity;
    }

    /**
     * token过期异常 .
     *
     * @param e
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/8/15 11:04
     */
    @ExceptionHandler(TokenExpiredException.class)
    public RespEntity tokenExpiredException(TokenExpiredException e) {
        log.error(getStackTrace(e));
        RespEntity respEntity = RespEntity.expired();
        respEntity.setMsg(I18NUtils.getI18N(e.getMessage(), e.getModel()));
        return respEntity;
    }

    /**
     * 业务异常处理器 .<br>
     *
     * @param e 异常对象
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/11 14:35
     */
    @ExceptionHandler(MSSmeBusinessException.class)
    public RespEntity businessExceptionHandler(MSSmeBusinessException e) {
        log.error(getStackTrace(e));
        RespEntity respEntity = new RespEntity(RespCodeEnum.FAILD, null);
        respEntity.setMsg(I18NUtils.getI18N(e.getMessage(), e.getModel()));
        return respEntity;
    }

    /**
     * 非法参数异常统一处理器 .<br>
     *
     * @param e 异常对象
     * @return com.vteam.sme.api.entity.RespEntity 统一相应信息
     * @author andy.sher
     * @date 2018/7/30 11:21
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public RespEntity illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error(getStackTrace(e));
        return new RespEntity(RespCodeEnum.ILLEGAL_PARAMETER);
    }

    /**
     * Mybatis系统异常处理器 .
     *
     * @param e
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/10/29 11:40
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public RespEntity myBatisSystemExceptionHandler(MyBatisSystemException e) {
        log.error(getStackTrace(e));
        RespEntity respEntity = RespEntity.error();
        if (StringUtils.isNotBlank(e.getMessage()) && e.getMessage().indexOf(GlobalConstants.Flag.SME_DATA_IS_INVALID) > 0) {
            respEntity.setMsg(I18NUtils.getI18N("该笔资料已失效。", null));
        }
        return respEntity;
    }

    /**
     * 线程池任务拒绝异常 .
     *
     * @param e
     * @return com.vteam.cars.api.entity.RespEntity
     * @author andy.sher
     * @date 2019/5/10 14:00
     */
    @ExceptionHandler(RejectedExecutionException.class)
    public RespEntity rejectedExecutionExceptionHandler(RejectedExecutionException e) {
        log.error(getStackTrace(e));
        RespEntity respEntity = RespEntity.error();
        respEntity.setMsg(I18NUtils.getI18N("当前操作的用户过多，请稍后再试。", null));
        return respEntity;
    }

    /**
     * 找不到数据异常 .
     *
     * @param e
     * @return com.vteam.cars.api.entity.RespEntity
     * @author andy.sher
     * @date 2019/5/10 14:00
     */
    @ExceptionHandler(DataNotFoundException.class)
    public RespEntity dataNotFoundExceptionHandler(DataNotFoundException e) {
        log.error(getStackTrace(e));
        RespEntity respEntity = new RespEntity(RespCodeEnum.NOT_FOUND, null);
        respEntity.setMsg(I18NUtils.getI18N(e.getMessage(), e.getModel()));
        return respEntity;
    }

    /**
     * 未绑定微信异常 .
     *
     * @param e
     * @return com.vteam.vtarm.api.RespEntity
     * @author andy.sher
     * @date 2019/11/28 10:09
     */
    @ExceptionHandler(UnbindWechatException.class)
    public RespEntity unbindWechatExceptionHandler(UnbindWechatException e) {
        log.error(getStackTrace(e));
        RespEntity respEntity = new RespEntity(RespCodeEnum.UNBIND_WECHAT_OPENID, null);
        respEntity.setMsg(I18NUtils.getI18N(e.getMessage(), e.getModel()));
        return respEntity;
    }

}
