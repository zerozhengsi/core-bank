package com.vteam.cars.plugin.file.upload.enums;

import lombok.Getter;

/**
 * 文件BKey枚举 .<br>
 *
 * @author andy.sher
 * @date 2018/11/14 16:41
 */
@Getter
public enum FileBKeyEnum {

    /**
     * 会员中心-会员管理-核心企业
     */
    MEMBERCENTER_MMNG_CORE("SME-MC-MMNG-COR", "会员中心/会员管理/核心企业"),

    /**
     * 用户中心-实名认证
     */
    UCENTER_RA("SME-UC-RA", "实名认证/${orgname}"),

    /**
     * 我的合同-在线签约
     */
    MYCONTRACT_ONLINESIGN("SME-MC-OS", "我的合同/${orgname}"),

    CONFIRM_COMPLETION("CONFIRM-COMPLETION","申购完成确认书"),

    RECEIPT_CONFIRMATION("RECEIPT-CONFIRMATION","正式到帳確認函"),

    /**
     * 合同制作
     */
    CONTRACT_PRODUCTION("SME_CON_PRO","合同制作"),

    INV_TRANS("SME_INV_TRS","应收账款转让"),

    /**
     * 内容中心-资讯管理-文章维护
     */
    CONTENT_INFORMATION_ART("SME-COT-INF-ART", "内容中心/资讯管理/文章维护"),

    /**
     * 内容中心-资讯管理-专家管理
     */
    CONTENT_INFORMATION_EXP("SME-COT-INF-EXP", "内容中心/资讯管理/专家管理"),

    /**
     * 内容中心-资讯管理-文库管理
     */
    CONTENT_INFORMATION_DOC("SME-COT-INF-DOC", "内容中心/资讯管理/文库管理"),

    /**
     * 内容中心-活动管理-活动维护
     */
    CONTENT_ACTIVITY_ACT("SME-COT-ACT-ACT", "内容中心/活动管理/活动维护"),

    /**
     * 内容中心-活动管理-课程维护
     */
    CONTENT_ACTIVITY_COU("SME-COT-ACT-COU", "内容中心/活动管理/课程维护"),

    /**
     * 内容中心-活动管理-报名管理
     */
    CONTENT_ACTIVITY_SU("SME-COT-ACT-SU", "内容中心/活动管理/报名管理"),

    /**
     * 内容中心-其他管理-融资故事
     */
    CONTENT_OTHER_FS("SME-COT-OT-FS", "内容中心/其他管理/融资故事"),

    /**
     * 审核管理-管理中心-项目管理
     */
    REVIEW_MANAGE_PROJECT("SME-REV-MAN-PRO", "审核管理/管理中心/项目管理"),

    /**
     * 审核管理-跨品牌推送管理-推介授权书
     */
    REVIEW_MANAGE_PUSH_AUTH("SME-REV-MAN-PUSH-AUTH", "审核管理/跨品牌推送管理/推介授权书/${project}"),

    /**
     * 栏目管理
     */
    CATEGORY_PRODUCT_BM("SME-CAT-PRD-BM", "栏目管理/板块管理/产品管理"),

    /**
     * 栏目管理
     */
    CATEGORY_PARTNER("SME-CAT-PARTNER", "栏目管理/板块管理/合作伙伴"),

    /**
     * 保付单
     */
    PAYMENT_BFD("SME-EPA-A", "保付单/${epaNo}"),
    /**
     * 应收账款
     */
    TRANSFER_ARP("SME-ARP", "应收账款/${arpNo}"),

    /**
     * 保付单交易文件
     */
    PAYMENT_BFD_TD("SME-EPA-TD", "保付单/交易文件"),

    /**
     * 应付单附件
     */
    PAYMENT_PAYABLE("SME-EPA-PAYABLE", "保付单/应付单"),

    /**
     * 文件管理-基本信息
     */
    FILEMANAGE_BASE("SME-APX-FM-A", "文件管理/${orgname}/基本信息"),

    /**
     * 文件管理-证照信息
     */
    FILEMANAGE_CERTIFICATE("SME-APX-FM-B", "文件管理/${orgname}/证照信息"),

    /**
     * 文件管理-补充资料
     */
    FILEMANAGE_SUPPLEMENT("SME-APX-FM-C", "文件管理/${orgname}/补充资料"),

    /**
     * 票据管理-银票
     */
    BILL_BANK("SME-BILL-BANK", "票据管理/银票/${billMediaDesc}/${mobilephone}"),

    /**
     * 票据管理-商票
     */
    BILL_BUSINESS("SME-BILL-BUSINESS", "票据管理/商票/${billMediaDesc}/${mobilephone}"),

    /**
     * 票据管理-转让协议
     */
    BILL_TRANSFER_AGREEMENT("SME-BILL-TA", "票据管理/转让协议/${orgname}"),

    /**
     * 票据管理-背书
     */
    BILL_ENDORSEMENT("SME-BILL-ENDORSEMENT", "票据管理/背书/${orgname}"),

    /**
     * 公告管理
     */
    PLATFORM_BULLETIN("SME-PLAT-BULLETIN", "运营管理/公告管理"),

    /**
     * 图片管理
     */
    IMAGEMANAGE("SME-IMAGEMANAGE", "栏目管理/导航栏管理/图片管理"),

    /**
     * 店铺管理-内容管理
     */
    STOREMANAGEMENT_CONTENTMANAGEMENT("SME-STORE-CONTENT", "店铺管理/${orgname}/内容管理"),

    /**
     * 尽调管理-尽调结果录入
     */
    DUEDILIGENCE_TASKINPUT("SME-DUEDILIGENCE-TASKINPUT", "尽调管理/尽调结果录入/${taskNo}"),

    /**
     * 项目管理-立项申请
     */
    PROJECTMANAGER_APPLY_ACCEPT("SME-APPLY-ACCEPT", "项目管理/项目受理"),

    /**
     * 项目管理-融资申请
     */
    PROJECTMANAGER_APPLY_FINANCING("SME-APPLY-FINANCING", "项目管理/融资申请"),

    /**
     * 管理员
     */
    ADMIN("ADMIN", "管理员"),

    /**
     * 保付单融资
     */
    SME_LOAN("SME-LOAN", "保付单融资"),

    /**
     * 资产融资
     */
    ARP_LOAN("ARP_LOAN", "资产融资"),

    /**
     * 专项融资
     */
    BYSME_LOAN("BYSME-LOAN", "专项融资"),

    /**
     * 电子印章
     */
    SEAL_IMAGES("SEAL-IMAGES", "电子印章/${orgname}"),

    /**
     * 电子印章快递单
     */
    SIGN_EXPRESS_FILE("SIGN-EXPRESS-FILE", "${orgname}/A0003电子印章寄送快递单"),

    /**
     * 投资申请
     */
    INV_APPLICATION("INV-APPLICATION", "投资申请书"),

    /**
     * 水单
     */
    WATER_RECEIPT("WATER-RECEIPT", "付款水单"),

    /**
     * 到账证明
     */
    FINANCIAL_CONFIRMATION("FINANCIAL-CONFIRMATION", "到账证明"),
    /**
     * 申购生成协议
     */
    PROTOCOL_GENERATE("PROTOCOL-GENERATE", "生成协议"),

    /**
     * 归档协议
     */
    PROTOCOL_ARCHIVING("PROTOCOL-ARCHIVING", "归档协议"),

    /**
     * 配息通知书
     */
    DIVIDEND_PAYMENT("finbusiness:px:dividendPayment:mod", "配息确认书"),

    /**
     * 赎回申请书
     */
    REDEEM("REDEEM", "赎回申请书"),

    /**
     * 付款通知书
     */
    REDEEM_PAY("REDEEM-PAY", "付款通知书"),

    /**
     * 赎回委托确认书
     */
    REDEEM_ENTRUST_CONGIRM("REDEEM-ENTRUST-CONGIRM","赎回委托确认书"),
    /**
     * 出金表
     */
    CASH_OUT("CASH-OUT", "出金表"),

    /**
     * 入金表
     */
    CASH_DEPOSIT("CASH-DEPOSIT", "入金表"),

    /**
     * 对账单
     */
    KF_SOA("KF-SOA", "对账单"),

    /**
     * 转续约附件
     */
    RENEWAL_FILE_UUID("RENEWAL-FILE-UUID", "转续约附件"),

    /**
     * 协议管理-春茂-应收账款收益权转让及回购协议
     */
    PROTOCOL_MANAGEMENT_CM_1("CM_1", "应收账款收益权转让及回购协议"),


    /**
     * 默认路径
     */
    DEFAULT("SME-DEFAULT", "默认");

    FileBKeyEnum(String code, String bkey) {
        this.code = code;
        this.bkey = bkey;
    }

    /**
     * 获取Code对应的BKey路径 .
     *
     * @param code 代号
     * @return com.vteam.sme.plugin.file.upload.enums.FileBKeyEnum 存储路径
     * @author andy.sher
     * @date 2018/11/15 13:45
     */
    public static FileBKeyEnum get(String code) {
        FileBKeyEnum fileBKeyEnum = null;
        FileBKeyEnum[] fileBKeyEnums = FileBKeyEnum.values();
        for (FileBKeyEnum temp : fileBKeyEnums) {
            if (temp.getCode().equals(code)) {
                fileBKeyEnum = temp;
                break;
            }
        }
        return fileBKeyEnum;
    }

    private String code;

    private String bkey;

}
