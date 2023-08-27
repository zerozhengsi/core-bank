package com.vteam.vtarm.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataProviderAutoConfigure {

    @Bean
    public DataProvider dataProvider() {
        return new DataProvider();
    }

}
