package com.vteam.cars.util.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 图片处理器.<br>
 *
 * @author shiping.song
 * @date 2022/11/30 17:02
 */
public abstract class AbstractPictureProcessor {

    /**
     * 默认初始化5个处理器
     */
    private final ConcurrentHashMap<Class<?>, AbstractPictureProcessor> pictureProcessorConcurrentHashMap = new ConcurrentHashMap<>(5);


    /**
     * 2
     */
    protected static final BigDecimal NUM_02 = new BigDecimal(2);

    /**
     * 4
     */
    protected static final BigDecimal NUM_04 = new BigDecimal(4);

    /**
     * 120
     */
    protected static final BigDecimal NUM_120 = new BigDecimal(120);

    /**
     * 200
     */
    protected static final BigDecimal NUM_200 = new BigDecimal(200);

    /**
     * 805
     */
    protected static final BigDecimal NUM_805 = new BigDecimal(805);
    /**
     * 1055
     */
    protected static final BigDecimal NUM_1055 = new BigDecimal(1055);

    /**
     * 处理方法
     *
     * @param material 待处理材料
     * @param param    辅助参数
     * @return 处理结果
     * @author shiping.song
     * @date 2022/11/30 17:03
     */
    public Image process(Image material, PictureProcessParam param) {
        if (Objects.nonNull(param)) {
            int k = param.getColIndex();
            int j = param.getRowIndex();
            BigDecimal watermarkWidth = param.getWatermarkWidth();
            BigDecimal watermarkHeight = param.getWatermarkHeight();
            if (k >= 0 && j >= 0 && watermarkWidth.compareTo(BigDecimal.ZERO) > 0 && watermarkHeight.compareTo(BigDecimal.ZERO) > 0) {
                // 坐标
                material.setAbsolutePosition(NUM_02.multiply(watermarkWidth).multiply(new BigDecimal(k)).floatValue(),
                        NUM_04.multiply(watermarkHeight).multiply(new BigDecimal(j)).floatValue());
                // 水印大小
                material.scaleToFit(watermarkWidth.floatValue(), watermarkHeight.floatValue());
            }
            // 旋转角度
            material.setRotationDegrees(30);
        }
        return material;
    }

    /**
     * 生成图形字典项
     *
     * @author shiping.song
     * @date 2022/11/30 17:10
     */
    public PdfGState buildGstate() {
        PdfGState gstate = new PdfGState();
        gstate.setFillOpacity(0.3f);
        gstate.setStrokeOpacity(0.4f);
        return gstate;
    }

    /**
     * 获取图片梳理参数
     *
     * @author shiping.song
     * @date 2022/11/30 17:30
     */
    public PictureProcessParam buildPictureProcessParam() {
        PictureProcessParam pictureProcessParam = new PictureProcessParam();
        pictureProcessParam.setWatermarkHeight(NUM_120);
        pictureProcessParam.setWatermarkWidth(NUM_200);
        return pictureProcessParam;
    }

    /**
     * 生成水印
     *
     * @param reader        pdf reader
     * @param stamper       额外内容
     * @param watermarkPath 水印路径
     * @author shiping.song
     * @date 2022/11/30 17:59
     */
    public void drawWaterMark(PdfReader reader, PdfStamper stamper, String watermarkPath) throws DocumentException, IOException {

    }

    /**
     * 增加新的处理器
     *
     * @param pictureProcessor 待添加的图片处理器
     * @author shiping.song
     * @date 2022/12/1 20:02
     */
    public void addPictureProcessor(AbstractPictureProcessor pictureProcessor) {
        if (null != pictureProcessor && !pictureProcessorConcurrentHashMap.containsKey(pictureProcessor.getClass())) {
            pictureProcessorConcurrentHashMap.put(pictureProcessor.getClass(), pictureProcessor);
        }
    }

    /**
     * 通过字节码文件获取图片处理器
     *
     * @param pictureProcessorClz 目标图片处理器字节码
     * @param <T>                 目标图片处理器泛型
     * @author shiping.song
     * @date 2022/12/1 20:34
     */
    protected <T> T getPictureProcessor(Class<T> pictureProcessorClz) {
        return (T) pictureProcessorConcurrentHashMap.get(pictureProcessorClz);
    }

    /**
     * 获取当前图片处理器水印路径，如果处理器的水印路径是固定的，则可以借由本方法返回水印路径。
     *
     * @author shiping.song
     * @date 2022/12/3 1:27
     */
    public String getWatermarkPath() {
        return null;
    }

    /**
     * 是否开启自定义处理器为true时则{@link AbstractPictureProcessor#drawWaterMark}处理逻辑生效
     *
     * @author shiping.song
     * @date 2022/11/30 18:04
     */
    public boolean open() {
        return false;
    }

    /**
     * 解析完成后可以借由本方法进行后置处理
     *
     * @param abstractPictureProcessor 图像处理器
     * @author shiping.song
     * @date 2022/12/8 16:06
     */
    public void afterProcess(AbstractPictureProcessor abstractPictureProcessor) {

    }
}
