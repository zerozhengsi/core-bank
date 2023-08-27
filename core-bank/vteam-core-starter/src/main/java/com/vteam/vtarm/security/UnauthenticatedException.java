package com.vteam.vtarm.security;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 未认证异常 .<br>
 *
 * @author andy.sher
 * @date 2019/5/5 9:38
 */
@Getter
@Setter
public class UnauthenticatedException extends RuntimeException {

    private static final long serialVersionUID = 2391518655024904724L;

    private Map<String, String> model;

    public UnauthenticatedException(String msg) {
        super(msg);
    }

    public UnauthenticatedException(String msg, Map<String, String> model) {
        super(msg);
        this.model = model;
    }

    public UnauthenticatedException() {
        super(StringUtils.EMPTY);
    }

}
