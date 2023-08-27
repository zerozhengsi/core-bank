package com.vteam.vtarm.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域配置 .<br>
 *
 * @author andy.sher
 * @date 2019/11/28 22:40
 */
public class CorsFilter implements Filter {

    /** 允许的请求类型 */
    public static final String ALLOW_METHOEDS = "GET,POST,DELETE,PUT";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN,Cookie");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
