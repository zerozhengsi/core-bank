package com.vteam.vtarm.security;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 请求拒绝异常 .
 *
 * @author andy.sher
 * @date 2020/4/22 11:35
 */
@Getter
@Setter
public class RequestRefuseException extends RuntimeException {

    private static final long serialVersionUID = -4836882045560387466L;

    private Map<String, String> model;

    public RequestRefuseException(String msg) {
        super(msg);
    }

    public RequestRefuseException(String msg, Map<String, String> model) {
        super(msg);
        this.model = model;
    }

    public RequestRefuseException() {
        super(StringUtils.EMPTY);
    }

}
