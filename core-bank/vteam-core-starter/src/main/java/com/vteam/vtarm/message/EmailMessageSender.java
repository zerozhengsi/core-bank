package com.vteam.vtarm.message;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;

import com.vteam.vtarm.utils.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailMessageSender implements MessageSender<String, String> {

    private final JavaMailSender javaMailSender;

    private String sender;

    private String[] receiver;

    private String title;

    private String content;

    private Map<String, File> attachment;

    public EmailMessageSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public MessageSender build(String sender, Set<String> receiver, String title, String content, Map<String, File> attachment) {
        Assert.hasText(sender, "发件人不能为空。");
        Assert.notEmpty(receiver, "收件人不能为空。");
        Assert.hasText(title, "邮件标题不能为空。");
        Assert.hasText(content, "邮件内容不能为空。");

        this.sender = sender;
        this.receiver = new String[receiver.size()];
        receiver.toArray(this.receiver);
        this.title = title;
        this.content = content;
        this.attachment = attachment;
        return this;
    }

    @Override
    public void subscribe() throws MessagingException {
    	// 设置mail.mime.encodeparameters属性为false,解决outlook接收附件名称异常问题
        System.setProperty("mail.mime.encodeparameters","false");
        // 组装邮件消息对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(this.sender);
        helper.setTo(this.receiver);
        helper.setSubject(this.title);
        helper.setText(this.content, true);

        // 设置附件
        if (null != this.attachment && !attachment.isEmpty()) {
            this.attachment.forEach((k, v) -> {
                try {
                    helper.addAttachment(k, new FileSystemResource(v));
                } catch (MessagingException e) {
                    log.error(EmailMessageSender.class.getName(), e);
                }
            });
        }

        TaskExecutor taskExecutor = SpringContextUtils.getBean("taskExecutor", TaskExecutor.class);
        taskExecutor.execute(() -> {
        	try {
        		log.info("EmailMessageSender Send：" + this.receiver + " -> " + this.title);
        		javaMailSender.send(mimeMessage);
        	} catch(MailSendException e) {
        		log.error("EmailMessageSender Error：" + e.getMessage());
        	}
        });
    }

    @Override
    public void subscribe(Consumer<MessageSender> consumer) {
        consumer.accept(this);
    }

}
