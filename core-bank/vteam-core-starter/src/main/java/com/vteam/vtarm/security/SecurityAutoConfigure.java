package com.vteam.vtarm.security;

import com.vteam.vtarm.cache.CacheContainerAutoConfigure;
import com.vteam.vtarm.cache.StringValueContainer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfigureAfter({CacheContainerAutoConfigure.class})
@EnableConfigurationProperties(SecurityProperties.class)
@Configuration
public class SecurityAutoConfigure {

    @Bean(name = "tokenContainer")
    public TokenContainer tokenContainer() {
        return new TokenContainer();
    }

    @Bean
    public AuthorizationAspect authorizationAspect() {
        return new AuthorizationAspect();
    }

    @Bean
    public LogoutFilter logoutFilter() {
        return new DefaultLogoutFilter(tokenContainer());
    }

    @Bean
    public InternalServiceAspect internalServiceAspect() {
        return new InternalServiceAspect();
    }

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter(tokenContainer());
    }

    @Bean
    public ReplayAttacksFilter replayAttacksFilter(StringValueContainer stringValueContainer, SecurityProperties securityProperties) {
        return new ReplayAttacksFilter(stringValueContainer, securityProperties);
    }


}
