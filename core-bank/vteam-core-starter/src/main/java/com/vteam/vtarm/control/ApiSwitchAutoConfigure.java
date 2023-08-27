package com.vteam.vtarm.control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiSwitchAutoConfigure {

    @Bean
    public ApiSwitchAspect apiSwitchAspect() {
        return new ApiSwitchAspect();
    }

}
