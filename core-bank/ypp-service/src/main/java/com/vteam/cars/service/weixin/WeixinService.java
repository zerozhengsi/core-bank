package com.vteam.cars.service.weixin;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.*;
import com.vteam.cars.entity.model.CspaFunM;
import com.vteam.cars.entity.model.SipaBtrM;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.entity.vo.SipaRolMVo;
import com.vteam.cars.exception.MSSmeBusinessException;
import com.vteam.cars.plugin.remote.client.weixin.WeiXinHttpsClient;
import com.vteam.cars.plugin.weixin.configure.WeixinPropertiesUtils;
import com.vteam.cars.service.cspa.CspaFunMService;
import com.vteam.cars.service.flog.FlogOptMService;
import com.vteam.cars.service.sipa.SipaBtrMService;
import com.vteam.cars.service.sipa.SipaBurMService;
import com.vteam.cars.service.sipa.SipaRolMService;
import com.vteam.cars.service.sipa.SipaRtfMService;
import com.vteam.cars.util.NumberUtils;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.security.AuthenticationInfo;
import com.vteam.vtarm.status.GlobalStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 微信服务类 .</br>
 *
 * @author toby.tang
 * @date 2023/2/16 9:04
 */
@Slf4j
@Getter
@Setter
@Service
public class WeixinService {

    /**
     * 微信小程序AccessToken缓存键（调用微信API需要使用）
     */
    private final static String CACHE_KEY = "WX-ACCESS-TOKEN";

    /**
     * ACCESS_TICKET缓存键
     */
    private final static String WX_ACCESS_TICKET = "WX-ACCESS-TICKET";

    /**
     * 微信公众号AccessToken缓存键
     */
    private final static String WX_PUBLIC_CACHE_KEY = "WX-PUBLIC-ACCESS-TOKEN";

    private static final String LOGIN_REGISTRY = "login-registry";

    /**
     * 微信https请求
     */
    @Resource(type = WeiXinHttpsClient.class)
    private WeiXinHttpsClient weiXinHttpsClient;

    /**
     * 系统配置
     */
    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Resource(type = SipaBurMService.class)
    private SipaBurMService sipaBurMService;

    @Resource
    private MapContainer mapContainer;

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    @Resource(type = WeixinPropertiesUtils.class)
    private WeixinPropertiesUtils weixinPropertiesUtils;

    @Resource(type = SipaRolMService.class)
    private SipaRolMService sipaRolMService;

    @Resource(type = SipaBtrMService.class)
    private SipaBtrMService sipaBtrMService;

    @Resource(type = SipaRtfMService.class)
    private SipaRtfMService sipaRtfMService;

    @Resource(type = CspaFunMService.class)
    private CspaFunMService cspaFunMService;


    @Resource(type = FlogOptMService.class)
    private FlogOptMService flogOptMService;

    /**
     * 获取小程序accessToken
     *
     * @since 2023/2/16 14:47
     * @author toby.tang
     * @return String
     */
    public synchronized String getXcxAccessToken() {
        Object value = stringValueContainer.get(CACHE_KEY);
        if (null == value) {
            log.info("进入getXcxAccessToken方法，获取微信小程序accessToken");
            String[] appConfigArr = weixinPropertiesUtils.getAppConfig(WeiXinConstants.Xcx.CONST_APPID_KEY);
            String appidConfig = null;
            String secretConfig = null;
            if (null != appConfigArr && appConfigArr.length == 2) {
                appidConfig = appConfigArr[0];
                secretConfig = appConfigArr[1];
            }
            // 开发者ID
            String appid = StringUtils.isNotBlank(RequestStore.getAppID()) ? RequestStore.getAppID() : appidConfig;
            // 开发者密码
            String appSecret = StringUtils.isNotBlank(RequestStore.getAppSecret()) ? RequestStore.getAppSecret() : secretConfig;
            // 拼接获取token的URL
            String url = WeiXinConstants.Api.API_URL_GETACCESSTOKEN;
            String params = "appid=" + appid + "&secret=" + appSecret + "&grant_type=" + WeiXinConstants.Xcx.CONST_ACCESS_TOKEN_GRANT_TYPE;
            if (null != appid || null != appSecret) {
                url += GlobalConstants.Symbol.QUESTION_EN + params;
            }else{
                log.info("appid和appSecret未获取成功");
            }
            // 请求微信获取数据
            log.info("请求微信获取数据AccessToken url =》 {}", url);
            JSONObject result = weiXinHttpsClient.httpsRequest(url, HttpGet.METHOD_NAME, "");
            SmeAssert.notNull(result, "请求失败，请检查您的网络环境。");
            // 获取accessToken
            String accessToken = result.getString("access_token");
            SmeAssert.notBlank(accessToken, "获取Token失败，appid 或 appsecret不正确。");
            // 目前微信access_token 的有效期目前为 2 个小时
            String expiresInStr = result.getString("expires_in");
            Integer expires_in = 7200;
            if(StringUtils.isNotBlank(expiresInStr) && NumberUtils.isNumber(expiresInStr)){
                expires_in = Integer.parseInt(expiresInStr);
            }
            stringValueContainer.set(CACHE_KEY, accessToken, expires_in);
            value = stringValueContainer.get(CACHE_KEY);
            log.info("获取微信小程序accessToken成功：{}", value);
        } else {
            log.info("获取缓存的微信小程序accessToken成功：{}", value);
        }
        return value.toString();
    }

    /**
     * 微信小程序手机自动注册 .<br>
     *
     * @param phoneNumber
     * @since 2023/2/16 18:54
     * @author toby.tang
     */
    @Transactional(rollbackFor = Exception.class)
    public void autoRegisterByPhoneNumber(String phoneNumber) {
        SmeAssert.notBlank(phoneNumber ,"手机号不能为空。");
        SmeAssert.isPhoneNum(phoneNumber, "手机号格式不合法。");
        log.info("开始进行微信用户手机自动注册phoneNumber：{}", phoneNumber);
        try {
            SipaBurMVo sipaBurMVo = sipaBurMService.getUserInfoByMobilephone(phoneNumber, FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL);
            if (null == sipaBurMVo) {
                try {
                    sipaBurMVo = new SipaBurMVo();
                    // userid规则为："渠道_手机号"
                    sipaBurMVo.setLoginId(sipaBurMVo.getUserid());
                    sipaBurMVo.setUseFlag(FieldConstant.SipaBurM.USE_FLAG_ENABLE);
                    sipaBurMVo.setCreateDate(LocalDateTime.now());
                    sipaBurMVo.setAdminFlag(GlobalConstants.Flag.FALSE);
                    sipaBurMVo.setSource(FieldConstant.SipaBurM.SOURCE_XCX);
                    sipaBurMVo.setMobilephone(phoneNumber);
                    // 未进行微信登录 无法获取openid
                    sipaBurMVo.setWeixinOpenId("");
                    // 前台用户
                    sipaBurMVo.setSystemType(FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL);
                    sipaBurMService.save(sipaBurMVo);
                    log.info("手机号自动注册成功，用户userid：" + sipaBurMVo.getUserid());
                    if (StringUtils.isNotBlank(sipaBurMVo.getUserid())) {
                        // 添加操作日志
                        String edtDesc = StringUtils.EMPTY;
                        edtDesc = edtDesc.concat("注册手机：").concat(sipaBurMVo.getMobilephone()).concat(GlobalConstants.Symbol.COMMA_CN);
                        flogOptMService.saveLogInfo(sipaBurMVo, EventTypeContants.Weixin.XCX_AUTOREG_MOBILE, edtDesc);
                    }
                } catch (Exception e){
                    log.error("手机号自动注册失败：" + phoneNumber, e);
                    throw new MSSmeBusinessException("手机号注册失败");
                }
            } else {
                // 手机号已存在，跳过注册处理
                SmeAssert.eq(FieldConstant.SipaBurM.USE_FLAG_ENABLE, sipaBurMVo.getUseFlag(), "该手机号已被禁用，请联系管理员处理。");
                log.error("手机号已存在，跳过注册处理，手机号：{},用户userid：{}", phoneNumber, sipaBurMVo.getUserid());
            }
        } catch (MSSmeBusinessException e){
            log.error("微信小程序手机自动注册失败", e);
            throw e;
        } catch (Exception e){
            log.error("微信小程序手机自动注册失败", e);
            throw new MSSmeBusinessException("微信小程序手机自动注册失败");
        }
    }

    /**
     *  微信小程序手机号自动登录 .<br>
     *
     * @param phoneNumber
     * @since 2023/2/16 18:40
     * @author toby.tang
     */
    public String autoLoginByPhoneNumber(String phoneNumber){
        SmeAssert.notBlank(phoneNumber ,"手机号不能为空。");
        SmeAssert.isPhoneNum(phoneNumber, "手机号格式不合法。");
        SipaBurMVo sipaBurMVo = sipaBurMService.getUserInfoByMobilephone(phoneNumber, FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL);
        log.info("开始进行微信用户手机号自动登录phoneNumber：{}", phoneNumber);
        if (sipaBurMVo != null) {
            // 微信小程序用户授权
            grantAuthorizationVirtual(phoneNumber);
            // 设置认证信息
            GlobalStatus.setAnonymous(false);
            // 计算过期时间
            LocalDateTime exTime = LocalDateTime.now().plusSeconds(smeConfiguration.getSecurityTimeToLive());
            // 设置认证信息
            GlobalStatus.setAnonymous(false);
            GlobalStatus.getAuthenticationInfo().setExpiresIn(exTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            GlobalStatus.getAuthenticationInfo().setAdditional(JSONObject.toJSONString(sipaBurMVo), GlobalStatus.getAuthenticationInfo());
            final AuthenticationInfo authenticationInfo = GlobalStatus.getAuthenticationInfo();
            final String accessToken = authenticationInfo.getAccessToken() ;
            // 记录注册表
            mapContainer.put(LOGIN_REGISTRY, sipaBurMVo.getUserid(), accessToken);
            log.info("微信小程序自动登录成功，手机号:{}，accessToken：{}",phoneNumber ,accessToken);
            // 更新最后登录时间
            sipaBurMVo.setLastLoginDate(LocalDateTime.now());
            sipaBurMVo.setLastLoginAddress(RequestStore.getIpAddress());
            sipaBurMService.updateById(sipaBurMVo);
            if (StringUtils.isNotBlank(sipaBurMVo.getUserid())) {
                // 添加操作日志
                String edtDesc = StringUtils.EMPTY;
                edtDesc = edtDesc.concat("用户登录：").concat(sipaBurMVo.getMobilephone()).concat(GlobalConstants.Symbol.COMMA_CN);
                flogOptMService.saveLogInfo(sipaBurMVo, EventTypeContants.Weixin.XCX_LOGIN, edtDesc);
            }
            return accessToken;
        } else {
            log.error("微信小程序自动登录失败，不存在手机号用户：{}", phoneNumber);
            throw new MSSmeBusinessException("微信小程序自动登录失败，不存在手机号用户：" + phoneNumber);
        }
    }

    /**
     * 微信小程序用户授权默认权限(虚拟权限).<br>
     * 备注：用于需要登录验证的接口进行权限校验 .<br>
     *
     * @since 2023/2/21 13:35
     * @author toby.tang
     */
    private void grantAuthorizationVirtual(String phoneNumber){
        AuthenticationInfo authenticationInfo = GlobalStatus.getAuthenticationInfo();
        if (authenticationInfo!= null) {
            Set<String> permissions = new HashSet<>();
            // 授权默认权限，该权限为虚拟权限，数据库不存在，仅供部分接口进行登录校验需要
            permissions.add(PermissionConstants.WeixinXcx.XCX_DEFAULT);
            authenticationInfo.setPermissions(permissions);
            log.info("微信小程序手机用户授权默认权限成功，手机号：{}", phoneNumber);
        } else {
            log.error("微信小程序手机用户授拟默认权限失败，用户登录未成功，手机号：{}", phoneNumber);
            throw new MSSmeBusinessException("微信小程序手机用户授权失败，用户登录未成功，手机号：" + phoneNumber);
        }
    }

    /**
     * 微信小程序用户授权角色和菜单权限(暂未使用).<br>
     * 备注：预留未来扩展需要，暂时授权使用grantAuthorizationVirtual方法授予默认权限 .<br>
     *
     * @param sipaBurMVo
     * @since 2023/2/17 16:13
     * @author toby.tang
     */
    private void grantAuthorization(SipaBurMVo sipaBurMVo) {
        String phoneNumber = sipaBurMVo.getMobilephone();
        SmeAssert.notBlank(phoneNumber ,"手机号不能为空。");
        SmeAssert.isPhoneNum(phoneNumber, "手机号格式不合法。");
        try {
            String userid = sipaBurMVo.getUserid();
            // 首先查询用户是否已分配校色
            String roleid = "";
            List<SipaBtrM> sipaBtrMList = sipaBtrMService.listRoleInfoByUserId(sipaBurMVo);
            if (CollectionUtils.isEmpty(sipaBtrMList)) {
                log.info("开始给微信用户分配角色，手机号：{}，用户userid：{}", phoneNumber, userid);
                List<SipaRolMVo> sipaRolMVoList = sipaRolMService.listWexinXcxRoleInfo();
                if (CollectionUtils.isNotEmpty(sipaRolMVoList)) {
                    SipaRolMVo sipaRolMVo = sipaRolMVoList.get(0);
                    roleid = sipaRolMVo.getRoleid();
                    log.info("数据库配置微信用户默认角色为：{}", roleid);
                    sipaBurMVo.setRoleid(roleid);
                    sipaBtrMService.saveWeixinUserInfo(sipaBurMVo);
                    log.info("分配角色成功，手机号：{}，用户userid：{}，角色代号：{} ，角色描述：{}", phoneNumber, userid, roleid, sipaRolMVo.getRoleName());
                } else {
                    log.error("数据库未配置微信用户默认角色，授权失败");
                }
            } else {
                log.info("手机号：{}，用户userid：{}已有角色，跳过处理", phoneNumber, userid );
            }
            // 查询权限
            List<Integer> funCodes = sipaRtfMService.listRoleByUser(sipaBtrMList);
            // 获取资源类型[11=运营端/21=企业端/22=机构端/23=合伙人端/24=核心企业端/81-微信小程序]
            String funSystemType = FieldConstant.CspaFun.SYSTEM_TYPE_WEIXINXCX;
            List<CspaFunM> funMList = cspaFunMService.listFunByFunRefcodeList(funCodes, funSystemType);
            if (CollectionUtils.isNotEmpty(funMList)) {
                Set<String> permissions = new HashSet<>();
                // 授予权限
                funMList.forEach(fun -> permissions.add(fun.getFunid()));
                GlobalStatus.getAuthenticationInfo().setPermissions(permissions);
            }else{
                log.info("角色：{}未配置权限，跳过授权", roleid);
            }
            log.info("微信小程序手机用户登录权限授予成功，手机号：{}，用户userid：{}", phoneNumber, userid);
        } catch (Exception e){
            log.error("微信小程序手机用户授权角色和登录权限失败：{}", phoneNumber, e);
            throw new MSSmeBusinessException("微信小程序手机用户授权角色和登录权限失败");
        }
    }

}
