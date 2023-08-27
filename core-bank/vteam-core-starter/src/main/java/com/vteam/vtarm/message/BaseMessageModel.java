package com.vteam.vtarm.message;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * 消息模型 .<br>
 *
 * @author andy.sher
 * @date 2018/9/18 13:53
 */
public interface BaseMessageModel<T, R> {

    /**
     * 发送渠道
     */
    public enum Channel {
        /**
         * 所有渠道
         */
        ALL,
        /**
         * 邮件
         */
        EMAIL,
        /**
         * 站内信
         */
        NOTICE,
        /**
         * 短信
         */
        SMS,
        /**
         * web socket
         */
        WS
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
     * @param noticeType   消息类型[1=正常/2=商承通]
     * @return com.vteam.sme.plugin.message.model.MessageModel 消息模型
     * @author andy.sher
     * @date 2018/9/19 11:36
     */
    R createMessageModel(T sender, Collection<T> receiver, String templateCode, String pushType, String taskType, Map<String, Object> model, Channel[] channel, String noticeType);

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
    R createMessageModel(T sender, Collection<T> receiver, String templateCode, String pushType, String taskType, Map<String, Object> model, Map<String, File> attachment, Channel[] channel);

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
     * @param model        模板填充数据
     * @param taskType     任务类型
     * @param channel      发送渠道
     * @return com.vteam.sme.plugin.message.model.MessageModel 消息模型
     * @author andy.sher
     * @date 2018/9/19 11:36
     */
    R createMessageModel(T sender, Collection<T> receiver, String templateCode, String pushType, String taskType, Map<String, Object> model, Channel[] channel);

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
     * @param model        模板填充数据
     * @param taskType     任务类型
     * @param attachment   附件集合
     * @param channel      发送渠道
     * @return com.vteam.sme.plugin.message.model.MessageModel 消息模型
     * @author andy.sher
     * @date 2018/9/19 11:36
     */
    R createMessageModel(T sender, Collection<T> receiver, String templateCode, String pushType, String taskType, Map<String, Object> model, Map<String, File> attachment, Channel[] channel, String noticeType);

}
