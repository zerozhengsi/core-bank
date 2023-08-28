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
 * 邮件配置表
 * </p>
 *
 * @author vteam-generator
 * @since 2023-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EmailConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId("REFCODE")
    private Integer refcode;

    /**
     * 产品流水号
     */
    @TableField("PROREFCODE")
    private Integer prorefcode;

    /**
     * 邮件类型[01=到账通知/02=发送申购完成确认书/
03=合约签署完成通知/04=发送赎回申请书/
赎回申请已接收通知/05=配息通知/
06=合约即将到期提醒/07=账户变更通知]
     */
    @TableField("EMAIL_TYPE")
    private String emailType;

    /**
     * 收件人客户
     */
    @TableField("CUST_TYPE")
    private String custType;

    /**
     * 抄送[开发业务=1/渠道=2]
     */
    @TableField("CC")
    private String cc;

    /**
     * 抄送角色
     */
    @TableField("CC_ROLE")
    private String ccRole;

    /**
     * 密抄[开发业务=1/渠道=2]
     */
    @TableField("BCC")
    private String bcc;

    /**
     * 密抄角色
     */
    @TableField("BCC_ROLE")
    private String bccRole;

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
