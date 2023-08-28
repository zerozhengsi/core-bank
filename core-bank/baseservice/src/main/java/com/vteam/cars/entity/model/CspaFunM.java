package com.vteam.cars.entity.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统资源表
 * </p>
 *
 * @author vteam-generator
 * @since 2023-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CspaFunM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId("REFCODE")
    private Integer refcode;

    /**
     * 资源类型[待定]
     */
    @TableField("SYSTEM_TYPE")
    private String systemType;

    /**
     * 功能代号
     */
    @TableField("FUNID")
    private String funid;

    /**
     * 序号
     */
    @TableField("SEQNO")
    private Integer seqno;

    /**
     * 当前层
     */
    @TableField("LAYID")
    private Integer layid;

    /**
     * 多语言代号-PCOLID
     */
    @TableField("FUN_DESC")
    private String funDesc;

    /**
     * 对应页面路径
     */
    @TableField("FUN_PATH")
    private String funPath;

    /**
     * 父功能代号(序号)
     */
    @TableField("PARENTID")
    private Integer parentid;

    /**
     * 层级代号（类似**Z**）
     */
    @TableField("LEVEL_CODE")
    private String levelCode;

    /**
     * 适用范围[A=平台/B=区域/C=产业/D=协会/O=OFS/W=全流程]
     */
    @TableField("SCPID")
    private String scpid;

    /**
     * 资源类型[0=菜单/1=按钮]
     */
    @TableField("FUN_TYPE")
    private String funType;

    /**
     * 是否是底层权限[0=否/1=是(default)]
     */
    @TableField("BOT_FLAG")
    private String botFlag;

    /**
     * 是否在使用[0=否/1=是(default)]
     */
    @TableField("USE_FLAG")
    private String useFlag;

    /**
     * 图标类型[1=font-awesome/2=iconfont/3=icon-svg]
     */
    @TableField("ICON_TYPE")
    private String iconType;

    /**
     * 图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 资源所在位置
     */
    @TableField("POSITION")
    private String position;


}
