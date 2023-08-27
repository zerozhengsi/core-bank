package com.vteam.cars.plugin.runner;

import com.vteam.cars.plugin.provider.HdyInfoProvider;
import com.vteam.vtarm.data.DataProvider;
import com.vteam.vtarm.runner.Runner;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 节假日启动初始化
 *
 * @author care.zheng
 */
@Component
public class HdyInfoRunner implements Runner {

    @Resource
    private DataProvider dataProvider;

    @Override
    public void run() throws Exception {
        dataProvider.rebuild(HdyInfoProvider.class);
    }

    @Override
    public int order() {
        return 2;
    }

}
