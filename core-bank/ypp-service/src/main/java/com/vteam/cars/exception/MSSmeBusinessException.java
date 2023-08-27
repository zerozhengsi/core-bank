package com.vteam.cars.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 业务异常 .<br>
 *
 * @author andy.sher
 * @date 2018/7/11 14:25
 */
@Getter
@Setter
public class MSSmeBusinessException extends RuntimeException {

    private static final long serialVersionUID = 2391518655024904724L;

    public static final String DEFAULT = "业务处理失败，请稍后再试！";

    private Map<String, String> model;

    public MSSmeBusinessException(String msg) {
        super(msg);
    }

    public MSSmeBusinessException(String msg, Map<String, String> model) {
        super(msg);
        this.model = model;
    }

    public MSSmeBusinessException() {
        super();
    }

}
