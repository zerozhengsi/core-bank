package com.vteam.cars.plugin.web.filter;

import com.vteam.vtarm.filter.Filter;
import org.springframework.stereotype.Component;

/**
 * 初始化请求参数库 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 14:06
 */
@Component
public class InitRequestStoreFilter implements Filter {

    /**
     * 地区代码
     */
    public static final String CITY_CODE = "cityCode";

    /**
     * 语言
     */
    public static final String LANGUAGE = "language";

    /**
     * 小程序ID
     */
    public static final String APP_ID = "appID";

    @Override
    public boolean active() {
        return true;
    }

    @Override
    public int order() {
        return ORDER_MAX;
    }

    @Override
    public void handle() throws RuntimeException {

    }

    @Override
    public int type() {
        return PRE;
    }
}
