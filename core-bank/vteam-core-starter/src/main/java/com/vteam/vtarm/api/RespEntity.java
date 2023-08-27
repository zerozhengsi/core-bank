package com.vteam.vtarm.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 标准响应实体类 .<br>
 *
 * @author andy.sher
 * @date 2018/7/10 17:19
 */
@Getter
@Setter
@ToString
public class RespEntity implements Serializable {

    private static final long serialVersionUID = 9120481866981475700L;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private String data;

    /**
     * 总记录数
     */
    private Integer totalCount;

    public RespEntity() {
    }

    public RespEntity(RespCodeEnum respCodeEnum) {
        this.code = respCodeEnum.getCode();
        this.msg = respCodeEnum.getMsg();
    }

    public RespEntity(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespEntity(RespCodeEnum respCodeEnum, String data) {
        this(respCodeEnum);
        this.data = data;
    }

    /**
     * 请求失败 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/10/19 11:20
     */
    public static RespEntity error() {
        return new RespEntity(RespCodeEnum.FAILD);
    }

    /**
     * 请求失败 .
     *
     * @param msg
     * @return com.vteam.vtarm.api.RespEntity
     * @author fu.tong
     * @date 2019/9/18 14:59
     */
    public static RespEntity error(String msg) {
        return new RespEntity(0, msg);
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }

    /**
     * 请求成功 .<br>
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/31 10:18
     */
    public static RespEntity ok() {
        return new RespEntity(RespCodeEnum.SUCCESS);
    }

    /**
     * 请求成功 .<br>
     *
     * @param dataString 响应JSON数据字符串
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/31 10:19
     */
    public static RespEntity ok(String dataString) {
        return new RespEntity(RespCodeEnum.SUCCESS, dataString);
    }

    /**
     * 请求成功 .<br>
     *
     * @param data 响应数据
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/31 10:19
     */
    public static RespEntity ok(JSONObject data) {
        return new RespEntity(RespCodeEnum.SUCCESS, JSON.toJSONString(data, SerializerFeature.DisableCircularReferenceDetect));
    }

    /**
     * token过期 .<br>
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/31 10:19
     */
    public static RespEntity expired() {
        return new RespEntity(RespCodeEnum.TOKEN_EXPIRED);
    }

    /**
     * token无效 .<br>
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/31 10:19
     */
    public static RespEntity invalid() {
        return new RespEntity(RespCodeEnum.TOKEN_INVALID);
    }

    /**
     * 授权失败 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/8/15 10:59
     */
    public static RespEntity unauthorized() {
        RespEntity respEntity = new RespEntity(RespCodeEnum.UNAUTHORIZED);
        respEntity.setMsg(RespCodeEnum.UNAUTHORIZED.getMsg());
        return respEntity;
    }

    /**
     * 认证失败 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/8/15 10:59
     */
    public static RespEntity unauthenticated() {
        RespEntity respEntity = new RespEntity(RespCodeEnum.UNAUTHENTICATED);
        respEntity.setMsg(RespCodeEnum.UNAUTHENTICATED.getMsg());
        return respEntity;
    }

    /**
     * 拒绝处理请求 .
     *
     * @param
     * @return com.vteam.vtarm.api.RespEntity
     * @author andy.sher
     * @date 2019/11/28 15:23
     */
    public static RespEntity requestRefuse() {
        RespEntity respEntity = new RespEntity(RespCodeEnum.REQUEST_REFUSE);
        respEntity.setMsg(RespCodeEnum.REQUEST_REFUSE.getMsg());
        return respEntity;
    }

    /**
     * 单点登录模式认证失败 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/8/15 10:59
     */
    public static RespEntity unauthenticatedSso() {
        RespEntity respEntity = new RespEntity(RespCodeEnum.UNAUTHENTICATED_SSO);
        respEntity.setMsg(RespCodeEnum.UNAUTHENTICATED_SSO.getMsg());
        return respEntity;
    }

}
