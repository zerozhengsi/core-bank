package com.vteam.cars.constant;

/**
 * 对外服务接口常量类
 *
 * @author oscar.yu
 * @date 2020-3-9 09:40:32
 */
public class BusinessConstants {

    private BusinessConstants() {
    }

    /**
     * 自动登录资源地址
     */
    public static final String LOGIN_URI = "businessService";

    /**
     * 公共服务资源地址
     */
    public static final String SERVICE_URI = "api-service/businessService";

    /**
     * 数据导入条数最大量限制
     */
    public static final Integer IMPORT_LENGTH = 1500;

    /**
     * 接口服务参数及集合键值
     */
    public final class Key {

        /**
         * 请求参数-平台来源代号
         */
        public static final String REQ_SOURCEID = "sourceid";

        /**
         * 请求参数-接口代号
         */
        public static final String REQ_TYPE = "type";

        /**
         * 请求参数-加密的接口数据<br>
         * 返回参数-未加密的数据
         */
        public static final String REQ_DATA = "data";

        /**
         * 请求参数-加密后的对称秘钥信息，aes[key-iv]
         */
        public static final String REQ_AKV = "akv";

        /**
         * 返回参数-响应状态码
         */
        public static final String RESP_CODE = "code";

        /**
         * 返回参数-宝亚响应状态码
         */
        public static final String RESP_RESULT = "result";

        /**
         * 返回参数-宝亚响应代码
         */
        public static final String RESP_RESULT_CODE = "resultcode";

        /**
         * 返回参数-响应消息
         */
        public static final String RESP_MSG = "msg";

        /**
         * 返回Header参数-文件名称
         */
        public static final String HEADER_FILE_NAME = "fileName";

        /**
         * 返回Header参数-文件类型
         */
        public static final String HEADER_FILE_TYPE = "fileType";
    }

    /**
     * 接口公共字段 .<br>
     *
     * @author oscar.yu
     * @date 2019/6/10 13:12
     */
    public final class Field {

    }

    /**
     * 自动登录功能定义 .<br>
     *
     * @author oscar.yu
     * @date 2019/6/10 13:12
     */
    public final class LoginTo {

    }

    /**
     * 接口名称 .<br>
     *
     * @author oscar.yu
     * @date 2019/6/10 13:12
     */
    public final class Method {

    }



}
