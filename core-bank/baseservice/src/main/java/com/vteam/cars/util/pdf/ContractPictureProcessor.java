package com.vteam.cars.util.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.util.PdfUtils;
import com.vteam.cars.util.SmeAssert;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.io.IOException;
import java.net.URL;

/**
 * 业务协议合同水印加工.<br>
 *
 * @author shiping.song
 * @date 2022/11/30 17:05
 */
@Component
public class ContractPictureProcessor extends AbstractPictureProcessor {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Override
    public Image process(Image material, PictureProcessParam param) {
        // 坐标
        material.setAbsolutePosition(0, 0);
        return material;
    }

    @Override
    public PdfGState buildGstate() {
        PdfGState pdfGstate = super.buildGstate();
        pdfGstate.setFillOpacity(0.75f);
        return pdfGstate;
    }

    @Override
    public void drawWaterMark(PdfReader reader, PdfStamper stamper, String watermarkPath) throws DocumentException, IOException {
        if (this.open()) {
            PdfGState gState = this.buildGstate();
            int pageSize = reader.getNumberOfPages() + 1;
            PdfContentByte overContent;
            Image watermarkImg;
            Image qrCodeImg;
            QRCodePictureProcessor qrCodePictureProcessor = this.getPictureProcessor(QRCodePictureProcessor.class);
            for (int pageIndex = 1; pageIndex < pageSize; pageIndex++) {
                overContent = stamper.getOverContent(pageIndex);
                overContent.saveState();
                overContent.setGState(gState);
                String targetWaterPath = PdfUtils.getWatermarkPath(watermarkPath);
                SmeAssert.notBlank(targetWaterPath, "合同制作水印为空，请联系系统管理员！");
                URL imageWatermarkResource = smeConfiguration.getClass().getResource(targetWaterPath);
                watermarkImg = Image.getInstance(imageWatermarkResource);
                // 加水印
                watermarkImg = this.process(watermarkImg, null);
                overContent.addImage(watermarkImg);
                // 只第一页加二维码,  获取二维码处理器
                boolean hasQrCodeWatermark = pageIndex == 1 && null != qrCodePictureProcessor && null != qrCodePictureProcessor.buildPictureProcessParam() && null != qrCodePictureProcessor.buildPictureProcessParam().getQrCodeBytes();
                if (hasQrCodeWatermark) {
                    PictureProcessParam qrCodePictureProcessParam = qrCodePictureProcessor.buildPictureProcessParam();
                    qrCodeImg = Image.getInstance(qrCodePictureProcessParam.getQrCodeBytes());
                    qrCodeImg = qrCodePictureProcessor.process(qrCodeImg, null);
                    overContent.saveState();
                    overContent.setGState(qrCodePictureProcessor.buildGstate());
                    overContent.addImage(qrCodeImg);
                }
            }
        }
    }

    @Override
    public String getWatermarkPath() {
        return "/static/images/watermark/contract/watermark.png";
    }

    @Override
    public boolean open() {
        return true;
    }

    @Override
    public void afterProcess(AbstractPictureProcessor abstractPictureProcessor) {
        if (abstractPictureProcessor instanceof QRCodePictureProcessor) {
            QRCodePictureProcessor qrCodePictureProcessor = (QRCodePictureProcessor) abstractPictureProcessor;
            qrCodePictureProcessor.remove();
        }
    }
}
