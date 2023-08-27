package com.vteam.cars.config;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.SmeConfigConstants;
import com.vteam.cars.plugin.provider.SystemConfigProvider;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.data.DataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 系统配置 .<br>
 *
 * @author andy.sher
 * @date 2018/8/7 13:18
 */
@Component("smeConfiguration")
@DependsOn(value = {"springContextUtil"})
public final class SmeConfiguration {

    @Resource(type = DataProvider.class)
    private DataProvider dataProvider;


    @Value("${spring.activemq.topic.ws-topic}")
    private String activeMqWsTopic;

    @Value(value = "${spring.activemq.queueName.weixinSender}")
    private String weixinSenderQueueName;

    @Value(value = "${spring.activemq.queueName.pushData}")
    private String pushDataQueueName;

    @Value("${spring.activemq.topic.lucene-topic}")
    private String activeMqLuceneTopic;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${application.security.timeToLive}")
    private Integer securityTimeToLive;

    @Value("${application.security.maxRetryTotal}")
    private Integer securityMaxRetryTotal;

    @Value("${application.security.lockedHours}")
    private Integer securityLockedHours;

    @Value(value = "${application.file.active}")
    private String fileActive;

    @Value("${application.encoding}")
    private String encoding;

    @Value("${application.file.tmpPath}")
    private String tmpPath;

    @Value("${application.file.repositoryPath}")
    private String fileRepositoryPath;

    @Value("${application.file.lucenePath}")
    private String lucenePath;
    @Value("${plugin.htmlConvertToPDFPluginPath}")
    private String htmlConvertToPDFPluginPath;


    /**
     * 系统数据库类型
     */
    @Value(value = "${application.dbType}")
    private String dbType;

    @Value("${spring.activemq.switch}")
    private boolean mqSwitch;

    @Value("${spring.activemq.queueName.message}")
    private String queueNameMessage;


    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${application.sms.active}")
    private String smsActive;


    @Value("${application.cache.mode}")
    private String cacheMode;

    /**
     * 增加系统语言缓存(解决导出数据频繁获取的性能问题)
     */
    private ThreadLocal<String> systemLanguageLocal = new ThreadLocal<>();

    /**
     * 连接超时时间，单位ms
     */
    @Value("${application.remote.config.connectTimeout}")
    private int connectTimeout;

    /**
     * 请求超时时间
     */
    @Value("${application.remote.config.connectRequestTimeout}")
    private int connectRequestTimeout;

    /**
     * sock超时时间
     */
    @Value("${application.remote.config.socketTimeout}")
    private int socketTimeout;

    /**
     * 连接池最大连接数
     */
    @Value("${application.remote.config.connMaxTotal}")
    private int connMaxTotal;

    @Value("${application.remote.config.maxPerRoute}")
    private int maxPerRoute;

    /**
     * http连接存活时间，单位为s
     */
    @Value("${application.remote.config.timeToLive}")
    private int timeToLive;

    /**
     * http长连接保持时间，单位为s
     */
    @Value("${application.remote.config.keepAliveTime}")
    private int keepAliveTime;

    /**
     * http连接超时或异常重试次数
     */
    @Value(value = "${application.remote.config.retryTime}")
    private int retryTime;

    /**
     * 档案模板存放地址
     */
    @Value("${application.file.templates.archivePath}")
    private String archivePath;

    /**
     * 导入模板路径
     */
    @Value("${application.file.templates.importPath}")
    private String importPath;

    /**
     * 导出模板路径
     */
    @Value("${application.file.templates.exportPath}")
    private String exportPath;


    /**
     * 水印
     */
    @Value(value = "${application.file.templates.watermark}")
    private String watermark;

    /**
     * 自定义配置
     */
    @Value("${spring.profiles.active}")
    private String active;


    /**
     * 字符集编码 .
     *
     * @return java.lang.String 字符集编码
     * @author andy.sher
     * @date 2018/8/24 13:39
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * token有效时长 .
     *
     * @return java.lang.Integer
     * @author andy.sher
     * @date 2018/12/28 13:24
     */
    public Integer getSecurityTimeToLive() {
        return securityTimeToLive;
    }

    /**
     * 获取验证码类型
     *
     * @return java.lang.String
     * @author xiu.fu
     * @date 2019/9/25 0025 下午 2:05
     */
    public String getVerificationFlag() {
        return get(SmeConfigConstants.VERIFICATION_CODE_TYPE);
    }

    /**
     * 缓存值 .<br>
     *
     * @param key 配置键
     * @return java.lang.String 配置值
     * @author andy.sher
     * @date 2018/8/7 14:01
     */
    private String get(String key) {
        return dataProvider.get(SystemConfigProvider.class, key);
    }

    /**
     * 获取用户登录最大尝试次数 .
     *
     * @return java.lang.Integer
     * @author andy.sher
     * @date 2018/12/28 9:10
     */
    public Integer getSecurityMaxRetryTotal() {
        return securityMaxRetryTotal;
    }

    /**
     * 获取用户尝试登录失败后锁定时长 .
     *
     * @return java.lang.Integer
     * @author andy.sher
     * @date 2018/12/28 9:09
     */
    public Integer getSecurityLockedHours() {
        return securityLockedHours;
    }

    /**
     * 临时文件存放路径 .
     *
     * @return java.lang.String 临时文件存放路径
     * @author andy.sher
     * @date 2018/8/24 13:43
     */
    public String getTmpPath() {
        return tmpPath;
    }

    /**
     * lucene索引存放路径 .
     *
     * @return java.lang.String lucene索引存放路径
     * @author andy.sher
     * @date 2018/9/7 11:20
     */
    public String getLucenePath() {
        return lucenePath;
    }

    /**
     * 获取系统数据库类型
     *
     * @author zhuang.shao
     * @date 2018年10月11日 下午3:16:24
     */
    public String getDbType() {
        return dbType;
    }

    /**
     * 应用名称 .
     *
     * @return java.lang.String 应用名称
     * @author andy.sher
     * @date 2019/4/16 15:46
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * 系统配置默认语言 .
     *
     * @return java.lang.String
     * @author andy.sher
     * @date 2018/9/13 13:52
     */
    public String getSystemLanguage() {
        String systemLanguage = systemLanguageLocal.get();
        if (null == systemLanguage) {
            systemLanguage = get(SmeConfigConstants.W_SYSTEM_LANGUAGE);
            if (StringUtils.isBlank(systemLanguage)) {
                // 默认取中文简体
                systemLanguage = GlobalConstants.Language.zh_CN;
            }
            systemLanguageLocal.set(systemLanguage);
        }
        return systemLanguage;
    }

    /**
     * 是否开启多语言
     *
     * @param
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/6/12 9:20
     */
    public String getSystemLanguageOpen() {
        return get(SmeConfigConstants.P_SYSTEM_LANGUAGE_OPEN);
    }


    /**
     * 获取系统所属平台名称 .
     *
     * @param
     * @return java.lang.String 系统所属平台名称
     * @author jie.yang
     * @date 2022/1/7 15:07
     */
    public String getSystemPlatformName() {
        return get(SmeConfigConstants.W_SYSTEM_PLATFORM_NAME);
    }

    /**
     * 获取文件上传处理方式 .
     *
     * @return java.lang.String
     * @author andy.sher
     * @date 2018/11/13 15:38
     */
    public String getFileActive() {
        return fileActive;
    }

    /**
     * 文件仓库路径
     *
     * @author zhuang.shao
     * @date 2018年9月6日 下午2:08:51
     */
    public String getFileRepositoryPath() {
        return fileRepositoryPath;
    }

    /**
     * MQ开关
     *
     * @param
     * @return boolean
     * @author jiangming.huang
     * @date 2019/7/25 13:38
     */
    public boolean getMqSwitch() {
        return mqSwitch;
    }

    /**
     * 是否开启短信 .
     *
     * @param
     * @return java.lang.String 是否开启短信开关
     * @author andy.sher
     * @date 2019/5/23 11:30
     */
    public String getSmsSwitch() {
        return get(SmeConfigConstants.SMS_SWITCH);
    }
    /**
     * 消息队列消息名称 .
     *
     * @param
     * @return java.lang.String 消息队列消息名称
     * @author andy.sher
     * @date 2018/9/25 10:48
     */
    public String getQueueNameMessage() {
        return queueNameMessage;
    }

    /**
     * 邮箱用户名 .
     *
     * @param
     * @return java.lang.String 邮箱用户名
     * @author andy.sher
     * @date 2018/9/17 10:38
     */
    public String getMailUsername() {
        return mailUsername;
    }

    /**
     * 连接超时时间，单位ms
     *
     * @return
     * @author zhuang.shao
     * @date 2018年10月11日 下午3:23:30
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * 请求超时时间
     *
     * @return
     * @author zhuang.shao
     * @date 2018年10月11日 下午3:23:45
     */
    public int getConnectRequestTimeout() {
        return connectRequestTimeout;
    }

    /**
     * sock超时时间
     *
     * @return
     * @author zhuang.shao
     * @date 2018年10月11日 下午3:23:52
     */
    public int getSocketTimeout() {
        return socketTimeout;
    }

    /**
     * 连接超时或异常重试次数
     *
     * @return
     * @author zhuang.shao
     * @date 2018年10月11日 下午3:26:37
     */
    public int getRetryTime() {
        return retryTime;
    }

    public int getConnMaxTotal() {
        return connMaxTotal;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    /**
     * 获取最长连接时间
     *
     * @return
     * @author zhuang.shao
     * @date 2018年10月11日 下午3:13:32
     */
    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    /**
     * 系统活跃环境 .
     *
     * @param
     * @return java.lang.String
     * @author andy.sher
     * @date 2018/8/27 16:03
     */
    public String getActive() {
        return active;
    }





    /**
     * 档案模板存放地址 .
     *
     * @param
     * @return java.lang.String
     * @author zack.yin
     * @date 2018/10/11 13:47
     */
    public String getArchivePath() {
        return archivePath;
    }

    /**
     * 获取导入模板路径
     *
     * @return
     * @author zhuang.shao
     * @date 2018年10月11日 下午2:54:20
     */
    public String getImportPath() {
        return importPath;
    }

    /**
     * 获取导出模板路径
     *
     * @return
     * @author zhuang.shao
     * @date 2018年10月11日 下午2:58:23
     */
    public String getExportPath() {
        return exportPath;
    }


    /**
     * 水印保存路径 .
     *
     * @param
     * @return java.lang.String 水印保存路径
     * @author andy.sher
     * @date 2018/10/18 11:40
     */
    public String getWatermark() {
        return watermark;
    }



    /**
     * 激活的短信服务接口 .
     *
     * @param
     * @return java.lang.String 激活的短信服务接口
     * @author andy.sher
     * @date 2018/9/20 9:14
     */
    public String getSmsActive() {
        return smsActive;
    }


    /**
     * 短信接口地址 .
     *
     * @param
     * @return java.lang.String 短信接口地址
     * @author andy.sher
     * @date 2018/9/20 14:45
     */
    public String getSmsUrl() {
        return get(SmeConfigConstants.W_SEND_SMS_URL);
    }

    /**
     * 获取active mq websocket 消息发布 .
     *
     * @param
     * @return java.lang.String active mq websocket 消息发布
     * @author andy.sher
     * @date 2019/5/9 10:17
     */
    public String getActiveMqWsTopic() {
        return activeMqWsTopic;
    }

    /**
     * 获取微信消息队列名称
     *
     * @return
     * @author zhuang.shao
     * @date 2018年9月27日 上午11:05:16
     */
    public String getWeiXinQueueName() {
        return weixinSenderQueueName;
    }

    /**
     * 获取推送数据消息队列名称
     *
     * @param
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/3/5 9:50
     */
    public String getPushDataQueueName() {
        return pushDataQueueName;
    }

    /**
     * 获取lucene队列名称 .
     *
     * @param
     * @return java.lang.String lucene队列名称
     * @author andy.sher
     * @date 2019/12/17 15:04
     */
    public String getActiveMqLuceneTopic() {
        return activeMqLuceneTopic;
    }

    /**
     * 获取缓存存储模式 .
     *
     * @param
     * @return java.lang.String 缓存存储模式
     * @author andy.sher
     * @date 2019/4/16 14:26
     */
    public String getCacheMode() {
        return cacheMode;
    }

    public String getHtmlConvertToPDFPluginPath() {
        return htmlConvertToPDFPluginPath;
    }
}
