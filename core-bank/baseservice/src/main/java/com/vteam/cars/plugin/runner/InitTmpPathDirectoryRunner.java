package com.vteam.cars.plugin.runner;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.vtarm.runner.Runner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.io.File;

/**
 * 初始化临时文件夹目录 .<br>
 *
 * @author andy.sher
 * @date 2018/11/27 9:25
 */
@Slf4j
@Component
public class InitTmpPathDirectoryRunner {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    public int order() {
        return 0;
    }

    public void run() throws Exception {
        // 创建临时文件夹
        File tmpDirectory = new File(smeConfiguration.getTmpPath());
        if (tmpDirectory.exists()) {
            if (!tmpDirectory.isDirectory()) {
                tmpDirectory.mkdirs();
            }
        } else {
            tmpDirectory.mkdirs();
        }
        log.info(String.format("成功初始化目录[%s]", smeConfiguration.getTmpPath()));
    }
}
