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
 * 附件存储表
 * </p>
 *
 * @author vteam-generator
 * @since 2020-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FbtxApxM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId("REFCODE")
    private Integer refcode;

    /**
     * 资源唯一编号
     */
    @TableField(value = "RESOURCE_UUID", fill = FieldFill.INSERT)
    private String resourceUuid;

    /**
     * 文件上传对象惟一编号
     */
    @TableField("FILE_UUID")
    private String fileUuid;

    /**
     * 文件来源[1-本系统/2-文档中心]
     */
    @TableField("FILE_SOURCE")
    private String fileSource;

    /**
     * 是否加密(0=否/1=是)
     */
    @TableField("ENCRYPT_FLAG")
    private String encryptFlag;

    /**
     * 文件名
     */
    @TableField("FILE_NAME")
    private String fileName;

    /**
     * 文件类型/后缀名
     */
    @TableField("FILE_TYPE")
    private String fileType;

    /**
     * 文件路径
     */
    @TableField("FILE_PATH")
    private String filePath;

    /**
     * 文件存储名
     */
    @TableField("STORE_NAME")
    private String storeName;

    /**
     * 说明
     */
    @TableField("MEMO")
    private String memo;

    /**
     * 文档中心文件ID
     */
    @TableField("FILE_ID")
    private String fileId;

    /**
     * 文档中心业务主键（存储路径）
     */
    @TableField("BIZ_KEY")
    private String bizKey;

    /**
     * 数据来源[1=本系统/2=其它平台]
     */
    @TableField("DATA_SOURCE")
    private String dataSource;

    /**
     * 第三方平台流水号
     */
    @TableField("TPP_REFCODE")
    private Integer tppRefcode;

    /**
     * 是否已成功下载[1=是/0=否]
     */
    @TableField("DOWNLOADED")
    private String downloaded;

    /**
     * 文件下载次数
     */
    @TableField("DOWNLOAD_COUNT")
    private Integer downloadCount;

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
