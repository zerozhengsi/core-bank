package com.vteam.cars.util;

import com.vteam.cars.constant.MathConstants;
import com.vteam.vtarm.CommonConstants;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * 数字工具类 .<br>
 *
 * @author andy.sher
 * @date 2018/8/30 14:16
 */
public final class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟",
            "兆", "拾", "佰", "仟"};

    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";

    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";

    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;

    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    /**
     * 数字
     */
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    /**
     * 10以内的大写英文数字 .<br>
     */
    private static final String[] SINGLE_NUM_ARR = new String[]{"", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};

    /**
     * 10-19的大写英文数字 .<br>
     */
    private static final String[] TEN_NUM_ARR = new String[]{"TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"};

    /**
     * 整十的大写英文数字 .<br>
     */
    private static final String[] TEN_INTEGER_ARR = new String[]{"TEN", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY"};

    /**
     * 数字2
     */
    private static final Integer TWO = 2;

    /**
     * 数字3
     */
    private static final Integer THREE = 3;

    /**
     * 字符串3个0
     */
    private static final String THREE_ZERO_STR = "000";

    /**
     * 字符串2个0
     */
    private static final String TWO_ZERO_STR = "00";

    /**
     * 字符串1个0
     */
    private static final String ONE_ZERO_STR = "0";

    /**
     * 字符串1
     */
    private static final String ONE_STR = "1";


    /**
     * 把输入的金额转换为汉语中人民币的大写 .
     *
     * @param numberOfMoney 金额
     * @return java.lang.String 金额中文字符串
     * @author andy.sher
     * @date 2018/8/30 14:20
     */
    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        // 这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (scale <= 0) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (scale <= 0) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }

    /**
     * 判断是否为整数 .
     *
     * @param str 字符串
     * @return boolean
     * @author andy.sher
     * @date 2018/9/18 18:57
     */
    public static boolean isInteger(String str) {
        return INTEGER_PATTERN.matcher(str).matches();
    }

    /**
     * 转换数字为千分位 .
     *
     * @param amt
     * @return java.lang.String
     * @author fu.tong
     * @date 2019/4/23 16:06
     */
    public static String doTransConversion(BigDecimal amt) {
        //保留两位位小数  
        DecimalFormat df = new DecimalFormat(",###,##0.00");
        return df.format(amt);
    }

    /**
     * 转换数字为千分位,保留四位以内小数 .
     *
     * @param amt BigDecimal类型的数据
     * @return java.lang.String
     * @author shaoquan.wu
     * @date 2022/9/6 15:49:53
     */
    public static String doTransConversionFourDecimal(BigDecimal amt) {
        //保留四位小数
        DecimalFormat df = new DecimalFormat(",###,###.####");
        return df.format(amt);
    }

    /**
     * 计算笔数百分百
     *
     * @param count
     * @param total
     * @param totalRate
     * @return
     * @author oscar.yu
     * @date 2019/11/11 13:48
     */
    public static BigDecimal calcCountRate(int count, int total, BigDecimal totalRate) {
        return calcDecimalRate(new BigDecimal(count), new BigDecimal(total), totalRate);
    }

    /**
     * 计算笔数百分百
     *
     * @param curr
     * @param total
     * @param totalRate
     * @return
     * @author oscar.yu
     * @date 2019/11/11 13:48
     */
    public static BigDecimal calcDecimalRate(BigDecimal curr, BigDecimal total, BigDecimal totalRate) {
        BigDecimal currRate = curr.divide(total, 4, BigDecimal.ROUND_HALF_UP).multiply(MathConstants.HUNDRED);
        if (currRate.add(totalRate).compareTo(MathConstants.HUNDRED) > 0) {
            currRate = MathConstants.HUNDRED.subtract(totalRate);
        }
        return currRate;
    }

    /**
     * 数字转英文大写 .<br>
     *
     * @param number 数字 (整数部分21位)
     * @param unit   单位,没有默认传null
     * @return java.lang.String 数字转英文大写字符串
     * @author shaoquan.wu
     * @date 2022/8/10 16:01:56
     */
    public static String number2UpperEng(BigDecimal number, String unit) {

        // 后缀
        StringBuilder suffixBuilder = new StringBuilder();
        if (unit != null) {
            suffixBuilder.append(unit);
            suffixBuilder.append(CommonConstants.Symbol.SPACE);
        }
        suffixBuilder.append("ONLY.");

        // number为null || number <= 0
        if (number == null || number.signum() <= 0) {
            return "ZERO" + CommonConstants.Symbol.SPACE + suffixBuilder;
        }

        // 数字(BigDecimal)四舍五入并转换成字符
        String numberStr = number.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
        // 获取小数点的位置
        int pointIndex = numberStr.indexOf(CommonConstants.Symbol.SPOT);

        // 小数点左右的字符串
        String leftStr = numberStr;
        String rightStr = "";
        // 看是否有小数，如果有，则分别取左边和右边，否则就是全部
        if (pointIndex > -1) {
            leftStr = numberStr.substring(0, pointIndex);
            rightStr = numberStr.substring(pointIndex + 1);
        }

        // 对左边的字串取反
        String leftStrRev = new StringBuilder(leftStr).reverse().toString();
        // 定义8个字串变量来存放解析出来的叁位一组的字串
        String[] arr = new String[8];

        // 长度不是3的倍数需要用0补足
        switch (leftStrRev.length() % THREE) {
            case 1:
                leftStrRev += TWO_ZERO_STR;
                break;
            case 2:
                leftStrRev += ONE_ZERO_STR;
                break;
            default:
                break;
        }
        // 处理整数部分
        String integerPart = "";

        for (int i = 0; i < leftStrRev.length() / THREE; i++) {
            StringBuilder builder = new StringBuilder();
            // 截取第一个叁位
            arr[i] = new StringBuilder(leftStrRev.substring(THREE * i, THREE * i + THREE)).reverse().toString();
            // 用来避免这种情况：1000000 = one million
            if (!arr[i].equals(THREE_ZERO_STR)) {
                if (i != 0) {
                    builder.append(transThree(arr[i]));
                    builder.append(CommonConstants.Symbol.SPACE);
                    builder.append(parseMore(String.valueOf(i)));
                    builder.append(CommonConstants.Symbol.SPACE);
                    builder.append(integerPart);
                    integerPart = builder.toString();
                }
                // thousand、million、billion
                else {
                    // 防止i=0时， 在多加两个空格.
                    integerPart = transThree(arr[i]);
                }
            } else {
                builder.append(integerPart);
                builder.append(transThree(arr[i]));
                integerPart = builder.toString();
            }
        }

        // 处理小数部分
        String fractionalPart = "";
        if (pointIndex > -1) {
            String transFour = transFour(rightStr);
            if (transFour == null || "".equals(transFour)) {
                fractionalPart = "";
            } else {
                // 小数部分存在时转换小数
                fractionalPart = "CENTS " + transFour + CommonConstants.Symbol.SPACE;
            }
        }
        return integerPart.trim() + " " + fractionalPart + suffixBuilder;
    }

    /**
     * 1-9以内的数字转换 .<br>
     *
     * @param str 请求对象
     * @return java.lang.String 响应对象
     * @author shaoquan.wu
     * @date 2022/8/11 11:25:19
     */
    private static String parseFirst(String str) {
        return SINGLE_NUM_ARR[Integer.parseInt(str.substring(str.length() - 1))];
    }

    /**
     * 10-19以内的数字转换 .<br>
     *
     * @param str 请求对象
     * @return java.lang.String 响应对象
     * @author shaoquan.wu
     * @date 2022/8/11 11:25:19
     */
    private static String parseTeen(String str) {
        return TEN_NUM_ARR[Integer.parseInt(str) - 10];
    }

    /**
     * 10, 20, 30, 40, 50, 60, 70, 80, 90数字转换 .<br>
     *
     * @param str 请求对象
     * @return java.lang.String 响应对象
     * @author shaoquan.wu
     * @date 2022/8/11 11:25:19
     */
    private static String parseTen(String str) {
        return TEN_INTEGER_ARR[Integer.parseInt(str.substring(0, 1)) - 1];
    }

    /**
     * 处理两位数 .<br>
     *
     * @param str 请求对象
     * @return java.lang.String 响应对象
     * @author shaoquan.wu
     * @date 2022/8/11 11:25:19
     */
    private static String transTwo(String str) {
        String value;
        // 判断位数
        if (str.length() > TWO) {
            str = str.substring(0, 2);
        } else if (str.length() < TWO) {
            str = str + "0";
        }

        // 07 - seven 是否小於10
        if (str.startsWith(ONE_ZERO_STR)) {
            value = parseFirst(str);
        } else if (str.startsWith(ONE_STR)) {
            // 17 seventeen 是否在10和20之间
            value = parseTeen(str);
        } else if (str.endsWith(ONE_ZERO_STR)) {
            // 是否在10与100之间的能被10整除的数
            value = parseTen(str);
        } else {
            value = parseTen(str) + " " + parseFirst(str);
        }
        return value;
    }

    /**
     * 每三位加单位 .<br>
     *
     * @param str 请求对象
     * @return java.lang.String 响应对象
     * @author shaoquan.wu
     * @date 2022/8/11 11:25:19
     */
    private static String parseMore(String str) {
        String[] a = new String[]{"", "THOUSAND", "MILLION", "BILLION", "TRILLION", "QUADRILLION", "QUINTILLION"};
        return a[Integer.parseInt(str)];
    }

    /**
     * 制作叁位的数 .<br>
     *
     * @param str 请求对象
     * @return java.lang.String 响应对象
     * @author shaoquan.wu
     * @date 2022/8/11 11:25:19
     */
    private static String transThree(String str) {
        String value;
        // 是否小于100
        if (str.startsWith(ONE_ZERO_STR)){
            value = transTwo(str.substring(1));
        } else if (str.substring(1).equals(TWO_ZERO_STR)){
            // 是否被100整除
            value = parseFirst(str.substring(0, 1)) + " HUNDRED";
        }else{
            value = parseFirst(str.substring(0, 1)) + " HUNDRED AND " + transTwo(str.substring(1));
        }
        return value;
    }

    /**
     * 制作肆位的数(小数部分处理) .<br>
     *
     * @param str 请求对象
     * @return java.lang.String 响应对象
     * @author shaoquan.wu
     * @date 2022/9/6 15:13:31
     */
    private static String transFour(String str) {
        String value = StringUtils.EMPTY;

        if (str.startsWith(TWO_ZERO_STR)) {
            value = transTwo(str.substring(2));
        } else if (str.startsWith(ONE_ZERO_STR)) {
            value = transThree(str.substring(1));
        }else if(str.substring(1).equals(THREE_ZERO_STR)){
            value = parseFirst(str.substring(0, 1)) + " THOUSAND";
        } else if (str.substring(1).equals(TWO_ZERO_STR)) {
            // 是否被100整除
            value = parseFirst(str.substring(0, 1)) + " HUNDRED";
        } else {
            value = parseFirst(str.substring(0, 1)) + " THOUSAND " + transThree(str.substring(1));
        }
        return value;
    }

    /**
     * 计算bigDecimal的值
     *
     * @param bigDecimal 待计算的bigDecimal
     * @return 计算结果
     * @author shiping.song
     * @date 2023/1/18 14:37
     */
    public static BigDecimal compute(BigDecimal bigDecimal) {
        return compute(bigDecimal, localBigDecimal -> null == localBigDecimal ? null : bigDecimal);
    }

    public static BigDecimal compute(BigDecimal bigDecimal, UnaryOperator<BigDecimal> function) {
        return function.apply(bigDecimal);
    }

}
