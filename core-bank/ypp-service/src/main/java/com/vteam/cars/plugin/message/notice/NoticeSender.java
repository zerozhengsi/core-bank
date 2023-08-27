package com.vteam.cars.plugin.message.notice;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.vo.FbtxNotMVo;
import com.vteam.cars.plugin.message.model.MessageModel;
import com.vteam.cars.service.fbtx.FbtxNotMService;
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
 * 站内信发送器 .<br>
 *
 * @author andy.sher
 * @date 2018/9/19 9:51
 */
@Slf4j
@DependsOn(value = {"smeConfiguration"})
@Service
public class NoticeSender {

    @Resource(type = FreeMarkerConfigurer.class)
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Resource(type = FbtxNotMService.class)
    private FbtxNotMService fbtxNotMService;

    private String TITLE_SUFFIX = GlobalConstants.Symbol.MINUS + "title";

    private String CONTENT_SUFFIX = GlobalConstants.Symbol.MINUS + "content";

    private String PREFIX = "notice" + GlobalConstants.Symbol.SLASH;

    /**
     * 发送站内信 .
     *
     * @param messageModel 消息模型
     * @return void
     * @author andy.sher
     * @date 2018/9/19 10:50
     */
    public void send(MessageModel messageModel) {
        try {
            // 获取标题文本
            final String templateCode = messageModel.getTemplateCode();
            Template titleTemplate = freeMarkerConfigurer.getConfiguration().getTemplate(PREFIX + templateCode + TITLE_SUFFIX + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.FTL);
            String titleText = FreeMarkerTemplateUtils.processTemplateIntoString(titleTemplate, messageModel.getModel());

            // 获取内容文本
            Template contentTemplate = freeMarkerConfigurer.getConfiguration().getTemplate(PREFIX + templateCode + CONTENT_SUFFIX + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.FTL);
            String contentText = FreeMarkerTemplateUtils.processTemplateIntoString(contentTemplate, messageModel.getModel());

            messageModel.getReceiver().forEach(receive -> {
                FbtxNotMVo fbtxNotMVo = new FbtxNotMVo();
                fbtxNotMVo.setNoticeTitle(titleText);
                fbtxNotMVo.setNoticeContent(contentText);
                fbtxNotMVo.setPushType(messageModel.getPushType());
                if (messageModel.getSender() != null) {
                    final Integer orgRefcode = messageModel.getSender().getOrgRefcode();
                    fbtxNotMVo.setPushOrgRefcode(orgRefcode != null ? orgRefcode : 0);
                }
                final String userid = receive.getUserid();
                if (StringUtils.isNotBlank(userid)) { // 已注册用户
                    fbtxNotMVo.setReceiveOrgRefcode(receive.getOrgRefcode());
                    fbtxNotMVo.setReceiveUserid(userid);
                } else {
                    // 未注册临时用户，保存用户信息用于后续匹配
                    fbtxNotMVo.setOrgname(receive.getOrgname());
                    fbtxNotMVo.setCompanyCreditCode(receive.getCompanyCreditCode());
                    fbtxNotMVo.setBrandRefcode(receive.getBrandRefcode());
                }
                fbtxNotMVo.setNoticeType(messageModel.getNoticeType());
                fbtxNotMVo.setTaskType(messageModel.getTaskType());
                // 发送站内信
                fbtxNotMService.save(fbtxNotMVo);
            });
        } catch (IOException | TemplateException e) {
            log.error(NoticeSender.class.getName(), e);
        }
    }

}
