package com.vteam.vtarm.security;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 重放攻击异常 .<br>
 *
 * @author andy.sher
 * @date 2019/11/28 14:52
 */
@Getter
@Setter
public class ReplayAttacksException extends RuntimeException {

    private static final long serialVersionUID = -3573795678443813920L;

    private Map<String, String> model;

    public ReplayAttacksException(String msg) {
        super(msg);
    }

    public ReplayAttacksException(String msg, Map<String, String> model) {
        super(msg);
        this.model = model;
    }

    public ReplayAttacksException() {
        super(StringUtils.EMPTY);
    }

}
