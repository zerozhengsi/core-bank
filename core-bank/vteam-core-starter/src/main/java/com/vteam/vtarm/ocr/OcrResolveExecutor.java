package com.vteam.vtarm.ocr;

import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

/**
 * Ocr解析执行器 .<br>
 *
 * @author andy.sher
 * @date 2018/8/30 19:39
 */
@Setter
@Scope
public class OcrResolveExecutor {

    /**
     * 执行解析 .
     *
     * @param imagePath 图片地址
     * @return com.vteam.sme.plugin.orc.model.base.OcrModel 解析模型数据
     * @author andy.sher
     * @date 2018/8/31 10:40
     */
    public OcrModel execute(String imagePath, Class<? extends OcrResolveStrategy> clazz) {
        return SpringContextUtils.getBean(clazz).doResolve(imagePath);
    }

}
