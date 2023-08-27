package com.vteam.cars.util;

import com.vteam.cars.exception.MSSmeBusinessException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.lang.Nullable;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 断言类 .<br>
 *
 * @author andy.sher
 * @date 2018/8/6 17:49
 */
public final class SmeAssert {

    private SmeAssert() {
    }

    /**
     * EMAIL 正则表达式
     */
    public static final String EMAIL_REG = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    /**
     * 手机号 正则表达式
     */
    private static final String PHONE_NUMBER_REG = "(^1[3|4|5|6|7|8|9]\\d{9}$)|(^09\\d{8}$)";

    /**
     * 非空 .<br>
     *
     * @param t       对象
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/6 17:50
     */
    public static <T> void notNull(T t, String message) {
        notNull(t, message, null);
    }

    /**
     * 非空 .<br>
     *
     * @param t       对象
     * @param message 错误信息
     * @param model   填充对象
     * @author andy.sher
     * @date 2018/8/6 17:50
     */
    public static <T> void notNull(T t, String message, Map<String, String> model) {
        doAssert((null == t), message, model);
    }

    /**
     * 数据一定存在 .<br>
     *
     * @param t       对象
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/6 17:50
     */
    public static <T> void existData(T t, String message) {
        existData(t, message, null);
    }

    /**
     * 数据一定存在 .<br>
     *
     * @param t       对象
     * @param message 错误信息
     * @param model   填充对象
     * @author andy.sher
     * @date 2018/8/6 17:50
     */
    public static <T> void existData(T t, String message, Map<String, String> model) {
        doAssert(null == t, message, model);
    }

    /**
     * 为空 .<br>
     *
     * @param t       对象
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/6 17:50
     */
    public static <T> void isNull(T t, String message) {
        isNull(t, message, null);
    }

    /**
     * 为空 .<br>
     *
     * @param t       对象
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/6 17:50
     */
    public static <T> void isNull(T t, String message, Map<String, String> model) {
        doAssert(null != t, message, model);
    }

    /**
     * 不为空字符 .<br>
     *
     * @param str     字符串
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/6 17:50
     */
    public static void notBlank(String str, String message) {
        doAssert(StringUtils.isBlank(str), message);
    }

    /**
     * 为空字符 .<br>
     *
     * @param str     字符串
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/6 17:50
     */
    public static void isBlank(String str, String message) {
        doAssert(StringUtils.isNotBlank(str), message);
    }

    /**
     * 字符串相等
     *
     * @param srr1    字符串1
     * @param str2    字符串2
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/24 13:10
     */
    public static void eq(@NotNull String srr1, @NotNull String str2, String message) {
        eq(srr1, str2, message, null);
    }

    /**
     * 字符串相等
     *
     * @param srr1    字符串1
     * @param str2    字符串2
     * @param message 错误信息
     * @param model   填充数据
     * @author andy.sher
     * @date 2018/8/24 13:10
     */
    public static void eq(@NotNull String srr1, @NotNull String str2, String message, Map<String, String> model) {
        doAssert(!srr1.equals(str2), message, model);
    }

    /**
     * 字符串相等（忽略大小写） .
     *
     * @param srr1    字符串1
     * @param str2    字符串2
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/24 13:10
     */
    public static void equalsIgnoreCase(@NotNull String srr1, @NotNull String str2, String message) {
        doAssert(!srr1.equalsIgnoreCase(str2), message);
    }

    /**
     * 日期1在日期2之后 .
     *
     * @param date1   日期1
     * @param date2   日期2
     * @param message 错误信息
     * @author zack.yin
     * @date 2018/8/28 13:39
     */
    public static void after(@NotNull LocalDateTime date1, @NotNull LocalDateTime date2, String message) {
        doAssert(!date1.isAfter(date2), message);
    }

    /**
     * 日期1在日期2之后 .
     *
     * @param date1   日期1
     * @param date2   日期2
     * @param message 错误信息
     * @author zack.yin
     * @date 2018/8/28 13:39
     */
    public static void after(@NotNull LocalDate date1, @NotNull LocalDate date2, String message) {
        doAssert(!date1.isAfter(date2), message);
    }

    /**
     * 日期1在日期2之前 .
     *
     * @param date1   日期1
     * @param date2   日期2
     * @param message 错误信息
     * @author zack.yin
     * @date 2018/8/28 13:44
     */
    public static void before(@NotNull LocalDateTime date1, @NotNull LocalDateTime date2, String message) {
        doAssert(!date1.isBefore(date2), message);
    }

    /**
     * 日期1小于等于日期2
     *
     * @param date1   日期1
     * @param date2   日期2
     * @param message 错误信息
     * @author jiangming.huang
     * @date 2019/4/18 17:30
     */
    public static void le(@NotNull LocalDateTime date1, @NotNull LocalDateTime date2, String message) {
        doAssert(date1.isAfter(date2), message);
    }

    /**
     * 日期1小于等于日期2
     *
     * @param date1   日期1
     * @param date2   日期2
     * @param message 错误信息
     * @author jiangming.huang
     * @date 2019/4/18 17:30
     */
    public static void le(@NotNull LocalDate date1, @NotNull LocalDate date2, String message) {
        doAssert(date1.isAfter(date2), message);
    }

    /**
     * 日期1等于日期2
     *
     * @param date1   日期1
     * @param date2   日期2
     * @param message 错误信息
     * @author jiangming.huang
     * @date 2019/4/18 17:30
     */
    public static void eq(@NotNull LocalDate date1, @NotNull LocalDate date2, String message) {
        doAssert(!date1.isEqual(date2), message);
    }

    /**
     * 小数1大于小数2 .
     *
     * @param num1    小数1
     * @param num2    小数2
     * @param message 错误信息
     * @author zack.yin
     * @date 2018/8/28 13:51
     */
    public static void gt(@NotNull BigDecimal num1, @NotNull BigDecimal num2, String message) {
        doAssert(num1.compareTo(num2) < 1, message);
    }

    /**
     * 数字1小于等于数字2
     *
     * @param num1    数字1
     * @param num2    数字2
     * @param message 错误信息
     * @author zack.yin
     * @date 2018/8/28 13:54
     */
    public static void le(@NotNull BigDecimal num1, @NotNull BigDecimal num2, String message) {
        doAssert(num1.compareTo(num2) > 0, message);
    }

    /**
     * 数字1小于数字2 .
     *
     * @param num1
     * @param num2
     * @param message
     * @author fu.tong
     * @date 2018/9/18 14:37
     */
    public static void lt(@NotNull BigDecimal num1, @NotNull BigDecimal num2, String message) {
        doAssert(num1.compareTo(num2) > -1, message);
    }

    /**
     * 小数1等于小数2 .
     *
     * @param num1    小数1
     * @param num2    小数2
     * @param message 错误信息
     * @author zack.yin
     * @date 2018/8/28 13:59
     */
    public static void eq(@NotNull BigDecimal num1, @NotNull BigDecimal num2, String message) {
        doAssert(num1.compareTo(num2) != 0, message);
    }

    /**
     * 验证电子邮箱格式
     *
     * @param field
     * @param message
     * @author zack.yin
     * @date 2018/7/23 18:59
     */
    public static void isEmail(@Nullable String field, String message) {
        // 复杂匹配
        Pattern p = Pattern.compile(EMAIL_REG);
        Matcher m = p.matcher(field);
        doAssert(!m.matches(), message);
    }

    /**
     * 验证手机号格式
     *
     * @param field
     * @param message
     * @author zack.yin
     * @date 2018/7/24 9:07
     */
    public static void isPhoneNum(@Nullable String field, String message) {
        doAssert(StringUtils.isBlank(field), message);
        Pattern p = Pattern.compile(PHONE_NUMBER_REG);
        Matcher m = p.matcher(field);
        doAssert(!m.matches(), message);
    }

    /**
     * 参数1必须大于参数2 .
     *
     * @param param1  参数1
     * @param param2  参数2
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/24 19:33
     */
    public static void gt(int param1, int param2, String message) {
        doAssert((param1 < param2 || param1 == param2), message);
    }

    /**
     * 参数1必须大于等于参数2 .
     *
     * @param param1  参数1
     * @param param2  参数2
     * @param message 错误信息
     * @author zack.yin
     * @date 2018/9/13 16:51
     */
    public static void ge(int param1, int param2, String message) {
        doAssert(param1 < param2, message);
    }

    /**
     * 数字1大于等于数字2 .
     *
     * @param b1
     * @param b2
     * @param message
     * @author fu.tong
     * @date 2019/3/7 10:03
     */
    public static void ge(@NotNull BigDecimal b1, @NotNull BigDecimal b2, String message) {
        doAssert(b1.compareTo(b2) < 0, message);
    }

    /**
     * 参数1必须小于等于参数2 .
     *
     * @param param1
     * @param param2
     * @param message
     * @author zack.yin
     * @date 2018/9/4 14:59
     */
    public static void le(int param1, int param2, String message) {
        le(param1, param2, message, null);
    }

    /**
     * 参数1必须小于等于参数2 .
     *
     * @param param1
     * @param param2
     * @param message
     * @param model
     * @author zack.yin
     * @date 2018/9/4 14:59
     */
    public static void le(int param1, int param2, String message, Map<String, String> model) {
        doAssert(param1 > param2, message, model);
    }

    /**
     * 数字相等 .
     *
     * @param param1  参数1
     * @param param2  参数2
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/27 19:17
     */
    public static void eq(int param1, int param2, String message) {
        doAssert(param1 != param2, message);
    }

    /**
     * 数字相等 .
     *
     * @param param1  参数1
     * @param param2  参数2
     * @param message 错误信息
     * @param model
     * @author andy.sher
     * @date 2018/8/27 19:17
     */
    public static void eq(int param1, int param2, String message, Map<String, String> model) {
        doAssert(param1 != param2, message, model);
    }

    /**
     * 数字不相等 .
     *
     * @param param1  参数1
     * @param param2  参数2
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/10/19 19:17
     */
    public static void ne(int param1, int param2, String message) {
        doAssert(param1 == param2, message);
    }

    /**
     * 字符串1和字符串2不相等 .
     *
     * @param str1    字符串1
     * @param str2    字符串2
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/8/31 10:16
     */
    public static void ne(@NotNull String str1, @NotNull String str2, String message) {
        doAssert(str1.equals(str2), message);
    }

    /**
     * 集合为空
     *
     * @param coll
     * @param message
     * @author jiangming.huang
     * @date 2018/9/3 20:21
     */
    public static <T> void isEmpty(Collection<T> coll, String message) {
        doAssert(CollectionUtils.isNotEmpty(coll), message);
    }

    /**
     * 集合不为空
     *
     * @param coll
     * @param message
     * @author jiangming.huang
     * @date 2018/9/3 20:21
     */
    public static <T> void notEmpty(Collection<T> coll, String message) {
        doAssert(CollectionUtils.isEmpty(coll), message);
    }

    /**
     * 数组为空
     *
     * @param array
     * @param message
     * @author jiangming.huang
     * @date 2018/12/4 0004 上午 11:00
     */
    public static <T> void isEmpty(final T[] array, String message) {
        doAssert(ArrayUtils.isNotEmpty(array), message);
    }

    /**
     * 数组不为空
     *
     * @param array
     * @param message
     * @author jiangming.huang
     * @date 2018/12/4 0004 上午 11:00
     */
    public static <T> void notEmpty(final T[] array, String message) {
        doAssert(ArrayUtils.isEmpty(array), message);
    }

    /**
     * 参数值为假 .
     *
     * @param param   参数
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/10/19 11:23
     */
    public static void isFalse(boolean param, String message) {
        isFalse(param, message, null);
    }

    /**
     * 参数值为假 .
     *
     * @param param   参数
     * @param message 错误信息
     * @param model   填充数据模型
     * @author andy.sher
     * @date 2018/10/19 11:23
     */
    public static void isFalse(boolean param, String message, Map<String, String> model) {
        doAssert(param, message, model);
    }

    /**
     * 参数值为真 .
     *
     * @param param   参数
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/10/19 11:23
     */
    public static void isTrue(boolean param, String message) {
        isFalse(!param, message);
    }

    /**
     * 参数值为真 .
     *
     * @param param   参数
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/10/19 11:23
     */
    public static void isTrue(boolean param, String message, Map<String, String> model) {
        isFalse(!param, message, model);
    }

    /**
     * 参数1必须小于参数2 .
     *
     * @param a       参数1
     * @param b       参数2
     * @param message 错误信息
     * @author andy.sher
     * @date 2018/10/19 11:23
     */
    public static void lt(int a, int b, String message) {
        doAssert(a >= b, message);
    }

    /**
     * 处理断言为真，则抛出异常
     *
     * @param bool
     * @param message
     */
    private static void doAssert(boolean bool, String message) {
        doAssert(bool, message, null);
    }

    /**
     * 处理断言为真，则抛出异常(含变量)
     *
     * @param bool
     * @param message
     * @param model
     * @author oscar.yu
     * @date 2019/10/15 17:59
     */
    private static void doAssert(boolean bool, String message, Map<String, String> model) {
        if (bool) {
            if (null == model) {
                throw new MSSmeBusinessException(message);
            } else {
                throw new MSSmeBusinessException(message, model);
            }
        }
    }
}
