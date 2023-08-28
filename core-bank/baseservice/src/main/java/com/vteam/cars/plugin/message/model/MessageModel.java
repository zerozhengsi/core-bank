package com.vteam.cars.plugin.message.model;

import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.vtarm.message.BaseMessageModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

/**
 * 消息模型 .<br>
 *
 * @author andy.sher
 * @date 2018/9/18 13:53
 */
@Slf4j
@Getter
@Setter
@ToString
public class MessageModel implements BaseMessageModel<SipaBurMVo, MessageModel> {

    public static MessageModel build() {
        return new MessageModel();
    }

    /**
     * 创建消息模型 .
     * <ul>
     * <li>渠道中包含ALL的时候请不要再选择其它渠道，否则无法执行。</li>
     * <li>消息发起用户类型为用户时，应保证发送者不为空，否则无法执行。</li>
     * <li>当发送渠道选择了 ALL 或 WS时，请保证每一个 receiver 的 systemType 字段不能为空。</li>
     * </ul>
     *
     * @param sender       发送者[当发起类型为企业或用户的时候，此选项不能为空]
     * @param receiver     接收者
     * @param templateCode 模板代号
     * @param pushType     消息发起用户类型[1=系统/2=企业/3=用户]
     * @param taskType     任务类型
     * @param model        模板填充数据
     * @param channel      发送渠道
     * @param noticeType   消息类型[1=正常/2=商承通/T=贸易通]
     * @return com.vteam.sme.plugin.message.model.MessageModel 消息模型
     * @author andy.sher
     * @date 2018/9/19 11:36
     */
    @Override
    public MessageModel createMessageModel(SipaBurMVo sender, Collection<SipaBurMVo> receiver, String templateCode, String pushType, String taskType, Map<String, Object> model, Channel[] channel, String noticeType) {
        return createMessageModel(sender, receiver, templateCode, pushType, taskType, model, null, channel, noticeType);
    }

    /**
     * 创建消息模型 .
     * <ul>
     * <li>渠道中包含ALL的时候请不要再选择其它渠道，否则无法执行。</li>
     * <li>消息发起用户类型为用户时，应保证发送者不为空，否则无法执行。</li>
     * <li>当发送渠道选择了 ALL 或 WS时，请保证每一个 receiver 的 systemType 字段不能为空。</li>
     * </ul>
     *
     * @param sender       发送者[当发起类型为企业或用户的时候，此选项不能为空]
     * @param receiver     接收者
     * @param templateCode 模板代号
     * @param pushType     消息发起用户类型[1=系统/2=企业/3=用户]
     * @param taskType     任务类型
     * @param model        模板填充数据
     * @param attachment   附件集合
     * @param channel      发送渠道
     * @return com.vteam.sme.plugin.message.model.MessageModel 消息模型
     * @author andy.sher
     * @date 2019/3/4 13:31
     */
    @Override
    public MessageModel createMessageModel(SipaBurMVo sender, Collection<SipaBurMVo> receiver, String templateCode, String pushType, String taskType, Map<String, Object> model, Map<String, File> attachment, Channel[] channel) {
        return createMessageModel(sender, receiver, templateCode, pushType, taskType, model, attachment, channel, FieldConstant.FbtxNot.NOTICE_TYPE_DEFAULT);
    }

    /**
     * 创建消息模型 .
     * <ul>
     * <li>渠道中包含ALL的时候请不要再选择其它渠道，否则无法执行。</li>
     * <li>消息发起用户类型为用户时，应保证发送者不为空，否则无法执行。</li>
     * <li>当发送渠道选择了 ALL 或 WS时，请保证每一个 receiver 的 systemType 字段不能为空。</li>
     * </ul>
     *
     * @param sender       发送者[当发起类型为企业或用户的时候，此选项不能为空]
     * @param receiver     接收者
     * @param templateCode 模板代号
     * @param pushType     消息发起用户类型[1=系统/2=企业/3=用户]
     * @param taskType     任务类型
     * @param model        模板填充数据
     * @param channel      发送渠道
     * @return com.vteam.sme.plugin.message.model.MessageModel 消息模型
     * @author andy.sher
     * @date 2018/9/19 11:36
     */
    @Override
    public MessageModel createMessageModel(SipaBurMVo sender, Collection<SipaBurMVo> receiver, String templateCode, String pushType, String taskType, Map<String, Object> model, Channel[] channel) {
        return createMessageModel(sender, receiver, templateCode, pushType, taskType, model, null, channel, FieldConstant.FbtxNot.NOTICE_TYPE_DEFAULT);
    }

    /**
     * 创建消息模型 .
     * <ul>
     * <li>渠道中包含ALL的时候请不要再选择其它渠道，否则无法执行。</li>
     * <li>消息发起用户类型为用户时，应保证发送者不为空，否则无法执行。</li>
     * <li>当发送渠道选择了 ALL 或 WS时，请保证每一个 receiver 的 systemType 字段不能为空。</li>
     * </ul>
     *
     * @param sender       发送者[当发起类型为企业或用户的时候，此选项不能为空]
     * @param receiver     接收者
     * @param templateCode 模板代号
     * @param pushType     消息发起用户类型[1=系统/2=企业/3=用户]
     * @param taskType     任务类型
     * @param model        模板填充数据
     * @param attachment   附件集合
     * @param channel      发送渠道
     * @param noticeType   消息类型[1=正常/2=商承通/T=贸易通]
     * @return com.vteam.sme.plugin.message.model.MessageModel 消息模型
     * @author andy.sher
     * @date 2018/9/19 11:36
     */
    @Override
    public MessageModel createMessageModel(SipaBurMVo sender, Collection<SipaBurMVo> receiver, String templateCode, String pushType, String taskType, Map<String, Object> model, Map<String, File> attachment, Channel[] channel, String noticeType) {
        if (!FieldConstant.FbtxNot.PUSH_TYPE_SYSTEM.equals(pushType) && null == sender) {
            log.error("消息发送失败，因消息发送者为空。");
        } else {
            for (int i = 0, len = channel.length; i < len; i++) {
                if (channel[i] == Channel.ALL) {
                    if (len > 1) {
                        log.error("消息发送失败，选择ALL渠道时无需再选择其他渠道。");
                        return null;
                    }
                }
            }

            this.sender = sender;
            this.receiver = receiver;
            this.templateCode = templateCode;
            this.pushType = pushType;
            this.model = model;
            this.attachment = attachment;
            this.channel = channel;
            this.noticeType = noticeType;
            this.timestamp = LocalDateTime.now();
            this.type = GlobalConstants.Flag.FALSE;
            this.taskType = taskType;
        }
        return this;
    }

    /**
     * 新账务系统专用发邮件消息
     * @param channel 渠道
     * @param model  数据
     * @return
     */
    public MessageModel createMessageModel4Email(Channel[] channel,Map<String,Object> model){
        if(channel==null){
            log.error("调用createMessageModel4Email发送邮件消息，渠道只能设置为EMAIL");
        }
        if(channel.length==1 && Channel.EMAIL == channel[0]){
            this.channel = channel;
            this.model = model;
//            this.emailFrom = emailFrom;
//            this.emailTo = emailTo;
//            this.emailCc = emailCc;
//            this.emailBcc = emailBcc;
//            this.title = title;
//            this.content = content;
//            this.port = port;
//            this.protocol = protocol;
//            this.host = host;
//            this.username = username;
//            this.password = password;
        }else{
            log.error("调用createMessageModel4Email发送渠道只能设置为EMAIL");
        }
        return this;
    }

    /**
     * 延时发送消息的时间
     */
    private LocalDateTime delayTime;

    /**
     * 发送方[站内信]
     */
    private SipaBurMVo sender;

    /**
     * 消息发起用户类型
     */
    private String pushType;

    /**
     * 接收方
     */
    private Collection<SipaBurMVo> receiver;

    /**
     * 视图模板代码
     */
    private String templateCode;

    /**
     * 数据模型
     */
    private Map<String, Object> model;

    /**
     * 附件
     */
    private Map<String, File> attachment;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送渠道
     */
    private Channel[] channel;

    /**
     * 消息类型[1=正常/2=商承通]
     */
    private String noticeType;

    /**
     * 时间戳
     */
    private LocalDateTime timestamp;

    /**
     * 类型[1=验证码/2=通用]
     */
    private String type;
    
    /**
     * 任务类型
     */
    private String taskType;



    public MessageModel createMessageModel(SipaBurMVo sender, Collection<SipaBurMVo> receiver, String templateCode,
                                           String pushType, Map<String, Object> model, Channel[] channel) {
        return createMessageModel(sender, receiver, templateCode, pushType, null, model, null, channel,
                FieldConstant.FbtxNot.NOTICE_TYPE_DEFAULT);
    }
}
