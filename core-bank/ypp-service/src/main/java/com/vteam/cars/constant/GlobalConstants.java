package com.vteam.cars.constant;

/**
 * 全局常量类 .<br>
 *
 * @author andy.sher
 * @date 2018/7/24 15:13
 */
public final class GlobalConstants {

    private GlobalConstants() {
    }

    /**
     * 分页起始页数
     */
    public static final Integer PAGE_NUM_START = 1;

    /**
     * 字符编码 .
     *
     * @author andy.sher
     * @date 2018/8/24 13:31
     */
    public final class Character {

        /**
         * 万国码
         */
        public static final String UTF_8 = "UTF-8";

        /**
         * 汉字内码扩展规范
         */
        public static final String GBK = "GBK";

        /**
         * 汉字内码扩展规范
         */
        public static final String GB2312 = "GB2312";

        /**
         * ASCII
         */
        public static final String ASCII = "ASCII";
    }

    /**
     * 文件格式 .
     *
     * @author andy.sher
     * @date 2018/8/24 9:09
     */
    public final class FileFormat {

        /**
         * txt
         */
        public static final String TXT = "txt";

        /**
         * jpg
         */
        public static final String JPG = "jpg";

        /**
         * PNG
         */
        public static final String PNG = "png";

        /**
         * bmp
         */
        public static final String BMP = "bmp";

        /**
         * gif
         */
        public static final String GIF = "gif";

        /**
         * 图片格式
         */
        public static final String JPEG = "jpeg";

        /**
         * 压缩包格式
         */
        public static final String ZIP = "zip";

        /**
         * Freemarker模板格式
         */
        public static final String FTL = "ftl";

        /**
         * doc
         */
        public static final String DOC = "doc";

        /**
         * docx
         */
        public static final String DOCX = "docx";

        /**
         * pdf
         */
        public static final String PDF = "pdf";

        /**
         * tif
         */
        public static final String TIF = "tif";

        /**
         * xls
         */
        public static final String XLS = "xls";

        /**
         * xlsx
         */
        public static final String XLSX = "xlsx";

        /**
         * pfx
         */
        public static final String PFX = "pfx";

        /**
         * file
         */
        public static final String FILE = "file";

        /**
         * webp
         */
        public static final String WEBP = "webp";

        /**
         * html
         */
        public static final String HTML = "html";

        /**
         * php
         */
        public static final String PHP = "php";

        /**
         * jsp
         */
        public static final String JSP = "jsp";

        /**
         * exe
         */
        public static final String EXE = "exe";
    }

    /**
     * 符号 .<br>
     *
     * @author andy.sher
     * @date 2018/8/7 13:29
     */
    public final class Symbol {

        /**
         * @
         */
        public static final String AT = "@";

        /**
         * 逗号
         */
        public static final String COMMA = ",";

        /**
         * 减号
         */
        public static final String MINUS = "-";

        /**
         * 空格
         */
        public static final String SPACE = " ";

        /**
         * 点
         */
        public static final String SPOT = ".";

        /**
         * 正斜杠
         */
        public static final String SLASH = "/";

        /**
         * 反斜杠
         */
        public static final String BACKSLASH = "\\";

        /**
         * 下划线
         */
        public static final String UNDERLINE = "_";

        /**
         * 左中括号
         */
        public static final String LEFT_SQUARE_BRACKET = "[";

        /**
         * 右中括号
         */
        public static final String RIGHT_SQUARE_BRACKET = "]";

        /**
         * 全角左中括号
         */
        public static final String DOUBLE_BYTE_LEFT_SQUARE_BRACKET = "［";

        /**
         * 全角右中括号
         */
        public static final String DOUBLE_BYTE_RIGHT_SQUARE_BRACKET = "］";

        /**
         * 星号
         */
        public static final String ASTERISK = "*";

        /**
         * 英文问号
         */
        public static final String QUESTION_EN = "?";

        /**
         * 顿号
         */
        public static final String DAWN = "、";

        /**
         * 全角空格
         */
        public static final String DOUBLE_BYTE_SPACE = " ";

        /**
         * 等号
         */
        public static final String EQUAL_MARK = "=";

        /**
         * 英文加号
         */
        public static final String PLUS = "+";

        /**
         * 左括号
         */
        public static final String LEFT_BRACKET = "(";

        /**
         * 右括号
         */
        public static final String RIGHT_BRACKET = ")";

        /**
         * 分号
         */
        public static final String SEMICOLON = ":";

        /**
         * 百分号
         */
        public static final String PERCENT_SIGN = "%";

        /**
         * 句号
         */
        public static final String PERCENT_STOP = "。";

        /**
         * 中文逗号
         */
        public static final String COMMA_CN = "，";

        /**
         * 冒号
         */
        public static final String COLON = ":";

        /**
         * 花括号开
         */
        public static final String CURLY_BRACES_OPEN = "{";

        /**
         * 花括号闭
         */
        public static final String CURLY_BRACES_CLOSE = "}";

        /**
         * 美元符号
         */
        public static final String DOLLAR = "$";

        /**
         * and符号
         */
        public static final String AND = "&";
        
        /**
         * #符号
         */
        public static final String COMMENT_SIGN = "#";
        
        /**
         * 换行符号
         */
        public static final String LINE_BREAK = "\\n";

        /**
         * 左方头括号
         */
        public static final String LEFT_BOLDFACE_SQUARE_BRACKETS = "【";

        /**
         * 右方头括号
         */
        public static final String RIGHT_BOLDFACE_SQUARE_BRACKETS = "】";

        /**
         * 中文感叹号
         */
        public static final String EXCLAMATION_MARK_CN = "！";

        /**
         * 英文感叹号
         */
        public static final String EXCLAMATION_MARK_EN = "!";

    }

    /**
     * 数据库 .<br>
     *
     * @author andy.sher
     * @date 2018/7/31 13:34
     */
    public final class DbType {
        /**
         * MYSQL数据库
         */
        public static final String SYSTEM_DB_MYSQL = "MYSQL";

        /**
         * ORACLE数据库
         */
        public static final String SYSTEM_DB_ORACLE = "ORACLE";

        /**
         * MSSQL数据库
         */
        public static final String SYSTEM_DB_SQLSERVER = "MSSQL";

        /**
         * 浪潮KDB数据库
         */
        public static final String SYSTEM_DB_INSPUR_KDB = "KDB";

        /**
         * DB2数据库
         */
        public static final String SYSTEM_DB_DB2 = "DB2";
    }

    /**
     * 标记 .<br>
     *
     * @author andy.sher
     * @date 2018/7/31 13:34
     */
    public final class Flag {
        /**
         * 标记1
         */
        public static final String FLAG_ONE = "1";
        /**
         * 标记2
         */
        public static final String FLAG_TWO = "2";
        /**
         * 标记3
         */
        public static final String FLAG_THREE = "3";
        /** 标记A */
        public static final String FLAG_A = "A";
        /** 标记B */
        public static final String FLAG_B = "B";
        /** 标记C */
        public static final String FLAG_C = "C";
        /** 标记D */
        public static final String FLAG_D = "D";
        /** 标记E */
        public static final String FLAG_E = "E";
        /** 标记F */
        public static final String FLAG_F = "F";
        /** 标记G */
        public static final String FLAG_G = "G";
        /** 标记h */
        public static final String FLAG_H = "H";
        /** 标记I */
        public static final String FLAG_I = "I";
        /** 标记J */
        public static final String FLAG_J = "J";
        /** 标记K */
        public static final String FLAG_K = "K";
        /** 标记L */
        public static final String FLAG_L = "L";
        /** 标记M */
        public static final String FLAG_M = "M";
        /** 标记N */
        public static final String FLAG_N = "N";
        /** 标记R */
        public static final String FLAG_R = "R";

        /**
         * 真
         */
        public static final String TRUE = "1";

        /**
         * 假
         */
        public static final String FALSE = "0";

        /**
         * Y
         */
        public static final String YES = "Y";

        /**
         * N
         */
        public static final String NO = "N";

        /**
         * token
         */
        public static final String TOKEN = "access_token";
        /**
         * sourceIp
         */
        public static final String SOURCE_IP = "sourceIp";

        /**
         * UUID
         */
        public static final String UUID = "uuid";

        /** loginId */
        public static final String LOGIN_ID = "loginId";

        /**
         * 手机号
         */
        public static final String MOBILEPHONE = "mobilephone";

        /**
         * 验证码
         */
        public static final String VERIFICATION_CODE = "verificationCode";

        /**
         * 结果
         */
        public static final String RESULT = "result";
        /**
         * 用户登录
         */
        public static final String LOGIN = "login";
        /**
         * 用户登出
         */
        public static final String LOGOUT = "logout";
        /**
         * UEKY用户登录
         */
        public static final String LOGIN_UKEY = "login_ukey";

        /**
         * 生产环境
         */
        public static final String PROD = "prod";
        /**
         * 成功
         */
        public static final String SUCCESS = "success";
        /**
         * 过期
         */
        public static final String EXPIRED = "expired";
        /**
         * 无效
         */
        public static final String INVALID = "invalid";
        /**
         * 文件
         */
        public static final String FILE = "file";
        /**
         * 文件名称
         */
        public static final String FILE_NAME = "fileName";
        /**
         * fileType
         */
        public static final String FILE_TYPE = "fileType";

        /**
         * redis
         */
        public static final String REDIS = "redis";
        /**
         * 企业名称
         */
        public static final String ORGNAME = "orgname";
        /**
         * 类名
         */
        public static final String CLASS_NAME = "className";
        /**
         * Java文件后缀
         */
        public static final String JAVA_FILE_SUFFIX = ".java";
        /**
         * user.dir
         */
        public static final String USER_DIR = "user.dir";

        /**
         * 用户admin
         */
        public static final String ADMIN = "admin";

        /**
         * Key
         */
        public static final String KEY = "key";

        /** 验证码类型 */
        public static final String CODE_TYPE = "codeType";

        /**
         * companyCreditCode
         */
        public static final String COMPANY_CREDIT_CODE = "companyCreditCode";

        /**
         * 数据失效
         */
        public static final String SME_DATA_IS_INVALID = "SME:DataIsInvalid.";

        /**
         * refcode
         */
        public static final String REFCODE = "refcode";

        /**
         * filecenter
         */
        public static final String FILE_ACTIVE_REMOTE = "filecenter";

        /**
         * local
         */
        public static final String FILE_ACTIVE_LOCAL = "local";

        /**
         * bkeyCode文件中心使用
         */
        public static final String BKEY_CODE = "bkeyCode";

        /**
         * model
         */
        public static final String MODEL = "model";


        /** Base64编码的公钥证书内容 */
        public static final String CERT_CONTENT = "certContent";

        /**
         * PDF签章计算HASH值_保留值标识ID
         */
        public static final String PDF_HASH_ID = "ID";

        /**
         * PDF签章计算HASH值_PKCS#7签名后的值
         */
        public static final String PDF_HASH_SIGNATURE = "pdfHashSignature";

        /**
         * url
         */
        public static final String URL = "url";

        /**
         * null
         */
        public static final String NULL = "null";

        /**
         * ignore
         */
        public static final String IGNORE = "ignore";

        /**
         * PC端
         */
        public static final String PC = "pc";

        /**
         * IOS端
         */
        public static final String IOS = "ios";

        /**
         * android端
         */
        public static final String AD = "ad";

        /**
         * 手机端（微信小程序端）
         */
        public static final String XCX = "xcx";

        /**
         * 手机html端
         */
        public static final String WAP = "wap";

        /**
         * 登录中文
         */
        public static final String LOGIN_CN = "登录";

        /**
         * 退出中文
         */
        public static final String LOGOUT_CN = "退出";

        /**
         * 成功中文
         */
        public static final String SUCCESS_CN = "成功";

        /**
         * 文件hash
         */
        public static final String HASH = "hash";

        /**
         * 操作系统名称
         */
        public static final String OS_NAME = "os.name";

        /**
         * win
         */
        public static final String WIN = "win";

        /**
         * 水印
         */
        public static final String WATERMARK = "watermark";

        /**
         * 整合文件成功目录
         */
        public static final String SUCCESS_DIR = "success";
        /**
         * 整合文件失败目录
         */
        public static final String FAILURE_DIR = "failure";

        /**
         * 上传数据整合对应数据文件名称
         */
        public static final String UPLOAD_DATA_FILE_NAME = "DATA_INTEGRATION";
        /**
         * 上传数据整合对应数据文件名称
         */
        public static final String UPLOAD_DATA_FILE_SUFFIX = ".data";

        /**
         * 上传数据整合对应数据文件名称
         */
        public static final String UPLOAD_DIR = "upload";

        /**
         * 上传数据整合对应数据文件名称
         */
        public static final String VERSION_THREE = "3.0";

        /**
         * email
         */
        public static final String EMAIL = "email";

        /**
         * image
         */
        public static final String IMAGE = "image";

        /**
         * ok
         */
        public static final String OK = "ok";

        /**
         * 开发
         */
        public static final String DEV = "dev";

        /**
         * 测试
         */
        public static final String TEST = "test";

        /**
         * 演示
         */
        public static final String PREV = "prev";

        /** 验证码类型[0=图形/1=短信/2=邮件] */
        public static final String CODE_TYPE_IMAGE = "0";

        /** 验证码类型[0=图形/1=短信/2=邮件] */
        public static final String CODE_TYPE_PHONE = "1";

        /** 验证码类型[0=图形/1=短信/2=邮件] */
        public static final String CODE_TYPE_EMAIL = "2";
    }

    /**
     * 日期格式常量类 .
     *
     * @author andy.sher
     * @date 2018/8/14 14:27
     */
    public final class DateFormat {
        public static final String TO_MONTH = "yyyy-MM";

        public static final String TO_DATE = "yyyy-MM-dd";

        public static final String TO_MINUTE = "yyyy-MM-dd HH:mm";

        public static final String TO_SECOND = "yyyy-MM-dd HH:mm:ss";

        public static final String TO_MILLISECOND = "yyyy-MM-dd HH:mm:ss SSSS";

        public static final String TO_SECOND_ENG = "EEE MMM dd HH:mm:ss zzz yyyy";

        public static final String TO_MONTHDATE = "MM-dd";

        public static final String TO_MONTH_N = "yyyyMM";

        public static final String TO_DATE_N = "yyyyMMdd";

        public static final String TO_HOUR_N = "yyyyMMddHH";

        public static final String FMT_SLASH = "yyyy/MM/dd";

        public static final String TO_MINUTE_N = "yyyyMMddHHmm";

        public static final String TO_SECOND_N = "yyyyMMddHHmmss";

        public static final String TO_MILLISECOND_N = "yyyyMMddHHmmssSSSS";

        public static final String TO_SHORT_MILLISECOND_N = "yyMMddHHmmssSSSS";

        public static final String TO_HMS_N = "HHmmss";

        public static final String YEAR = "y";

        public static final String MONTH = "m";

        public static final String DAY = "d";

        public static final String TO_DATE_SLASH = "yyyy/M/d";

        public static final String HOUR_N = "H";

        public static final String HOUR = "HH";

        public static final String TO_DATE_SIMPLE = "yyyy年MM月dd日";
        
        public static final String TO_DATE_ENGLISH = "MMM.dd,yyyy";
    }

    /**
     *
     * zip常量. <br>
     *
     * @author olivia.liu
     * @date 2022年12月5日 上午11:12:40
     */
    public final class Zip {
        public static final int BUFFER_LENGTH = 1024 * 1024;
    }

    /**
     *
     * excel常量. <br>
     *
     * @author olivia.liu
     * @date 2022年10月26日 下午5:12:51
     */
    public final class Excel {
        /**
         * 像素点值
         */
        public static final int TO_POINTS = 256;

        /**
         * 10号字体
         */
        public static final int FONT_SIZE_TEN = 10;

        /**
         * 图片-最大x坐标值
         */
        public static final int MAX_COORD_X_SIZE = 1023;

        /**
         * 图片-最大y坐标值
         */
        public static final int MAX_COORD_Y_SIZE = 255;

        /**
         * 中文字符
         */
        public static final String FONT_CN = "[\u4e00-\u9fa5]";
    }


    /**
     * 语言 .
     *
     * @author andy.sher
     * @date 2018/10/19 17:08
     */
    public final class Language {
        public static final String zh_CN = "zh_CN";
        public static final String zh_TW = "zh_TW";
        public static final String en_US = "en_US";
    }

    /**
     * 币种
     *
     * @author jiangming.huang
     * @date 2019/4/25 17:32
     */
    public final class Ccyid {

        /**
         * 人民币
         */
        public static final String CCYID_RMB = "RMB";

        /**
         * 人民币描述
         */
        public static final String CCYID_RMB_DESCRIBE = "人民币";
    }

    /**
     * 英文字母表
     */
    public final class EnglishAlphabet {

        public static final String A = "A";
        public static final String B = "B";
        public static final String C = "C";
        public static final String D = "D";
        public static final String E = "E";
        public static final String F = "F";
        public static final String G = "G";
        public static final String H = "H";
        public static final String I = "I";
        public static final String J = "J";
        public static final String K = "K";
        public static final String L = "L";
        public static final String M = "M";
        public static final String N = "N";
        public static final String O = "O";
        public static final String P = "P";
        public static final String Q = "Q";
        public static final String R = "R";
        public static final String S = "S";
        public static final String T = "T";
        public static final String U = "U";
        public static final String V = "V";
        public static final String W = "W";
        public static final String X = "X";
        public static final String Y = "Y";
        public static final String Z = "Z";

    }

    /**
     * 阿拉伯字母
     */
    public final class ArabicNumeral {

        public static final String N0 = "0";
        public static final String N1 = "1";
        public static final String N2 = "2";
        public static final String N3 = "3";
        public static final String N4 = "4";
        public static final String N5 = "5";
        public static final String N6 = "6";
        public static final String N7 = "7";
        public static final String N8 = "8";
        public static final String N9 = "9";

    }

    /**
     * 协议
     */
    public final class Protocol {

        public static final String HTTP = "http";

        public static final String HTTPS = "https";

        public static final String WS = "ws";

        public static final String WSS = "wss";

    }

    /**
     * 短信配置 .
     *
     * @author andy.sher
     * @date 2018/9/20 13:35
     */
    public final class Sms {
        private Sms() {
        }

        /**
         * 闪信通
         */
        public static final String SXT = "sxt";

        /**
         * 沃讯通
         */
        public static final String WXT = "wxt";

        /**
         * 阿里短信服务
         */
        public static final String ALISMS = "alisms";
    }

}
