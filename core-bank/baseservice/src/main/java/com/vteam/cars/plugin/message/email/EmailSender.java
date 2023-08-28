package com.vteam.cars.plugin.message.email;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.plugin.message.model.MessageModel;
import com.vteam.cars.util.DateUtils;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.message.EmailMessageSender;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.NotNull;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 邮件发送适配器 .<br>
 *
 * @author andy.sher
 * @date 2018/9/18 13:53
 */
@Slf4j
@DependsOn(value = {"smeConfiguration"})
@Service
public class EmailSender {

    private String TITLE_SUFFIX = GlobalConstants.Symbol.MINUS + "title";

    private String CONTENT_SUFFIX = GlobalConstants.Symbol.MINUS + "content";

    private String PREFIX = "email" + GlobalConstants.Symbol.SLASH;

    private String COMMON_CONTENT = "common-email";

    @Resource(type = FreeMarkerConfigurer.class)
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Value("${spring.mail.sendmail}")
    private String sendmail;

    @Resource(type = TemplateEngine.class)
    private TemplateEngine templateEngine;

    @Resource
    private EmailMessageSender emailMessageSender;

    /**
     * 发送邮件 .
     *
     * @param messageModel
     * @return void
     * @author andy.sher
     * @date 2018/9/18 14:47
     */
    public void send(@NotNull MessageModel messageModel) {
        try {
            List<SipaBurMVo> realReceiver = new ArrayList<>();
            messageModel.getReceiver().forEach(receive -> {
                if (StringUtils.isNotBlank(receive.getEmail()) && StringUtils.isEmail(receive.getEmail())) {
                    realReceiver.add(receive);
                }
            });
            if (CollectionUtils.isEmpty(realReceiver)) {
                return;
            }
            messageModel.setReceiver(realReceiver);
            // 数据模型
            Map<String, Object> model = messageModel.getModel();

            // 获取标题文本
            Template titleTemplate = freeMarkerConfigurer.getConfiguration().getTemplate(PREFIX + messageModel.getTemplateCode() + TITLE_SUFFIX + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.FTL);
            String titleText = FreeMarkerTemplateUtils.processTemplateIntoString(titleTemplate, model);
            // 设置品牌名称
            String titleTextBrand = ( GlobalConstants.Symbol.LEFT_BOLDFACE_SQUARE_BRACKETS + GlobalConstants.Symbol.RIGHT_BOLDFACE_SQUARE_BRACKETS + titleText );

            // 获取内容文本
            Template contentTemplate = freeMarkerConfigurer.getConfiguration().getTemplate(PREFIX + messageModel.getTemplateCode() + CONTENT_SUFFIX + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.FTL);
            String contentText = FreeMarkerTemplateUtils.processTemplateIntoString(contentTemplate, model);

            // 设置邮件正文、发送时间
            Context context = new Context();
            context.setVariable("contentText", contentText);
            context.setVariable("sendEmailDate", LocalDateTime.now().format(DateUtils.FMT_DATE));
            String[] templateCodes = messageModel.getTemplateCode().split(GlobalConstants.Symbol.SLASH);
            String templateCode = templateCodes[templateCodes.length - 1];
            String tmp = smeConfiguration.getTmpPath() + UUID.randomUUID() + templateCode;
            // 填充邮件模板
            try (FileWriter writer = new FileWriter(tmp);) {
                templateEngine.process(COMMON_CONTENT, context, writer);
            } catch (IOException e) {
                log.error(EmailSender.class.getName(), e);
            }

            // 读取填充完毕的邮件内容
            StringBuilder emailText = new StringBuilder();
            Files.readAllLines(Paths.get(tmp)).forEach(emailText::append);

            // 兼容发件人与用户名不一致的情况
            String from;
            if (StringUtils.isNotBlank(sendmail)) {
                from = sendmail;
            } else {
                from = smeConfiguration.getMailUsername();
            }

            emailMessageSender.build(from,
                    messageModel.getReceiver().stream().map(SipaBurMVo::getEmail).collect(Collectors.toSet()),
                    titleTextBrand, emailText.toString(), messageModel.getAttachment()).subscribe();

        } catch (IOException | TemplateException | MessagingException e) {
            log.error(EmailSender.class.getName(), e);
        }

    }

}
