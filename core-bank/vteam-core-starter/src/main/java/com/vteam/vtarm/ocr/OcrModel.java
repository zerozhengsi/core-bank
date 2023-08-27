package com.vteam.vtarm.ocr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Ocr模型 .<br>
 *
 * @author andy.sher
 * @date 2018/8/30 19:33
 */
@Getter
@Setter
@ToString
public abstract class OcrModel implements Serializable {

    private static final long serialVersionUID = 5835145094780392459L;

    /**
     * 错误代号
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 图像方向，当detect_direction=true时存在。- -1:未定义，- 0:正向，- 1: 逆时针90度，- 2:逆时针180度，- 3:逆时针270度
     */
    private Integer direction;

    /**
     * 输入参数 detect_risk = true 时，则返回该字段识别身份证类型: normal-正常身份证；copy-复印件；temporary-临时身份证；screen-翻拍；unknow-其他未知情况
     */
    private String riskType;

    /**
     * 如果参数 detect_risk = true 时，则返回此字段。如果检测身份证被编辑过，该字段指定编辑软件名称，如:Adobe Photoshop CC 2014 (Macintosh),如果没有被编辑过则返回值无此参数
     */
    private String editTool;

    /**
     * 识别结果数，表示words_result的元素个数
     */
    private Integer wordsResultNum;

    /**
     * 识别状态 normal-识别正常;reversed_side-未摆正身份证;non_idcard-上传的图片中不包含身份证;blurred-身份证模糊;over_exposure-身份证关键字段反光或过曝;unknown-未知状态
     */
    private String image_status;

}
