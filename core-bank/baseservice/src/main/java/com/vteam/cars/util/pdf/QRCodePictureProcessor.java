package com.vteam.cars.util.pdf;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfGState;
import com.vteam.cars.util.FileUtils;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 二维码处理策略.<br>
 *
 * @author shiping.song
 * @date 2022/12/1 13:39
 */
@Component
public class QRCodePictureProcessor extends AbstractPictureProcessor {

    /**
     * 图片处理器本地线程参数
     */
    private final ThreadLocal<PictureProcessParam> pictureProcessParamThreadLocal = new ThreadLocal<>();

    /**
     * 5.5
     */
    private static final BigDecimal NUM_5_DOT_5 = BigDecimal.valueOf(5.5d);

    @Override
    public Image process(Image material, PictureProcessParam param) {
        // 坐标
        material.setAbsolutePosition(NUM_805.subtract(BigDecimal.valueOf(material.getWidth())).floatValue(), NUM_1055.subtract(BigDecimal.valueOf(material.getHeight())).floatValue());
        // 等比例缩放
        material.scaleAbsolute(BigDecimal.valueOf(material.getWidth()).divide(NUM_5_DOT_5, RoundingMode.CEILING).floatValue(), BigDecimal.valueOf(material.getHeight()).divide(NUM_5_DOT_5, RoundingMode.CEILING).floatValue());
        return material;
    }

    @Override
    public PdfGState buildGstate() {
        PdfGState pdfGstate = super.buildGstate();
        pdfGstate.setFillOpacity(1f);
        return pdfGstate;
    }

    @Override
    public PictureProcessParam buildPictureProcessParam() {
        return pictureProcessParamThreadLocal.get();
    }

    /**
     * 设置辅助图片处理参数
     *
     * @param pictureProcessParam 图片参数
     * @author shiping.song
     * @date 2022/12/1 20:49
     */
    public void setPictureProcessParam(@NonNull PictureProcessParam pictureProcessParam) {
        if (null == pictureProcessParamThreadLocal.get()) {
            pictureProcessParamThreadLocal.set(pictureProcessParam);
        }
    }

    /**
     * 设置二维码文件
     *
     * @param qrCodeFile 二维码文件
     * @author shiping.song
     * @date 2022/12/1 20:57
     */
    public void setQrCodeFile(File qrCodeFile) {
        if (null != qrCodeFile && qrCodeFile.exists()) {
            try {
                setQrCodeFile(FileUtils.readAllBytes(new FileInputStream(qrCodeFile)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 设置二维码字节码数组
     *
     * @param qrCodeBytes 二维码字节码数组
     * @author shiping.song
     * @date 2022/12/1 21:45
     */
    public void setQrCodeFile(byte[] qrCodeBytes) {
        PictureProcessParam pictureProcessParam = new PictureProcessParam();
        pictureProcessParam.setQrCodeBytes(qrCodeBytes);
        setPictureProcessParam(pictureProcessParam);
    }


    /**
     * 清除图片处理器参数
     *
     * @author shiping.song
     * @date 2022/12/1 20:45
     */
    public void remove() {
        pictureProcessParamThreadLocal.remove();
    }

}
