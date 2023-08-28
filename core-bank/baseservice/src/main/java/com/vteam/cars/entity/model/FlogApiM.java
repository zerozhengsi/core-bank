package com.vteam.cars.entity.model;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 应用程序接口记录表
 * </p>
 *
 * @author vteam-generator
 * @since 2020-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FlogApiM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId("REFCODE")
    private Integer refcode;

    /**
     * 接口唯一标识码
     */
    @TableField("API_ID")
    private String apiId;

    /**
     * 接口名称
     */
    @TableField("API_NAME")
    private String apiName;

    /**
     * 接口类型[I=入方向/O=出方向]
     */
    @TableField("API_TYPE")
    private String apiType;

    /**
     * 请求报文
     */
    @TableField("REQUEST_BODY")
    private String requestBody;

    /**
     * 响应报文
     */
    @TableField("RESPONSE_BODY")
    private String responseBody;

    /**
     * 请求时间
     */
    @TableField("REQUEST_TIME")
    private LocalDateTime requestTime;

    /**
     * 响应时间
     */
    @TableField("RESPONSE_TIME")
    private LocalDateTime responseTime;

    /**
     * 响应状态码
     */
    @TableField("RESULT_CODE")
    private String resultCode;

    /**
     * 创建者
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_DATE", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 操作者
     */
    @TableField(value = "LAST_MOD_USER", fill = FieldFill.INSERT_UPDATE)
    private String lastModUser;

    /**
     * 操作时间
     */
    @TableField(value = "LAST_MOD_DATE", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastModDate;


}
