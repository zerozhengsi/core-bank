package com.vteam.cars.entity.model;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 第三方平台信息表
 * </p>
 *
 * @author vteam-generator
 * @since 2020-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FipaTppM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId("REFCODE")
    private Integer refcode;

    /**
     * 平台代号
     */
    @TableField("PLATFORM_CODE")
    private String platformCode;

    /**
     * 平台名称
     */
    @TableField("PLATFORM_NAME")
    private String platformName;

    /**
     * 平台简称
     */
    @TableField("PLATFORM_SHORT_NAME")
    private String platformShortName;

    /**
     * 平台访问地址
     */
    @TableField("PLATFORM_URL")
    private String platformUrl;

    /**
     * 平台接口地址
     */
    @TableField("PLATFORM_INTERFACE_URL")
    private String platformInterfaceUrl;

    /**
     * 平台对应公钥
     */
    @TableField("PLATFORM_PUBLIC_KEY")
    private String platformPublicKey;

    /**
     * 系统对应公钥
     */
    @TableField("SYSTEM_PUBLIC_KEY")
    private String systemPublicKey;

    /**
     * 系统对应私钥
     */
    @TableField("SYSTEM_PRIVATE_KEY")
    private String systemPrivateKey;

    /**
     * 系统对称加密偏移量
     */
    @TableField("SYSTEM_KEY_IV")
    private String systemKeyIv;

    /**
     * 接口加密方式[0=不加密/R=RSA非对称加密/A=ASE对称加密]
     */
    @TableField("CRYPT_TYPE")
    private String cryptType;

    /**
     * 平台所属行业
     */
    @TableField("INDUSTRY_TYPE")
    private String industryType;

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
