package com.vteam.cars.util;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.WeiXinConstants;
import com.vteam.cars.plugin.weixin.configure.WeixinPropertiesUtils;
import com.vteam.vtarm.cache.StringValueContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 二维码工具类 .<br>
 *
 * @author andy.sher
 * @date 2019/1/17 17:02
 */
@Slf4j
@Component
public class QRCodeUtils {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    @Resource(type = WeixinPropertiesUtils.class)
    private WeixinPropertiesUtils weixinPropertiesUtils;

    /**
     * 生成二维码 .
     *
     * @param width   宽度
     * @param height  高度
     * @param content 二维码内容
     * @return java.io.File 二维码文件
     * @author andy.sher
     * @date 2019/1/18 10:02
     */
    public File build(int width, int height, String content) {
        Map<EncodeHintType, Object> hints = new HashMap<>(1);
        hints.put(EncodeHintType.CHARACTER_SET, smeConfiguration.getEncoding());
        // 生成矩阵
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            log.error(QRCodeUtils.class.getName(), e);
        }
        String filePath = smeConfiguration.getTmpPath() + UUID.randomUUID().toString();
        FileUtils.createDir(filePath);
        Path path = FileSystems.getDefault().getPath(filePath, UUID.randomUUID().toString());
        // 输出图像
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, GlobalConstants.FileFormat.PNG, path);
            return path.toFile();
        } catch (IOException e) {
            log.error(QRCodeUtils.class.getName(), e);
        }
        return null;
    }

    /**
     * 解析二维码 .
     *
     * @param file 二维码文件
     * @return java.lang.String 二维码内容
     * @author andy.sher
     * @date 2019/1/18 10:01
     */
    public String resolve(File file) {
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>(1);
            hints.put(DecodeHintType.CHARACTER_SET, smeConfiguration.getEncoding());
            // 对图像进行解码
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);
            return result.getText();
        } catch (NotFoundException e) {
            log.error(QRCodeUtils.class.getName(), e);
        }
        return null;
    }

    /**
     * 获取微信小程序token
     *
     * @param
     * @return java.lang.String
     * @author hang.xia
     * @date 2020/3/9 17:13
     */
    public String getToken() {
        try {
            String[] appConfigArr = weixinPropertiesUtils.getAppConfig(WeiXinConstants.QRCode.CONST_APPID_KEY);
            if (null != appConfigArr && appConfigArr.length == 2) {
                String appid = appConfigArr[0];
                String secret = appConfigArr[1];
                String grant_type = WeiXinConstants.QRCode.CONST_GRANT_TYPE;
                Map<String, String> map = new LinkedHashMap<>();
                map.put("grant_type", grant_type);
                map.put("appid", appid);
                map.put("secret", secret);
                String rt = UrlUtils.sendPost(WeiXinConstants.URL, map);
                JSONObject json = JSONObject.parseObject(rt);
                if (json.getString("access_token") != null || json.getString("access_token") != "") {
                    return json.getString("access_token");
                } else {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            log.error("获取微信小程序token报错", e);
            return null;
        }
    }

    /**
     * 获取小程序二维码图片
     *
     * @param uuid, hashMap, accessToken, request
     * @return java.lang.String
     * @author hang.xia
     * @date 2020/3/9 17:14
     */
    public String getMiniqrQr(String uuid, String frUuid, JSONObject hashMap, String accessToken, HttpServletRequest request) {
        String tempPath = smeConfiguration.getTmpPath() + uuid + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.PNG;
        try {
            URL url = new URL(WeiXinConstants.QR_CODE_URL + accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            String userName = hashMap.getString("userName");
            String idNo = hashMap.getString("idNo");
            String idFileUuid = hashMap.getString("idFileUuid");
            String path = "a=" + userName + "&" + "b=" + idNo + "&" + "c=" + idFileUuid + "&" + "e=" + frUuid;
            JSONObject paramJson = new JSONObject();
            paramJson.put("path", "?" + path); //携带参数
            paramJson.put("width", 430);
            paramJson.put("is_hyaline", true);
            paramJson.put("auto_color", true);
            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            OutputStream os = new FileOutputStream(new File(tempPath));
            int len;
            byte[] arr = new byte[2048];
            while ((len = bis.read(arr)) != -1) {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        } catch (Exception e) {
            log.error("获取小程序二维码图片报错", e);
        }
        return tempPath;
    }

}
