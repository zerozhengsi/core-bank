package com.vteam.vtarm.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheContainerAutoConfigure {

    @Bean
    public CacheContainer cacheContainer() {
        return new CacheContainer();
    }

    @Bean
    public MapContainer mapContainer() {
        return new MapContainer();
    }

    @Bean
    public SequenceContainer sequenceContainer() {
        return new SequenceContainer();
    }

    @Bean
    public SetContainer setContainer() {
        return new SetContainer();
    }

    @Bean
    public StringValueContainer stringValueContainer() {
        return new StringValueContainer();
    }

}
