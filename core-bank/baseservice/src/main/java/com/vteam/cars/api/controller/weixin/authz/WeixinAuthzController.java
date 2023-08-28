package com.vteam.cars.api.controller.weixin.authz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.plugin.remote.client.weixin.WeixinSessionHttpsClient;
import com.vteam.cars.service.common.CommonService;
import com.vteam.cars.service.cspa.CspaFunMService;
import com.vteam.cars.service.flog.FlogOptMService;
import com.vteam.cars.service.sipa.SipaBtrMService;
import com.vteam.cars.service.sipa.SipaBurMService;
import com.vteam.cars.service.sipa.SipaRolMService;
import com.vteam.cars.service.sipa.SipaRtfMService;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.security.TokenContainer;
import com.vteam.vtarm.utils.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 微信登录模块控制器 .<br>
 *
 * @author toby.tang
 * @date 2023/2/15 19:08
 */
@Slf4j
@RestController
@RequestMapping("/weixin/authz")
public class WeixinAuthzController {

    public static final String BUSINESS_CODE = "business_code";
    public static final String BUSINESS_MSG = "business_msg";
	public static final String LOGIN_REGISTRY = "login-registry";

    @Resource(type = SipaBurMService.class)
    private SipaBurMService sipaBurMService;

    @Resource(type = SipaRtfMService.class)
    private SipaRtfMService sipaRtfMService;

    @Resource(type = CspaFunMService.class)
    private CspaFunMService cspaFunMService;

    @Resource(type = FlogOptMService.class)
    private FlogOptMService flogOptMService;

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;


    @Resource(type = CommonService.class)
    private CommonService commonService;

    @Resource(type = WeixinSessionHttpsClient.class)
    private WeixinSessionHttpsClient weixinSessionHttpsClient;

    @Resource
    private SmeConfiguration smeConfiguration;

    @Resource(type = SipaBtrMService.class)
    private SipaBtrMService sipaBtrMService;

    @Resource(type = SipaRolMService.class)
    private SipaRolMService sipaRolMService;

    @Resource
    private TokenContainer tokenContainer;

    @Resource
    private MapContainer mapContainer;


    /**
     * 微信用户注册 .
     *
     * @param reqEntity
     * @return com.vteam.vtarm.api.RespEntity
     * @author andy.sher
     * @date 2019/11/27 17:13
     */
    @PostMapping("/doCheckExistWxUser")
    public RespEntity doCheckExistWxUser(@RequestBody ReqEntity reqEntity) {
        JSONObject requestData = JSONObject.parseObject(reqEntity.getData());
        JSONObject responseData = new JSONObject();

        SipaBurMVo sipaBurMVo = null;
        String sessionInfoStr = weixinSessionHttpsClient.execute(requestData.getString("js_code"));
        if (StringUtils.isNotBlank(sessionInfoStr)) {
            JSONObject sessionInfo = JSONObject.parseObject(sessionInfoStr);
            String openid = sessionInfo.getString("openid");
            if (StringUtils.isNotBlank(openid)) {
                sipaBurMVo = sipaBurMService.getUserInfoByOpenid(openid, FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL);
            }
        }

        if (null == sipaBurMVo) {
            responseData.put("result", GlobalConstants.Flag.FALSE);
        } else {
            responseData.put("result", GlobalConstants.Flag.TRUE);
        }

        return RespEntity.ok(responseData);
    }

    /**
     * 微信用户注册 .
     *
     * @param reqEntity
     * @return com.vteam.vtarm.api.RespEntity
     * @author andy.sher
     * @date 2019/11/27 17:14
     */
    @PostMapping("/doRegisterWxUser")
    public RespEntity doRegisterWxUser(@RequestBody ReqEntity reqEntity) {
        JSONObject requestData = JSONObject.parseObject(reqEntity.getData());
        JSONObject responseData = new JSONObject();

        String sessionInfoStr = weixinSessionHttpsClient.execute(requestData.getString("js_code"));
        JSONObject sessionInfo = JSONObject.parseObject(sessionInfoStr);

        String encryptedData = requestData.getString("encryptedData");
        String aesIv = requestData.getString("iv");
        String aesKey = sessionInfo.getString("session_key");

        if (StringUtils.isNotBlank(aesIv) && StringUtils.isNotBlank(aesKey)) {
            String openid = sessionInfo.getString("openid");

            // 微信小程序使用此方式解密
            String userInfo = AesUtils.doDecryptWx(encryptedData, aesKey, aesIv);

            if (StringUtils.isNotBlank(userInfo)) {
                JSONObject userData = JSONObject.parseObject(userInfo);

                SipaBurMVo sipaBurMVo = sipaBurMService.getUserInfoByOpenid(openid, FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL);
                if (null == sipaBurMVo) {
                    sipaBurMVo = new SipaBurMVo();
                    sipaBurMVo.setUserid(StringUtils.generateSequenceNo());
                    sipaBurMVo.setLoginId(sipaBurMVo.getUserid());
                    sipaBurMVo.setUseFlag(FieldConstant.SipaBurM.USE_FLAG_ENABLE);
                    sipaBurMVo.setCreateDate(LocalDateTime.now());
                    sipaBurMVo.setAdminFlag(GlobalConstants.Flag.TRUE);
                    String mobilephone = userData.getString("phoneNumber");
                    sipaBurMVo.setMobilephone(mobilephone);
                    sipaBurMVo.setWeixinOpenId(openid);
                    sipaBurMVo.setSystemType(FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL);
                    sipaBurMService.save(sipaBurMVo);
                    responseData.put(BUSINESS_CODE, GlobalConstants.ArabicNumeral.N1);
                    responseData.put(BUSINESS_MSG, "用户已保存。");
                } else {
                    responseData.put(BUSINESS_CODE, GlobalConstants.ArabicNumeral.N2);
                    responseData.put(BUSINESS_MSG, "用户已存在。");
                }
            }
        }

        return RespEntity.ok(responseData);
    }


    /**
     * 检查手机号是否存在 .<br>
     *
     * @param reqEntity 请求对象
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/26 18:10
     */
    @PostMapping("/doCheckExistMobilephone")
    public RespEntity doCheckExistMobilephone(@RequestBody ReqEntity reqEntity) {
        SipaBurMVo sipaBurMVo = JSONObject.parseObject(reqEntity.getData(), SipaBurMVo.class);
        final String mobilephone = sipaBurMVo.getMobilephone();
        SmeAssert.notBlank(mobilephone, "手机号码为空");

        int count = sipaBurMService.countByMobilephone(mobilephone);

        SmeAssert.eq(count, 0, "手机号已存在。");

        return RespEntity.ok();
    }

    /**
     * 检查手机号是否存在 .<br>
     *
     * @param reqEntity 请求对象
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/7/26 18:10
     */
    @PostMapping("/doRegisterCheckExistMobilephone")
    public RespEntity doRegisterCheckExistMobilephone(@RequestBody ReqEntity reqEntity) {
        SipaBurMVo sipaBurMVo = JSONObject.parseObject(reqEntity.getData(), SipaBurMVo.class);
        final String mobilephone = sipaBurMVo.getMobilephone();
        SmeAssert.notBlank(mobilephone, "手机号码为空");
        boolean exist = sipaBurMService.countByMobilephone(mobilephone) > 0;

        JSONObject data = new JSONObject();
        data.put("result", exist ? GlobalConstants.Flag.TRUE : GlobalConstants.Flag.FALSE);

        return RespEntity.ok(data);
    }



}
