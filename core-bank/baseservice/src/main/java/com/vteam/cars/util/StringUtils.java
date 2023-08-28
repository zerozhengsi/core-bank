package com.vteam.cars.util;

import com.vteam.cars.annotation.Sensitive;
import com.vteam.cars.constant.GlobalConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.constraints.NotNull;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理相关工具类
 *
 * @author oscar.yu
 * @version 1.0.0
 * @history 2012-6-25 oscar.yu 创建StringUtils.java
 */
public final class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * EMAIL 正则表达式
     */
    public static final String EMAIL_REG = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    /**
     * 手机号 正则表达式
     */
    private static final String PHONE_NUMBER_REG = "(^1[3|4|5|6|7|8|9]\\d{9}$)|(^09\\d{8}$)";

    private StringUtils() {
    }

    /**
     * 处理邮件中title数据
     * @param custname
     * @return
     */
    public static String protectData(String custname){
        String originalString = custname;
        String maskedString = custname;
        if(originalString.length()==2 || originalString.length()==3){
            maskedString = masking(originalString,1,2);
        } else if(originalString.length()==4){
            maskedString = masking(originalString,2,3);
        } else if(originalString.length()>4){
            maskedString = masking(originalString,2,originalString.length()-2);
        }
        return maskedString;
    }

    /**
     * 指定位置字符串替换成*号
     * @param originalString
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static String masking(String originalString,int startIndex, int endIndex ){
        // 获取需要替换的部分字符串
        String maskedSubstring = originalString.substring(startIndex, endIndex);

        // 使用循环将部分字符串中的字符替换为星号 *
//        StringBuilder maskedBuilder = new StringBuilder();
//        for (int i = 0; i < maskedSubstring.length(); i++) {
//            maskedBuilder.append('*');
//        }

        // 将部分字符串中的字符替换为星号 *
        String maskedString = originalString.replace(maskedSubstring, "*");

        return maskedString;
    }

    /**
     * 随机产生定长字符串
     *
     * @param length
     * @return RandomString
     */
    public static String getRandomString(final int length) {
        final String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        return RandomStringUtils.random(length, base);
    }

    /**
     * 随机产生定长数字字符串
     *
     * @param length
     * @return RandomString
     */
    public static String getRandomNumString(final int length) {
        final String base = "0123456789";
        return RandomStringUtils.random(length, base);
    }

    /**
     * 时间格式生成序列
     *
     * @return String
     */
    public static synchronized String generateSequenceNo() {
        // 获取时间字符串
        String nowStr = LocalDateTime.now().format(DateUtils.FMT_SECOND_N);
        // 获取3随机数
        String randomNum = getRandomNum(3);
        return nowStr + randomNum;
    }

    /**
     * 时间格式生成序列6位随机数
     *
     * @return String
     */
    public static synchronized String generateSequenceNoOfSix() {
        // 获取时间字符串
        String nowStr = LocalDateTime.now().format(DateUtils.FMT_SECOND_N);
        // 获取3随机数
        String randomNum = getRandomNum(6);
        return nowStr + randomNum;
    }

    /**
     * 根据时间YYMMDD+时分秒微秒(年份只取后两位数)获取对应编号
     *
     * @return
     * @author zhuang.shao
     * @date 2018年10月22日 下午5:45:29
     */
    public static synchronized String generateCurrentTimeNo() {
        // 获取时间字符串
        String currentDate = LocalDateTime.now().format(DateUtils.FMT_MILLISECOND_N);
        return currentDate.substring(2);
    }

    /**
     * 产生n位随机数
     *
     * @return
     */
    public static String getRandomNum(int n) {
        String randomStr = EMPTY;
        if (n <= 0) {
            return randomStr;
        }
        double random = Math.random();
        double pow = Math.pow(10, n - 1);
        Long randomNum = (long) ((random * 9 * pow) + (long) pow);
        return randomNum.toString();
    }

    /**
     * 填补字符串
     *
     * @param str      原字符串
     * @param length   长度
     * @param filler   填补字符
     * @param isAppend 是否加在末尾
     * @return java.lang.String
     * @author fu.tong
     * @date 2018/8/6  13:29
     */
    public static String fillStr(String str, int length, String filler, boolean isAppend) {
        if (null == str) {
            return space(filler, length);
        }
        if (str.length() > length) {
            return str;
        }
        String fillStr = space(filler, length - str.length());
        if (isAppend) {
            return str + fillStr;
        } else {
            return fillStr + str;
        }
    }

    /**
     * 填补字符串方法
     *
     * @param filler 填补字符串
     * @param len    长度
     * @return java.lang.String
     * @author fu.tong
     * @date 2018/8/6  13:31
     */
    public static String space(String filler, int len) {
        if (len <= 0) {
            return "";
        }
        if (StringUtils.isBlank(filler)) {
            filler = GlobalConstants.Symbol.SPACE;
        }
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < len; i++) {
            buff.append(filler);
        }

        return buff.toString();
    }

    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。<br>
     * 例如：HelloWorld->HELLO_WORLD
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String camelToUnderLine(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。<br>
     * 例如：HELLO_WORLD->helloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String underLineToCamel(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (null == name || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 获取系统文件路径分隔符
     *
     * @return
     * @author zhuang.shao
     * @date 2018年9月6日 下午2:02:24
     */
    public static String getSysSeparator() {
        if (System.getProperty(GlobalConstants.Flag.OS_NAME).toLowerCase().startsWith(GlobalConstants.Flag.WIN)) {
            return File.separator + File.separator;
        } else {
            return File.separator;
        }
    }

    /**
     * 去除HTML标签 .
     *
     * @param content 内容
     * @return java.lang.String 去除HTML标签后的内容
     * @author andy.sher
     * @date 2018/11/15 16:59
     */
    public static String stripHtml(String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        // 去掉其它&;
        content = content.replaceAll("&\\w+?;", "");
        return content;
    }

    /**
     * 获取文件后缀名 .
     *
     * @param fileName 文件名
     * @return java.lang.String 文件后缀名
     * @author andy.sher
     * @date 2018/11/15 11:22
     */
    public static String getFileNameSuffix(String fileName) {
        String sufix = fileName.substring((fileName.indexOf(GlobalConstants.Symbol.SPOT) + 1));
        return sufix;
    }

    /**
     * 截取字符串，不足补充字符
     * @param input   要处理的字符串
     * @param length  要获取的后缀长度
     * @param paddingChar  补字符
     * @return
     */
    public static String getSuffixWithPadding(String input, int length, char paddingChar) {
        if(isBlank(input)){
            return "";
        }
        if (input.length() >= length) {
            return input.substring(input.length() - length);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length - input.length(); i++) {
                sb.append(paddingChar);
            }
            sb.append(input);
            return sb.toString();
        }
    }

    /**
     * 将传入的字符串数据组成连接成字符串，“_”分隔
     *
     * @param strArray
     * @return
     */
    public static String getMapKey(Object... strArray) {
        return join(strArray, GlobalConstants.Symbol.UNDERLINE);
    }

    /**
     * 将传入的字符串数据组成连接成字符串，“:”分隔
     *
     * @param strArray
     * @return
     */
    public static String getRedisKey(Object... strArray) {
        return join(strArray, GlobalConstants.Symbol.SEMICOLON);
    }

    /**
     * 将传入的字符串数据组成连接成字符串，“,”分隔
     *
     * @param strArray
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2018/11/27 0027 下午 2:15
     */
    public static String getCommaSeparated(String... strArray) {
        return joinNotEmpty(strArray, GlobalConstants.Symbol.COMMA);
    }

    /**
     * 将传入的字符串数据组成连接成字符串，分隔符参数分隔
     *
     * @param array
     * @param separator
     * @return
     */
    public static String joinNotEmpty(final Object[] array, String separator) {
        if (null == array || array.length == 0) {
            return EMPTY;
        }
        if (null == separator) {
            separator = EMPTY;
        }
        final int startIndex = 0;
        final int endIndex = array.length;
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        int notEmptyCount = 0;
        for (int i = startIndex; i < endIndex; i++) {
            final Object str = array[i];
            if (null == str || isEmpty(str.toString())) {
                continue;
            }
            if (i > startIndex && notEmptyCount > 0) {
                buf.append(separator);
            }
            buf.append(str);
            notEmptyCount++;
        }
        return buf.toString();
    }

    /**
     * 校验是否为url地址 .
     *
     * @param url
     * @return boolean
     * @author fu.tong
     * @date 2018/11/30 09:37
     */
    public static boolean regexUrl(String url) {
        boolean isUrl = false;
        String regex = "[a-zA-z]+://[^\\s]*";
        Pattern pat = Pattern.compile(regex.trim());
        Matcher mat = pat.matcher(url.trim());
        if (mat.matches()) {
            isUrl = true;
        }
        return isUrl;
    }

    /**
     * 清除全角空格 .
     *
     * @param text 原文本
     * @return java.lang.String 清除空格后的文本
     * @author andy.sher
     * @date 2018/12/14 17:05
     */
    public static String clearDoubleByteSpace(@NotNull String text) {
        return text.replaceAll(" ", EMPTY);
    }

    /**
     * 企业/项目名称加密 .
     *
     * @param str
     * @return java.lang.String
     * @author fu.tong
     * @date 2019/1/31 15:07
     */
    public static String cutProject(String str) {
        // 加密最小
        final int len = 7;
        String cutStr = EMPTY;
        if (StringUtils.isNotBlank(str)) {
            String star = GlobalConstants.Symbol.ASTERISK + GlobalConstants.Symbol.ASTERISK + GlobalConstants.Symbol.ASTERISK;
            if (str.length() >= len) {
                cutStr = str.substring(0, 1) + star + str.substring(5, 7) + star;
            } else if (str.length() < len) {
                cutStr = str.substring(0, 1) + star;
            }
        }
        return cutStr;
    }

    /**
     * 拼接两串URL地址,根据分隔符 '/'
     *
     * @param oneUrl 地址前段
     * @param twoUrl 地址后段
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/3/6 11:55
     */
    public static String spliceUrl(String oneUrl, String twoUrl) {
        if (StringUtils.isBlank(twoUrl)) {
            return oneUrl;
        }
        // 拼接后的地址
        String spliceUrl = StringUtils.EMPTY;
        // 截取oneUrl的最后一个字符串
        String urlLast = oneUrl.substring(oneUrl.length() - 1);
        // 截取twoUrl的第一个字符串
        String urlFirst = twoUrl.substring(0, 1);
        if (GlobalConstants.Symbol.SLASH.equals(urlLast)) {
            if (GlobalConstants.Symbol.SLASH.equals(urlFirst)) {
                oneUrl = oneUrl.substring(0, oneUrl.length() - 2);
                spliceUrl = oneUrl + twoUrl;
            } else {
                spliceUrl = oneUrl + twoUrl;
            }
        } else {
            if (GlobalConstants.Symbol.SLASH.equals(urlFirst)) {
                spliceUrl = oneUrl + twoUrl;
            } else {
                spliceUrl = oneUrl + GlobalConstants.Symbol.SLASH + twoUrl;
            }
        }
        return spliceUrl;
    }

    /**
     * 拼接地址参数,根据分隔符 '?'
     *
     * @param url        地址
     * @param paramName  参数名称
     * @param paramValue 参数值
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/3/6 11:53
     */
    public static String spliceUrlParam(String url, String paramName, String paramValue) {
        StringBuilder urlBuffer = new StringBuilder();
        urlBuffer.append(url);
        // 拼接请求地址参数
        String urlLast = url.substring(url.length() - 1);
        if (GlobalConstants.Symbol.QUESTION_EN.equals(urlLast)) {
            urlBuffer.append(paramName).append(GlobalConstants.Symbol.EQUAL_MARK).append(paramValue);
        } else {
            urlBuffer.append(GlobalConstants.Symbol.QUESTION_EN).append(paramName).append(GlobalConstants.Symbol.EQUAL_MARK).append(paramValue);
        }
        return urlBuffer.toString();
    }

    /**
     * 转换为字符串
     *
     * @param data
     * @return
     */
    public static String toUtf8String(byte[] data) {
        String result = EMPTY;
        try {
            result = new String(data, StandardCharsets.UTF_8);
        } catch (Exception err) {
        }
        return result;
    }

    /**
     * 验证字符串是否是电子邮箱 .
     *
     * @param str 字符串
     * @return boolean 是否是电子邮箱
     * @author andy.sher
     * @date 2019/9/25 11:25
     */
    public static boolean isEmail(String str) {
        // 复杂匹配
    	if (StringUtils.isEmpty(str)) {
    		return false;
    	} else {
	        Pattern p = Pattern.compile(EMAIL_REG);
	        Matcher m = p.matcher(str);
	        if (!m.matches()) {
	            return false;
	        }
	        return true;
    	}
    }

    /**
     * 判断字符串是否是手机号 .
     *
     * @param str 字符串
     * @return boolean 是否是手机号
     * @author andy.sher
     * @date 2019/9/25 11:25
     */
    public static boolean isPhoneNum(String str) {
    	if (StringUtils.isEmpty(str)) {
    		return false;
    	} else {
	        Pattern p = Pattern.compile(PHONE_NUMBER_REG);
	        Matcher m = p.matcher(str);
	        if (!m.matches()) {
	            return false;
	        }
	        return true;
    	}
    }

    /**
     * 字符串脱敏 .
     *
     * @param target 包含敏感字符的对象
     * @param ignore 忽略的字段
     * @return
     * @author andy.sher
     * @date 2019/9/9 13:48
     */
    public static <T> void removeSensitive(T target, String... ignore) {
        Sensitive sensitive = target.getClass().getAnnotation(Sensitive.class);
        if (null == sensitive || ArrayUtils.isEmpty(sensitive.fields())) {
            return;
        }
        String[] fields = sensitive.fields();
        List<String> ignoreList = null;
        if (ArrayUtils.isNotEmpty(ignore)) {
            ignoreList = Arrays.asList(ignore);
        }
        BeanWrapper beanWrapper = new BeanWrapperImpl(target);
        for (int i = 0, len = fields.length; i < len; i++) {
            if (CollectionUtils.isEmpty(ignoreList) || !ignoreList.contains(fields[i])) {
                final Object fieldValue = beanWrapper.getPropertyValue(fields[i]);
                if (null != fieldValue && StringUtils.isNotBlank(fieldValue.toString()) && fieldValue instanceof String) {
                    String res = fieldValue.toString();
                    // 保留与V2.0一致的脱敏逻辑
                    res = cutProject(res);
                    beanWrapper.setPropertyValue(fields[i], res);
                }
            }
        }
    }

    /**
     * 填充表达式模板 .
     *
     * @param text   含有表达式的字符串（例如${year}年${month}月${days}日）
     * @param params 模板填充值（例如{year: 2019, month: 11, days:5}）
     * @return java.lang.String 填充后的字符串（2019年11月5日）
     * @author andy.sher
     * @date 2019/11/5 15:17
     */
    public static String full(String text, Map<String, String> params) {
        StringSubstitutor substitutor = new StringSubstitutor(params);
        return substitutor.replace(text);
    }

    /**
     * 生成单证委托制单excel文件名称 .<br>
     *
     * @param documentTypeName 单证类型名称
     * @param vendorCompanyName 卖方企业名称
     * @return java.lang.String excel文件名称
     * @author shaoquan.wu
     * @date 2022/8/18 15:07:54
     */
    public static String createDocumentMakingExcelFileName(String documentTypeName, String vendorCompanyName, File file){
        String suffix = StringUtils.EMPTY;
        Integer companyNameLength = 60;
        if(file != null){
            String fileName = file.getName();
            int suffixIndex = fileName.lastIndexOf(GlobalConstants.Symbol.SPOT);
            if (suffixIndex > 0) {
                suffix = fileName.substring((suffixIndex + 1));
            }
        }
        if (vendorCompanyName.length() > companyNameLength) {
            vendorCompanyName = vendorCompanyName.substring(0,companyNameLength);
        }
        return documentTypeName + GlobalConstants.Symbol.MINUS + vendorCompanyName + GlobalConstants.Symbol.LEFT_BRACKET
                + LocalDateTime.now().format(DateUtils.FMT_DATE_N) + GlobalConstants.Symbol.RIGHT_BRACKET
                + GlobalConstants.Symbol.SPOT + suffix;

    }
}
