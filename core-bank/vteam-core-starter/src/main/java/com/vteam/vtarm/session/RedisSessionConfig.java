package com.vteam.vtarm.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * redis session共享 .<br>
 *
 * @author andy.sher
 * @date 2019/7/23 13:51
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
