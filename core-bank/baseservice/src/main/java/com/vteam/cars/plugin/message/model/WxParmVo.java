package com.vteam.cars.plugin.message.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 微信发送消息时,需要传的参数Vo
 *
 * @author zhuang.shao
 * @date 2018年9月26日 下午2:27:35
 */
@Getter
@Setter
@ToString
public class WxParmVo {

    public WxParmVo(String name, String value, String color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数值
     */
    private String value;

    /**
     * 参数值颜色
     */
    private String color;
}
