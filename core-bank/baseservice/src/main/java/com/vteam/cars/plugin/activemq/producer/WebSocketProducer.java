package com.vteam.cars.plugin.activemq.producer;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.plugin.message.model.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import jakarta.jms.Topic;

/**
 * web socket 消息发布者 .<br>
 *
 * @author andy.sher
 * @date 2019/5/9 10:18
 */
@Slf4j
@Component
public class WebSocketProducer {

    @Resource(type = JmsMessagingTemplate.class)
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "wsMessageTopic")
    private Topic wsMessageTopic;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    /**
     * 发送消息 .
     *
     * @param messageModel 消息模型
     * @return void
     * @author andy.sher
     * @date 2018/9/25 10:59
     */
    public void send(MessageModel messageModel) {
        if (!smeConfiguration.getMqSwitch()) {
            return;
        }
        if (null != messageModel) {
            String message = JSONObject.toJSONString(messageModel);
            jmsMessagingTemplate.convertAndSend(wsMessageTopic, message);
            log.debug("发布消息：" + message);
        }
    }

}
