package com.vteam.cars.entity.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 平台消息提醒表
 * </p>
 *
 * @author vteam-generator
 * @since 2020-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FbtxNotM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId("REFCODE")
    private Integer refcode;

    /**
     * 消息发起用户类型[1=系统/2=企业/3=用户]
     */
    @TableField("PUSH_TYPE")
    private String pushType;

    /**
     * 消息发起企业(机构)流水号
     */
    @TableField("PUSH_ORG_REFCODE")
    private Integer pushOrgRefcode;

    /**
     * 消息接收用户id
     */
    @TableField("RECEIVE_USERID")
    private String receiveUserid;

    /**
     * 消息接收企业(机构)流水号
     */
    @TableField("RECEIVE_ORG_REFCODE")
    private Integer receiveOrgRefcode;

    /**
     * 消息编号
     */
    @TableField("NOTICE_CODE")
    private String noticeCode;

    /**
     * 消息标题
     */
    @TableField("NOTICE_TITLE")
    private String noticeTitle;

    /**
     * 消息内容
     */
    @TableField("NOTICE_CONTENT")
    private String noticeContent;

    /**
     * 消息带链接内容
     */
    @TableField("NOTICE_CONTENT_HREF")
    private String noticeContentHref;

    /**
     * 消息状态(0=未读/1=已读)
     */
    @TableField("NOTICE_FLAG")
    private String noticeFlag;

    /**
     * 消息类型[1=正常/2=商承通/T=贸易通]
     */
    @TableField("NOTICE_TYPE")
    private String noticeType;

    /**
     * 读取时间
     */
    @TableField("READ_TIME")
    private LocalDateTime readTime;

    /**
     * 是否在首页显示(0=否/1=是)
     */
    @TableField("SHOW_HOME")
    private String showHome;

    /**
     * 接收企业名称
     */
    @TableField("ORGNAME")
    private String orgname;

    /**
     * 接收企业统一社会信用代码
     */
    @TableField("COMPANY_CREDIT_CODE")
    private String companyCreditCode;

    /**
     * 接收企业品牌流水号
     */
    @TableField("BRAND_REFCODE")
    private Integer brandRefcode;

    /**
     * 任务类型[A=审批流待办/B=待受理报价/C=待签约项目/D=待确认应收单/E=待确认应付单/F=待签收保付单/G=待受理开单申请/H=待受理项目/I=待业务审核/J=待发起签约/K=待审批放款申请/L=待兑付保付单/M=待报价项目/N=项目审核/O=待确认签约/P=资产融资申请待受理/Q=资产融资确认/R=资产融资放款审批/S=发票补录提醒]]
     */
    @TableField("TASK_TYPE")
    private String taskType;

    /**
     * 资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]
     */
    @TableField(value = "DATA_STATUS", fill = FieldFill.INSERT)
    private String dataStatus;

    /**
     * 审批流PK
     */
    @TableField("ENTITY_ID")
    private String entityId;

    /**
     * 删除标记[是=1/否=0(Default)]
     */
    @TableField("DEL_FLAG")
    @TableLogic
    private String delFlag;

    /**
     * 创建者
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建者代理人
     */
    @TableField("CREATE_AGENT_USER")
    private String createAgentUser;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_DATE", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 操作类型
     */
    @TableField(value = "EDTID", fill = FieldFill.INSERT_UPDATE)
    private String edtid;

    /**
     * 操作者
     */
    @TableField(value = "LAST_MOD_USER", fill = FieldFill.INSERT_UPDATE)
    private String lastModUser;

    /**
     * 操作代理人
     */
    @TableField("AGENT_USER")
    private String agentUser;

    /**
     * 操作时间
     */
    @TableField(value = "LAST_MOD_DATE", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastModDate;


}
