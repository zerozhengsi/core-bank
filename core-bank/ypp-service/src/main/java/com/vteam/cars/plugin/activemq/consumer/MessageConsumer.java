package com.vteam.cars.plugin.activemq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.plugin.activemq.producer.WebSocketProducer;
import com.vteam.cars.plugin.message.email.EmailSender;
import com.vteam.cars.plugin.message.model.MessageModel;
import com.vteam.cars.plugin.message.notice.NoticeSender;
import com.vteam.cars.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息消费者 .<br>
 *
 * @author andy.sher
 * @date 2018/9/25 11:48
 */
@Slf4j
@Service
public class MessageConsumer {

    @Resource(type = NoticeSender.class)
    private NoticeSender noticeSender;

    @Resource(type = EmailSender.class)
    private EmailSender emailSender;

    @Resource(type = WebSocketProducer.class)
    private WebSocketProducer webSocketProducer;

    @JmsListener(destination = "${spring.activemq.queueName.message}")
    public void receive(String message) {
        try {
            MessageModel messageModel = JSONObject.parseObject(message, MessageModel.class);

            for (int i = 0, len = messageModel.getChannel().length; i < len; i++) {
                MessageModel.Channel channel = messageModel.getChannel()[i];
                // 根据渠道设置发送消息
                switch (channel) {
                    case EMAIL:
                        emailSender.send(messageModel);
                        break;
                    case NOTICE:
                        noticeSender.send(messageModel);
                        break;
                    case WS:
                        // 委托ws发布者发布消息
                         webSocketProducer.send(messageModel);
                        break;
                    default:
                        // 什么都不做
                        break;
                }
            }
            List<String> emails = new ArrayList<String>();
            if(messageModel.getReceiver() != null) {
                for (SipaBurMVo sipaBurMVo : messageModel.getReceiver()) {
                    if (StringUtils.isNotBlank(sipaBurMVo.getEmail())) {
                        emails.add(sipaBurMVo.getEmail());
                    }
                }
            }

            log.info("MessageConsumer ,Channel:" + StringUtils.join(messageModel.getChannel(), GlobalConstants.Symbol.COMMA)
                    + ";TemplateCode:" + messageModel.getTemplateCode() + ";emails:" + StringUtils.join(emails.toArray(), GlobalConstants.Symbol.COMMA));
        } catch (Exception e) {
            log.error(MessageConsumer.class.getName(), e);
        }
    }

}
