package com.vteam.vtarm.runner;

import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 启动项管理器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 17:39
 */
@Slf4j
public class RunnerManager implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Map<String, Runner> runnerMap = SpringContextUtils.getBeansOfType(Runner.class);
        if (null != runnerMap && !runnerMap.isEmpty()) {
            List<Map.Entry<String, Runner>> filterMapEntry = new ArrayList<>(runnerMap.entrySet());
            List<Runner> runners = new ArrayList<>();
            filterMapEntry.stream().sorted(Comparator.comparing(e -> e.getValue().order())).forEach(e -> {
                runners.add(e.getValue());
            });
            String errorRunner = StringUtils.EMPTY;
            try {
                long start = System.currentTimeMillis();
                for (Runner runner : runners) {
                    errorRunner = runner.getClass().getSimpleName();
                    runner.run();
                }
                long end = System.currentTimeMillis();
                log.info("服务启动成功，启动项耗时：{} ms.", end - start);
            } catch (Exception e) {
                log.error("启动失败。", e);
                log.error("原因： {}", errorRunner);
                System.exit(1);
            }
        }
    }
}
