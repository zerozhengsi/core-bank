package com.vteam.vtarm.ocr;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * ocr服务不可用异常 .<br>
 *
 * @author andy.sher
 * @date 2019/12/2 12:27
 */
public class OcrUnavailableException extends RuntimeException {

    private static final long serialVersionUID = -4454659312139955807L;

    private Map<String, String> model;

    public OcrUnavailableException(String msg) {
        super(msg);
    }

    public OcrUnavailableException(String msg, Map<String, String> model) {
        super(msg);
        this.model = model;
    }

    public OcrUnavailableException() {
        super(StringUtils.EMPTY);
    }
    
}
