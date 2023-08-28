package com.vteam.cars.plugin.web.websocket.server;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.MessageConstants;
import com.vteam.cars.entity.model.FbtxUrMsgM;
import com.vteam.cars.entity.vo.FbtxUrMsgMVo;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.plugin.container.WebSocketClientContainer;
import com.vteam.cars.plugin.web.websocket.model.WsMessageModel;
import com.vteam.cars.service.fbtx.FbtxUrMsgMService;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.security.AuthenticationInfo;
import com.vteam.vtarm.security.TokenContainer;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * WebSocket服务类 .<br>
 *
 * @author andy.sher
 * @date 2018/9/1 12:37
 */
@Slf4j
@Service
@ServerEndpoint(value = "/smews")
public class SmeWebSocketServerEndpoint {

    /**
     * 心跳消息
     */
    private static String PING = "ping";
    private static String PONG = "pong";

    /**
     * 客户端唯一标识
     */
    private String key;

    /**
     * 连接建立成功调用的方法 .<br>
     *
     * @param session 会话连接
     * @return
     * @author andy.sher
     * @date 2018/9/1 16:47
     */
    @OnOpen
    public void onOpen(Session session) {
        Map<String, List<String>> parameterMap = session.getRequestParameterMap();

        if (parameterMap.containsKey(GlobalConstants.Flag.KEY)) {
            List<String> values = parameterMap.get(GlobalConstants.Flag.KEY);
            if (values.size() == 1) {
                try {
                    String access_token = values.get(0);
                    // 根据规则将token拆分为特定的标识符
                    TokenContainer tokenContainer = SpringContextUtils.getBean(TokenContainer.class);
                    String userStr = tokenContainer.get(access_token);
                    if (StringUtils.isBlank(userStr)) {
                        session.close();
                        return;
                    }
                    AuthenticationInfo authenticationInfo = JSONObject.parseObject(userStr, AuthenticationInfo.class);
                    if (StringUtils.isBlank(authenticationInfo.getAdditional())) {
                        session.close();
                        return;
                    }
                    SipaBurMVo sipaBurMVo = JSONObject.parseObject(authenticationInfo.getAdditional(), SipaBurMVo.class);
                    String userid = sipaBurMVo.getUserid();
                    Integer orgRefcode = sipaBurMVo.getOrgRefcode();
                    // 前端用户的key和后端用户的key生成规则不一样
                    if (FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL.equals(sipaBurMVo.getSystemType())) {
                        key = userid + GlobalConstants.Symbol.COMMA + orgRefcode;
                    } else if (FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION.equals(sipaBurMVo.getSystemType())) {
                        key = userid;
                    }

                    // 将客户端会话连接添加到容器中
                    WebSocketClientContainer webSocketClientContainer = SpringContextUtils.getBean(WebSocketClientContainer.class);
                    webSocketClientContainer.set(key, session);

                    // 当用户为前端用户时并且没有推送消息时进行推送未读消息
                    if (FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL.equals(sipaBurMVo.getSystemType())) {
                        // 推送未读消息
                        pushUnreadMsg(userid, orgRefcode, sipaBurMVo.getSystemType());
                    }
                } catch (Exception e) {
                    log.error(SmeWebSocketServerEndpoint.class.getName(), e);
                }
            }
        }
    }

    /**
     * 连接关闭调用的方法 .<br>
     *
     * @param
     * @return
     * @author andy.sher
     * @date 2018/9/1 16:47
     */
    @OnClose
    public void onClose() {
        if (StringUtils.isNotBlank(key)) {
            WebSocketClientContainer webSocketClientContainer = SpringContextUtils.getBean(WebSocketClientContainer.class);
            // 从容器中删除
            try {
                if (webSocketClientContainer.get(key).isOpen()) {
                    webSocketClientContainer.get(key).close();
                }
            } catch (IOException ignore) {
            } finally {
                log.info("客户端已断开连接{}。", key);
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法 .<br>
     *
     * @param message 消息
     * @param session 会话连接
     * @return
     * @author andy.sher
     * @date 2018/9/1 16:47
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        // 心跳消息
        if (PING.equals(message)) {
            session.getBasicRemote().sendText(PONG);
        }
    }

    /**
     * 发生错误时调用 .<br>
     *
     * @param session 会话连接
     * @param error   错误信息
     * @return
     * @author andy.sher
     * @date 2018/9/1 16:47
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error(SmeWebSocketServerEndpoint.class.getName(), error);
    }

    /**
     * 发送消息 .<br>
     *
     * @param message 消息
     * @return
     * @author andy.sher
     * @date 2018/9/1 16:47
     */
    private static void sendMessage(WsMessageModel message) {
        // 单发
        if (MessageConstants.WS_MODE_SINGLE.equals(message.getMode())) {
            String receiverClientKey;
            if (FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL.equals(message.getReceiverType())) {
                receiverClientKey = message.getReceiver() + GlobalConstants.Symbol.COMMA + message.getOrgRefcode();
            } else if (FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION.equals(message.getReceiverType())) {
                receiverClientKey = message.getReceiver();
            } else {
                throw new IllegalArgumentException("接收者的所属系统类型不能为空。");
            }
            sendMessage(receiverClientKey, message);
        }
    }

    /**
     * 发送消息 .
     *
     * @param clientKey 客户端标识符
     * @param message   消息
     * @return void
     * @author andy.sher
     * @date 2018/11/6 10:14
     */
    public static void sendMessage(String clientKey, WsMessageModel message) {
        WebSocketClientContainer webSocketClientContainer = SpringContextUtils.getBean(WebSocketClientContainer.class);
        try {
            if (webSocketClientContainer.get(clientKey).isOpen()) {
                // 发送消息
                webSocketClientContainer.get(clientKey).getBasicRemote().sendText(JSONObject.toJSONString(message));
            } else {
                // 存档未读消息
                archive(message);
            }
        } catch (IOException e) {
            log.error("发送消息失败{}。", e.getMessage());
        }
    }

    /**
     * 推送未读消息 .
     *
     * @param userid     用户id
     * @param orgRefcode 用户企业流水号
     * @param systemType 系统类型
     * @return void
     * @author andy.sher
     * @date 2019/5/29 16:05
     */
    private void pushUnreadMsg(String userid, Integer orgRefcode, String systemType) {
        // 初次连接时推送未读消息
        SmeConfiguration smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
        FbtxUrMsgM condition = new FbtxUrMsgM();
        condition.setReceiveOrgRefcode(orgRefcode);
        condition.setReceiveUserid(userid);
        FbtxUrMsgMService fbtxUrMsgMService = SpringContextUtils.getBean(FbtxUrMsgMService.class);
        PageHelper.startPage(1, 5);
        List<FbtxUrMsgM> unreadList = fbtxUrMsgMService.listUnreadMessageByCondition(condition);
        PageHelper.clearPage();
        if (CollectionUtils.isNotEmpty(unreadList)) {
            unreadList.forEach(msg -> {
                WsMessageModel messageModel = new WsMessageModel();
                messageModel.setReceiver(userid);
                messageModel.setReceiverType(systemType);
                messageModel.setOrgRefcode(orgRefcode);
                messageModel.setType(MessageConstants.WS_TYPE_DIALOG);
                messageModel.setMode(MessageConstants.WS_MODE_SINGLE);
                messageModel.setTitle(msg.getTitle());
                messageModel.setContent(msg.getContent());
                sendMessage(messageModel);

                // 推送成功后删除
                msg.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_YES);
                fbtxUrMsgMService.updateById(msg);
            });
        }
    }

    /**
     * 发送消息 .
     *
     * @param titleText   消息标题
     * @param contentText 消息内容
     * @param receiver    接收者
     * @return void
     * @author andy.sher
     * @date 2019/6/19 9:16
     */
    public static WsMessageModel buildMessage(String titleText, String contentText, SipaBurMVo receiver) {
        // 发送即时消息
        WsMessageModel wsMessageModel = new WsMessageModel();
        wsMessageModel.setMode(MessageConstants.WS_MODE_SINGLE);
        wsMessageModel.setReceiver(receiver.getUserid());
        wsMessageModel.setReceiverType(receiver.getSystemType());
        wsMessageModel.setOrgRefcode(receiver.getOrgRefcode());
        wsMessageModel.setType(MessageConstants.WS_TYPE_DIALOG);
        wsMessageModel.setTitle(titleText);
        wsMessageModel.setContent(contentText);
        return wsMessageModel;
    }

    /**
     * 发送消息
     *
     * @author Tianci.li
     * @since 2022/10/10 17:41
     * @return WsMessageModel WS消息模型
     */
    public static WsMessageModel buildMessage(String titleText, String contentText, SipaBurMVo receiver, String type) {
        // 发送即时消息
        WsMessageModel wsMessageModel = new WsMessageModel();
        wsMessageModel.setMode(MessageConstants.WS_MODE_SINGLE);
        wsMessageModel.setReceiver(receiver.getUserid());
        wsMessageModel.setReceiverType(receiver.getSystemType());
        wsMessageModel.setOrgRefcode(receiver.getOrgRefcode());
        wsMessageModel.setType(type);
        wsMessageModel.setTitle(titleText);
        wsMessageModel.setContent(contentText);
        return wsMessageModel;
    }


    /**
     * 存档未读消息 .
     *
     * @param wsMessageModel 消息模型
     * @return void
     * @author andy.sher
     * @date 2019/6/19 9:27
     */
    public static void archive(WsMessageModel wsMessageModel) {
        // 否则存档，待用户上线时推送
        SmeConfiguration smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
        FbtxUrMsgMVo fbtxUrMsgMVo = new FbtxUrMsgMVo();
        fbtxUrMsgMVo.setReceiveOrgRefcode(wsMessageModel.getOrgRefcode());
        fbtxUrMsgMVo.setReceiveUserid(wsMessageModel.getReceiver());
        fbtxUrMsgMVo.setTitle(wsMessageModel.getTitle());
        fbtxUrMsgMVo.setContent(wsMessageModel.getContent());
        FbtxUrMsgMService fbtxUrMsgMService = SpringContextUtils.getBean(FbtxUrMsgMService.class);
        fbtxUrMsgMService.saveUnreadMsg(fbtxUrMsgMVo);
    }

}
