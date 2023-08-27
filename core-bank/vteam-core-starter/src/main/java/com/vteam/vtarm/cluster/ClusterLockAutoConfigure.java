package com.vteam.vtarm.cluster;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClusterLockAutoConfigure {

    @Bean
    public RedisClusterLock redisClusterLock() {
        return new RedisClusterLock();
    }

}
