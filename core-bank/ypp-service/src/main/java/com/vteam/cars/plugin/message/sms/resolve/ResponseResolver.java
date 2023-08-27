package com.vteam.cars.plugin.message.sms.resolve;

import jakarta.validation.constraints.NotNull;

/**
 * 响应内容解析器 .<br>
 *
 * @author andy.sher
 * @date 2019/5/22 15:26
 */
public interface ResponseResolver {

    /**
     * 解析响应内容 .
     *
     * @param content 响应内容
     * @return java.lang.String 解析后的结果
     * @author andy.sher
     * @date 2019/5/22 15:27
     */
    String resolve(@NotNull String content);

}
