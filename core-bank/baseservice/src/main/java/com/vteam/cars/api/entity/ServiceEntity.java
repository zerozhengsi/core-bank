package com.vteam.cars.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 提供对外服务接口实体类
 * 
 * @author zhuang.shao
 * @date 2019年1月29日 下午1:30:04
 */
@Getter
@Setter
@ToString
public class ServiceEntity implements Serializable {

    private static final long serialVersionUID = -3155665581199987867L;

    /**
     * 请求数据
     */
    private String data;

    /**
     * 请求来源
     */
    private String sourceId;

    /**
     * 请求类型
     */
    private String type;
    
    /**
     * 非对称加密处理的公钥及混淆值(aes[key和iv])
     */
    private String akv;

}
