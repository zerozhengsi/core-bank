package com.vteam.cars.util;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片IO操作工具类 .<br>
 *
 * @author andy.sher
 * @date 2018/8/24 9:01
 */
@Slf4j
public final class ImageIO {

    private static final String DEFAULT_WATERMARK = "watermark10.png";

    private ImageIO() {
    }

    /**
     * 将图片写入到文件 .
     *
     * @param image  图片
     * @param format 格式
     * @param output 文件
     * @return void
     * @author andy.sher
     * @date 2018/8/24 9:07
     */
    public static void write(RenderedImage image, String format, File output) {
        try {
            javax.imageio.ImageIO.write(image, format, output);
        } catch (IOException e) {
            log.error(ImageIO.class.getName(), e);
        }
    }

    /**
     * 从文件读取图片 .
     *
     * @param file 文件
     * @return java.awt.image.BufferedImage 图片流
     * @author andy.sher
     * @date 2019/1/17 17:12
     */
    public static BufferedImage read(File file) {
        try {
            return javax.imageio.ImageIO.read(file);
        } catch (IOException e) {
            log.error(ImageIO.class.getName(), e);
        }
        return null;
    }

    /**
     * 压缩图片（尺寸不变，压缩大小） .
     *
     * @param srcFilePath  源文件路径
     * @param descFilePath 新文件存储路径
     * @return boolean 是否压缩成功
     * @author andy.sher
     * @date 2019/2/25 9:24
     */
    public static boolean compressPic(String srcFilePath, String descFilePath) throws IOException {
        Thumbnails.of(srcFilePath)
                .scale(1f)
                .outputQuality(0.5f)
                .toFile(descFilePath);
        return true;
    }

    /**
     * 将图片写入到图片流 .
     *
     * @param image  图片
     * @param format 格式
     * @param byteArrayOutput 图片流
     * @author olivia.liu
     * @date 2022/8/5 14:12
     */
    public static void write(RenderedImage image, String format, ByteArrayOutputStream byteArrayOutput) {
        try {
            javax.imageio.ImageIO.write(image, format, byteArrayOutput);
        } catch (IOException e) {
            log.error(ImageIO.class.getName(), e);
        }
    }

    /**
     * 从文件读取图片JDK方式 .
     *
     * @param file 文件
     * @return java.awt.image.BufferedImage 图片流
     * @author olivia.liu
     * @date 2022/8/5 14:12
     */
    public static BufferedImage readByToolkit(File file) {
    	try {
			Image src=Toolkit.getDefaultToolkit().getImage(file.getPath());
			return toBufferedImage(src);
		} catch (Exception e) {
			log.error(ImageIO.class.getName(), e);
		}
        return null;
    }
    
    /**
     * 将图片转换为图片流（解决图片蒙上红色阴影或有黑边问题）
     * @param image 图片
     * @return java.awt.image.BufferedImage 图片流
     * @author olivia.liu
     * @date 2022/8/5 14:12
     */
	private static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		// 创建一个与屏幕相适应的BufferedImage对象
		image = new ImageIcon(image).getImage();
		BufferedImage bimage = null;
		if (bimage == null) {
			// 创建一个与屏幕相适应的BufferedImage对象
			int type = BufferedImage.TYPE_INT_RGB;
			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}
		// 将图片转换为图片流
		Graphics graphics = bimage.createGraphics();
		// 设置图片背景为白色，解决图片有黑边框问题
		graphics.drawImage(image, 0, 0, Color.white,null);
		graphics.dispose();
		return bimage;
	}

    /**
     * 添加水印 .
     *
     * @param filePath          需要添加水印的文件
     * @param watermarkPathFile 添加水印后的文件
     * @return boolean
     * @author zack.yin
     * @date 2019/3/25 13:08
     */
    public static boolean waterMark(String filePath, String watermarkPathFile) {
        SmeConfiguration smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
        String watermark = smeConfiguration.getWatermark();
        StringValueContainer stringValueContainer = SpringContextUtils.getBean(StringValueContainer.class);
        String defaultWm = watermark + GlobalConstants.Symbol.SLASH  + GlobalConstants.Symbol.SLASH + DEFAULT_WATERMARK;
        // 判断文件是否存在,存在添加水印
        if (FileUtils.exist(defaultWm, false)) {
            InputStream fis = smeConfiguration.getClass().getResourceAsStream(defaultWm);
            BufferedImage bi;
            try {
                bi = javax.imageio.ImageIO.read(fis);

                Thumbnails.of(new File(filePath)).scale(1f).watermark(Positions.CENTER, bi, 1).toFile(watermarkPathFile);
            } catch (IOException e) {
                log.error(ImageIO.class.getName(), e);
            }
        } else {
            return false;
        }

        return true;
    }
}
