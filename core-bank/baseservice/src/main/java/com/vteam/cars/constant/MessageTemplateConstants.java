package com.vteam.cars.constant;


import lombok.Getter;

/**
 * 消息模板常量 .<br>
 *
 * @author andy.sher
 * @date 2018/9/27 9:25
 */
@Getter
public enum MessageTemplateConstants {

    /**
     * 导出文件
     */
    EXPORT_FILE("exportFile"),

    /**
     * 邮箱验证码
     */
    GLOBAL_YXYZM("global-yxyzm"),

    /**
     * 手机验证码
     */
    GLOBAL_DHYZM("global-dhyzm"),

    /**
     * 批量导入兑换码
     */
    REDEEMLIST_IMPORT_FAILD("redeemList-import-faild"),

    /**
     * 批量导入兑换码分配清单
     */
    REDEEMALLOTLIST_IMPORT_FAILD("redeemAllotList-import-faild"),

    /**
     * 批量导入订单
     */
    ORDERLIST_IMPORT_FAILD("orderList-import-faild"),

    /**
     * 批量导入发票
     * @author mark.qu
     * @date 2023/3/15 9:29
     **/
    BILLLIST_IMPORT_FAILD("billList-import-faild"),
    /**
     * 批量导入推广信息
     */
    INFOLIST_IMPORT_FAILD("infoList-import-faild"),
    /**
     * 批量导入推广人员
     */
    PERSONLIST_IMPORT_FAILD("personList-import-faild"),

    /**
     * 批量导入门店
     */
    STORELIST_IMPORT_FAILD("storeList-import-faild");

    MessageTemplateConstants(String templateName) {
        this.templateName = templateName;
    }

    private String templateName;


}
