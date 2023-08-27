package com.vteam.vtarm.message;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@AutoConfigureAfter(JavaMailSender.class)
@Configuration
public class MessageAutoConfigure {

    @Bean
    public EmailMessageSender emailMessageSender(JavaMailSender javaMailSender) {
        return new EmailMessageSender(javaMailSender);
    }

}
