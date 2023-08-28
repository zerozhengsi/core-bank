package com.vteam.cars.plugin.runner;

import com.vteam.cars.plugin.provider.I18NResourceProvider;
import com.vteam.vtarm.data.DataProvider;
import com.vteam.vtarm.runner.Runner;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * i18n资源 启动初始化 .<br>
 *
 * @author andy.sher
 * @date 2019/5/21 15:12
 */
@Component
public class I18NResourceRunner implements Runner {

    @Resource(type = DataProvider.class)
    private DataProvider dataProvider;

    @Override
    public int order() {
        return 4;
    }

    @Override
    public void run() throws Exception {
        dataProvider.rebuild(I18NResourceProvider.class);
    }
}
