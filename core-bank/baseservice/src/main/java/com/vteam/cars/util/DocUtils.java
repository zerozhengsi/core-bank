package com.vteam.cars.util;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.vtarm.utils.SpringContextUtils;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;


/**
 * doc文档工具类 .<br>
 *
 * @author andy.sher
 * @date 2019/11/5 14:54
 */
@Slf4j
public class DocUtils {

    private DocUtils() {
    }
    
    private static FreeMarkerConfigurer freeMarkerConfigurer;
    
    /**
     * 合同ftl模板文件夹
     */
    private static String PREFIX = "contract" + GlobalConstants.Symbol.SLASH;

    /**
     * 填充参数 .
     *
     * @param src    源文件模板路径
     * @param dest   填充后的文件路径
     * @param params 填充参数
     * @return void
     * @author andy.sher
     * @date 2019/11/6 10:05
     */
    public static void full(String src, String dest, Map<String, String> params) {
        try (InputStream is = DocUtils.class.getResourceAsStream(src); OutputStream os = new FileOutputStream(Paths.get(dest).toFile());) {
            @SuppressWarnings("resource")
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            if (null != params && !params.isEmpty()) {
                params.entrySet().forEach(e -> {
                    range.replaceText(GlobalConstants.Symbol.DOLLAR + GlobalConstants.Symbol.CURLY_BRACES_OPEN + e.getKey() + GlobalConstants.Symbol.CURLY_BRACES_CLOSE, e.getValue());
                });
            }
            doc.write(os);
        } catch (Exception e) {
            log.error(DocUtils.class.getName(), e);
        }
    }
  

    /**
     * 根据ftl模板生成word
     *
     * @param templateName 模板文件名(***)(不含后缀)
     * @param targetPath   生成的文件存储路径(不含文件名)
     * @param fileName     生成的文件名
     * @param dataMap      填充数据集合
     * @author olivia.liu
     * @date 2022/12/01 19:05
     */
    public static void createWordDocx(String templateName, String targetPath, String fileName, Map<String, Object> dataMap) {
    	initService();
        Writer out = null;
        try {
        	Template template = freeMarkerConfigurer.getConfiguration().getTemplate(PREFIX + templateName + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.FTL);
            //动态生成文件夹名称
            File outFile = new File(targetPath + GlobalConstants.Symbol.SLASH + fileName);
            // 输出文件夹不存在  则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            //将数据文件 与 模型文件合并
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), GlobalConstants.Character.UTF_8));
            // 填充数据到文件
            template.process(dataMap, out);
        } catch (Exception e) {
            log.error(DocUtils.class.getName() + "[创建word发生错误]", e);
        } finally {
            if (out != null) {
                //关闭流
                try {
                    out.flush();
                    out.close();
                } catch (Exception ex) {
                    log.error(DocUtils.class.getName(), "创建word发生错误");
                }
            }
        }
    }   
    
    /**
     * 初始化外部服务类
     *
     * @return SmeConfiguration
     */
    private static void initService() {
        if (null == freeMarkerConfigurer) {
        	freeMarkerConfigurer = SpringContextUtils.getBean(FreeMarkerConfigurer.class);
        }
    }
}
