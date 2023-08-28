package com.vteam.cars.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.houbb.opencc4j.util.ZhTwConverterUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: I18nJsonTest
 * @Description: TODO
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/7/6 16:02
 */
public class I18nJsonTest {

    @Test
    public void testExgJson2TW() {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("E:\\i18n\\constant_zh-CN.js")));

            // 解析 JSON 字符串为 JSONObject
            JSONObject jsonObject = JSONObject.parseObject(jsonString, Feature.OrderedField);

            // 传入要转换的属性
            for(String key:jsonObject.keySet()){
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                if (jsonArray != null) {
                    for(int i=0;i<jsonArray.size();i++){
                        JSONObject json = jsonArray.getJSONObject(i);
                        json.put("value",ZhTwConverterUtil.toTraditional(json.get("value").toString()));
                    }
                }
            }

            System.out.println(jsonObject.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testI18nJson() {
        // 目录下文件中包含$t()字符串转繁体
        String directoryPath = "E:\\i18n";  // 指定目录路径
        Map<String, String> translations = new HashMap<>();  // 用于存储翻译数据

        parseVueFiles(directoryPath, translations);

        // 将简体中文值转换为繁体中文值
        translations.replaceAll((key, value) -> ZhTwConverterUtil.toTraditional(value));

        // 打印翻译数据
        translations.forEach((key, value) -> System.out.println(key + ": '" + value +"',"));
    }

    public static boolean containsSymbolOrSpace(String input) {
        // 正则表达式模式，匹配任何符号或空格
        String pattern = "[\\p{P}\\p{S}\\p{Z}\\p{Sm}\\p{Ps}\\p{Pe}]";

        return Pattern.compile(pattern).matcher(input).find();
    }

    public static void parseVueFiles(String directoryPath, Map<String, String> translations) {
        File directory = new File(directoryPath);

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        parseVueFiles(file.getAbsolutePath(), translations);  // 递归遍历子目录
                    } else if (file.getName().endsWith(".vue")) {
                        parseVueFile(file, translations);  // 解析 .vue 文件
                    }
                }
            }
        }
    }

    public static void parseVueFile(File file, Map<String, String> translations) {
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            Pattern pattern = Pattern.compile("\\$t\\('([^']+)'\\)");
            Matcher matcher = pattern.matcher(content);

            while (matcher.find()) {
                String key = matcher.group(1);
                if(containsSymbolOrSpace(key)){
                    continue;
                }
                translations.put(key, key);  // 以 key 作为 value，可以根据需要修改为翻译后的文本
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
