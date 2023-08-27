package com.vteam.cars.constant;

/**
 * 数据库字段相关信息常量类
 *
 * @author chad.mei
 * @date 2022/9/28 9:27
 */
public final class FieldConstant {

    private FieldConstant() {
    }

    /**
     * 公共字段 .<br>
     *
     * @author andy.sher
     * @date 2018/8/3 13:12
     */
    public static final class PublicField {

        /**
         * 流水号
         */
        public static final String REFCODE = "refcode";

        /**
         * 交易批次号
         */
        public static final String TXNO = "txno";

        /**
         * 资源唯一编号
         */
        public static final String RESOURCE_UUID = "resourceUuid";

        /**
         * 创建日期
         */
        public static final String CREATEDATE = "createDate";

        /**
         * 创建用户
         */
        public static final String CREATEUSER = "createUser";

        /**
         * 最后修改日期
         */
        public static final String LASTMODDATE = "lastModDate";

        /**
         * 最后修改日期
         */
        public static final String LASTMODUSER = "lastModUser";

        /**
         * 操作类型
         */
        public static final String EDTID = "edtid";

        /**
         * 数据状态
         */
        public static final String DATA_STATUS = "dataStatus";
    }

    /**
     * 公共字段值 .<br>
     *
     * @author andy.sher
     * @date 2018/8/3 13:11
     */
    public static final class PublicFieldValue {

        private PublicFieldValue() {
        }

        /**
         * 成功标记
         */
        public static final String FLAG_OK = "1";
        /**
         * 失败标记
         */
        public static final String FLAG_ERROR = "0";
        /**
         * 数据删除标记：否=0(Default)
         */
        public static final String DEL_FLAG_NO = "0";

        /**
         * 数据启用[1=启用]
         */
        public static final String USE_FLAG_YES = "1";

        /**
         * 数据禁用[0=禁用]
         */
        public static final String USR_FLAG_NO = "0";

        /**
         * 数据删除标记：是
         */
        public static final String DEL_FLAG_YES = "1";

        /**
         * EDTID - NEW
         */
        public static final String EDTID_NEW = "NEW";

        /**
         * EDTID - MOD
         */
        public static final String EDTID_MOD = "MOD";

        /**
         * EDTID - DEL
         */
        public static final String EDTID_DEL = "DEL";

        /**
         * 资料状态-空状态
         */
        public static final String DATA_STATUS_BLANK = "00";

        /**
         * 资料状态-保存
         */
        public static final String DATA_STATUS_SAVE = "01";

        /**
         * 资料状态-流程中
         */
        public static final String DATA_STATUS_PROCESS = "11";

        /**
         * 资料状态-拒绝
         */
        public static final String DATA_STATUS_REFUSE = "12";

        /**
         * 资料状态-生效
         */
        public static final String DATA_STATUS_EFFECTIVE = "99";

        /**
         * 所属系统类型[1=后台](运营/管理)
         */
        public static final String SYSTEM_TYPE_MANAGE = "1";

        /**
         * 所属系统类型[2=前台](门户)
         */
        public static final String SYSTEM_TYPE_PORTAL = "2";

        /**
         * 是否存在[1= 是]
         */
        public static final String EXISTENCE_YES = "1";

        /**
         * 是否存在[0=否]
         */
        public static final String EXISTENCE_NO = "0";

        /**
         * 提交按钮操作类型[0= 通过]
         */
        public static final String SUBMIT_TYPE_PASS = "0";

        /**
         * 提交按钮操作类型[1= 拒绝]
         */
        public static final String SUBMIT_TYPE_REFUSE = "1";

        /**
         * 提交按钮操作类型[2= 打回]
         */
        public static final String SUBMIT_TYPE_BACK = "2";

        /** 置顶标记-1=是 */
        public static final String TOP_FLAG_YES = "1";

        /** 置顶标记-0=否 */
        public static final String TOP_FLAG_NO = "0";
    }

    /**
     * 系统资源表-CSPA_FUN_M相关常量 .<br>
     *
     * @author andy.sher
     * @date 2018/8/3 13:09
     */
    public final class CspaFun {

        private CspaFun() {
        }

        /**
         * 资源类型[0=菜单]
         */
        public static final String FUN_TYPE_MENU = "0";

        /**
         * 资源类型[1=按钮]
         */
        public static final String FUN_TYPE_BUTTON = "1";

        /**
         * 菜单类型 企业 =21
         */
        public static final String SYSTEM_TYPE_ENTERPRISE = "21";

        /**
         * 菜单类型 机构 =22
         */
        public static final String SYSTEM_TYPE_ORG = "22";

        /**
         * 菜单类型 核心企业 =24
         */
        public static final String SYSTEM_TYPE_CORE_COMPANY = "24";

        /**
         * 菜单类型 运营端 =11
         */
        public static final String SYSTEM_TYPE_OPERATION = "11";

        /**
         * 菜单类型 合伙人 =23
         */
        public static final String SYSTEM_TYPE_PARTNER = "23";

        /**
         * 菜单类型 个人 =41
         */
        public static final String SYSTEM_TYPE_PERSONAL = "41";


        /**
         * 菜单类型 微信小程序=81
         */
        public static final String SYSTEM_TYPE_WEIXINXCX = "81";


        /**
         * 菜单适用类型 A = 平台
         */
        public static final String SCPID_A = "A";

        /**
         * 菜单适用类型 B = 区域
         */
        public static final String SCPID_B = "B";

        /**
         * 菜单适用类型 C = 产业
         */
        public static final String SCPID_C = "C";

        /**
         * 菜单适用类型 D = 协会
         */
        public static final String SCPID_D = "D";

        /**
         * 菜单适用类型 O = OFS
         */
        public static final String SCPID_O = "O";

        /**
         * 菜单适用类型 W = 全流程
         */
        public static final String SCPID_W = "W";

        /**
         * 当前层-顶层
         */
        public static final int LAYID_TOP = 1;

        /**
         * 当前层-第二层
         */
        public static final int LAYID_SECOND = 2;

        /**
         * 当前层-第三层
         */
        public static final int LAYID_THIRD = 3;

        /**
         * 当前层-第四层
         */
        public static final int LAYID_FOUR = 4;

        /**
         * 是否使用 0=否
         */
        public static final int USE_FLAG_NO = 0;

        /**
         * 是否使用 1=是
         */
        public static final int USE_FLAG_YES = 1;


    }

    /**
     * 用户信息表相关常量 .
     *
     * @author zack.yin
     * @return
     * @date 2018/8/7 17:14
     */
    public final class SipaBurM {

        private SipaBurM() {
        }

        /**
         * 所属系统类型[后台]
         */
        public static final String SYSTEM_TYPE_OPERATION = "1";

        /**
         * 所属系统类型[前台]
         */
        public static final String SYSTEM_TYPE_PORTAL = "2";

        /**
         * 是否超级管理员[1=是]
         */
        public static final String ADMIN_FLAG_YES = "1";

        /**
         * 是否超级管理员[0=否]
         */
        public static final String ADMIN_FLAG_NO = "0";

        /**
         * 邮箱验证状态[0=未验证/1=已验证]
         */
        public static final String EMAIL_VAILD_UNCERTIFIED = "0";
        /**
         * 邮箱验证状态[0=未验证/1=已验证]
         */
        public static final String EMAIL_VAILD_CERTIFIED = "1";

        /**
         * 手机验证状态[0=未验证/1=已验证]
         */
        public static final String MOBILE_VAILD_STATUS_UNCERTIFIED = "0";
        /**
         * 手机验证状态[0=未验证/1=已验证]
         */
        public static final String MOBILE_VAILD_STATUS_CERTIFIED = "1";

        /**
         * 是否启用[0=禁用]
         */
        public static final String USE_FLAG_DISABLE = "0";

        /**
         * 是否启用[1=启用]
         */
        public static final String USE_FLAG_ENABLE = "1";

        /**
         * 前台用户类型[1=企业]
         */
        public static final String WEB_USER_TYPE_ENTERPRISE = "1";

        /**
         * 前台用户类型[12=金融机构]
         */
        public static final String WEB_USER_TYPE_BANK = "2";

        /**
         * 前台用户类型[3=合作机构]
         */
        public static final String WEB_USER_TYPE_COOPERATIVE = "3";

        /**
         * 前台用户类型[9=个人]
         */
        public static final String WEB_USER_TYPE_INDIVIDUAL = "9";

        /**
         * 数据来源[I=正常(default,PC)]
         */
        public static final String SOURCE_DEFAULT = "I";

        /**
         * 数据来源[A=APP]
         */
        public static final String SOURCE_APP = "A";

        /**
         * 数据来源[X=小程序]
         */
        public static final String SOURCE_XCX = "X";
        /**
         * 数据来源[W=微信]
         */
        public static final String SOURCE_WX = "W";

        /**
         * 数据来源[H=H5]
         */
        public static final String SOURCE_H5 = "H";

    }

    /**
     * 系统编号生成表相关常量 .
     *
     * @author oscar.yu
     * @date 2018-8-14 17:15
     */
    public final class FipaNum {

        private FipaNum() {
        }

        /**
         * 编号类型-1=交易流水号
         */
        public static final int NUM_TYPE_TX_NO = 1;

        /**
         * 编号类型-2=兑换码批次号
         */
        public static final int NUM_TYPE_REDEEM_BATCHNO = 2;

        /**
         * 编号类型-3=投资申请编号
         */
        public static final int NUM_TYPE_INV_APLCODE = 3;

        /**
         * 编号类型-3=投资申请编号
         */
        public static final int NUM_TYPE_FCNCODE = 4;

        /**
         * 交易流水号序号长度
         */
        public static final int TX_NO_SEQNUM_LENGTH = 6;

        /**
         * 兑换码批次号序号长度
         */
        public static final int REDEEM_BATCHNO_SEQNUM_LENGTH = 4;

        /**
         * 兑换码批次号序号长度
         */
        public static final int INV_APLCODE_SEQNUM_LENGTH = 4;



    }

    /**
     * 应用程序接口记录表 .
     *
     * @author andy.sher
     * @date 2019/2/18 15:17
     */
    public static final class FlogApiM {
        /**
         * 接口类型[I=入方向]
         */
        public static final String API_TYPE_I = "I";
        /**
         * 接口类型[O=出方向]
         */
        public static final String API_TYPE_O = "O";

        /**
         * 响应状态码[200 = 成功]
         */
        public static final String RESULT_CODE_SUCCESS = "200";

    }

    /**
     * 平台消息表 .
     *
     * @author andy.sher
     * @date 2018/8/20 13:15
     */
    public final class FbtxNot {

        /**
         * 消息发起用户类型[系统]
         */
        public static final String PUSH_TYPE_SYSTEM = "1";

        /**
         * 消息发起用户类型[用户]
         */
        public static final String PUSH_TYPE_USER = "3";

        /**
         * 消息状态 0=未读
         */

        public static final String NOTICE_FLAG_UNREAD = "0";

        /**
         * 消息状态 1=已读
         */
        public static final String NOTICE_FLAG_READ = "1";

        /**
         * 消息类型 1=正常
         */
        public static final String NOTICE_TYPE_DEFAULT = "1";

        /**
         * 任务类型[S=平台客服通知提醒]
         */
        public static final String TASK_TYPE_S = "S";

        /**
         * 任务类型[RL=重复登录消息提醒]
         */
        public static final String TASK_TYPE_RL = "RL";

    }

    /**
     * 品牌信息表
     *
     * @author fu.tong
     * @date 2018/9/11 17:58
     */
    public final class FbpaBrdM {
        private FbpaBrdM() {

        }

        /**
         * 来源品牌 [I = 正常]
         */
        public static final String BRAND_SOURCE_I = "I";

        /**
         * 来源品牌 [W = 接口-数据整合]
         */
        public static final String BRAND_SOURCE_W = "W";

        /**
         * 来源品牌 [P = 数据推送]
         */
        public static final String BRAND_SOURCE_P = "P";

        /**
         * 品牌版本代码-聚莹金链2.0
         */
        public static final String BRAND_VERSION_JYJL2 = "1";

        /**
         * 品牌版本代码-省级
         */
        public static final String BRAND_VERSION_PROVINCE = "2";

        /**
         * 品牌版本代码-宝亚
         */
        public static final String BRAND_VERSION_BY = "3";

        /**
         * 品牌版本代码-聚莹金链3.0
         */
        public static final String BRAND_VERSION_JYJL3 = "4";

        /**
         * 承做状态[0=已拒绝]
         */
        public static final String UNDERTAKE_STATUS_REFUSE = "0";

        /**
         * 承做状态[1=已承做]
         */
        public static final String UNDERTAKE_STATUS_MAKE = "1";

        /**
         * 承做状态[2=审核中]
         */
        public static final String UNDERTAKE_STATUS_AUDIT = "2";

        /**
         * 品牌风格 platform = 平台
         */
        public static final String BRAND_STYLE_PLATFORM = "platform";

        /**
         * 品牌风格 area = 区域
         */
        public static final String BRAND_STYLE_AREA = "area";

        /**
         * 品牌风格 industry = 产业
         */
        public static final String BRAND_STYLE_INDUSTRY = "industry";

        /**
         * 品牌风格 association = 协会
         */
        public static final String BRAND_STYLE_ASSOCIATION = "association";

        /**
         * 品牌风格 ofs = OFS
         */
        public static final String BRAND_STYLE_OFS = "ofs";

        /**
         * 品牌风格 whole = 全流程
         */
        public static final String BRAND_STYLE_WHOLE = "whole";
    }

    /**
     * 附件信息表 .
     *
     * @author andy.sher
     * @date 2018/12/17 14:45
     */
    public final class FbtxApx {

        /**
         * 本系统
         */
        public static final String FILESOURCE_LOCAL = "1";

        /**
         * 文档中心
         */
        public static final String FILESOURCE_FC = "2";

        /**
         * 数据来源[1=本系统]
         */
        public static final String DATA_SOURCE_LOCAL = "1";

        /**
         * 数据来源[2=其它平台]
         */
        public static final String DATA_SOURCE_TPP = "2";
    }

    public final class SipaRolM {
        /**
         * 一般企业管理员
         */
        public static final String ROLE_ID_GENERAL = "company_admin";
        /**
         * 核心企业管理员
         */
        public static final String ROLE_ID_CORE = "core_company_admin";
        /**
         * 机构管理员
         */
        public static final String ROLE_ID_FINANCIAL = "financial_admin";

        /**
         * 合伙人管理员
         */
        public static final String ROLE_ID_PARTNER = "partner_admin";

        /**
         * 运营端管理员
         */
        public static final String ROLE_ID_ADMIN = "admin";

        /**
         * 所属系统类型[1=后台]
         */
        public static final String SYSTEM_TYPE_BACK = "1";

        /**
         * 所属系统类型[2=前台]
         */
        public static final String SYSTEM_TYPE_FRONT = "2";

        /**
         * 角色类型 21 =企业端（一般企业）
         */
        public static final String ROLE_TYPE_COMPANY = "21";

        /**
         * 角色类型 22 =机构端
         */
        public static final String ROLE_TYPE_FINANCIAL = "22";


        /**
         * 角色类型 23 =合伙人端
         */
        public static final String ROLE_TYPE_PARTNER = "23";

        /**
         * 角色类型 24 =企业端（核心企业）
         */
        public static final String ROLE_TYPE_CORE_COMPANY = "24";

        /**
         * 角色类型 11 =后端(运营端)
         */
        public static final String ROLE_TYPE_BACK = "11";

        /**
         * 角色类型 81 =微信小程序
         */
        public static final String ROLE_TYPE_WEIXIN_XCX= "81";

        /**
         * 是否启用[0=禁用]
         */
        public static final String USE_FLAG_DISABLE = "0";

        /**
         * 是否启用[1=启用]
         */
        public static final String USE_FLAG_ENABLE = "1";


    }

    /**
     * 多语言描述表 .
     *
     * @author zack.yin
     * @return
     * @date 2018/9/9 22:55
     */
    public final class FspaColM {
        /**
         * 语言种类 中文
         */
        public static final String LANGUAGE_ID_zh_CN = "zh_CN";

        /**
         * 语言种类 台湾
         */
        public static final String LANGUAGE_ID_zh_TW = "zh_TW";

        /**
         * 语言种类 英语
         */
        public static final String LANGUAGE_ID_en_US = "en_US";
    }

    /**
     * 字典项常量
     */
    public final class FspaExgM {
        /**
         * 币种
         */
        public static final String CURRENCY = "currency";
        /**
         * 客户证件类型
         */
        public static final String CUST_ID_TYPE = "custIdType";
        /**
         * 期限
         */

        public static final String TIME_LIMIT = "timeLimit";

        /**
         * 服务行业
         */
        public static final String INDUSTRY_TYPE = "industryType";

        /**
         * 所属区域
         */
        public static final String AREA_TYPE = "areaType";

        /**
         * 地址类型
         */
        public static final String ADDRESS_TYPE = "orgAddressType";
        /**
         * 是/否
         */
        public static final String YES_NO = "yesNo";

        /**
         * 有/无
         */
        public static final String HAVE_OR_NOT = "haveOrNot";

        /**
         * 启用/禁用
         */
        public static final String USE_FLAG = "useFlag";

    }

    /**
     *
     * @author toby.tang
     * @date 2023/2/23 12:41
     */
    public final class FcarRedeemBatchM {

        /**
         * 是否已分配推广人[0-未分配(default)]
         */
        public static final String ALLOT_FLAG_NO = "0";
        /**
         * 是否已分配推广人[1-已分配]
         */
        public static final String ALLOT_FLAG_YES = "1";

        /**
         * 是否批量导入[0-否(default)]
         */
        public static final String IMPORT_FLAG_NO = "0";
        /**
         * 是否批量导入[1-是]
         */
        public static final String IMPORT_FLAG_YES = "1";

    }


    /**
     *
     * @author toby.tang
     * @date 2023/2/23 12:41
     */
    public final class FcarRedeemM {
        /**
         * 状态 [-1-兑换处理中]
         */
        public static final String STATUS_DOREDEEMING = "-1";
        /**
         * 状态 [0-未使用(default)]
         */
        public static final String STATUS_NOTUSE = "0";

        /**
         * 状态 [1-已使用]
         */
        public static final String STATUS_USED = "1";

        /**
         * 状态 [2-已失效]
         */
        public static final String STATUS_CANTUSE = "2";

        /**
         * 是否已分配推广人[0-未分配(default)]
         */
        public static final String ALLOT_FLAG_NO = "0";
        /**
         * 是否已分配推广人[1-已分配]
         */
        public static final String ALLOT_FLAG_YES = "1";

    }

    /**
     *
     * @author toby.tang
     * @date 2023/2/23 12:41
     */
    public final class FcarCouponsM {
        /**
         * 状态 [0-未使用(default)]
         */
        public static final String STATUS_NOTUSE = "0";

        /**
         * 状态 [1-已使用]
         */
        public static final String STATUS_USED = "1";

        /**
         * 状态 [2-已失效]
         */
        public static final String STATUS_CANTUSE = "2";

    }

    /**
     * 发票管理字典项常量
     * @author mark.qu
     * @date 2023/3/7 17:18
     **/
    public final class FcarBillM {

        /**
         * 发票类型 [2=电子专票]
         */
        public static final String BILLTYPE_DZ = "2";

        /**
         * 发票类型 [1=电子普票]
         */
        public static final String BILLTYPE_DP = "1";

        /**
         * 发票类型 [4=纸质专票]
         */
        public static final String BILLTYPE_ZZ = "4";

        /**
         * 发票类型 [3=纸质普票]
         */
        public static final String BILLTYPE_ZP = "3";

        /**
         * 发票类型 [5=全电普通发票]
         */
        public static final String BILLTYPE_QDP = "5";

        /**
         * 发票类型 [6=全电专用发票]
         */
        public static final String BILLTYPE_QDZ = "6";
    }

    /**
     * 服务提供商
     *
     * @author toby.tang
     * @date 2023/3/10 14:05
     */
    public final class FcarServiceProviderM {
        /**
         * 状态[0=正常]
         */
        public static final String STATUS_ENABLE = "0";

        /**
         * 状态[/1=已禁用]
         */
        public static final String STATUS_DISABLE = "1";
    }

    /**
     * 渠道
     *
     * @author toby.tang
     * @date 2023/3/10 14:05
     */
    public final class FcarChannelM {
        /**
         * 状态[0=正常]
         */
        public static final String STATUS_ENABLE = "0";

        /**
         * 状态[/1=已禁用]
         */
        public static final String STATUS_DISABLE = "1";
    }

    /**
     * 服务提供商
     *
     * @author toby.tang
     * @date 2023/3/10 14:05
     */
    public final class FcarServiceM {
        /**
         * 状态[0=正常]
         */
        public static final String SERVICE_STATUS_ENABLE = "0";

        /**
         * 状态[/1=已禁用]
         */
        public static final String SERVICE_STATUS_DISABLE = "1";
    }

    public static final class FaccPlnlintM{
        /**
         * 是否已支付[未支付]
         */
        public static final String PAY_FLAG_0 = "0";
        /**
         * 是否已支付[已支付]
         */
        public static final String PAY_FLAG_1 = "1";
        /**
         * 是否已支付[当期待支付]
         */
        public static final String PAY_FLAG_2 = "2";
        /**
         * 是否已支付[外付]
         */
        public static final String PAY_FLAG_3 = "3";
    }

    public static final class FinancialProduct{
        /**
         * 计息方式[按日计息]
         */
        public static final String BEARING_TYPE_1 = "1";

        /**
         * 计息方式[按月计息]
         */
        public static final String BEARING_TYPE_2 = "2";
        /**
         * 配息方式[3个月付息]
         */
        public static final String ALLOCATION_TYPE_1 = "1";
        /**
         * 配息方式[6个月付息]
         */
        public static final String ALLOCATION_TYPE_2 = "2";
        /**
         * 配息方式[季付息]
         */
        public static final String ALLOCATION_TYPE_3 = "3";

        /**
         * 产品期限类型[固定期限]
         */
        public static final String PRODUCT_LIMIT_1 = "1";
        /**
         * 产品期限类型[固定到期日]
         */
        public static final String PRODUCT_LIMIT_2 = "2";
        /**
         * 产品期限类型[固定日期]
         */
        public static final String PRODUCT_LIMIT_3 = "3";

        /**
         * 利率类型[1-固定利率，2-阶梯利率]
         */
        public static final String INTEREST_TYPE_1 = "1";

        /**
         * 利率类型[1-固定利率，2-阶梯利率]
         */
        public static final String INTEREST_TYPE_2 = "2";
    }

    /**
     * 交易表<br>.
     *
     * @author xia.hang
     * @date 2023/5/30 17:14

    */
    public final class FaccBtxM {
        /**
         * 交易项目[待受理=00]
         */
        public static final String PENDING_ACCEPTANCE = "00";

        /**
         * 交易项目[待受理=00/申购=01/配息=02/续约=04/账户变更=05/利率变更=06/赎回=07]
         */
        public static final String SUBSCRIBE = "01";

        /**
         * 交易项目[待受理=00/申购=01/配息=02/续约=04/账户变更=05/利率变更=06/赎回=07]
         */
        public static final String DIVIDEND = "02";

        /**
         * 交易项目[待受理=00/申购=01/配息=02/补利差=03/续约=04/账户变更=05/利率变更=06/赎回=07]
         */
        public static final String COVER = "03";

        /**
         * 交易项目[待受理=00/申购=01/配息=02/续约=04/账户变更=05/利率变更=06/赎回=07]
         */
        public static final String ACCOUNT = "05";
        /**
         * 交易项目[待受理=00/申购=01/配息=02/续约=04/账户变更=05/利率变更=06/赎回=07]
         */
        public static final String MODIFY = "06";

        /**
         * 交易项目[待受理=00/申购=01/配息=02/续约=04/账户变更=05/利率变更=06/赎回=07]
         */
        public static final String REDEEM = "07";

        /**
         * 变更方式[待受理=0000]
         */
        public static final String PENDING = "0000";

        /**
         * 变更方式[新增=0101]
         */
        public static final String ADD = "0101";

        /**
         * 变更方式[新增=0101/上传水单=0102/账务确认=0103/上传签署合同=0104/上传赎回申请书=0105/配息请款=0201/配息付款=0202/利息调整=0601/赎回付款=0701/赎回确认=0702]
         */
        public static final String WATER_RECEIPT = "0102";

        /**
         * 变更方式[账务确认=0103]
         */
        public static final String FINANCIAL_CONFIRMATION = "0103";

        /**
         * 变更方式[合约待归档=0104]
         */
        public static final String PROTOCOL = "0104";

        /**
         * 变更方式[协议已归档=0105]
         */
        public static final String PROTOCOL_COMPLETION = "0105";

        /**
         * 变更方式[申购完成=0106]
         */
        public static final String SUBSCRIPTION_COMPLETION = "0106";

        /**
         * 变更方式[配息请款=0201]
         */
        public static final String DIVIDEND_PLEASE = "0201";

        /**
         * 变更方式[配息付款=0202]
         */
        public static final String DIVIDEND_PAYMENT = "0202";

        /**
         * 变更方式[配息付款完成=0203]
         */
        public static final String DIVIDEND_PAYMENT_OVER = "0203";

        /**
         * 变更方式[补利差=0301]
         */
        public static final String DIVIDEND_COVER = "0301";

        /**
         * 变更方式[补利差付款=0302]
         */
        public static final String DIVIDEND_COVER_PAYMENT = "0302";

        /**
         * 变更方式[账户变更=0501]
         */
        public static final String ACCOUNT_CHANGE = "0501";
        /**
         * 变更方式[利息调整=0601]
         */
        public static final String DIVIDEND_MODIFY = "0601";

        /**
         * 变更方式[赎回请款=0701]
         */
        public static final String REDEEM_PAYMENT = "0701";
        /**
         * 变更方式[赎回付款=0702]
         */
        public static final String REDEEM_PAY = "0702";
        /**
         * 变更方式[归档赎回申请书=0703]
         */
        public static final String REDEEM_RECIEVE = "0703";
    }

    /**
     * 合同表
     */
    public final class FaccFcnM {
        /**
         * 合约状态[01=正常]
         */
        public static final String NORMAL = "01";

        /**
         * 合约类型[01=新合约]
         */
        public static final String FCN_TYPE_01 = "01";

        /**
         * 合约类型[02=转续合约]
         */
        public static final String FCN_TYPE_02 = "02";

        /**
         * 合约状态[01=正常/02=解约/03=部分赎回/04=到期结清/05=到期合额转续/06=到期部分转续]
         */
        public static final String FCN_STATUS_01 = "01";

        /**
         * 合约状态[01=正常/02=解约/03=部分赎回/04=到期结清/05=到期合额转续/06=到期部分转续]
         */
        public static final String FCN_STATUS_02 = "02";

        /**
         * 合约状态[01=正常/02=解约/03=部分赎回/04=到期结清/05=到期合额转续/06=到期部分转续]
         */
        public static final String FCN_STATUS_03 = "03";

        /**
         * 合约状态[01=正常/02=解约/03=部分赎回/04=到期结清/05=到期合额转续/06=到期部分转续]
         */
        public static final String FCN_STATUS_04 = "04";

        /**
         * 合约状态[01=正常/02=解约/03=部分赎回/04=到期结清/05=到期合额转续/06=到期部分转续]
         */
        public static final String FCN_STATUS_05 = "05";

        /**
         * 合约状态[01=正常/02=解约/03=部分赎回/04=到期结清/05=到期合额转续/06=到期部分转续]
         */
        public static final String FCN_STATUS_06 = "06";
    }

    /**
     * 投资申请表
     */
    public final class FaccAplM {
        /**
         * 待创建合约状态[0]
         */
        public static final String PENDING_CREATED = "0";

        /**
         * 已创建合约状态[1]
         */
        public static final String CREATED = "1";
    }

    /**
     * 业务待办表
     */
    public final class FaccTaskM {
        /**
         * 创建合约
         */
        public static final String CREATE_CONTRACT = "创建合约";

        /**
         * 上传水单
         */
        public static final String WATER_RECEIPT = "上传水单";

        /**
         * 到账确认
         */
        public static final String FINANCIAL_CONFIRMATION = "到账确认";
        /**
         * 赎回请款
         */
        public static final String REDEEM = "赎回请款";
        /**
         * 赎回付款
         */
        public static final String REDEEM_PAY = "赎回付款";

        /**
         * 配息请款
         */
        public static final String DIVIDEND = "配息请款";

        /**
         * 配息请款
         */
        public static final String DIVIDEND_PLEASE = "配息请款";

        /**
         * 补利差
         */
        public static final String DIVIDEND_COVERS = "补利差";

        /**
         * 配息付款
         */
        public static final String DIVIDEND_PAYMENT = "配息付款";


        /**
         * 利息调整
         */
        public static final String DIVIDEND_MODIFY = "利息调整";

        /**
         * 补利差
         */
        public static final String DIVIDEND_COVER = "补利差付款";

        /**
         * 银行账户变更
         */
        public static final String BM_COC_CBA = "银行账户变更";

        /**
         * 合约变更
         */
        public static final String BM_COC = "合约变更";

        /**
         * 续约
         */
        public static final String BM_RENEWAL = "续约";

        /**
         * 申购完成
         */
        public static final String SUBSCRIPTION_COMPLETION = "申购完成";

        public static final String SUBSCRIPTION_COMPLETION_BATCH = "批量申购完成";
        /**
         * 申购完成RESOURCE_ID
         */
        public static final String RESOURCE_SUBSCRIPTION_COMPLETION = "subscription-completion";
        public static final String RESOURCE_SUBSCRIPTION_COMPLETION_BACK = "subscription-completion-back";


        public static final String RESOURCE_SUBSCRIPTION_COMPLETION_BACK_BATCH = "subscription-completion-back-batch";

        /**
         * 创建合约待办RESOURCE_ID
         */
        public static final String RESOURCE_CREATE_CONTRACT = "resource-create-contract";

        /**
         * 上传水单RESOURCE_ID
         */
        public static final String RESOURCE_WATER_RECEIPT = "resource-water-receipt";

        /**
         * 配息打回RESOURCE_ID
         */
        public static final String DIVIDEND_BACK_ID = "dividend_back_id";

        /**
         * 配息付款RESOURCE_ID
         */
        public static final String DIVIDEND_PAYMENT_ID = "dividend_payment_id";

        /**
         * 赎回打回RESOURCE_ID
         */
        public static final String REDEEM_BACK_ID = "redeem_back_id";

        /**
         * 赎回付款RESOURCE_ID
         */
        public static final String REDEEM_PAY_ID = "redeem_pay_id";


        /**
         * 银行账户变更RESOURCE_ID
         */
        public static final String BM_COC_CBA_ID = "bm_coc_cba_id";

        /**
         * 合约变更RESOURCE_ID
         */
        public static final String BM_COC_ID = "bm_coc_id";

        /**
         * 续约RESOURCE_ID
         */
        public static final String BM_RENEWAL_ID = "bm_renewal_id";

        /**
         * 合约到期RESOURCE_ID
         */
        public static final String BM_DUE_RENEWAL_ID = "bm_due_renewal_id";

        /**
         * 配息请款RESOURCE_ID
         */
        public static final String DIVIDEND_PLEASE_ID = "dividend_please_id";

        /**
         * 利息调整RESOURCE_ID
         */
        public static final String DIVIDEND_MODIFY_ID = "dividend_modify_id";

        /**
         * 利息调整打回RESOURCE_ID
         */
        public static final String DIVIDEND_MODIFY_BACK_ID = "dividend_modify_back_id";

        /**
         * 补利差RESOURCE_ID
         */
        public static final String DIVIDEND_COVER_ID = "dividend_cover_id";

        /**
         * 补利差打回RESOURCE_ID
         */
        public static final String DIVIDEND_COVER_BACK_ID = "dividend_cover_back_id";

        /**
         * 配息付款消息提醒RESOURCE_ID
         */
        public static final String DIVIDEND_TASK = "dividend_task";

        /**
         * 到账确认RESOURCE_ID
         */
        public static final String RESOURCE_FINANCIAL_CONFIRMATION = "resource-financial-confirmation";
        /**
         * 批量赎回付款
         */
        public static final String RESOURCE_BATCHREDEEM_PAY = "resource-batch_redeem_pay";

        /**
         * 到期结清
         */
        public static final String RESOURCE_DUE_PAY = "resource-batch_due_pay";

        /**
         * 待办=1(Default)/消息提醒=2
         */
        public static final String INFO_TYPE_1 = "1";

        /**
         * 待办=1(Default)/消息提醒=2
         */
        public static final String INFO_TYPE_2 = "2";

        /**
         * 待办=1(Default)/消息提醒=3
         */
        public static final String INFO_TYPE_3 = "3";

        /**
         * 到账提醒
         */
        public static final String FINANCIAL_NOTIC = "到账提醒";

        /**
         * 已付款提醒
         */
        public static final String PAYMENT_NOTIC = "已付款提醒";
        /**
        * 合约变更提醒
        */
       public static final String ACCOUNT_CHANGE_NOTIC = "合约变更提醒";

        /**
         * 30日内即将到期之合约
         */
        public static final String DUE_NOTIC = "30日内即将到期之合约";

        /**
         * 到期结清提醒
         */
        public static final String DUE_PAYOFF_NOTIC = "到期结清提醒";

        /**
         * 配息付款提醒
         */
        public static final String DIVIDEND_NOTIC = "配息付款提醒";

    }

    public final class EmailConfig {
        /**
         * 客户[开发业务=1/渠道=2]
         */
        public static final String CUST_1 = "1";

        /**
         * 客户[开发业务=1/渠道=2]
         */
        public static final String CUST_2 = "2";
    }

    /**
     * 邮件发送历史表
     */
    public final class EmailSendHis {
        /**
         * 发送状态[1=未发送/2=发送成功/3=发送失败]
         */
        public static final String SEND_STATUS_1 = "1";

        /**
         * 发送状态[1=未发送/2=发送成功/3=发送失败]
         */
        public static final String SEND_STATUS_2 = "2";
        /**
         * 发送状态[1=未发送/2=发送成功/3=发送失败]
         */
        public static final String SEND_STATUS_3 = "3";
    }
}
