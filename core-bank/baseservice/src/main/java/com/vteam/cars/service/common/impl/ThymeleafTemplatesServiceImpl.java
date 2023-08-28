package com.vteam.cars.service.common.impl;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.exception.MSSmeBusinessException;
import com.vteam.cars.service.common.TemplatesService;
import com.vteam.cars.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @className: ThymeleafTemplatesServiceImpl
 * @Description: TODO
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/6/5 10:28
 */
@Service
@Slf4j
public class ThymeleafTemplatesServiceImpl<T> implements TemplatesService<T> {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Resource(type = TemplateEngine.class)
    private TemplateEngine templateEngine;

    @Override
    public String generateHtml(String templateId,T t) {
        String path = smeConfiguration.getTmpPath() + DateUtils.getCurrDatetime2MilliSecond()+".html";
        //构建模板参数
        Context context = new Context();
        context.setVariable("data", t);
        try (FileWriter writer = new FileWriter(path)) {
            templateEngine.process(templateId, context, writer);
        } catch (IOException e) {
            log.error("根据模版生成临时文件时发生异常：", e);
            throw new MSSmeBusinessException(I18NUtils.getI18N("根据模版生成临时文件时发生异常:"+templateId,null));
        }
        log.info("---生成HTML："+path);
        return path;
    }

    @Override
    public String generatePdf(String templateId,T t) {
        Object pdfpassword = null;
        if(t!=null) {
            pdfpassword = BeanWrapperUtils.getPropertyValue(t, "pdfpassword");
        }
        Context context = new Context();
        context.setVariable("data", t);
        String source=this.generateHtml(templateId, t);
        String target=source.replace(".html",".pdf");
        PdfUtils.htmlToPdf(source,target,false);
        if(pdfpassword!=null){
            String protectTarget = source.replace(".html","p.pdf");
            PdfUtils.protectPdf(target,protectTarget,pdfpassword.toString());
            return protectTarget;
        }
        log.info("-----生成PDF："+target);
        return target;
    }

    @Override
    public String generateDoc(String path) {
        return null;
    }

    /**
     * 创建临时文件,并返回其完整路径
     *
     */
    private String createTmpFile(String suffix) {
        String filePath = smeConfiguration.getTmpPath() + DateUtils.getCurrDatetime2MilliSecond();
        FileUtils.createFile(filePath);
        return filePath + suffix;
    }
}
