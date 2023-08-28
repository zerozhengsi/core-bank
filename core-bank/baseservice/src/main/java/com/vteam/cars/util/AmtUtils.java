package com.vteam.cars.util;

import com.vteam.cars.annotation.AmtField;
import com.vteam.cars.constant.MathConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * 金额工具类 .<br>
 *
 * @author andy.sher
 * @date 2018/10/24 10:42
 */
@Slf4j
public class AmtUtils {

    private final static String comma = ",";
    private final static String decimal = ".";
    private final static String maskStr = "#";
    private final static String subtractionSign = "-";
    private final static String plusSign = "+";

    /**
     * 对象中的金额值乘以10000 .
     * <p>调用此方法的前提动作</p>
     * <ol>
     * <li>target必须是一个VO对象。</li>
     * <li>VO类头部必须声明#{@AmtField}注解。</li>
     * <li>将需要操作的字段声明到@AmtField的fields属性中。</li>
     * <li>如需要排除声明字段中的某些字段，将字段名称写到此方法的第二个参数中即可。</li>
     * </ol>
     *
     * @param target 需要转换金额对象
     * @param ignore 排除字段
     * @author andy.sher
     * @date 2018/10/24 10:44
     */
    public static void multiply10000(Object target, String... ignore) {
        calc10000(target, true, false, ignore);
    }

    /**
     * 对象中的金额值乘以10000,超过1万亿的数据，置为0
     * <p>调用此方法的前提动作</p>
     * <ol>
     * <li>target必须是一个VO对象。</li>
     * <li>VO类头部必须声明#{@AmtField}注解。</li>
     * <li>将需要操作的字段声明到@AmtField的fields属性中。</li>
     * <li>如需要排除声明字段中的某些字段，将字段名称写到此方法的第二个参数中即可。</li>
     * </ol>
     *
     * @param target 需要转换金额对象
     * @param ignore 排除字段
     * @author leo.xie
     * @date 2018/10/24 10:44
     */
    public static void multiply10000Zero(Object target, String... ignore) {
        calc10000(target, true, true, ignore);
    }

    /**
     * 对象中的金额值除以10000 .
     * <p>调用此方法的前提动作</p>
     * <ol>
     * <li>target必须是一个VO对象。</li>
     * <li>VO类头部必须声明#{@AmtField}注解。</li>
     * <li>将需要操作的字段生命的写到@AmtField的fields属性中。</li>
     * <li>如需要排除声明字段中的某些字段，将字段名称写到此方法的第二个参数中即可。</li>
     * </ol>
     *
     * @param target 需要转换金额对象
     * @param ignore 排除字段
     * @return void
     * @author andy.sher
     * @date 2018/10/24 10:44
     */
    public static void divide10000(Object target, String... ignore) {
        calc10000(target, false, false, ignore);
    }

    /**
     * 处理对象字段万元的计算
     * @param target 需要转换金额对象
     * @param multiply 乘法/除法
     * @param trillionToZero 超过1万亿的数据置为0
     * @param ignore 排除字段
     */
    public static void calc10000(Object target, boolean multiply, boolean trillionToZero, String... ignore) {
        if (null != target && target instanceof Serializable) {
            AmtField amtFields = target.getClass().getAnnotation(AmtField.class);
            if (null == amtFields || ArrayUtils.isEmpty(amtFields.fields())) {
                return;
            }
            String[] fields = amtFields.fields();
            List<String> ignoreList = null;
            if (ArrayUtils.isNotEmpty(ignore)) {
                ignoreList = Arrays.asList(ignore);
            }
            final BigDecimal hundredMillion = new BigDecimal("100000000"); // 一亿
            BeanWrapper beanWrapper = new BeanWrapperImpl(target);
            for (int i = 0, len = fields.length; i < len; i++) {
                if (CollectionUtils.isEmpty(ignoreList) || !ignoreList.contains(fields[i])) {
                    final Object fieldValue = beanWrapper.getPropertyValue(fields[i]);
                    if (null != fieldValue && fieldValue instanceof BigDecimal) {
                        BigDecimal fieldDecimal = (BigDecimal) fieldValue;
                        if (multiply) {
                            // 乘法，超过1万亿的数据，置为0
                            if (trillionToZero && fieldDecimal.compareTo(hundredMillion) >= 0) {
                                beanWrapper.setPropertyValue(fields[i], MathConstants.ZERO);
                            } else {
                                beanWrapper.setPropertyValue(fields[i], calc10000(fieldDecimal, true));
                            }
                        } else {
                            // 除法处理
                            beanWrapper.setPropertyValue(fields[i], calc10000(fieldDecimal, false));
                        }
                    }
                }
            }
        }
    }

    /**
     * 金额值乘以10000
     * @param decimal
     * @return
     */
    public static BigDecimal multiply10000(BigDecimal decimal) {
        return calc10000(decimal, true);
    }

    /**
     * 金额除以10000
     * @param decimal
     * @return
     */
    public static BigDecimal divide10000(BigDecimal decimal) {
        return calc10000(decimal, false);
    }

    /**
     * 处理金额万元的计算
     * @param decimal
     * @param multiply
     * @return
     */
    public static BigDecimal calc10000(BigDecimal decimal, boolean multiply) {
        BigDecimal result = MathConstants.ZERO;
        if (null != decimal) {
            if (multiply) {
                result = decimal.multiply(MathConstants.TEN_THOUSAND);
            } else {
                result = decimal.multiply(MathConstants.TEN_THOUSAND_percent).setScale(MathConstants.AMT_ROUNDING,
                        MathConstants.ROUND_MODE_UP);
            }
        }
        return result;
    }

    /**
     * 将数字格式化成保留两位小数的金额
     *
     * @param value
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2018/12/27 0027 下午 6:40
     */
    public static String formatMoney(BigDecimal value) {
        String ccyFmtStr = "###,###,###,###,###.##";
        if (value == null) {
            return "0.00";
        } else {
            BigDecimal amtDecimal = new BigDecimal(value.toString());
            return applyPattern(amtDecimal, ccyFmtStr);
        }
    }

    /**
     * 得到百分数的值的格式化的值
     *
     * @param1 百分值
     */
    public static String getPcntFmt(BigDecimal pcntValue) {
        String fmtStr = "";
        try {
            if (pcntValue == null) {
                pcntValue = BigDecimal.ZERO;
            }
            fmtStr = getPcntFmt(decimal2String(pcntValue));
        } catch (Exception e) {
        }
        return fmtStr;
    }

    /**
     * 得到百分数的值的格式化的值
     *
     * @param pcntValue String
     * @return String
     */
    public static String getPcntFmt(String pcntValue) {
        try {
            if (pcntValue == null) {
                pcntValue = "0";
            }
            BigDecimal temp = new BigDecimal(pcntValue);
            pcntValue = decimal2String(temp);
            pcntValue = trimPerZero(pcntValue);
            log.debug(String.format("in obtainPcntFmt 去掉前导零后 = %s", pcntValue));
            pcntValue = trimSuffixZero(pcntValue);
            log.debug(String.format("in obtainPcntFmt 去掉后缀零后 = %s", pcntValue));
        } catch (Exception e) {
        }
        return pcntValue;
    }

    /**
     * 根据自定义的格式来得到金额的显示字串
     *
     * @param1 BigDecimal 金额
     * @param2 String 格式字串
     */
    public static String applyPattern(BigDecimal amtValue, String ccyFmtStr) {
        StringBuffer formatStr = new StringBuffer();
        // 修饰字串
        String decorateStr = StringUtils.EMPTY;
        // 小数位数
        int decimalDigits = 0;
        // 整数部分分隔长度
        int intDivLen = 0;
        try {
            log.debug(String.format("in applyPattern 格式化开始,格式字串为: = %s", ccyFmtStr));
            if (amtValue == null) {
                amtValue = BigDecimal.ZERO;
            }
            log.debug(String.format("in applyPattern 金额值为：%s", amtValue.toString()));
            String amtStr = decimal2String(amtValue);

            if (ccyFmtStr == null || ccyFmtStr.trim().equals("")) {
                return decimal2String(amtValue);
            }

            if (ccyFmtStr.indexOf(maskStr) < 0) {
                return decimal2String(amtValue);
            }

            decorateStr = ccyFmtStr.substring(0, ccyFmtStr.indexOf(maskStr));
            log.debug(String.format("in applyPattern 修饰字串= ", decorateStr));

            intDivLen = ccyFmtStr.indexOf(comma) - ccyFmtStr.indexOf(maskStr);
            log.debug(String.format("in applyPattern 整数部分分隔长度= ", intDivLen));

            if (ccyFmtStr.indexOf(decimal) > 0) {
                decimalDigits = ccyFmtStr.length() - ccyFmtStr.lastIndexOf(decimal) - 1;
            } else {
                decimalDigits = 0;
            }
            if (decimalDigits < 0) {
                decimalDigits = 0;
            }
            log.debug(String.format("in applyPattern 小数部分长度= ", decimalDigits));
            // 进行四舍五入
            amtStr = round(amtStr, decimalDigits);

            log.debug(String.format("四舍五入后的金额值是 =", amtStr));
            String intValue = "";
            if (amtStr.indexOf(decimal) >= 0) {
                intValue = amtStr.substring(0, amtStr.indexOf(decimal));
            } else {
                intValue = amtStr;
            }
            if (intValue.substring(0, 1).equals(subtractionSign)) {
                decorateStr += subtractionSign;
                intValue = intValue.substring(1, intValue.length());
            } else if (intValue.substring(0, 1).equals(plusSign)) {
                decorateStr += plusSign;
                intValue = intValue.substring(1, intValue.length());
            }
            String decimalValue = "";
            if (amtStr.indexOf(decimal) >= 0) {
                decimalValue = amtStr.substring(amtStr.indexOf(decimal) + 1, amtStr.length());
            }

            log.debug(String.format("in applyPattern 金额整数部分= ", intValue));
            log.debug(String.format("in applyPattern 金额小数部分= ", decimalValue));
            // 逗号的个数
            int commaCount = 0;
            if (intValue.length() % intDivLen == 0) {
                commaCount = intValue.length() / intDivLen - 1;
            } else {
                commaCount = intValue.length() / intDivLen;
            }
            if (commaCount < 0) {
                commaCount = 0;
            }
            log.debug(String.format("in applyPattern 逗号个数 = ", commaCount));

            // 下面开始形成格式字串
            formatStr.append(decorateStr);
            if (commaCount > 0) {
                String perStr = "";
                String suffStr = "";
                for (int i = 0; i < commaCount; i++) {
                    perStr = intValue.substring(0, intValue.length() - (i + 1) * intDivLen - i);
                    log.debug(String.format("in applyPattern 前半部分 = ", perStr));
                    suffStr = intValue.substring(intValue.length() - (i + 1) * intDivLen - i, intValue.length());
                    log.debug(String.format("in applyPattern 后半部分 = ", suffStr));
                    intValue = perStr + comma + suffStr;
                }
            }
            if (decimalValue.equals("")) {
                formatStr.append(intValue);
            } else {
                formatStr.append(intValue + decimal + decimalValue);
            }
            log.debug(String.format("in applyPattern 格式后的字串 = ", formatStr));
        } catch (Exception e) {
            formatStr = new StringBuffer(amtValue.toString());
        }
        return formatStr.toString();
    }

    /**
     * 取精度
     *
     * @param amt         BigDecimal
     * @param decimalPosi int
     * @return BigDecimal
     */
    public static BigDecimal round(BigDecimal amt, int decimalPosi) {
        BigDecimal big = BigDecimal.ZERO;
        try {
            if (amt == null) {
                amt = BigDecimal.ZERO;
            }
            String amtStr = round(decimal2String(amt), decimalPosi);
            if (amtStr == null) {
                amtStr = "0.0";
            }
            big = new BigDecimal(amtStr);
        } catch (Exception e) {
        }
        return big;
    }

    /**
     * 金额四舍五入
     *
     * @param1 String amtStr    金额字串
     * @param2 int  decimalPosi 精确位数
     */
    public static String round(String amtStr, int decimalPosi) {
        String roundStr = "";
        // 符号
        String sign = "";
        try {
            if (amtStr == null) {
                amtStr = "";
            }
            amtStr = exponent2DecimalString(amtStr);
            amtStr = trimPerZero(amtStr);
            log.debug(String.format("金额值 = ", amtStr));
            log.debug(String.format("指定精确位数据 = ", decimalPosi));
            int decimalIndex = amtStr.indexOf(decimal);
            log.debug(String.format("小数点的INDEX =", decimalIndex));
            // .234234
            if (decimalIndex == 0) {
                //  0.234234
                amtStr = "0" + amtStr;
            }
            // 2342
            else if (decimalIndex < 0) {
                String tmpStr = "";
                // 补零
                while (tmpStr.length() < decimalPosi) {
                    tmpStr = tmpStr + "0";
                }
                log.debug(String.format("没有 decimal"));
                if (!tmpStr.equals("")) {
                    amtStr = amtStr + decimal + tmpStr;
                }
                return amtStr;
            }

            log.debug(String.format("金额字串为: ", amtStr));
            if (amtStr.substring(0, 1).equals(subtractionSign)) {
                // 载取符号
                sign = subtractionSign;
                // 得到无符号的整数部分
                amtStr = amtStr.substring(1, amtStr.length());
            } else if (amtStr.substring(0, 1).equals(plusSign)) {
                sign = plusSign;
                amtStr = amtStr.substring(1, amtStr.length());
            }
            log.debug(String.format("in round break.................1"));
            String intStr = amtStr.substring(0, amtStr.indexOf(decimal));
            String decimalStr = amtStr.substring(amtStr.indexOf(decimal) + 1, amtStr.length());
            log.debug(String.format("in round 整数部分：", intStr));
            log.debug(String.format("in round 小数部分：", decimalStr));
            // 原始的小数部分长度比指定精确位数大
            if (decimalStr.length() > decimalPosi) {
                String roundDecimalStr = decimalStr.substring(0, decimalPosi);
                if (roundDecimalStr == null || roundDecimalStr.equals(""))
                    roundDecimalStr = "0";
                BigInteger decValue = new BigInteger(roundDecimalStr);
                String carryStr = decimalStr.substring(decimalPosi, decimalPosi + 1);
                log.debug(String.format("进位字符= ", carryStr));
                log.debug(String.format("decValue=", decValue));
                int carry = Integer.parseInt(carryStr);
                if (carry >= 5) {
                    decValue = decValue.add(new BigInteger("1"));
                }
                // 得到计算后的小数部分的字串
                decimalStr = String.valueOf(decValue);
                log.debug(String.format("四舍五入后的小数部分=", decimalStr));
                if (decimalPosi == 0) {
                    decimalStr = "";
                    if (carry >= 5) {
                        BigInteger bigInt = new BigInteger(intStr);
                        intStr = String.valueOf(bigInt.add(new BigInteger("1")));
                    }
                } else if (decimalStr.length() > decimalPosi) {
                    BigInteger bigInt = new BigInteger(intStr);
                    intStr = String.valueOf(bigInt.add(new BigInteger("1")));
                    decimalStr = decimalStr.substring(1, decimalStr.length());
                } else if (decimalStr.length() < decimalPosi) {
                    while (decimalStr.length() < decimalPosi) {
                        decimalStr = "0" + decimalStr;
                    }
                }
            }
            // 四舍五入后不足指定的小数位数,小数位数补零
            else if (decimalStr.length() < decimalPosi) {
                while (decimalStr.length() < decimalPosi) {
                    decimalStr += "0";
                }
            }
            if (decimalStr.equals("")) {
                roundStr = sign + intStr;
            } else {
                roundStr = sign + intStr + decimal + decimalStr;
            }
            log.debug(String.format("四舍五入后的字串= ", roundStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roundStr;
    }

    /**
     * 按精确位数保留小数，只舍不入(ROUND_DOWN)
     *
     * @param amt
     * @param decimalPosi
     * @return
     */
    public static BigDecimal scale(BigDecimal amt, int decimalPosi) {
        BigDecimal big = BigDecimal.ZERO;
        try {
            if (amt == null) {
                amt = BigDecimal.ZERO;
            }
            String amtStr = scale(decimal2String(amt), decimalPosi);
            if (amtStr == null) {
                amtStr = "0.0";
            }
            big = new BigDecimal(amtStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return big;
    }

    /**
     * 按精确位数保留小数，只舍不入(ROUND_DOWN)
     *
     * @param1 String amtStr    金额字串
     * @param2 int  decimalPosi 精确位数
     */
    public static String scale(String amtStr, int decimalPosi) {
        String roundStr = "";
        // 符号
        String sign = "";
        try {
            if (amtStr == null) {
                amtStr = "";
            }
            // [1.4]
            amtStr = exponent2DecimalString(amtStr);
            amtStr = trimPerZero(amtStr);
            log.debug(String.format("金额值 = ", amtStr));
            log.debug(String.format("指定精确位数据 = ", decimalPosi));
            int decimalIndex = amtStr.indexOf(decimal);
            log.debug(String.format("小数点的INDEX =", decimalIndex));
            // .234234
            if (decimalIndex == 0) {
                // 生成 0.234234
                amtStr = "0" + amtStr;
            }
            // 2342
            else if (decimalIndex < 0) {
                String tmpStr = StringUtils.EMPTY;
                // 补零
                while (tmpStr.length() < decimalPosi) {
                    tmpStr = tmpStr + "0";
                }
                log.debug(String.format("没有 decimal"));
                if (!tmpStr.equals("")) {
                    amtStr = amtStr + decimal + tmpStr;
                }
                return amtStr;
            }

            log.debug(String.format("金额字串为: ", amtStr));
            if (amtStr.substring(0, 1).equals(subtractionSign)) {
                // 载取符号
                sign = subtractionSign;
                // 得到无符号的整数部分
                amtStr = amtStr.substring(1, amtStr.length());
            } else if (amtStr.substring(0, 1).equals(plusSign)) {
                sign = plusSign;
                amtStr = amtStr.substring(1, amtStr.length());
            }
            log.debug(String.format("in round break.................1"));
            String intStr = amtStr.substring(0, amtStr.indexOf(decimal));
            String decimalStr = amtStr.substring(amtStr.indexOf(decimal) + 1, amtStr.length());
            log.debug(String.format("in round 整数部分：", intStr));
            log.debug(String.format("in round 小数部分：", decimalStr));
            // 原始的小数部分长度比指定精确位数大
            if (decimalStr.length() > decimalPosi) {
                String roundDecimalStr = decimalStr.substring(0, decimalPosi);
                if (StringUtils.isBlank(roundDecimalStr)) {
                    roundDecimalStr = "0";
                }
                BigInteger decValue = new BigInteger(roundDecimalStr);
                String carryStr = decimalStr.substring(decimalPosi, decimalPosi + 1);
                log.debug(String.format("进位字符= ", carryStr));
                log.debug(String.format("decValue=", decValue));
                // int carry = Integer.parseInt(carryStr);
                // if(carry>=5)decValue =decValue.add(new BigInteger("1"));
                // 得到计算后的小数部分的字串
                decimalStr = String.valueOf(decValue);
                log.debug(String.format("处理后的小数部分=", decimalStr));
                if (decimalPosi == 0) {
                    decimalStr = "";
                } else if (decimalStr.length() > decimalPosi) {
                    BigInteger bigInt = new BigInteger(intStr);
                    intStr = String.valueOf(bigInt.add(new BigInteger("1")));
                    decimalStr = decimalStr.substring(1, decimalStr.length());
                } else if (decimalStr.length() < decimalPosi) {
                    while (decimalStr.length() < decimalPosi) {
                        decimalStr = "0" + decimalStr;
                    }
                }
            }
            // 四舍五入后不足指定的小数位数,小数位数补零
            else if (decimalStr.length() < decimalPosi) {
                while (decimalStr.length() < decimalPosi) {
                    decimalStr += "0";
                }
            }
            if (decimalStr.equals("")) {
                roundStr = sign + intStr;
            } else {
                roundStr = sign + intStr + decimal + decimalStr;
            }
            log.debug(String.format("处理后的字串= ", roundStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roundStr;
    }

    /**
     * 去掉前导的零
     *
     * @param amtStr
     */
    private static String trimPerZero(String amtStr) {
        try {
            if (amtStr == null || amtStr.trim().equals("")) {
                return "";
            }
            String signStr = "";
            String noSignStr = "";
            if (amtStr.substring(0, 1).equals(subtractionSign)) {
                signStr = subtractionSign;
                noSignStr = amtStr.substring(1, amtStr.length());
            } else if (amtStr.substring(0, 1).equals(plusSign)) {
                signStr = plusSign;
                noSignStr = amtStr.substring(1, amtStr.length());
            } else {
                signStr = "";
                noSignStr = amtStr;
            }
            if (noSignStr.equals("")) {
                return "0";
            }
            log.debug(String.format("in trimPerZero break.....nosignStr=", noSignStr));
            while (noSignStr.substring(0, 1).equals("0")) {
                noSignStr = noSignStr.substring(1, noSignStr.length());
                if (noSignStr == null || noSignStr.equals("")) {
                    break;
                }
            }
            log.debug(String.format("in trimPerZero break.....nosignStr=", noSignStr));
            if (noSignStr.length() > 0 && noSignStr.substring(0, 1).equals(decimal)) {
                noSignStr = "0" + noSignStr;
            }
            if (noSignStr.equals("")) {
                return "0";
            }
            amtStr = signStr + noSignStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amtStr;
    }

    /**
     * 去掉尾缀的零
     *
     * @param amtStr
     * @return
     */
    public static String trimSuffixZero(String amtStr) {
        try {
            if (amtStr == null || amtStr.trim().equals("")) {
                return "";
            }
            if (amtStr.indexOf(decimal) < 0) {
                return amtStr;
            }
            while (amtStr.substring(amtStr.length() - 1, amtStr.length()).equals("0")) {
                amtStr = amtStr.substring(0, amtStr.length() - 1);
            }
            if (amtStr.length() > 0 && amtStr.substring(amtStr.length() - 1, amtStr.length()).equals(decimal)) {
                amtStr = amtStr.substring(0, amtStr.length() - 1);
            }
        } catch (Exception e) {
        }
        return amtStr;
    }

    /**
     * decimal2String
     *
     * @param decimal
     * @return
     */
    public static String decimal2String(BigDecimal decimal) {
        if (decimal == null) {
            return "0";
        }
        int scale = decimal.scale();
        StringBuffer buff = new StringBuffer();
        buff.append("#");
        if (scale > 0) {
            buff.append(".");
            for (int i = 0; i < scale; i++) {
                buff.append("#");
            }
        }
        DecimalFormat df = new DecimalFormat(buff.toString());
        return df.format(decimal);
    }

    /**
     * 将指数形式的数字转成十进制形式
     *
     * @param numStr
     * @return
     */
    public static String exponent2DecimalString(String numStr) {
        if (numStr == null) {
            return "0";
        }
        BigDecimal tmp = new BigDecimal(numStr);
        return decimal2String(tmp);
    }

    /**
     * 将数字转换成指定的格式
     *
     * @param amtObj
     * @return
     */
    public static String formatNumber(BigDecimal amtObj) {
        String ccyFmtStr = "###,###,###,##0.00";
        String amtFmt = "";
        if (null != amtObj) {
            BigDecimal amtDecimal = new BigDecimal(amtObj.toString());
            amtFmt = AmtUtils.applyPattern(amtDecimal, ccyFmtStr);
        }
        return amtFmt;
    }

    /**
     * 将数字格式化为利率
     *
     * @param amtObj
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/5/8 8:59
     */
    public static String formatRate(BigDecimal amtObj) {
        String ccyFmtStr = "###,###,###,##0.0000";
        String amtFmt = "";
        if (null != amtObj) {
            BigDecimal amtDecimal = new BigDecimal(amtObj.toString());
            amtFmt = AmtUtils.applyPattern(amtDecimal, ccyFmtStr);
        }
        return amtFmt;
    }
}
