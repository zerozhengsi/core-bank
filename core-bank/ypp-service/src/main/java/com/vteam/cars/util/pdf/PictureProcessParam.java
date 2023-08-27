package com.vteam.cars.util.pdf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 图片处理参数.<br>
 *
 * @author shiping.song
 * @date 2022/11/30 17:22
 */
@Getter
@Setter
@ToString
public class PictureProcessParam implements Serializable {
    private static final long serialVersionUID = 4905153799007101501L;

    /**
     * 行索引
     */
    private int rowIndex;

    /**
     * 列索引
     */
    private int colIndex;

    /**
     * pdf页数索引
     */
    private int pageIndex;

    /**
     * 水印图片高
     */
    private BigDecimal watermarkHeight;

    /**
     * 水印图片宽
     */
    private BigDecimal watermarkWidth;

    /**
     * 生成的二维码文件
     */
    private File qrCodeFile;

    /**
     * 二维码字节数组
     */
    private byte[] qrCodeBytes;
}
