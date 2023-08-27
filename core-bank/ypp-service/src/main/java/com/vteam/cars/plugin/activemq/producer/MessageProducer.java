package com.vteam.cars.plugin.activemq.producer;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.plugin.message.model.MessageModel;
import com.vteam.cars.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import jakarta.jms.Queue;
import java.util.HashMap;
import java.util.Map;

/**
 * 站内信生产者 .<br>
 *
 * @author andy.sher
 * @date 2018/9/25 10:28
 */
@Slf4j
@Component
public class MessageProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "messageQueue")
    private Queue messageQueue;

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
            Map<String, Object> headers = new HashMap<>();
            //如果设置了消息发送时间，则进行延时处理
            if(messageModel.getDelayTime()!=null){
                long delay=DateUtils.getMillisBetween(DateUtils.getCurrDatetime(),messageModel.getDelayTime());
                headers.put(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
                log.info("MessageProducer 推送消息延时"+delay+"毫秒");
            }
            jmsMessagingTemplate.convertAndSend(messageQueue, message,headers);
            log.info("MessageProducer 推送消息内容：" + message);

        }
    }

}
