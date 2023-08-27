package com.vteam.vtarm.filter;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.TaskExecutor;

import com.vteam.vtarm.VtarmActuatorProperties;
import com.vteam.vtarm.security.DefaultAnonymousAuthFilter;
import com.vteam.vtarm.security.DefaultBasicAuthFilter;
import com.vteam.vtarm.security.DefaultSecurityValidFilter;
import com.vteam.vtarm.security.DefaultUrlAuthFilter;
import com.vteam.vtarm.security.ProlongedTokenFilter;
import com.vteam.vtarm.security.SecurityProperties;
import com.vteam.vtarm.security.TokenContainer;

/**
 * 处理器注册器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/19 9:45
 */
@EnableConfigurationProperties({FilterProperties.class})
@AutoConfigureAfter({SecurityProperties.class, TokenContainer.class, TaskExecutor.class})
@DependsOn(value = {"springContextUtil"})
@Configuration
public class FilterAutoConfigure {

    /**
     * token续费过滤器 .
     *
     * @param tokenContainer token容器
     * @param taskExecutor   线程池
     * @return com.vteam.vtarm.security.ProlongedTokenFilter
     * @author andy.sher
     * @date 2020/7/24 15:03
     */
    @Bean
    public ProlongedTokenFilter prolongedTokenFilter(TokenContainer tokenContainer, TaskExecutor taskExecutor) {
        return new ProlongedTokenFilter(tokenContainer, taskExecutor);
    }

    /**
     * 注册默认鉴权处理器 .
     *
     * @param
     * @return com.vteam.vtarm.web.filter.DefaultAuthenticationFilter 默认鉴权处理器
     * @author andy.sher
     * @date 2019/11/19 19:56
     */
    @Bean
    public DefaultUrlAuthFilter defaultAuthenticationFilter(TokenContainer tokenContainer, SecurityProperties securityProperties) {
        return new DefaultUrlAuthFilter(tokenContainer, securityProperties);
    }

    /**
     * 注册默认Basic鉴权处理器 .
     *
     * @param vtarmActuatorProperties Actuator 配置
     * @return com.vteam.vtarm.security.DefaultBasicAuthFilter 默认Basic鉴权处理器
     * @author andy.sher
     * @date 2020/4/22 14:28
     */
    @Bean
    public DefaultBasicAuthFilter defaultBasicAuthFilter(VtarmActuatorProperties vtarmActuatorProperties) {
        return new DefaultBasicAuthFilter(vtarmActuatorProperties);
    }

    /**
     * 注册默认匿名鉴权处理器 .
     *
     * @param securityProperties 安控配置
     * @return com.vteam.vtarm.security.DefaultAnonymousAuthFilter 默认匿名鉴权处理器
     * @author andy.sher
     * @date 2020/4/22 14:28
     */
    @Bean
    public DefaultAnonymousAuthFilter defaultAnonymousAuthFilter(SecurityProperties securityProperties) {
        return new DefaultAnonymousAuthFilter(securityProperties);
    }

    /**
     * 注册默认检查认证结果处理器 .
     *
     * @param securityProperties 安控配置
     * @return com.vteam.vtarm.security.DefaultCheckAuthFilter 默认检查认证结果处理器
     * @author andy.sher
     * @date 2020/4/22 14:30
     */
    @Bean
    public DefaultSecurityValidFilter defaultCheckAuthFilter(SecurityProperties securityProperties) {
        return new DefaultSecurityValidFilter(securityProperties);
    }

    /**
     * 过滤器代理 .
     *
     * @param
     * @return com.vteam.vtarm.filter.FilterManager 过滤器代理
     * @author andy.sher
     * @date 2020/4/22 14:55
     */
    @Bean
    public FilterManager filterManager() {
        return new FilterManager();
    }

    /**
     * 跨域过滤器 .
     *
     * @param
     * @return com.vteam.vtarm.filter.CorsFilter 跨域过滤器
     * @author andy.sher
     * @date 2020/4/22 14:56
     */
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }

    /**
     * 注册过滤器代理 .
     *
     * @param
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean 注册过滤器代理
     * @author andy.sher
     * @date 2019/11/19 13:09
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterManagerRegistrationBean() {
        // 新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加自定义 过滤器
        registration.setFilter(filterManager());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        //设置过滤器顺序
        registration.setOrder(8);
        return registration;
    }

    /**
     * 注册跨域过滤器 .
     *
     * @param
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean 注册跨域过滤器
     * @author andy.sher
     * @date 2019/11/19 13:09
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        // 新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加自定义 过滤器
        registration.setFilter(corsFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        //设置过滤器顺序
        registration.setOrder(0);
        return registration;
    }

}
