package com.vteam.vtarm.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

/**
 * 富文本工具类 .
 *
 * @author andy.sher
 * @date 2019/12/3 9:02
 */
public final class RichTextUtils extends org.apache.commons.lang3.StringUtils {

    private RichTextUtils() {
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
        return content.toLowerCase();
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

}
