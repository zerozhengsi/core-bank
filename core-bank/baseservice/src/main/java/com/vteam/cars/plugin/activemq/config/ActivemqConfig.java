package com.vteam.cars.plugin.activemq.config;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.plugin.license.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Queue;
import jakarta.jms.Topic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

/**
 * Activemq配置信息
 *
 * @author care.zheng
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ActivemqProperties.class)
public class ActivemqConfig {

    @Resource
    private SmeConfiguration smeConfiguration;

    private final ActivemqProperties activemqProperties;

    public ActivemqConfig(ActivemqProperties activemqProperties) {
        this.activemqProperties = activemqProperties;
        // 增加许可证认证控制
        boolean hasAuthorized = Authorization.HAS_AUTHORIZED;
        if (!hasAuthorized) {
            System.exit(0);
            return;
        }
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(activemqProperties.getBrokerUrl());
        connectionFactory.setUserName(activemqProperties.getUser());
        connectionFactory.setPassword(activemqProperties.getPassword());
        return connectionFactory;
    }

    @Bean
    public JmsTemplate genJmsTemplate(){
        return new JmsTemplate(connectionFactory());
    }
    @Bean
    public JmsMessagingTemplate jmsMessageTemplate(){
        return new JmsMessagingTemplate(connectionFactory());
    }

    /**
     * 消息发送队列 .
     *
     * @param
     * @return jakarta.jms.Queue 消息发送队列
     * @author andy.sher
     * @date 2019/1/9 15:02
     */
    @Bean
    public Queue messageQueue() {
        log.info(String.format("初始化消息发送队列[%s]", smeConfiguration.getQueueNameMessage()));
        return new ActiveMQQueue(smeConfiguration.getQueueNameMessage());
    }

    /**
     * 微信推送发送队列 .
     *
     * @param
     * @return jakarta.jms.Queue 微信推送发送队列
     * @author andy.sher
     * @date 2019/1/9 15:03
     */
    @Bean(name = "weixinSenderQueue")
    public Queue weixinSenderQueue() {
        log.info(String.format("初始化微信消息发送队列[%s]", smeConfiguration.getWeiXinQueueName()));
        return new ActiveMQQueue(smeConfiguration.getWeiXinQueueName());
    }

    /**
     * websocket 消息发送队列 .
     *
     * @param
     * @return jakarta.jms.Topic 消息发送队列
     * @author andy.sher
     * @date 2019/1/9 15:02
     */
    @Bean
    public Topic wsMessageTopic() {
        log.info(String.format("初始化WS消息发布者[%s]", smeConfiguration.getActiveMqWsTopic()));
        return new ActiveMQTopic(smeConfiguration.getActiveMqWsTopic());
    }

    /**
     * Lucene消息发送队列
     *
     * @param
     * @return jakarta.jms.Queue
     * @author jiangming.huang
     * @date 2019/7/23 9:31
     */
    @Bean
    public Topic luceneTopic() {
        log.info(String.format("初始化Lucene消息发布者[%s]", smeConfiguration.getActiveMqLuceneTopic()));
        return new ActiveMQTopic(smeConfiguration.getActiveMqLuceneTopic());
    }

    /**
     * 处理ws消息的监听模式为发布订阅 .
     *
     * @param connectionFactory
     * @return org.springframework.jms.config.JmsListenerContainerFactory<?>
     * @author andy.sher
     * @date 2019/5/9 11:05
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

}
