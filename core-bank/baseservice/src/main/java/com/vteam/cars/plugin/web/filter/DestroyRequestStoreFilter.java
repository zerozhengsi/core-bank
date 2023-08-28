package com.vteam.cars.plugin.web.filter;

import com.vteam.cars.common.RequestStore;
import com.vteam.vtarm.filter.Filter;
import org.springframework.stereotype.Component;

/**
 * 销毁请求参数库 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 15:55
 */
@Component
public class DestroyRequestStoreFilter implements Filter {

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
        RequestStore.destroy();
    }

    @Override
    public int type() {
        return AFTER;
    }
}
