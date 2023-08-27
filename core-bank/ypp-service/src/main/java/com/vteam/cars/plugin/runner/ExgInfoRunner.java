package com.vteam.cars.plugin.runner;

import com.vteam.cars.plugin.provider.ExgParamProvider;
import com.vteam.vtarm.data.DataProvider;
import com.vteam.vtarm.runner.Runner;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 数据字典信息启动初始化
 *
 * @author jiangming.huang
 * @date 2019/6/10 14:06
 */
@Component
public class ExgInfoRunner implements Runner {

    @Resource
    private DataProvider dataProvider;

    @Override
    public int order() {
        return 3;
    }

    @Override
    public void run() throws Exception {
        dataProvider.rebuild(ExgParamProvider.class);
    }
}
