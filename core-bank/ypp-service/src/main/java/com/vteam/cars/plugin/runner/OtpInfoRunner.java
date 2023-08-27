package com.vteam.cars.plugin.runner;

import com.vteam.cars.plugin.provider.OtpInfoProvider;
import com.vteam.vtarm.data.DataProvider;
import com.vteam.vtarm.runner.Runner;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 行业信息启动初始化
 *
 * @author jiangming.huang
 * @date 2019/6/6 17:10
 */
@Component
public class OtpInfoRunner implements Runner {

    @Resource
    private DataProvider dataProvider;

    @Override
    public void run() throws Exception {
        dataProvider.rebuild(OtpInfoProvider.class);
    }

    @Override
    public int order() {
        return 2;
    }

}
