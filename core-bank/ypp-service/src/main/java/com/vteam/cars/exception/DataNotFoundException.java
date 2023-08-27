package com.vteam.cars.exception;

import com.vteam.cars.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 找不到数据异常 .<br>
 *
 * @author andy.sher
 * @date 2019/8/9 13:08
 */
@Getter
@Setter
public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1099987846852095140L;

    private Map<String, String> model;

    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException(String msg, Map<String, String> model) {
        super(msg);
        this.model = model;
    }

    public DataNotFoundException() {
        super(StringUtils.EMPTY);
    }

}
