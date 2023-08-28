package com.vteam.cars.service.common;

/**
 * @className: TemplatesService
 * @Description: 处理各种类型模版,与业务系统无关
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/6/5 9:40
 */
public interface TemplatesService<T> {
    public String generateHtml(String templateId,T t);

    public String generatePdf(String templateId,T t);

    public String generateDoc(String path);

}
