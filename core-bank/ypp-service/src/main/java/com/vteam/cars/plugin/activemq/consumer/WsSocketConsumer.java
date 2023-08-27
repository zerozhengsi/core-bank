package com.vteam.cars.plugin.activemq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.plugin.message.model.MessageModel;
import com.vteam.cars.plugin.message.websocket.WebSocketSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * web socket 消息消费者 .<br>
 *
 * @author andy.sher
 * @date 2019/5/9 11:06
 */
@Slf4j
@Component
public class WsSocketConsumer {

    @Resource(type = WebSocketSender.class)
    private WebSocketSender webSocketSender;

    @JmsListener(destination = "${spring.activemq.topic.ws-topic}", containerFactory = "topicListenerFactory")
    public void receive(String message) {
        MessageModel messageModel = JSONObject.parseObject(message, MessageModel.class);
        webSocketSender.send(messageModel);
        log.debug("订阅消息：" + message);
    }

}
