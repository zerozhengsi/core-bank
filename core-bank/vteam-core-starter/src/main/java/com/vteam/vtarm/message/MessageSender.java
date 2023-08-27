package com.vteam.vtarm.message;

import jakarta.mail.MessagingException;
import java.util.function.Consumer;

public interface MessageSender<I, O> {

    /**
     * 以同步的模式发送邮件 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2020/1/16 12:34
     */
    void subscribe() throws MessagingException;

    /**
     * 订阅并指定对消息发送器如何处理 .
     *
     * @param consumer 消息发送器
     * @return void
     * @author andy.sher
     * @date 2020/1/16 12:36
     */
    void subscribe(Consumer<MessageSender> consumer) throws MessagingException;

}
