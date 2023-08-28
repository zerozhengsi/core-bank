package com.vteam.cars.util.pdf;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * pdf处理器视图对象.<br>
 *
 * @author shiping.song
 * @date 2022/12/3 1:50
 */
@Builder
@Getter
@ToString
public class PdfProcessVo implements Serializable {
    private static final long serialVersionUID = 5992516879647506281L;

    /**
     * 输入文件路径(绝对路径)
     */
    private String inputFilePath;

    /**
     * 输出文件路径(绝对路径)
     */
    private String outputFilePath;

    /**
     * 图片处理器
     */
    private AbstractPictureProcessor pictureProcessor;
}
