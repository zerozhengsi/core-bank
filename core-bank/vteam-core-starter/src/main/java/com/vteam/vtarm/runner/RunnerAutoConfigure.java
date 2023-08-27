package com.vteam.vtarm.runner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunnerAutoConfigure {

    @Bean
    public RunnerManager runnerManager() {
        return new RunnerManager();
    }

}
