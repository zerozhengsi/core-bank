package com.vteam.cars.plugin.message.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 微信消息模板
 *
 * @author zhuang.shao
 * @date 2018年9月26日 下午2:07:27
 */
@Getter
@Setter
@ToString
public class WxMessageVo implements Serializable {

    private static final long serialVersionUID = 5942315114841187574L;

    /**
     * 微信公众号access_token(必输)
     */
    private String accessToken;

    /**
     * 接收用户id(必输)
     */
    private String touser;

    /**
     * 微信消息模板id(必输)
     */
    private String templateId;

    /**
     * 详情页链接
     */
    private String url;

    /**
     * 消息顶部的颜色
     */
    private String topColor;

    /**
     * 模板中的参数列表
     */
    private List<WxParmVo> wxParmVoList;

}
