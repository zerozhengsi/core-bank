package com.vteam.cars.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.util.regex.Pattern;

/**
 * 正则工具类.<br>
 *
 * @author shiping.song
 * @date 2023/1/16 11:36
 */
@Slf4j
public class RegularUtils {

    /**
     * 日期正则表达式，匹配yyyymmdd、yyyy-mm-dd、yyyy/mm/dd
     */
    public static final String DATE_FMT_N = "^([1-9][0-9]{3})[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?((0[1-9])|([12][0-9])|(30)))|(02[\\-\\/\\s]?(0[1-9]|[12][0-9])))$";

    private RegularUtils() {
    }

    /**
     * 使用指定规则校验指定内容是否符合规则
     *
     * @param content 待校验内容
     * @param pattern 校验规则
     * @return 校验结果
     * @author shiping.song
     * @date 2023/1/16 11:52
     */
    public static boolean exist(String content, String pattern) {
        doAssert(content, pattern);
        Pattern compile = Pattern.compile(pattern);
        return compile.matcher(content).find();
    }

    /**
     * 校验参数
     *
     * @param content 待校验内容
     * @author shiping.song
     * @date 2023/1/16 11:57
     */
    private static void doAssert(String... content) {
        SmeAssert.isTrue(ArrayUtils.isNotEmpty(content), "待校验内容为空");
        for (String localContent : content) {
            SmeAssert.notBlank(localContent, "参数为空");
        }
    }

    public static void main(String[] args) {
        System.out.println(RegularUtils.exist("00000201", DATE_FMT_N));
        System.out.println(RegularUtils.exist("20220200", DATE_FMT_N));
        System.out.println(RegularUtils.exist("10220400", DATE_FMT_N));

        System.out.println(RegularUtils.exist("10240304", DATE_FMT_N));
        System.out.println(RegularUtils.exist("90220501", DATE_FMT_N));
        System.out.println(RegularUtils.exist("20230312", DATE_FMT_N));
        System.out.println(RegularUtils.exist("20221001", DATE_FMT_N));
        System.out.println(RegularUtils.exist("20220212", DATE_FMT_N));
        System.out.println(RegularUtils.exist("2022/04/03", DATE_FMT_N));
        System.out.println(RegularUtils.exist("2022-02-01", DATE_FMT_N));
    }

}
