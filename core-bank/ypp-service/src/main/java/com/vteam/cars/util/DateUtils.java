package com.vteam.cars.util;

import com.vteam.cars.constant.GlobalConstants;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 日期处理工具类
 *
 * @author jiangming.huang
 * @date 2019/3/13 16:22
 */
public final class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private DateUtils() {
    }

    /**
     * yyyy-MM
     **/
    public static final DateTimeFormatter FMT_MONTH = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_MONTH);
    /**
     * yyyy-MM-dd
     **/
    public static final DateTimeFormatter FMT_DATE = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_DATE);
    /**
     * yyyy-MM-dd HH:mm
     **/
    public static final DateTimeFormatter FMT_MINUTE = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_MINUTE);
    /**
     * yyyy-MM-dd HH:mm:ss
     **/
    public static final DateTimeFormatter FMT_SECOND = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_SECOND);
    /**
     * yyyy-MM-dd HH:mm:ss SSSS
     **/
    public static final DateTimeFormatter FMT_MILLISECOND = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_MILLISECOND);
    /**
     * EEE MMM dd HH:mm:ss zzz yyyy
     **/
    public static final DateTimeFormatter FMT_SECOND_ENG = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_MILLISECOND);
    /**
     * MM-dd
     **/
    public static final DateTimeFormatter FMT_MONTH_DATE = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_MONTHDATE);

    /**
     * yyyyMMdd
     **/
    public static final DateTimeFormatter FMT_DATE_N = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_DATE_N);

    /** yyyy/mm/dd **/
    public static final DateTimeFormatter FMT_SLASH = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.FMT_SLASH);

    /**
     * yyyyMMddHHmm
     **/
    public static final DateTimeFormatter FMT_MINUTE_N = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_MINUTE_N);
    /**
     * yyyyMMddHHmmss
     **/
    public static final DateTimeFormatter FMT_SECOND_N = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_SECOND_N);
    /**
     * yyyyMMddHHmmssSSSS
     **/
    public static final DateTimeFormatter FMT_MILLISECOND_N = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_MILLISECOND_N);
    /**
     * yyMMddHHmmssSSSS
     **/
    public static final DateTimeFormatter FMT_SHORT_MILLISECOND_N = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_SHORT_MILLISECOND_N);
    /**
     * HHmmss
     **/
    public static final DateTimeFormatter FMT_HMS_N = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_HMS_N);

    /**
     * yyyy/M/d
     **/
    public static final DateTimeFormatter FMT_DATE_SLASH = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_DATE_SLASH);

    /**
     * H
     **/
    public static final DateTimeFormatter FMT_HOUR_N = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.HOUR_N);

    /**
     * yyyy年MM月dd日
     **/
    public static final DateTimeFormatter FMT_SIMPLE = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_DATE_SIMPLE);

    /**
     * MMM.dd,yyyy
     **/
    public static final DateTimeFormatter FMT_ENGLISH = DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_DATE_ENGLISH, Locale.ENGLISH);

    /**
     * 数字3
     */
    public static final int N3 = 3;

    /**
     * 数字6
     */
    public static final int N6 = 6;

    /**
     * 数字9
     */
    public static final int N9 = 9;

    /**
     * 数字10
     */
    public static final int N10 = 10;

    /**
     * 此处集合顺序要以最长的开始，否则获取格式不准确
     */
    public static final String[] FORMATS = {GlobalConstants.DateFormat.TO_SECOND_ENG, GlobalConstants.DateFormat.TO_MILLISECOND,
            GlobalConstants.DateFormat.TO_MILLISECOND_N, GlobalConstants.DateFormat.TO_SECOND, GlobalConstants.DateFormat.TO_SECOND_N,
            GlobalConstants.DateFormat.TO_MINUTE, GlobalConstants.DateFormat.TO_HOUR_N, GlobalConstants.DateFormat.TO_MINUTE_N,
            GlobalConstants.DateFormat.TO_DATE, GlobalConstants.DateFormat.TO_DATE_N, GlobalConstants.DateFormat.TO_MONTH,
            GlobalConstants.DateFormat.TO_MONTH_N};

    public static String getCurrDatetime2MilliSecond(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateUtils.FMT_MILLISECOND_N);
    }

    public static LocalDateTime getCurrDatetime(){
        return LocalDateTime.now();
    }

    /**
     * 获取两个日期相隔毫秒数
     *
     * @param startDateTime 开始日期
     * @param endDateTime 结束日期
     * @return 毫秒数
     * @author care.zheng
     */
    public static long getMillisBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        long millis = 0L;
        if (null != startDateTime && null != endDateTime && !startDateTime.isAfter(endDateTime)) {
            Duration duration = Duration.between(startDateTime, endDateTime);
            millis = duration.toMillis();
        }
        return millis;
    }

    /**
     * 获取两个日期相隔毫秒数
     *
     * @param startDateTime 开始日期
     * @param endDateTime 结束日期
     * @return 毫秒数
     * @author deng.dun
     */
    public static int getDayBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int day = 0;
        if (null != startDateTime && null != endDateTime && !startDateTime.isAfter(endDateTime)) {
            Duration duration = Duration.between(startDateTime, endDateTime);
            day = (int) duration.toDays();
        }
        return day;
    }
    /**
     * 获取两个日期相隔天数
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 天数
     * @author oscar.yu
     * @date 2019/10/18 16:03
     */
    public static int getDayBetween(LocalDate startDate, LocalDate endDate) {
        long day = 0L;
        if (null != startDate && null != endDate && !startDate.isAfter(endDate)) {
            day = endDate.toEpochDay() - startDate.toEpochDay();
        }
        return (int) day;
    }

    /**
     * 获取两个日期相隔月数(不足一个月时为一个月)
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 月数
     * @author oscar.yu
     * @date 2019/12/12 10:49
     */
    public static int getMonthBetween(LocalDate startDate, LocalDate endDate) {
        int month = 0;
        if (null != startDate && null != endDate && !startDate.isAfter(endDate)) {
            Period period = Period.between(startDate, endDate);
            month = period.getMonths();
            // 默认为1个月
            month = month == 0 ? 1 : month;
        }
        return month;
    }

    /**
     * Date转LocalDateTime
     *
     * @param date date时间
     * @return java.time.LocalDateTime
     * @author jiangming.huang
     * @date 2019/3/13 16:23
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (null == date) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * LocalDateTime转Date
     *
     * @param localDateTime localDateTime时间
     * @return java.util.Date
     * @author jiangming.huang
     * @date 2019/3/13 16:26
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取日期的月份 .
     *
     * @param date 日期
     * @return int 月份
     * @author fu.tong
     * @date 2019/6/5 16:35
     */
    public static int getYear(Date date) {
        return getValueByField(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份 .
     *
     * @param date 日期
     * @return int
     * @author fu.tong
     * @date 2019/6/5 16:35
     */
    public static int getMonth(Date date) {
        // 因月份从0开始，故加1
        return getValueByField(date, Calendar.MONTH) + 1;
    }

    /**
     * 根据Calendar中的日期字段获取日期的对应值 .
     *
     * @param date 日期
     * @param field 日期字段
     * @return int
     * @author fu.tong
     * @date 2019/6/5 16:34
     */
    public static int getValueByField(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 根据指定的格式 格式化日期
     *
     * @param date 日期
     * @param pattern 格式化模板
     * @return 日期字符串
     */
    public static String formatDateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 根据指定的格式 格式化日期
     *
     * @param date 日期
     * @param pattern 格式化模板
     * @return java.lang.String
     * @author oscar.yu
     * @date 2019/10/16 10:58
     */
    public static String formatDate(LocalDateTime date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    /**
     * 传入的日期转换成星期几
     *
     * @param changeDate 日期
     * @return int 星期
     */
    public static int dateToWeekday(Date changeDate) {
        int[] weekdays = {0, 1, 2, 3, 4, 5, 6};
        if (null == changeDate) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(changeDate);
        int numOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekdays[numOfWeek];
    }

    /**
     * 转换时间
     *
     * @param year 年份
     * @param month 月份
     * @return 时间字符串
     */
    public static String formatYmDate(int year, int month) {
        // 转换时间
        String excludeDate = year + GlobalConstants.Symbol.MINUS;
        if (month < N10) {
            excludeDate += "0" + month;
        } else {
            excludeDate += month;
        }
        return excludeDate;
    }

    /**
     * 将一个字符串转换为日期
     *
     * @param dateStr 字符串
     * @return Date日期
     */
    public static Date parseStringToDate(String dateStr) {
        String formatter = getDateFormat(dateStr);
        if (null != formatter) {
            return parseDate(dateStr, formatter);
        } else {
            return null;
        }
    }

    /**
     * 获取字符串的日期格式
     *
     * @param str 字符串
     * @return 返回字符串的日期格式
     */
    public static String getDateFormat(String str) {
        for (String format : FORMATS) {
            if (isDate(str, format)) {
                return format;
            }
        }
        return null;
    }

    /**
     * 判断字符串是否为指定日期格式的字符串。
     *
     * @param datesStr 字符串
     * @param format   日期格式
     * @return 如果是指定日期格式的字符串返回true，否则返回false。
     */
    public static Boolean isDate(String datesStr, String format) {
        boolean isDate = false;
        try {
            Date date = parseDate(datesStr, format);
            if (date != null) {
                isDate = true;
            }
        } catch (Exception ignored) {
            throw new IllegalArgumentException("Invalid date format");
        }
        return isDate;
    }

    /**
     * 根据指定日期格式将给出的日期字符串dateStr转换成一个日期对象
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return Date
     */
    public static Date parseDate(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern)) {
            return null;
        }
        DateFormat dateFormat;
        if (GlobalConstants.DateFormat.TO_SECOND_ENG.equals(pattern)) {
            dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        } else {
            dateFormat = new SimpleDateFormat(pattern);
        }
        dateFormat.setLenient(false);
        Date result;
        try {
            result = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
        return result;
    }

    /**
     * 根据月份获取季度
     *
     * @param month 月份
     * @author oscar.yu
     * @date 2019/8/16 17:21
     */
    public static int getSeasonByMonth(int month) {
        int season;
        if (month <= N3) {
            season = 1;
        } else if (month <= N6) {
            season = 2;
        } else if (month <= N9) {
            season = 3;
        } else {
            season = 4;
        }
        return season;
    }

    /**
     * 获取统计月份条件(不含当前月份)
     *
     * @param queryCount 统计月份条件
     * @return statisticsDateList
     * @author oscar.yu
     * @date 2019/12/9 13:40
     */
    public static List<String> getStatisticsMonths(int queryCount) {
        final LocalDate now = LocalDate.now();
        List<String> statisticsDateList = new ArrayList<>();
        for (int i = 1; i <= queryCount; i++) {
            LocalDate statisticsDate = now.minusMonths(i);
            statisticsDateList.add(DateUtils.FMT_MONTH.format(statisticsDate));
        }
        return statisticsDateList;
    }

    /**
     * 获取统计季度条件(不含当前季度)
     *
     * @param queryCount 统计季度条件
     * @return quarterStaticsList
     * @author oscar.yu
     * @date 2019/12/9 15:39
     */
    public static List<String> getStatisticsSeasons(int queryCount) {
        List<String> quarterStaticsList = new ArrayList<>();
        String seasonDate;
        LocalDate queryDate = LocalDate.now();
        for (int i = 0; i < queryCount; i++) {
            // 四个季度的时间
            queryDate = queryDate.minusMonths(3);
            // X轴时间
            seasonDate = queryDate.getYear() + "Q" + DateUtils.getSeasonByMonth(queryDate.getMonthValue());
            quarterStaticsList.add(seasonDate);
        }
        return quarterStaticsList;
    }

    /**
     * 判断日期是否有效
     *
     * @param dataTime 日期时间
     * @author oscar.yu
     * @date 2021/8/6 17:40
     */
    public static boolean isValid(LocalDateTime dataTime) {
        if (null == dataTime) {
            return false;
        }
        return dataTime.getYear() <= 9999;
    }

    /**
     * 转换时间yyyy-MM-dd
     *
     * @param year 年
     * @param month 月
     * @param day 日
     * @return Date日期
     * @author olivia.liu
     * @date 2022/3/4 15:40
     */
    public static Date parseDate(int year, int month, int day) {
        // 转换时间
        String excludeDate = year + GlobalConstants.Symbol.MINUS;
        if (month < N10) {
            excludeDate += "0" + month;
        } else {
            excludeDate += month;
        }
        excludeDate += GlobalConstants.Symbol.MINUS;
        if (day < 10) {
            excludeDate += "0" + day;
        } else {
            excludeDate += day;
        }
        return parseDate(excludeDate, GlobalConstants.DateFormat.TO_DATE);
    }


    /**
     * 得到某年的最后一天日期
     *
     * @param year 年份
     * @return Date日期
     * @author olivia.liu
     * @date 2022/3/4 15:40
     */
    public static Date getLastDateOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 根据年月获取到月初第一天日期<br>.
     *
     * @author xia.hang
     * @date 2023/7/5 16:23
     * @param year
     * @param month
     * @return String
    */
    public static String getFirstDay(int year,int month){
        Calendar cale = Calendar.getInstance();

        cale.set(Calendar.YEAR,year); //赋值年份
        cale.set(Calendar.MONTH, month-1);//赋值月份
        int lastDay = cale.getActualMinimum(Calendar.DAY_OF_MONTH);//获取月最大天数
        cale.set(Calendar.DAY_OF_MONTH, lastDay);//设置日历中月份的最大天数
        SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DateFormat.TO_DATE);
        String firstDayOfMonth = sdf.format(cale.getTime());
        return firstDayOfMonth;
    }

    /**
     * 根据年月获取到月末最后一天日期<br>.
     *
     * @author xia.hang
     * @date 2023/7/5 16:23
     * @param year
     * @param month
     * @return String
     */
    public static String getLastDay(int year,int month){
        Calendar cale = Calendar.getInstance();

        cale.set(Calendar.YEAR,year); //赋值年份
        cale.set(Calendar.MONTH, month-1);//赋值月份
        int lastDay = cale.getActualMaximum(Calendar.DAY_OF_MONTH);//获取月最大天数
        cale.set(Calendar.DAY_OF_MONTH, lastDay);//设置日历中月份的最大天数
        SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DateFormat.TO_DATE);
        String lastDayOfMonth = sdf.format(cale.getTime());
        return lastDayOfMonth;
    }

}
