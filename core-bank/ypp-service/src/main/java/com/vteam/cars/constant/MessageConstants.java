package com.vteam.cars.constant;

/**
 * 消息常量 .<br>
 *
 * @author andy.sher
 * @date 2018/9/29 17:50
 */
public final class MessageConstants {

    private MessageConstants() {
    }

    /**
     * websocket消息发送模式[1=单发]
     */
    public static final String WS_MODE_SINGLE = "1";

    /**
     * websocket消息类型[1=会话]
     */
    public static final String WS_TYPE_DIALOG = "1";

    /**
     * websocket消息类型[2=弹窗]
     */
    public static final String WS_TYPE_POPUP = "2";

}
