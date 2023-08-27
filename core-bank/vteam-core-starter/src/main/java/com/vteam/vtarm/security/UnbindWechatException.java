package com.vteam.vtarm.security;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 未绑定微信用户异常 .<br>
 *
 * @author andy.sher
 * @date 2019/11/28 10:01
 */
@Getter
@Setter
public class UnbindWechatException extends RuntimeException {

    private static final long serialVersionUID = 832613948082482034L;

    private Map<String, String> model;

    public UnbindWechatException(String msg) {
        super(msg);
    }

    public UnbindWechatException(String msg, Map<String, String> model) {
        super(msg);
        this.model = model;
    }

    public UnbindWechatException() {
        super(StringUtils.EMPTY);
    }

}
