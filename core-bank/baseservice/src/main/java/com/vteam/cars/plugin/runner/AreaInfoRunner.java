package com.vteam.cars.plugin.runner;

import com.vteam.cars.plugin.provider.AreaInfoProvider;
import com.vteam.vtarm.data.DataProvider;
import com.vteam.vtarm.runner.Runner;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 省市区信息启动初始化
 *
 * @author jiangming.huang
 * @date 2019/6/6 17:44
 */
@Component
public class AreaInfoRunner implements Runner {

    @Resource(type = DataProvider.class)
    private DataProvider dataProvider;

    @Override
    public int order() {
        return 2;
    }

    @Override
    public void run() throws Exception {
        dataProvider.rebuild(AreaInfoProvider.class);
    }
}
