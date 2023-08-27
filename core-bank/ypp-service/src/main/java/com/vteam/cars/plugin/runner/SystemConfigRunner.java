package com.vteam.cars.plugin.runner;

import com.vteam.cars.plugin.provider.SystemConfigProvider;
import com.vteam.vtarm.data.DataProvider;
import com.vteam.vtarm.runner.Runner;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 系统配置信息启动初始化 .<br>
 *
 * @author andy.sher
 * @date 2019/5/21 15:15
 */
@Component
public class SystemConfigRunner implements Runner {

    @Resource(type = DataProvider.class)
    private DataProvider dataProvider;

    @Override
    public int order() {
        return 2;
    }

    @Override
    public void run() throws Exception {
        dataProvider.rebuild(SystemConfigProvider.class);
    }

}
