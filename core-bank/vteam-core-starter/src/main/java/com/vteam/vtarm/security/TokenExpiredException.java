package com.vteam.vtarm.security;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * token过期异常 .<br>
 *
 * @author andy.sher
 * @date 2019/4/18 9:17
 */
@Getter
@Setter
public class TokenExpiredException extends RuntimeException {

    private static final long serialVersionUID = 2391518655024904724L;

    private Map<String, String> model;

    public TokenExpiredException(String msg) {
        super(msg);
    }

    public TokenExpiredException(String msg, Map<String, String> model) {
        super(msg);
        this.model = model;
    }

    public TokenExpiredException() {
        super(StringUtils.EMPTY);
    }

}
