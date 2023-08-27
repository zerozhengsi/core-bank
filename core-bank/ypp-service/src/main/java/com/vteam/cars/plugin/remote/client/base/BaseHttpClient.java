package com.vteam.cars.plugin.remote.client.base;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.BusinessConstants;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.vo.FlogApiMVo;
import com.vteam.cars.service.flog.FlogApiMService;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.core.task.TaskExecutor;

import jakarta.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Http客户端基类 .<br>
 *
 * @author andy.sher
 * @date 2018/9/19 14:54
 */
@Slf4j
public abstract class BaseHttpClient<T> {

    public static final String REQUEST_BODY = "requestBody";
    public static final String START_TIME = "startTime";
    public static final String RESPONSE_BODY = "responseBody";
    public static final String END_TIME = "endTime";
    public static final String STATUS_CODE = "statusCode";
    public static final Integer TIME_OUT = 30000;

    @Resource(type = SmeConfiguration.class)
    protected SmeConfiguration smeConfiguration;

    @Resource(name = "smeTaskExecutor")
    protected TaskExecutor taskExecutor;

    @Resource(name = "httpClientManagerFactoryBean")
    private CloseableHttpClient client;

    @Resource(type = FlogApiMService.class)
    private FlogApiMService flogApiMService;

    /**
     * 文件名称
     */
    protected static ThreadLocal<String> fileNameLocal = new ThreadLocal<>();

    /**
     * 文件类型
     */
    protected static ThreadLocal<String> fileTypeLocal = new ThreadLocal<>();

    /**
     * 执行请求 .
     *
     * @param target 参数对象
     * @return java.lang.String 响应数据
     * @author andy.sher
     * @date 2018/10/24 13:28
     */
    public abstract String execute(T target);

    /**
     * 请求并获取响应实体 .
     *
     * @param requestBase 请求对象
     * @return org.apache.http.HttpEntity 响应实体
     * @author andy.sher
     * @date 2018/9/19 15:42
     */
    public String doRequest(HttpRequestBase requestBase) {
        // 请求体
        String requestBody;
        // 响应体
        String responseBody = StringUtils.EMPTY;
        // 状态码
        Integer statusCode;

        Map<String, Object> params = new HashMap<>(5);
        try {
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(TIME_OUT)
                    .setSocketTimeout(TIME_OUT).build();
            requestBase.setConfig(requestConfig);
            // 获取请求体
            requestBody = getRequestParameters(requestBase);
            // 获取请求时间
            params.put(REQUEST_BODY, requestBody);
            log.info(String.format("%s -> %s\r\n", requestBase.toString(), requestBody));
            params.put(START_TIME, LocalDateTime.now());
            CloseableHttpResponse response = client.execute(requestBase);
            // 获取响应时间
            params.put(END_TIME, LocalDateTime.now());
            // 获取响应码
            statusCode = response.getStatusLine().getStatusCode();
            params.put(STATUS_CODE, statusCode);
            if (HttpStatus.SC_OK == statusCode) {
                // 获取响应体
                responseBody = EntityUtils.toString(response.getEntity(), smeConfiguration.getEncoding());
                params.put(RESPONSE_BODY, responseBody);
            }
            log.info(String.format("%s -> %s\r\n", response.toString(), responseBody));
        } catch (Exception e) {
            log.warn(BaseHttpClient.class.getName(), e);
        } finally {
            // 记录日志
            taskExecutor.execute(() -> saveApiLog(params));
        }

        return responseBody;
    }

    /**
     * 记录API日志 .
     *
     * @param params 接口数据
     * @return void
     * @author andy.sher
     * @date 2019/2/18 15:24
     */
    private void saveApiLog(Map<String, Object> params) {
        // 记录应用程序接口请求记录
        FlogApiMVo flogApiMVo = new FlogApiMVo();
        flogApiMVo.setApiId(this.getId().getName());
        flogApiMVo.setApiName(this.getName());
        flogApiMVo.setApiType(FieldConstant.FlogApiM.API_TYPE_O);
        if (null != params.get(REQUEST_BODY)) {
            flogApiMVo.setRequestBody((String) params.get(REQUEST_BODY));
        }
        flogApiMVo.setRequestTime(null != params.get(START_TIME) && params.get(START_TIME) instanceof LocalDateTime ? (LocalDateTime) params.get(START_TIME) : null);
        final Object respBody = params.get(RESPONSE_BODY);
        if (null != respBody) {
            if (respBody instanceof String) {
                flogApiMVo.setResponseBody((String) respBody);
            } else if (respBody instanceof byte[]) {
                // 返回对象为文件时，不记录文件字节流
                flogApiMVo.setResponseBody("提示：成功返回文件结果。");
            } else {
                flogApiMVo.setResponseBody(String.valueOf(respBody));
            }
        }
        flogApiMVo.setResponseTime(null != params.get(END_TIME) && params.get(END_TIME) instanceof LocalDateTime ? (LocalDateTime) params.get(END_TIME) : null);
        flogApiMVo.setResultCode(null != params.get(STATUS_CODE) ? String.valueOf(params.get(STATUS_CODE)) : null);
        flogApiMService.save(flogApiMVo);
    }

    /**
     * 获取请求参数 .
     *
     * @param requestBase 请求对象
     * @return void
     * @author andy.sher
     * @date 2019/2/18 9:37
     */
    private String getRequestParameters(HttpRequestBase requestBase) throws IOException {
        JSONObject requestBody;
        if (requestBase instanceof HttpPost) {
            HttpPost httpPost = (HttpPost) requestBase;
            HttpEntity entity = httpPost.getEntity();
            if (null != entity) {
                List<NameValuePair> data = URLEncodedUtils.parse(entity);
                if (CollectionUtils.isNotEmpty(data)) {
                    Iterator<NameValuePair> it = data.iterator();
                    requestBody = new JSONObject();
                    NameValuePair temp;
                    while (it.hasNext()) {
                        temp = it.next();
                        requestBody.put(temp.getName(), temp.getValue());
                    }
                    return requestBody.toJSONString();
                } else {
                    return EntityUtils.toString(entity, GlobalConstants.Character.UTF_8);
                }
            }
        } else if (requestBase instanceof HttpGet) {
            HttpGet httpGet = (HttpGet) requestBase;
            return httpGet.getURI().toString();
        }
        return null;
    }

    /**
     * 请求并获取响应实体-下载附件使用
     *
     * @param requestBase
     * @return byte[]
     * @author jiangming.huang
     * @date 2019/1/21 10:36
     */
    public byte[] doRequestAndReturnByte(HttpRequestBase requestBase) {
        // 请求体
        String requestBody;
        byte[] result = new byte[0];
        log.info("下载文件，请求(HttpRequestBase)对象：" + requestBase.toString());
        Map<String, Object> params = new HashMap<>();
        try {
            // 获取请求体
            requestBody = getRequestParameters(requestBase);
            params.put(REQUEST_BODY, requestBody);
            log.info(String.format("%s -> %s\r\n", requestBase.toString(), requestBody));
            // 获取请求时间
            params.put(START_TIME, LocalDateTime.now());
            CloseableHttpResponse response = client.execute(requestBase);
            // 获取响应时间
            params.put(END_TIME, LocalDateTime.now());
            log.info("下载文件，响应(Response)对象：" + response.toString());
            // 获取响应码
            final int statusCode = response.getStatusLine().getStatusCode();
            params.put(STATUS_CODE, statusCode);
            if (HttpStatus.SC_OK == statusCode) {
                // 获取响应体Header中的Content-Type，若为application/json，则说明下载文件失败，否则成功
                String contentType = this.getResponseHeaderValue(response, "Content-Type");
                if (StringUtils.isNotBlank(contentType) && contentType.contains("application/json")) {
                    // 下载失败，获取失败信息
                    String responseBody = null;
                    // A.判断Header中是否有响应码(ARM平台及宝亚平台)
                    String code = this.getResponseHeaderValue(response, BusinessConstants.Key.RESP_CODE);
                    if (StringUtils.isNotBlank(code) && GlobalConstants.Flag.FALSE.equals(code)) {
                        responseBody = this.getResponseHeaderValue(response, BusinessConstants.Key.RESP_MSG);
                    } else {
                        // B.直接获取响应体内容(如象屿ERP平台)
                        responseBody = EntityUtils.toString(response.getEntity(), smeConfiguration.getEncoding());
                    }
                    params.put(STATUS_CODE, -1);
                    params.put(RESPONSE_BODY, responseBody);
                    return null;
                } else {
                    // 下载成功
                    // 1.获取文件流
                    result = EntityUtils.toByteArray(response.getEntity());
                    params.put(RESPONSE_BODY, result);
                    // 2.获取文件名
                    // A.判断Header中是否有文件名(ARM平台及宝亚平台)
                    String fileName = this.getResponseHeaderValue(response, GlobalConstants.Flag.FILE_NAME);
                    if (StringUtils.isBlank(fileName)) {
                        // B.从Header的Content-Disposition中获取文件名(如象屿ERP平台)
                        String contentDisposition = this.getResponseHeaderValue(response, "Content-Disposition");
                        if (StringUtils.isNotBlank(contentDisposition)) {
                            try {
                                // 内容示例：attachment;filename*=utf-8''515fffe2-08ea-4103-814f-54642318e9b2.zip
                                // 或：attachment;filename=515fffe2-08ea-4103-814f-54642318e9b2.pdf
                                fileName = contentDisposition.split(";")[1].split("=")[1];
                                // 兼容新的中文文件名编码规范
                                if (fileName.startsWith("utf-8''")) {
                                    fileName = fileName.replaceFirst("utf-8''", "");
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                    // 若文件名称为空，则生成默认文件名称
                    if (StringUtils.isBlank(fileName)) {
                        fileName = UUID.randomUUID().toString() + ".unknown";
                    }
                    // 记录文件名称
                    fileNameLocal.set(fileName);
                    // 获取存在头部的信息，文件类型
                    fileTypeLocal.set(this.getResponseHeaderValue(response, GlobalConstants.Flag.FILE_TYPE));
                }
            } else {
                params.put(RESPONSE_BODY, "下载失败，请求异常。");
            }
        } catch (Exception e) {
            log.warn(BaseHttpClient.class.getName(), e);
        } finally {
            // 记录日志
            taskExecutor.execute(() -> saveApiLog(params));
        }
        return result;
    }

    /**
     * 获取唯一标识符 .
     *
     * @param
     * @return java.lang.Class<?> 获取唯一标识符（接口类型）
     * @author andy.sher
     * @date 2019/2/18 15:19
     */
    protected abstract Class<?> getId();

    /**
     * 获取接口名称 .
     *
     * @param
     * @return java.lang.String 接口名称
     * @author andy.sher
     * @date 2019/2/18 15:19
     */
    protected abstract String getName();

    /**
     * 销毁参数仓库
     *
     * @param
     * @return void
     * @author jiangming.huang
     * @date 2019/3/12 11:36
     */
    protected static void destroyParams() {
        fileNameLocal.remove();
        fileTypeLocal.remove();
    }

    /**
     * 根据接口服务类型获取接口服务地址
     *
     * @param serviceType
     * @return
     * @author zhuang.shao
     * @date 2018年10月23日 下午4:31:51
     */
    protected String getServiceUrlByType(String serviceType) {
        String serviceSuffix = StringUtils.EMPTY;
        String serviceUrl = serviceSuffix;
        String[] strArray = serviceType.split(GlobalConstants.Symbol.UNDERLINE);
        for (String str : strArray) {
            serviceUrl += GlobalConstants.Symbol.SLASH + str;
        }
        if (StringUtils.isBlank(serviceSuffix)) {
            serviceUrl = serviceUrl.substring(1);
        }
        return serviceUrl;
    }

    /**
     * 组装请求的完整地址
     *
     * @param interfaceUrl
     * @param serviceUrl
     * @param paramMap
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/3/18 17:31
     */
    protected String processRequestUri(String interfaceUrl, String serviceUrl, Map<String, String> paramMap) {
        SmeAssert.notBlank(serviceUrl, "请求地址不能为空");
        if (!interfaceUrl.endsWith(GlobalConstants.Symbol.SLASH)) {
            interfaceUrl += GlobalConstants.Symbol.SLASH;
        }
        String requestUri = interfaceUrl + serviceUrl;
        requestUri = this.appendParamMapToUri(requestUri, paramMap);
        return requestUri;
    }

    /**
     * 在请求地址上增加时间戳
     *
     * @param url
     * @return 追加t=$timestamp
     * @author oscar.yu
     * @date 2020/3/14 11:38
     */
    protected String appendTimespanToUrl(String url) {
        final String timespan = "t=" + System.currentTimeMillis();
        if (url.indexOf(GlobalConstants.Symbol.QUESTION_EN) > -1) {
            url += GlobalConstants.Symbol.AND + timespan;
        } else {
            url += GlobalConstants.Symbol.QUESTION_EN + timespan;
        }
        return url;
    }

    /**
     * 获取返回Header中的值
     *
     * @param response
     * @param key
     * @return
     * @author oscar.yu
     * @date 2020/3/15 14:27
     */
    protected String getResponseHeaderValue(CloseableHttpResponse response, String key) {
        String fileValue = null;
        if (response.containsHeader(key)) {
            Header[] header = response.getHeaders(key);
            fileValue = header[0].getValue();
            if (StringUtils.isNotBlank(fileValue)) {
                // 解码文件名称
                try {
                    fileValue = URLDecoder.decode(fileValue, GlobalConstants.Character.UTF_8);
                    fileValue = fileValue.replace(GlobalConstants.Symbol.PLUS, "%20");
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
        return fileValue;
    }

    /**
     * 将请求参数集合拼接到请求URL
     *
     * @param requestUri
     * @param paramMap
     * @return
     * @author zhuang.shao
     * @date 2018年10月23日 下午4:48:15
     */
    protected String appendParamMapToUri(String requestUri, Map<String, String> paramMap) {
        String uri = requestUri;
        if (MapUtils.isEmpty(paramMap)) {
            return requestUri;
        }
        URIBuilder builder = new URIBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            builder.addParameter(entry.getKey(), entry.getValue());
        }
        try {
            uri = requestUri + builder.build().toString();
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }
        return uri;
    }

}