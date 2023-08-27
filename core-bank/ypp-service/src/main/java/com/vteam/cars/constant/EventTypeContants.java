package com.vteam.cars.constant;

/**
 * 操作类型
 *
 * @author jiangming.huang
 * @date 2019/1/25 15:01
 */
public class EventTypeContants {

    private EventTypeContants() {
    }


    /***********************************   微信端操作类型 start  ********************************************/
    public static final class Weixin {

        /**
         * 操作类型：微信小程序用户登录
         */
        public final static String XCX_LOGIN = "微信小程序用户登录";

        /**
         * 操作类型：微信小程序用户退出
         */
        public final static String XCX_LOGOUT = "微信小程序用户退出";

        /**
         * 操作类型：微信小程序手机注册
         */
        public final static String XCX_AUTOREG_MOBILE = "微信小程序手机注册";

        /**
         * 操作类型：兑换码兑换
         */
        public final static String XCX_DO_REDEEM = "兑换码兑换";


        /**
         * 操作类型：订单预约
         */
        public final static String XCX_DO_ORDER = "订单预约";

    }

    /***********************************    微信端操作类型 end  ********************************************/

    /***********************************   运营操作类型 start  ********************************************/
    public static final class Operation {

        /**
         * 操作类型：服务券管理
         */
        public final static String REDEEM_MANAGE = "服务券管理";

        /**
         * 操作类型：门店信息管理
         */
        public final static String STORE_MANAGE = "门店信息管理";

        /**
         * 操作类型：订单信息管理
         */
        public final static String ORDER_MANAGE = "订单信息管理";

        /**
         * 操作类型：推广管理-推广人员管理"
         */
        public final static String EXT_PERSON_MANAGE_ = "推广管理-推广人员管理";

        /**
         * 操作类型：推广管理-推广信息管理
         */
        public final static String EXT_INFO_MANAGE = "推广管理-推广信息管理";

    }

    /***********************************    运营操作类型 end  ********************************************/


}
