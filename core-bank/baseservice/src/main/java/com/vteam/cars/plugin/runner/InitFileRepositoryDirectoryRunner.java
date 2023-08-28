package com.vteam.cars.plugin.runner;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.SmeConfigConstants;
import com.vteam.vtarm.runner.Runner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.io.File;

/**
 * 初始化文件仓库文件夹执行器 .<br>
 *
 * @author andy.sher
 * @date 2018/11/27 9:30
 */
@Slf4j
@Component
public class InitFileRepositoryDirectoryRunner implements Runner {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void run() throws Exception {
        // 如果启用的是本地文件仓库则初始化文件仓库文件夹
        if (SmeConfigConstants.FILE_ACTIVE_LOCAL.equals(smeConfiguration.getFileActive())) {
            File fileRepository = new File(smeConfiguration.getFileRepositoryPath());
            if (fileRepository.exists()) {
                if (!fileRepository.isDirectory()) {
                    fileRepository.mkdirs();
                }
            } else {
                fileRepository.mkdirs();
            }
            log.info(String.format("成功初始化目录[%s]", smeConfiguration.getFileRepositoryPath()));
        }
    }
}
