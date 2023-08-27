package com.vteam.cars.plugin.message.websocket;

import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.MessageConstants;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.plugin.container.WebSocketClientContainer;
import com.vteam.cars.plugin.message.model.MessageModel;
import com.vteam.cars.plugin.message.notice.NoticeSender;
import com.vteam.cars.plugin.web.websocket.model.WsMessageModel;
import com.vteam.cars.plugin.web.websocket.server.SmeWebSocketServerEndpoint;
import com.vteam.cars.util.StringUtils;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import jakarta.annotation.Resource;
import java.io.IOException;

/**
 * websocket 消息发送器 .<br>
 *
 * @author andy.sher
 * @date 2019/5/8 15:04
 */
@Slf4j
@DependsOn(value = {"smeConfiguration"})
@Service
public class WebSocketSender {

    @Resource(type = FreeMarkerConfigurer.class)
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private String TITLE_SUFFIX = GlobalConstants.Symbol.MINUS + "title";

    private String CONTENT_SUFFIX = GlobalConstants.Symbol.MINUS + "content";

    private String PREFIX = "notice" + GlobalConstants.Symbol.SLASH;

    private static final String REPEATED_LOGIN = "用户重复登陆";

    @Resource(type = WebSocketClientContainer.class)
    private WebSocketClientContainer webSocketClientContainer;

    /**
     * 发送websocket消息 .
     *
     * @param messageModel 消息模型
     * @return void
     * @author andy.sher
     * @date 2019/5/8 10:50
     */
    public void send(MessageModel messageModel) {
        try {
            // 获取标题文本
            Template titleTemplate = freeMarkerConfigurer.getConfiguration().getTemplate(PREFIX + messageModel.getTemplateCode() + TITLE_SUFFIX + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.FTL);
            String titleText = FreeMarkerTemplateUtils.processTemplateIntoString(titleTemplate, messageModel.getModel());

            // 获取内容文本
            Template contentTemplate = freeMarkerConfigurer.getConfiguration().getTemplate(PREFIX + messageModel.getTemplateCode() + CONTENT_SUFFIX + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.FTL);
            String contentText = FreeMarkerTemplateUtils.processTemplateIntoString(contentTemplate, messageModel.getModel());

            messageModel.getReceiver().forEach(receiver -> {
                final String userid = receiver.getUserid();
                // 修复未注册的临时用户处理逻辑
                if (StringUtils.isBlank(userid)) {
                    return;
                }
                // 如果目标用户在线，则发送即时消息
                String clientKey;
                if (FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL.equals(receiver.getSystemType())) {
                    clientKey = userid + GlobalConstants.Symbol.COMMA + receiver.getOrgRefcode();
                } else if (FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION.equals(receiver.getSystemType())) {
                    clientKey = userid;
                } else {
                    throw new IllegalArgumentException("接收者的所属系统类型不能为空。");
                }
                // 处理推送未读消息
                handlerMessage(titleText, contentText, receiver, clientKey);
            });
        } catch (IOException | TemplateException e) {
            log.error(NoticeSender.class.getName(), e);
        }
    }

    /**
     * 消息处理器 .
     *
     * @param titleText   消息标题
     * @param contentText 消息内容
     * @param receiver    接收者
     * @param clientKey   接收者客户端标识
     * @return void
     * @author andy.sher
     * @date 2019/6/19 9:30
     */
    private void handlerMessage(String titleText, String contentText, SipaBurMVo receiver, String clientKey) {
        if (webSocketClientContainer.hasClient(clientKey)) {
            WsMessageModel wsMessageModel;
            if (REPEATED_LOGIN.equals(titleText)) {
                wsMessageModel = SmeWebSocketServerEndpoint.buildMessage(titleText, contentText, receiver, MessageConstants.WS_TYPE_POPUP);
            } else {
                wsMessageModel = SmeWebSocketServerEndpoint.buildMessage(titleText, contentText, receiver);
            }
            if (webSocketClientContainer.get(clientKey).isOpen()) {
                // 发送即时消息
                SmeWebSocketServerEndpoint.sendMessage(clientKey, wsMessageModel);
            } else {
                // 否则存档，待用户上线时推送
                SmeWebSocketServerEndpoint.archive(wsMessageModel);
            }
        }
    }
}
