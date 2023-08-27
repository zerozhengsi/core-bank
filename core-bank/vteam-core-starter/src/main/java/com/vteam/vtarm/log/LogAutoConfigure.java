package com.vteam.vtarm.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogAutoConfigure {

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

}
