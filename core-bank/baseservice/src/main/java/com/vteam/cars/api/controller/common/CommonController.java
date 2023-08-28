package com.vteam.cars.api.controller.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.*;
import com.vteam.cars.entity.vo.*;
import com.vteam.cars.plugin.activemq.producer.MessageProducer;
import com.vteam.cars.plugin.container.SmsSendRecordContainer;
import com.vteam.cars.plugin.file.upload.FileManager;
import com.vteam.cars.plugin.file.upload.enums.FileBKeyEnum;
import com.vteam.cars.plugin.file.upload.model.FileUploadModel;
import com.vteam.cars.plugin.message.model.MessageModel;
import com.vteam.cars.service.common.CommonService;
import com.vteam.cars.service.common.VerificationCodeService;
import com.vteam.cars.service.fipa.FipaCcyMService;
import com.vteam.cars.service.fipa.FipaCtyMService;
import com.vteam.cars.service.fipa.FipaNumMService;
import com.vteam.cars.util.*;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.data.DataProvider;
import com.vteam.vtarm.security.GrantAuthorization;
import com.vteam.vtarm.utils.HttpRequestUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 公共模块控制器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/24 13:26
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    /**
     * 验证码验证次数信息
     */
    public final static String CHECK_CODE_TRY_COUNT = "check-code-try-count-%s";

    /**
     * 验证码业务信息标记，用于生成临时授权码
     */
    public final static String CHECK_CODE_SCOPE = "check-code-scope";


    @Resource(type = DataProvider.class)
    private DataProvider dataProvider;

    @Resource(type = VerificationCodeService.class)
    private VerificationCodeService verificationCodeBuilder;

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Resource(type = MessageProducer.class)
    private MessageProducer messageProducer;

    @Resource(type = SmsSendRecordContainer.class)
    private SmsSendRecordContainer smsSendRecordContainer;

    @Resource(type = CommonService.class)
    private CommonService commonService;

    @Resource(type = QRCodeUtils.class)
    private QRCodeUtils qrCodeUtils;


    @Resource(type = FileManager.class)
    private FileManager fileManager;

    @Resource(type = FipaNumMService.class)
    private FipaNumMService fipaNumMService;

    @Resource(type = FipaCtyMService.class)
    private FipaCtyMService fipaCtyMService;

    @Resource(type = FipaCcyMService.class)
    private FipaCcyMService fipaCcyMService;


    /**
     * 发送手机验证码 .
     *
     * @param reqEntity 请求对象
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/24 13:35
     */
    @PostMapping("/doSendPhoneCode")
    public RespEntity doSendPhoneCode(@RequestBody ReqEntity reqEntity) {
        JSONObject requestData = JSON.parseObject(reqEntity.getData(), JSONObject.class);
        String mobilephone = requestData.getString(GlobalConstants.Flag.MOBILEPHONE);
        SmeAssert.notBlank(mobilephone, "手机号不能为空。");
        // 校验手机号是否频繁发送短信
        SmeAssert.isTrue(smsSendRecordContainer.setIfAbsent(mobilephone, mobilephone, 60), "短信验证码一分钟内只能获取一次哦。");

        // 生成UUID和验证码
        String uuid = UUID.randomUUID().toString();
        String code;
        if (GlobalConstants.Flag.TRUE.equals(smeConfiguration.getSmsSwitch())) {
            code = StringUtils.getRandomNum(6);
        } else {
            code = "111111";
        }

        Set<SipaBurMVo> receiver = new HashSet<>();
        SipaBurMVo sipaBurMVo = new SipaBurMVo();
        sipaBurMVo.setMobilephone(mobilephone);
        receiver.add(sipaBurMVo);
        Map<String, Object> model = new HashMap<>(2);
        model.put("code", code);
        model.put("timeout", TimeUnit.SECONDS.toMinutes(TimeoutConstants.MOBILE_PHONE_CODE_TIMEOUT));
        MessageModel messageModel = MessageModel.build().createMessageModel(null, receiver,
                MessageTemplateConstants.GLOBAL_DHYZM.getTemplateName(), FieldConstant.FbtxNot.PUSH_TYPE_SYSTEM, null, model,
                new MessageModel.Channel[]{MessageModel.Channel.SMS});
        // 设置为验证码类型
        messageModel.setType(GlobalConstants.Flag.TRUE);
        messageProducer.send(messageModel);

        // 存入缓存，格式为：验证码_手机号
        final String saveCode = code + GlobalConstants.Symbol.UNDERLINE + mobilephone;
        stringValueContainer.set(uuid, saveCode, TimeoutConstants.VERIFICATION_CODE_TIMEOUT);

        // 存入短信发送记录
        smsSendRecordContainer.set(mobilephone, LocalDateTime.now().format(DateUtils.FMT_SECOND), 60);

        JSONObject data = new JSONObject();
        data.put(GlobalConstants.Flag.UUID, uuid);
        return RespEntity.ok(data);
    }

    /**
     * 获取图片验证码 .<br>
     *
     * @param length 长度
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/24 14:21
     */
    @GetMapping("/getImageVerificationCode/{length}/{width}/{height}")
    public RespEntity getImageVerificationCode(@PathVariable("length") Integer length, @PathVariable("width") Integer width, @PathVariable("height") Integer height) {
        // 获取验证码内容
        String uuid = UUID.randomUUID().toString();
        // 获取验证码图片
        verificationCodeBuilder.setSize(length, width, height);
        BufferedImage image = verificationCodeBuilder.getImage();
        // 存入缓存
        stringValueContainer.set(uuid, verificationCodeBuilder.getText(), TimeoutConstants.VERIFICATION_CODE_TIMEOUT);
        String tempPath = smeConfiguration.getTmpPath() + uuid + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.JPEG;
        // 存入临时目录
        File file = new File(tempPath);
        ImageIO.write(image, GlobalConstants.FileFormat.JPEG, file);

        JSONObject data = new JSONObject();
        data.put("image", Base64Utils.getImageStrFromPath(tempPath));
        data.put(GlobalConstants.Flag.UUID, uuid);

        return RespEntity.ok(data);
    }

    /**
     * 校验手机验证码 .<br>
     *
     * @param reqEntity 请求对象
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/26 14:20
     */
    @GrantAuthorization(temporarily = true, scope = CHECK_CODE_SCOPE)
    @PostMapping("/doCheckPhoneVerificationCode")
    public RespEntity doCheckPhoneVerificationCode(@RequestBody ReqEntity reqEntity) {
        // 校验手机验证码
        JSONObject data = commonService.checkVerificationCodeCommon(reqEntity, GlobalConstants.Flag.MOBILEPHONE);
        return RespEntity.ok(data);
    }

    /**
     * 验证图片验证码 .<br>
     *
     * @param reqEntity 请求对象
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/31 10:27
     */
    @GrantAuthorization(temporarily = true)
    @PostMapping("/doCheckImageVerificationCode")
    public RespEntity doCheckImageVerificationCode(@RequestBody ReqEntity reqEntity) {
        // 校验图片验证码
        commonService.checkVerificationCodeCommon(reqEntity, GlobalConstants.Flag.IMAGE);
        return RespEntity.ok();
    }



    /**
     * 生成二维码 .
     *
     * @param width   二维码宽度
     * @param height  二维码高度
     * @param content 二维码内容
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2019/1/17 17:52
     */
    @GetMapping("/getQRCode/{width}/{height}/{content}")
    public void getQRCode(@PathVariable("width") Integer width, @PathVariable("height") Integer height, @PathVariable("content") String content, HttpServletResponse response) {
        // 生成二维码
        File file = qrCodeUtils.build(width, height, Base64Utils.decode(content));

        SmeAssert.notNull(file, "二维码生成失败。");

        // 上传二维码
        FileUploadModel fileUploadModel = FileUploadModel.createFileUploadModel(content, RequestStore.getLoginUser().getUserid(), FileBKeyEnum.PAYMENT_BFD.getCode(), null, UUID.randomUUID().toString(), file);
        fileManager.upload(fileUploadModel);

        // 响应客户端
        FileUtils.copy(file, response);

    }
    /**
     *
     * 生成二维码
     * @param width, height, content, response
     * @return void
     * @author wanjy.wan
     * @date 2020/6/16 14:16
     */
    @GetMapping("/getQRCodeImg/{width}/{height}/{content}")
    public void getQRCodeImg(@PathVariable("width") Integer width, @PathVariable("height") Integer height, @PathVariable("content") String content, HttpServletResponse response) {
        // 生成二维码
        File file = qrCodeUtils.build(width, height, Base64Utils.decode(content));

        SmeAssert.notNull(file, "二维码生成失败。");

        // 响应客户端
        FileUtils.copy(file, response);
    }
    /**
     * 将二维码以图片的形式返回给前端
     *
     * @param  width, height, content
     * @return com.vteam.cars.api.entity.RespEntity
     * @author wanjy.wan
     * @date 2020/7/3 11:15
     */
    @GetMapping("/getImageQrCode/{width}/{height}/{content}")
    public RespEntity getImageQrCode( @PathVariable("width") Integer width, @PathVariable("height") Integer height,@PathVariable("content") String content) {
        // 生成二维码
        File fileQr = qrCodeUtils.build(width, height, Base64Utils.decode(content));

        // 获取验证码图片
        BufferedImage image = ImageIO.read(fileQr);

        ImageIO.write(image, GlobalConstants.FileFormat.JPEG, fileQr);
        JSONObject data = new JSONObject();
        data.put("imageQrCode", Base64Utils.getImageStrFromFile(fileQr));

        return RespEntity.ok(data);
    }

    /**
     * 获取交易流水号 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2019/1/29 14:31
     */
    @GetMapping(value = "/getCurrentTxNo")
    public RespEntity getCurrentTxNo() {
        String currentTxNo = fipaNumMService.getCurrentTxNo();
        JSONObject data = new JSONObject();
        data.put("currentTxNo", currentTxNo);
        return RespEntity.ok(data);
    }


    /**
     * 获取服务端系统配置信息
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/9/4 13:06
     */
    @GetMapping("/getServerSystemConfig")
    public RespEntity getServerSystemConfig(HttpServletRequest request) {
        final String W_SYSTEM_LANGUAGE = smeConfiguration.getSystemLanguage();
        final String P_SYSTEM_LANGUAGE_OPEN = smeConfiguration.getSystemLanguageOpen();
        final String SYSTEM_PLATFORM_NAME = smeConfiguration.getSystemPlatformName();

        JSONObject data = new JSONObject();
        data.put(SmeConfigConstants.P_SYSTEM_LANGUAGE_OPEN, P_SYSTEM_LANGUAGE_OPEN);
        data.put(SmeConfigConstants.W_SYSTEM_LANGUAGE, W_SYSTEM_LANGUAGE);
        data.put(SmeConfigConstants.W_SYSTEM_PLATFORM_NAME, SYSTEM_PLATFORM_NAME);

        return RespEntity.ok(data);
    }

    /**
     * 获取国家列表 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/9/14 11:36
     */
    @GetMapping("/getCountryList")
    public RespEntity getCountryList() {
        List<FipaCtyMVo> fipaCtyMVoList = fipaCtyMService.listCountry();

        JSONObject data = new JSONObject();
        data.put("countryList", fipaCtyMVoList);

        return RespEntity.ok(data);
    }

    /**
     * 获取币种列表 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/9/14 15:01
     */
    @GetMapping("/getCurrencyList")
    public RespEntity getCurrencyList() {
        List<FipaCcyMVo> fipaCcyMVoList = fipaCcyMService.listCurrency();

        JSONObject data = new JSONObject();
        data.put("currencyList", fipaCcyMVoList);

        return RespEntity.ok(data);
    }

    /**
     * 获取IP地址和位置信息 .
     *
     * @param request
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2018/9/20 10:55
     */
    @GetMapping("/getIpAddress")
    public RespEntity getIpAddress(HttpServletRequest request) {
        String ip = HttpRequestUtils.getRemoteIP(request);
        JSONObject data = new JSONObject();
        data.put("ip", ip);
        // 20220531 高德通知后续要收费,暂停高德定位调用
        // data.put("location", LocationUtils.getPlaceInfoByIP(ip));
        return RespEntity.ok(data);
    }




}
