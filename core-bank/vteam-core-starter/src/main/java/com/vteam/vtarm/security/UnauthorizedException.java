package com.vteam.vtarm.security;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 未授权异常 .<br>
 *
 * @author andy.sher
 * @date 2019/5/5 9:40
 */
@Getter
@Setter
public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 2391518655024904724L;

    private Map<String, String> model;

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException(String msg, Map<String, String> model) {
        super(msg);
        this.model = model;
    }

    public UnauthorizedException() {
        super(StringUtils.EMPTY);
    }
}
