package com.vteam.cars.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * @className: HtmlUtils
 * @Description: 此处采用字符串处理，采用jsoup转换文档时会按jsoup规范转换文档内容
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/6/9 16:07
 */
@Slf4j
public class HtmlUtils {
    /**
     * 普通HTML转成thymeleaf模版
     * @param sourcePath
     * @param targetPath
     * @return
     */
    public static String htmlConvertThymeleaf(String sourcePath,String targetPath){
        try {

            // 从文件加载HTML文档
            File htmlFile = new File(sourcePath);
            String text=FileUtils.readFileToString(htmlFile,"UTF-8");
            //处理文件中的变量
            String replacedText = text.replaceAll("\\$\\{(.*?)\\}", "<span th:text=\"\\$\\{$1\\}\"></span>");
            //处理文件中的表格
            String tableText = tableProcess4String(replacedText);
            FileUtils.writeStringToFile(new File(targetPath),tableText,"UTF-8");
            /*
            Document document = Jsoup.parse(htmlFile, "UTF-8");

            // 获取所有包含${x}的元素
            Elements elementsWithPlaceholder = document.select("*:matchesOwn(\\$\\{.*\\})");

            // 遍历元素并替换文本内容
            for (Element element : elementsWithPlaceholder) {
                String text = element.ownText();
                String replacedText = text.replaceAll("\\$\\{(.*?)\\}", "<span th:text=\"\\$\\{$1\\}\"></span>");
                log.info("replacedText:"+replacedText);
                element.text(replacedText);
            }

            tableProcess(document);

            // 清理HTML标签和属性
//            Document.OutputSettings outputSettings = new Document.OutputSettings();
//            outputSettings.prettyPrint(false);
//            document.outputSettings(outputSettings);
//            document.select("body").unwrap();
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
            document.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
            document.outputSettings().charset("UTF-8");
//            document.outputSettings().indentAmount(0);
//            document.outputSettings().outline(true);
            document.outputSettings().prettyPrint(true);

            // 输出转换后的Thymeleaf模板,替换生成的转义字符
//            String thymeleafTemplate = document.outerHtml();
            String thymeleafTemplate = document.outerHtml().replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;","\"");
            FileUtils.writeStringToFile(new File(targetPath),thymeleafTemplate,"UTF-8");*/
        } catch (IOException e) {
            log.info("普通HTML转成thymeleaf模版异常：",e);
        }
        return targetPath;
    }

    public static String tableProcess4String(String input){
        String item = "${item";

        String divStart = "<div th:each=\"item:${data.datatable}\">";
        String divEnd = "</div>";

        StringBuilder output = new StringBuilder();
        int startIndex = 0;
        int endIndex;

        while (true) {
            // 查找下一个<tr>
            startIndex = input.indexOf("<tr", startIndex);
            if (startIndex == -1) {
                break;
            }

            // 查找下一个</tr>
            endIndex = input.indexOf("</tr>", startIndex);
            if (endIndex == -1) {
                break;
            }

            // 提取<tr>前的内容
            output.append(input, 0, startIndex);

            // 提取<tr>和</tr>之间的内容
            String trContent = input.substring(startIndex + 3, endIndex);

            // 检查是否包含完整的item
            if (trContent.contains(item)) {
                // 在<tr>前加上<div>
                output.append(divStart);
                output.append("<tr").append(trContent).append("</tr>");
                output.append(divEnd);
            } else {
                // 不包含完整的item，保留原内容
                output.append("<tr").append(trContent).append("</tr>");
            }

            // 更新下一个查找的起始位置
            input = input.substring(endIndex + 5);
            startIndex = 0;
        }

        // 添加剩余的内容
        output.append(input);
        return output.toString();
    }

    /**
     * TODO
     * @param document
     * @return
     */
    public static void tableProcess(Document document){
        // 获取table元素
        /*Elements tables = document.select("table");
        for(Element table:tables){
            Elements trElements = table.select("tr");
            // 遍历tr元素
            // 用于存储找到的tr元素
            Element targetTr = null;
            for (Element tr : trElements) {
                // 判断tr中的文本是否包含${item}
                if (tr.text().contains("${item")) {
                    // 找到目标tr元素
                    targetTr = tr;
                    // 创建新的div元素
                    Element divWrapper = new Element("div");
                    divWrapper.attr("th:each", "item:${data.datatable}");
                    // 在tr前插入新创建的div元素
                    tr.before(divWrapper);

                    // 移动tr元素到div元素内部
                    divWrapper.appendChild(tr);
                    // 退出循环，只处理第一个匹配的tr
                    break;
                }
            }
        }*/
    }
}
