package com.vteam.cars.util;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.plugin.provider.I18NResourceProvider;
import com.vteam.vtarm.data.DataProvider;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 国际化工具类 .<br>
 *
 * @author andy.sher
 * @date 2019/8/16 14:24
 */
@Slf4j
public class I18NUtils {

    /**
     * 消息生成器 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/5/6 13:06
     */
    public static JSONObject messageGenerator(Class<?> clazz) {
        String regex = clazz.getSimpleName() + ".\\w+";
        String regex1 = "\"\\S+\"";
        ArrayList<String> scanFiles = new ArrayList<>();
        FileUtils.scanFilesWithRecursion(System.getProperty(GlobalConstants.Flag.USER_DIR) + "\\src\\main\\java", scanFiles);
        Pattern pattern = Pattern.compile(regex);
        Pattern pattern1 = Pattern.compile(regex1);
        String line;
        Set<String> messageSet = new HashSet<>(10);
        Matcher matcher;
        Matcher matcher1;
        File file;
        for (String filePath : scanFiles) {
            file = new File(filePath);
            if (!file.getName().contains((I18NUtils.class.getSimpleName() + GlobalConstants.Flag.JAVA_FILE_SUFFIX)) && file.getName().indexOf(GlobalConstants.Flag.JAVA_FILE_SUFFIX) > -1) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
                    while ((line = bufferedReader.readLine()) != null) {
                        line = line.trim();
                        matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            matcher1 = pattern1.matcher(line);
                            if (matcher1.find()) {
                                messageSet.add(matcher1.group());
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error(I18NUtils.class.getName(), e);
                }
            }
        }
        String messageStr = StringUtils.EMPTY;
        for (String message : messageSet) {
            message = message.replaceAll("\"", "");
            messageStr = messageStr + message + GlobalConstants.Symbol.UNDERLINE;
        }
        messageStr = messageStr.substring(0, messageStr.length() - 1);

        String[] simplifiedArr = messageStr.split(GlobalConstants.Symbol.UNDERLINE);
        JSONObject simplifiedArrResult = new JSONObject();
//        if (simplifiedArr.length == simplifiedArr.length) {
        if (simplifiedArr.length > 0) {
            for (int i = 0; i < simplifiedArr.length; i++) {
                simplifiedArrResult.put(simplifiedArr[i], simplifiedArr[i]);
            }
        }
        return simplifiedArrResult;
    }

    /**
     * 根据语言获取国际化消息 .
     *
     * @param message   消息（支持${...}占位符）
     * @param fillModel 填充数据模型
     * @return java.lang.String 国际化后的消息
     * @author andy.sher
     * @date 2019/8/16 14:26
     */
    public static String getI18N(String message, Map<String, String> fillModel) {
        if (StringUtils.isNotBlank(message)) {
            DataProvider dataProvider = SpringContextUtils.getBean(DataProvider.class);
            // 获取国际化信息
            String i18nMessage = dataProvider.get(I18NResourceProvider.class, message);

            // 如果没有找到国际化消息，则使用默认消息
            if (StringUtils.isBlank(i18nMessage)) {
                i18nMessage = message;
            }

            // 如果存在模型则进行模型填充
            if (null != fillModel && !fillModel.isEmpty()) {
                i18nMessage = StringSubstitutor.replace(i18nMessage, fillModel);
            }
            return i18nMessage;
        } else {
            return message;
        }
    }

}
