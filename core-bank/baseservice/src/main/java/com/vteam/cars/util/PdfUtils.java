package com.vteam.cars.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.cars.plugin.file.upload.FileManager;
import com.vteam.cars.service.fbtx.FbtxApxMService;
import com.vteam.cars.util.pdf.AbstractPictureProcessor;
import com.vteam.cars.util.pdf.DefaultPictureProcessor;
import com.vteam.cars.util.pdf.PdfProcessVo;
import com.vteam.cars.util.pdf.PictureProcessParam;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Html文档转PDF文档工具类
 *
 * @author zack.yin
 * @date 2018/7/11 9:17
 */
@Slf4j
public final class PdfUtils {

    private PdfUtils() {
    }

    private static final String DEFAULT_WATERMARK = "watermark10.png";

    /**
     * word转pdf许可证文件路径
     */
    public static final String LICENSE_PATH = "license/license.xml";

    /**
     * window操作系统
     */
    public static final String WIN_OS = "win";

    /**
     * 系统配置服务类
     */
    private static SmeConfiguration smeConfiguration;

    /**
     * 图片处理器
     */
    private static AbstractPictureProcessor abstractPictureProcessor;

    /**
     * 字符串值存储容器
     */
    private static StringValueContainer stringValueContainer;

    private static FbtxApxMService fbtxApxMService;

    private static FileManager fileManager;

    public static void protectPdf(String sourcePath, String targetPath,String password) {
        try {
            // 加载源 PDF 文件
            PDDocument document = PDDocument.load(new File(sourcePath));

            // 创建密码保护策略
            StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(password, password, new AccessPermission());

            // 设置允许复制和打印
            protectionPolicy.setEncryptionKeyLength(128);
            protectionPolicy.setPermissions(new AccessPermission());

            // 应用密码保护策略
            document.protect(protectionPolicy);

            // 保存加密后的 PDF 文件
            document.save(targetPath);

            // 关闭文档
            document.close();

            log.info("文件："+targetPath+"加密处理");
        } catch (IOException e) {
            SmeAssert.isNull(e, "加密文件："+sourcePath+"发生异常");
        }
    }

    /**
     * HTML文档转为PDF文档(默认不生成页码) .
     *
     * @param sourcePath 转换源路径，可以是硬盘上的路径，也可以是网络路径
     * @param targetPath 转换目标保存路径
     * @param landscape  是否为横向生成方式
     * @return void
     * @author zack.yin
     * @date 2018/7/11 9:20
     */
    public static void htmlToPdf(String sourcePath, String targetPath, boolean landscape) {
        htmlToPdf(sourcePath, targetPath, landscape, false, StringUtils.EMPTY);
    }

    /**
     * HTML文档转为PDF文档
     *
     * @param sourcePath   转换源路径，可以是硬盘上的路径，也可以是网络路径
     * @param targetPath   转换目标保存路径
     * @param landscape    是否为横向生成方式
     * @param pageNumber   是否生成页码
     * @param globalOption 生成pdf时自定义参数
     * @author oscar.yu
     * @date 2021/8/11 16:50
     */
    public static void htmlToPdf(String sourcePath, String targetPath, boolean landscape, boolean pageNumber, String globalOption) {
        // 初始化外部服务
        initService();
        final String htmlConvertToPDFPluginPath = smeConfiguration.getHtmlConvertToPDFPluginPath();
        StringBuilder cmd = new StringBuilder();
        cmd.append(htmlConvertToPDFPluginPath);
        if (landscape) {
            // 横向打印处理
            cmd.append(GlobalConstants.Symbol.SPACE);
            cmd.append("--orientation Landscape");
        }
        // 判断操作系统为win10，禁止缩放，linux服务器下正常
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith(WIN_OS)) {
            cmd.append(GlobalConstants.Symbol.SPACE);
            cmd.append("--disable-smart-shrinking");
        }
        // 生成页码
        if (pageNumber) {
            cmd.append(GlobalConstants.Symbol.SPACE);
            cmd.append("--footer-center [page]/[topage]");
        }
        if (StringUtils.isNotBlank(globalOption)) {
            cmd.append(GlobalConstants.Symbol.SPACE);
            cmd.append(globalOption);
        }
        // 允许使用本地文件，防止某些版本--enable-local-file-access是非默认配置
        cmd.append(GlobalConstants.Symbol.SPACE);
        cmd.append("--enable-local-file-access");
        cmd.append(GlobalConstants.Symbol.SPACE);
        cmd.append(sourcePath);
        cmd.append(GlobalConstants.Symbol.SPACE);
        cmd.append(targetPath);
        Process proc = null;
        try {
            log.info(cmd.toString());
            proc = Runtime.getRuntime().exec(cmd.toString());

            ClearBufferThread error = new ClearBufferThread(proc.getErrorStream());
            ClearBufferThread output = new ClearBufferThread(proc.getInputStream());
            // 修正在k8s服务器中内存溢出的问题
            // TaskExecutor taskExecutor = SpringContextUtils.getBean("smeTaskExecutor", TaskExecutor.class);
            // taskExecutor.execute(error);
            // taskExecutor.execute(output);
            error.run();
            output.run();
            proc.waitFor();
            proc.destroy();
        } catch (Exception e) {
            log.error(PdfUtils.class.getName(), e);
        }
    }

    /**
     * 获取文件内容
     *
     * @param pdfFile PDF文件
     * @return java.lang.String 文件内容
     * @author andy.sher
     * @date 2018/8/4 23:18
     */
    public static String getText(File pdfFile) {
        StringBuffer stringBuffer;
        try (PDDocument document = PDDocument.load(pdfFile)) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();

                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                String[] lines = pdfFileInText.split("\\r?\\n");
                stringBuffer = new StringBuffer();
                for (String line : lines) {
                    stringBuffer.append(line);
                }
                return stringBuffer.toString();
            }
        } catch (IOException e) {
            log.error(PdfUtils.class.getName(), e);
        }

        return StringUtils.EMPTY;
    }

    /**
     * (单独)添加水印 .
     *
     * @param inputFile     需要添加水印的PDF文件
     * @param outputFile    添加水印后的PDF文件
     * @param watermarkPath 水印文件存放路径
     * @return void
     * @author andy.sher
     * @date 2018/10/18 11:45
     */
    public static void waterMark(String inputFile, String outputFile, String watermarkPath) {
        waterMarkAndPagingSeal(inputFile, outputFile, watermarkPath, null);
    }

    /**
     * 只生成水印(路径均为绝对路径)
     *
     * @param inputFilePath            输入文件路径
     * @param outputFilePath           输出文件路径
     * @param watermarkPath            水印路径
     * @param abstractPictureProcessor 图片处理器
     * @author shiping.song
     * @date 2022/11/30 21:36
     */
    public static void waterMark(String inputFilePath, String outputFilePath, String watermarkPath, AbstractPictureProcessor abstractPictureProcessor) {
        waterMarkAndPagingSeal(inputFilePath, outputFilePath, watermarkPath, null, abstractPictureProcessor, true);
    }

    /**
     * 只生成水印
     *
     * @param pdfProcessVo pdf vo
     * @author shiping.song
     * @date 2022/12/3 1:39
     */
    public static void waterMark(PdfProcessVo pdfProcessVo) {
        AbstractPictureProcessor localPictureProcessor = pdfProcessVo.getPictureProcessor();
        SmeAssert.notNull(localPictureProcessor, "图片处理器为空！");
        SmeAssert.notBlank(pdfProcessVo.getInputFilePath(), "输入文件路径为空！");
        SmeAssert.notBlank(pdfProcessVo.getOutputFilePath(), "输出文件路径为空！");
        waterMarkAndPagingSeal(pdfProcessVo.getInputFilePath(), pdfProcessVo.getOutputFilePath(), localPictureProcessor.getWatermarkPath(), null, localPictureProcessor, true);
    }

    /**
     * 单独盖骑缝章(路径均为绝对路径)
     *
     * @param inputFilePath  输入文件路径
     * @param outputFilePath 输出文件路径
     * @param orgRefcode     企业流水号
     * @author shiping.song
     * @date 2022/12/1 19:20
     */
    public static void pagingSeal(Integer orgRefcode, String inputFilePath, String outputFilePath) {
        waterMarkAndPagingSeal(inputFilePath, outputFilePath, null, orgRefcode, null, false);
    }

    /**
     * (单独)盖骑缝章
     *
     * @param inputFile
     * @param outputFile
     * @param orgRefcode
     * @author oscar.yu
     * @date 2021/9/9 11:09
     */
    public static void pagingSeal(String inputFile, String outputFile, Integer orgRefcode) {
        waterMarkAndPagingSeal(inputFile, outputFile, GlobalConstants.Flag.FALSE, orgRefcode);
    }

    /**
     * 添加水印及骑缝章
     *
     * @param inputFile     需要添加水印的PDF文件
     * @param outputFile    添加水印后的PDF文件
     * @param watermarkPath 水印文件存放路径，传0时不添加水印
     * @param orgRefcode    企业流水号，为空时不盖骑缝章
     * @author oscar.yu
     * @date 2021/9/9 09:56
     */
    public static void waterMarkAndPagingSeal(String inputFile, String outputFile, String watermarkPath, Integer orgRefcode) {
        // 初始化外部服务
        initService();
        PdfReader reader = null;
        PdfStamper stamper = null;
        try {
            reader = new PdfReader(inputFile);
            // PdfReader.unethicalreading = true;
            stamper = new PdfStamper(reader, new FileOutputStream(outputFile));

            // 添加水印
            if (!GlobalConstants.Flag.FALSE.equals(watermarkPath)) {
                drawWaterMark(reader, stamper, watermarkPath);
            }

            // 绘制骑缝章
            if (null == orgRefcode || orgRefcode <= 0) {
                drawPagingSeal(reader, stamper, orgRefcode);
            }
        } catch (Exception e) {
            log.error(PdfUtils.class.getName(), e);
        } finally {
            if (null != stamper) {
                try {
                    stamper.close();
                } catch (DocumentException e) {
                    log.error(PdfUtils.class.getName(), e);
                } catch (IOException e) {
                    log.error(PdfUtils.class.getName(), e);
                }
            }
            if (null != reader) {
                reader.close();
            }
        }
    }

    /**
     * 处理添加水印
     *
     * @param reader
     * @param stamper
     * @param watermarkPath
     * @throws MalformedURLException
     * @throws IOException
     * @throws DocumentException
     * @author oscar.yu
     * @date 2021/9/9 11:10
     */
    private static void drawWaterMark(PdfReader reader, PdfStamper stamper, String watermarkPath) throws MalformedURLException, IOException, DocumentException {
        // 获取默认水印图片
        String watermark = smeConfiguration.getWatermark();
        String defaultWm = watermark + GlobalConstants.Symbol.SLASH + GlobalConstants.Symbol.SLASH + DEFAULT_WATERMARK;
        // 判断文件是否存在，存在添加水印
        if (FileUtils.exist(defaultWm, false) || (StringUtils.isNotBlank(watermarkPath) && FileUtils.exist(watermarkPath, false))) {
            PdfGState gstate = new PdfGState();
            gstate.setFillOpacity(0.3f);
            gstate.setStrokeOpacity(0.4f);
            int pageSize = reader.getNumberOfPages() + 1;

            final int watermarkHeight = 34;
            final int watermarkWidth = 108;

            PdfContentByte overContent;
            Image image;
            for (int pageIndex = 1; pageIndex < pageSize; pageIndex++) {
                overContent = stamper.getOverContent(pageIndex);
                overContent.saveState();
                overContent.setGState(gstate);
                for (int j = 0; j < 6; j++) {
                    for (int k = 0; k < 3; k++) {
                        URL resource = smeConfiguration.getClass().getResource(StringUtils.isBlank(watermarkPath) ? defaultWm : watermarkPath);
                        image = Image.getInstance(resource);
                        // 坐标
                        image.setAbsolutePosition(k * (watermarkWidth * 2), j * (watermarkHeight * 4));
                        // 水印大小
                        image.scaleToFit(watermarkWidth, watermarkHeight);
                        // 旋转角度
                        image.setRotationDegrees(30);
                        overContent.addImage(image);
                    }
                }
            }
        }
    }

    /**
     * 添加水印及骑缝章
     *
     * @param inputFilePath            输入文件
     * @param outputFilePath           输出文件路径
     * @param watermarkPath            水印路径
     * @param orgRefcode               企业流水号
     * @param abstractPictureProcessor 图片处理器
     * @param drawWaterFlag            生成水印标识
     * @author shiping.song
     * @date 2022/11/30 21:21
     */
    public static void waterMarkAndPagingSeal(String inputFilePath, String outputFilePath, String watermarkPath, Integer orgRefcode, AbstractPictureProcessor abstractPictureProcessor, boolean drawWaterFlag) {
        // 初始化外部服务
        initService();
        PdfReader reader = null;
        PdfStamper stamper = null;
        try {
            reader = new PdfReader(inputFilePath);
            stamper = new PdfStamper(reader, Files.newOutputStream(Paths.get(outputFilePath)));
            // 添加水印
            if (drawWaterFlag) {
                abstractPictureProcessor = Objects.isNull(abstractPictureProcessor) ? PdfUtils.abstractPictureProcessor : abstractPictureProcessor;
                drawWaterMark(reader, stamper, watermarkPath, abstractPictureProcessor);
            }
            // 绘制骑缝章
            if (null == orgRefcode || orgRefcode <= 0) {
                drawPagingSeal(reader, stamper, orgRefcode);
            }
        } catch (Exception e) {
            log.error(PdfUtils.class.getName(), e);
        } finally {
            if (null != stamper) {
                try {
                    stamper.close();
                } catch (DocumentException | IOException e) {
                    log.error(PdfUtils.class.getName(), e);
                }
            }
            if (null != reader) {
                reader.close();
            }
        }
    }

    /**
     * 生成水印
     *
     * @param reader                   pdf reader
     * @param stamper                  额外内容
     * @param watermarkPath            水印路径
     * @param abstractPictureProcessor 图片处理器
     * @throws MalformedURLException
     * @throws IOException
     * @throws DocumentException
     * @author shiping.song
     * @date 2022/11/30 16:51
     */
    private static void drawWaterMark(PdfReader reader, PdfStamper stamper, String watermarkPath, AbstractPictureProcessor abstractPictureProcessor) throws MalformedURLException, IOException, DocumentException {
        SmeAssert.notNull(abstractPictureProcessor, "水印处理参数为空！");
        // 判断文件是否存在，存在添加水印
        String targetWatermarkPath = getWatermarkPath(watermarkPath);
        if (StringUtils.isNotBlank(targetWatermarkPath)) {
            if (abstractPictureProcessor.open()) {
                abstractPictureProcessor.drawWaterMark(reader, stamper, targetWatermarkPath);
            } else {
                PdfGState gstate = abstractPictureProcessor.buildGstate();
                int pageSize = reader.getNumberOfPages() + 1;
                PdfContentByte overContent;
                Image image;
                for (int pageIndex = 1; pageIndex < pageSize; pageIndex++) {
                    overContent = stamper.getOverContent(pageIndex);
                    overContent.saveState();
                    overContent.setGState(gstate);
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 3; k++) {
                            URL resource = smeConfiguration.getClass().getResource(targetWatermarkPath);
                            image = Image.getInstance(resource);
                            PictureProcessParam pictureProcessParam = abstractPictureProcessor.buildPictureProcessParam();
                            pictureProcessParam.setRowIndex(k);
                            pictureProcessParam.setColIndex(j);
                            image = abstractPictureProcessor.process(image, pictureProcessParam);
                            overContent.addImage(image);
                        }
                    }
                }
            }
        }
    }

    /**
     * 构建水印路径
     *
     * @param watermarkPath 系统水印相对路径 例如：/static/images/a.png
     * @author shiping.song
     * @date 2022/11/30 17:42
     */
    public static String getWatermarkPath(String watermarkPath) {
        boolean existFlag = StringUtils.isNotBlank(watermarkPath) && FileUtils.exist(watermarkPath, false);
        if (existFlag) {
            // 如果存在则返回传入的水印路径
            return watermarkPath;
        }
        // 如果不存在则返回默认的产品水印的路径
        String watermark = smeConfiguration.getWatermark();
        String defaultWaterMark = watermark + GlobalConstants.Symbol.SLASH + GlobalConstants.Symbol.SLASH + DEFAULT_WATERMARK;
        return StringUtils.isNotBlank(defaultWaterMark) && FileUtils.exist(defaultWaterMark, false) ? defaultWaterMark : null;
    }

    /**
     * 处理盖骑缝章
     *
     * @param reader
     * @param stamper
     * @param orgRefcode
     * @throws IOException
     * @throws DocumentException
     * @author oscar.yu
     * @date 2021/9/9 11:10
     */
    private static void drawPagingSeal(PdfReader reader, PdfStamper stamper, Integer orgRefcode) throws IOException, DocumentException {
        if (null == orgRefcode || orgRefcode <= 0) {
            return;
        }
        // 1.获取企业电子签章图片
//        final FbpaOrgM currOrg = fbpaOrgMService.getById(RequestStore.getLoginUser().getOrgRefcode());
//        SmeAssert.notNull(currOrg, "企业信息不存在");
        // 计算印章代号及印章图片资源UUID
//        String sealImgUuid = currOrg.getSealImgUuid();
        String sealImgUuid = "";
        FbtxApxM fbtxApxM = fbtxApxMService.getByResourceUuid(sealImgUuid);
        SmeAssert.notNull(fbtxApxM, "盖骑缝章处理失败，企业印模不存在");
        FbtxApxMVo fbtxApxMVo = BeanUtils.copy(fbtxApxM, FbtxApxMVo.class);
        File sealFile = fileManager.download(fbtxApxMVo);

        // 2.盖骑缝章
        Rectangle pageFirst = reader.getPageSize(1);// 获得第一页
        float height = pageFirst.getHeight();
        float width = pageFirst.getWidth();
        int pageNums = reader.getNumberOfPages();
        // 生成骑缝章切割图片
        Image[] nImage = subImages(sealFile, pageNums);
        // 处理盖章
        int sealWidth = 110;
        int sealHeight = 110;
        int avgWidth = sealWidth / pageNums;
        PdfContentByte overContent;
        Image image;
        for (int n = 1; n <= pageNums; n++) {
            int currWidth = 0;
            if (n == pageNums - 1) {// 最后剩余部分
                currWidth = sealWidth - n * avgWidth;
            } else {// 前n-1块均匀切
                currWidth = avgWidth;
            }
            overContent = stamper.getOverContent(n);// 设置在第几页打印印章
            image = nImage[n - 1];// 选择图片
            // img.setAlignment(1);
            image.scaleAbsolute(currWidth, sealHeight);// 控制图片大小
            // img.scaleToFit(myWidth, sealHeight);
            image.setAbsolutePosition(width - currWidth, height / 2 - sealHeight / 2);// 控制图片位置

            overContent.addImage(image);
        }
    }

    /**
     * 分割骑缝章需要的图片
     *
     * @param imgFile
     * @param num
     * @return
     * @throws IOException
     * @throws BadElementException
     * @author oscar.yu
     * @date 2021/9/9 11:10
     */
    private static Image[] subImages(File imgFile, int num) throws IOException, BadElementException {
        String imgPath = imgFile.getAbsolutePath();
        Image[] nImage = new Image[num];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedImage img = javax.imageio.ImageIO.read(imgFile);
        int height = img.getHeight();
        int width = img.getWidth();
        int avgWidth = width / num;
        for (int i = 0; i < num; i++) {
            BufferedImage subImg;
            if (i == num - 1) {// 最后剩余部分
                subImg = img.getSubimage(i * avgWidth, 0, width - i * avgWidth, height);
            } else {// 前n-1块均匀切
                subImg = img.getSubimage(i * avgWidth, 0, avgWidth, height);
            }

            javax.imageio.ImageIO.write(subImg, imgPath.substring(imgPath.lastIndexOf('.') + 1), out);
            nImage[i] = Image.getInstance(out.toByteArray());
            out.flush();
            out.reset();
        }
        return nImage;
    }

    /**
     * 获取PDF文件页面属性
     *
     * @param pdfFile 文件属性
     * @return com.vteam.vtarm.api.RespEntity 响应对象
     * @author snade.zhou
     * @date 2021/6/23 11:33
     */
    public static List<Map<String, Integer>> getPdfPageInfo(File pdfFile) {
        PdfReader reader = null;
        List<Map<String, Integer>> fileMapList = new ArrayList<Map<String, Integer>>();
        try {
            reader = new PdfReader(new FileInputStream(pdfFile));
            int pageNum = reader.getNumberOfPages();
            for (int i = 1; i <= pageNum; i++) {
                Map<String, Integer> fileMap = new HashMap<String, Integer>();
//                fileMap.put(GlobalConstants.Flag.PAGE_NUM, i);
//                fileMap.put(GlobalConstants.Flag.PAGE_WIDTH, (int) reader.getPageSizeWithRotation(i).getWidth());
//                fileMap.put(GlobalConstants.Flag.PAGE_HEIGHT, (int) reader.getPageSizeWithRotation(i).getHeight());

                fileMapList.add(fileMap);
            }
        } catch (Exception e) {
            log.error(PdfUtils.class.getName(), e);
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        return fileMapList;
    }



    /**
     * 初始化外部服务类
     *
     * @return SmeConfiguration
     */
    private static void initService() {
        if (null == smeConfiguration) {
            smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
        }
        if (null == abstractPictureProcessor) {
            abstractPictureProcessor = SpringContextUtils.getBean(DefaultPictureProcessor.class);
        }
        if (null == stringValueContainer) {
            stringValueContainer = SpringContextUtils.getBean(StringValueContainer.class);
        }
        if (null == fbtxApxMService) {
            fbtxApxMService = SpringContextUtils.getBean(FbtxApxMService.class);
        }
        if (null == fileManager) {
            fileManager = SpringContextUtils.getBean(FileManager.class);
        }
    }
}

@Slf4j
class ClearBufferThread implements Runnable {

    private InputStream is;

    public ClearBufferThread(InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {
        SmeConfiguration smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
        try (InputStreamReader isr = new InputStreamReader(is, smeConfiguration.getEncoding());) {
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                // 输出内容
                log.info(line);
            }
        } catch (IOException e) {
            log.error(ClearBufferThread.class.getName(), e);
        }
    }
}
