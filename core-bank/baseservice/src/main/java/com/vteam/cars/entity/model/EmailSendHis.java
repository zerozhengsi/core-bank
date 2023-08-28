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
 * 邮件发送历史
 * </p>
 *
 * @author vteam-generator
 * @since 2023-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EmailSendHis implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId("REFCODE")
    private Integer refcode;

    /**
     * 业务功能流水号
     */
    @TableField("BUSREFCODE")
    private Integer busrefcode;

    /**
     * 交易项目
     */
    @TableField("ITEM")
    private String item;
    /**
     * 业务状态
     */
    @TableField("OPER_TYPE")
    private String operType;


    /**
     * 批次号
     */
    @TableField("TXNO")
    private String txno;

    /**
     * 客户流水号
     */
    @TableField("CUSTREFCODE")
    private Integer custrefcode;

    /**
     * 产品流水号
     */
    @TableField("PROREFCODE")
    private Integer prorefcode;

    /**
     * 合约编号
     */
    @TableField("FCN_NUM")
    private String fcnNum;

    /**
     * 邮件类型[01=到账通知/02=发送申购完成确认书/
03=合约签署完成通知/04=发送赎回申请书/
05=赎回申请已接收通知/06=配息通知/
07=合约即将到期提醒/08=账户变更通知]
     */
    @TableField("EMAIL_TYPE")
    private String emailType;

    /**
     * 邮件服务器
     */
    @TableField("HOST")
    private String host;

    /**
     * 是否开启SSL
     */
    @TableField("SSL_FLAG")
    private String sslFlag;

    /**
     * 端口号
     */
    @TableField("PORT")
    private Integer port;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 发件人
     */
    @TableField("EMAIL_FROM")
    private String emailFrom;

    /**
     * 收件人
     */
    @TableField("EMAIL_TO")
    private String emailTo;

    /**
     * 抄送
     */
    @TableField("EMAIL_CC")
    private String emailCc;

    /**
     * 密抄
     */
    @TableField("EMAIL_BCC")
    private String emailBcc;

    /**
     * 附件
     */
    @TableField("FILE_UUID")
    private String fileUuid;
    /**
     * 生成的附件
     */
    @TableField("GENFILE_UUID")
    private String genfileUuid;

    /**
     * 附件密码
     */
    @TableField("ENCRYPTION_FILE")
    private String encryptionFile;

    /**
     * 邮件内容
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 邮件标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 预约发送时间
     */
    @TableField("DELAY_TIME")
    private LocalDateTime delayTime;

    /**
     * 实际发送时间
     */
    @TableField("ACTUAL_TIME")
    private LocalDateTime actualTime;

    /**
     * 发送状态[1=未发送/2=发送成功/3=发送失败]
     */
    @TableField("SEND_STATUS")
    private String sendStatus;

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
