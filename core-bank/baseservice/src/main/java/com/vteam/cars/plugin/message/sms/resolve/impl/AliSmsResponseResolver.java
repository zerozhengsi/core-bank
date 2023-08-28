package com.vteam.cars.plugin.message.sms.resolve.impl;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.plugin.message.sms.resolve.ResponseMessage;
import com.vteam.cars.plugin.message.sms.resolve.ResponseResolver;
import com.vteam.cars.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;

/**
 * 阿里短信服务接口响应值解析 .<br>
 *
 * @author andy.sher
 * @date 2019/5/22 15:31
 */
@Slf4j
@Service
public class AliSmsResponseResolver implements ResponseResolver {

    private final String CODE_KEY = "Code";

    @Override
    public String resolve(@NotNull String content) {

        // 如果响应内容为空，则返回失败
        if (StringUtils.isBlank(content)) {
            return ResponseMessage.FAILURE;
        }

        JSONObject responseData = JSONObject.parseObject(content);

        if (!GlobalConstants.Flag.OK.equalsIgnoreCase(responseData.getString(CODE_KEY))) {
            log.error(content);
        }

        return responseData.getString("Message");
    }

}
