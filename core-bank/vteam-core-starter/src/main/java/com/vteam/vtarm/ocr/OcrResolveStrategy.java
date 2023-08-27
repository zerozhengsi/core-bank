package com.vteam.vtarm.ocr;

/**
 * Ocr解析策略 .<br>
 *
 * @author andy.sher
 * @date 2018/8/30 19:40
 */
public interface OcrResolveStrategy {

    /**
     * 解析 .
     *
     * @param imagePath 图片路径
     * @return com.vteam.sme.plugin.orc.model.base.OcrModel 解析结果
     * @author andy.sher
     * @date 2018/8/30 19:43
     */
    OcrModel doResolve(String imagePath) throws OcrUnavailableException;

}
