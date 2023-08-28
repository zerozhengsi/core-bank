package com.vteam.cars.plugin.runner;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.vtarm.runner.Runner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.io.File;

/**
 * 初始化Lucene索引文件夹执行器 .<br>
 *
 * @author andy.sher
 * @date 2018/11/27 9:28
 */
@Slf4j
@Component
public class InitLuceneDirectoryRunner implements Runner {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void run() throws Exception {
        // 创建Lucene索引文件夹
        File luceneDirectory = new File(smeConfiguration.getLucenePath());
        if (luceneDirectory.exists()) {
            if (!luceneDirectory.isDirectory()) {
                luceneDirectory.mkdirs();
            }
        } else {
            luceneDirectory.mkdirs();
        }
        log.info(String.format("成功初始化Lucene索引目录[%s]", smeConfiguration.getLucenePath()));
    }
}
