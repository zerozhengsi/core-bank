package com.vteam.cars.entity.vo;

import com.vteam.cars.entity.model.FbtxApxM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * 附件扩展类 .<br>
 *
 * @author andy.sher
 * @date 2018/8/3 13:23
 */
@Getter
@Setter
@ToString(callSuper = true)
public class FbtxApxMVo extends FbtxApxM {

    private static final long serialVersionUID = 5982172520098556966L;

    /**
     * 附件数据
     */
    private byte[] data;

    /**
     * 附件流水号数组
     */
    private Integer[] refcodes;

    /**
     * 附件流水号数组
     */
    private String[] orgApxRefcodes;

    /**
     * 附件唯一UUID数组
     */
    private String[] resourceUuids;

    /**
     * 文件类型[1=合同/2=发票/9=其它]
     */
    private String archiveType;

    /**
     * 文件编号
     */
    private String archiveNo;

    /**
     * 备注说明
     */
    private String archiveRemark;

    /**
     * 合同编号
     */
    private String docNo;

    /**
     * 合同/发票金额
     */
    private BigDecimal docAmt;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页面宽度
     */
    private Integer pageWidth;

    /**
     * 页面高度
     */
    private Integer pageHeight;

    /**
     * fileUuid集合
     */
    private List<String> fileUuidList;
}
