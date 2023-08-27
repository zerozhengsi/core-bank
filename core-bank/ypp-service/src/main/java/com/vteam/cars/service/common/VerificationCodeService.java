package com.vteam.cars.service.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 验证码业务类 .<br>
 *
 * @author andy.sher
 * @date 2018/7/31 9:41
 */
@Service
@Scope
public class VerificationCodeService {

    /**
     * 验证码图片的宽、高、长度
     */
    private int width;

    private int height;

    private int length;
    /**
     * 用来保存验证码的文本内容
     */
    private String text;
    /**
     * 获取随机数对象
     */
    private Random r = new Random();
    /**
     * 字体数组
     */
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "楷体_GB2312"};
    /**
     * 验证码字符
     */
    private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    /**
     * 获取随机的颜色 .<br>
     *
     * @param
     * @return java.awt.Color 颜色
     * @author andy.sher
     * @date 2018/7/31 14:23
     */
    private Color randomColor() {
        //这里为什么是150，因为当r，g，b都为255时，即为白色，为了好辨认，需要颜色深一点。
        int r = this.r.nextInt(150);
        int g = this.r.nextInt(150);
        int b = this.r.nextInt(150);
        //返回一个随机颜色
        return new Color(r, g, b);
    }

    /**
     * 获取随机字体 .<br>
     *
     * @param
     * @return java.awt.Font 字体
     * @author andy.sher
     * @date 2018/7/31 14:23
     */
    private Font randomFont() {
        //获取随机的字体
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];
        //随机获取字体的样式，0是无样式，1是加粗，2是斜体，3是加粗加斜体
        int style = r.nextInt(4);
        //随机获取字体的大小
        int size = r.nextInt(5) + 24;
        //返回一个随机的字体
        return new Font(fontName, style, size);
    }

    /**
     * 获取随机字符 .<br>
     *
     * @param
     * @return char 随机字符
     * @author andy.sher
     * @date 2018/7/31 14:24
     */
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    /**
     * 画干扰线，验证码干扰线用来防止计算机解析图片 .<br>
     *
     * @param image 图片
     * @return void
     * @author andy.sher
     * @date 2018/7/31 14:24
     */
    private void drawLine(BufferedImage image) {
        int num = 155;
        //定义干扰线的数量
        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            int xl = r.nextInt(width);
            int yl = r.nextInt(height);
            g.setColor(getRandColor(160, 200));
            g.drawLine(x, y, x + xl, y + yl);
        }
    }

    /**
     * 创建图片的方法 .<br>
     *
     * @param
     * @return java.awt.image.BufferedImage 图片
     * @author andy.sher
     * @date 2018/7/31 14:24
     */
    private BufferedImage createImage() {
        //创建图片缓冲区
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 设定图像背景色(因为是做背景，所以偏淡)
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        //返回一个图片
        return image;
    }

    /**
     * 获取验证码图片的方法 .<br>
     *
     * @param
     * @return java.awt.image.BufferedImage 图片
     * @author andy.sher
     * @date 2018/7/31 14:25
     */
    public BufferedImage getImage() {
        BufferedImage image = createImage();
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        drawLine(image);
        //画四个字符即可
        for (int i = 0; i < length; i++) {
            // 随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画
            String s = randomChar() + "";
            //添加到StringBuilder里面
            sb.append(s);
            // 定义字符的x坐标
            float x = i * 1.0F * width / 4;
            //设置字体，随机
            g.setFont(randomFont());
            //设置颜色，随机
            g.setColor(randomColor());
            g.drawString(s, x, height - 5);
        }
        this.text = sb.toString();
        return image;
    }

    /**
     * 给定范围获得随机颜色 .<br>
     *
     * @param fc 最大值
     * @param bc 最大值
     * @return java.awt.Color
     * @author andy.sher
     * @date 2018/7/31 14:21
     */
    private Color getRandColor(int fc, int bc) {
        SecureRandom random = new SecureRandom();
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 获取验证码文本 .<br>
     *
     * @return java.lang.String 验证码
     * @author andy.sher
     * @date 2018/7/31 13:17
     */
    public String getText() {
        return text;
    }

    /**
     * 设置大小 .<br>
     *
     * @param width  宽
     * @param height 高
     * @return void
     * @author andy.sher
     * @date 2018/7/31 14:01
     */
    public void setSize(Integer length, Integer width, Integer height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
}
