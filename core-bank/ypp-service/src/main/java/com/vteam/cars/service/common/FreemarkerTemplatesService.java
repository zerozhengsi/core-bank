package com.vteam.cars.service.common;

import com.vteam.cars.exception.MSSmeBusinessException;
import com.vteam.cars.util.I18NUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

/**
 * @className: FreemarkerTemplatesService
 * @Description: 处理简字符串模板
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/6/16 18:58
 */
@Service
@Slf4j
public class FreemarkerTemplatesService {
    @Autowired
    private Configuration freemarkerConfig;

    public String processTemplate(String templateString, Object dataModel) {
        Template template=null;
        StringWriter writer=null;

        try {
            template = new Template("template", templateString, freemarkerConfig);
            writer = new StringWriter();
            template.process(dataModel, writer);
        } catch (Exception e) {
            throw new MSSmeBusinessException(I18NUtils.getI18N("Freemarker根据模版生成内容时发生异常:",null));
        }
        return writer.toString();
    }
}
