package com.vteam.cars.constant;

import java.math.BigDecimal;

/**
 * 数学运算常量类 .<br>
 *
 * @author andy.sher
 * @date 2018/11/15 15:57
 */
public class MathConstants {

    /**
     * 金额精度
     */
    public final static Integer AMT_ROUNDING = 2;

    /**
     * 金额临时精度，用于解决精度不够对四舍五入造成的影响
     */
    public final static Integer AMT_TEMP_ROUNDING = 8;

    /**
     * 利率精度
     */
    public final static Integer RATE_ROUNDING = 4;

    /**
     * 金额保留6位小数
     */
    public static int BASE_AMOUNT_SCALE_SIX = 6;

    /**
     * 利息/年费率基础日
     */
    public static int BASE_DAY = 360;
    
    /**
     * 四舍五入的方式
     */
    public static final int ROUND_MODE_UP = BigDecimal.ROUND_HALF_UP;

    /**
     * 一个月天数
     */
    public static final BigDecimal ONE_MONTH_DAY = new BigDecimal("30");

    /**
     * 月息转年息
     */
    public static final BigDecimal MONTH_TO_YEAR_RATE = new BigDecimal("1.2");

    public static final BigDecimal ZERO = new BigDecimal("0");
    public static final BigDecimal ONE = new BigDecimal("1");
    public static final BigDecimal MINUS_ONE = new BigDecimal("-1");

    public static final BigDecimal HUNDRED = new BigDecimal("100");
    public static final BigDecimal HUNDRED_PERCENT = new BigDecimal("0.01");

    public static final BigDecimal THOUSAND = new BigDecimal("1000");
    public static final BigDecimal THOUSAND_PERCENT = new BigDecimal("0.001");

    public static final BigDecimal TEN_THOUSAND = new BigDecimal("10000");
    public static final BigDecimal TEN_THOUSAND_percent = new BigDecimal("0.0001");

}
