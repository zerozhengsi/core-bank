package com.vteam.cars.plugin.web.websocket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Websocket消息模型 .<br>
 *
 * @author andy.sher
 * @date 2018/9/29 15:38
 */
@Getter
@Setter
@ToString(callSuper = true)
public class WsMessageModel implements Serializable {

    private static final long serialVersionUID = 1225561491128987846L;

    /**
     * 消息模式[1=单发/2=群发]
     */
    private String mode;

    /**
     * 消息类型[1=弹出框]
     */
    private String type;

    /**
     * 接收者
     */
    private String receiver;

    /**
     * 接收者类型
     */
    private String receiverType;

    /**
     * 接收企业
     */
    private Integer orgRefcode;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 站内信未读数
     */
    private Integer noticeUnreadCount;

}
