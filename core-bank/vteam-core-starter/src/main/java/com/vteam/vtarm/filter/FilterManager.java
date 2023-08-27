package com.vteam.vtarm.filter;

import com.alibaba.fastjson.JSONObject;
import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.security.*;
import com.vteam.vtarm.status.GlobalStatus;
import com.vteam.vtarm.utils.HttpRequestUtils;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;

import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 过滤器组管理器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/19 11:30
 */
@Slf4j
public class FilterManager implements jakarta.servlet.Filter {

    private String[] ignoreUrls;

    private List<Filter> preFilters;

    private List<Filter> aroundFilters;

    private List<Filter> afterFilters;

    @Resource
    private FilterProperties filterProperties;

    @Resource
    private DefaultAnonymousAuthFilter defaultAnonymousAuthFilter;

    @Resource
    private DefaultUrlAuthFilter defaultUrlAuthFilter;

    @Resource
    private DefaultBasicAuthFilter defaultBasicAuthFilter;

    @Resource
    private DefaultSecurityValidFilter defaultSecurityValidFilter;

    @Resource
    private ReplayAttacksFilter replayAttacksFilter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器管理器。");
        ignoreUrls = filterProperties.getIgnoreUrl();
        this.preFilters = new ArrayList<>();
        this.aroundFilters = new ArrayList<>();
        this.afterFilters = new ArrayList<>();
        Map<String, Filter> filterMap = SpringContextUtils.getBeansOfType(Filter.class);
        if (null != filterMap && !filterMap.isEmpty()) {
            List<Map.Entry<String, Filter>> filterMapEntry = new ArrayList<>(filterMap.entrySet());
            // 前置过滤器
            preFilters.add(defaultAnonymousAuthFilter);
            preFilters.add(defaultUrlAuthFilter);
            preFilters.add(defaultBasicAuthFilter);
            preFilters.add(defaultSecurityValidFilter);
            preFilters.add(replayAttacksFilter);
            filterMapEntry.stream().filter(e -> Filter.PRE == e.getValue().type() && e.getValue().order() >= Filter.ORDER_MAX && e.getValue().order() <= Filter.ORDER_MIN).sorted(Comparator.comparing(e -> e.getValue().order())).forEach(e -> preFilters.add(e.getValue()));
            // 环绕过滤器
            filterMapEntry.stream().filter(e -> Filter.AROUND == e.getValue().type() && e.getValue().order() >= Filter.ORDER_MAX && e.getValue().order() <= Filter.ORDER_MIN).sorted(Comparator.comparing(e -> e.getValue().order())).forEach(e -> aroundFilters.add(e.getValue()));
            // 后置过滤器
            filterMapEntry.stream().filter(e -> Filter.AFTER == e.getValue().type() && e.getValue().order() >= Filter.ORDER_MAX && e.getValue().order() <= Filter.ORDER_MIN).sorted(Comparator.comparing(e -> e.getValue().order())).forEach(e -> afterFilters.add(e.getValue()));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        GlobalStatus.setStartTime(System.currentTimeMillis());
        // 设置全局http请求响应对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        GlobalStatus.setRequest(servletRequest);
        GlobalStatus.setResponse(servletResponse);

        // 初始化IP地址
        GlobalStatus.setIpAddress(HttpRequestUtils.getRemoteIP(GlobalStatus.getRequest()));

        // 忽略ws请求
        String url = GlobalStatus.getRequest().getRequestURI();
        if (Arrays.asList(ignoreUrls).contains(url)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            // 调用前置处理器进行处理
            this.preFilters.stream().filter(filter -> filter.active()).forEach(filter -> filter.handle());
            // 调用环绕处理器进行处理
            this.aroundFilters.stream().filter(filter -> filter.active()).forEach(filter -> filter.handle());
            chain.doFilter(request, response);
            // 调用环绕处理器进行处理
            this.aroundFilters.stream().filter(filter -> filter.active()).forEach(filter -> filter.handle());
            // 调用后置处理器进行处理
            this.afterFilters.stream().filter(filter -> filter.active()).forEach(filter -> filter.handle());
        } catch (UnauthenticatedException ue) {
            log.info(FilterManager.class.getName(), ue);
            write(RespEntity.unauthenticated(), response);
        } catch (TokenExpiredException tee) {
            log.info(FilterManager.class.getName(), tee);
            write(RespEntity.expired(), response);
        } catch (ReplayAttacksException | RequestRefuseException raerre) {
            log.info(FilterManager.class.getName(), raerre);
            write(RespEntity.requestRefuse(), response);
        } catch (Exception e) {
            log.info(FilterManager.class.getName(), e);
            write(RespEntity.error(), response);
        } finally {
            GlobalStatus.setEndTime(System.currentTimeMillis());
            log.info("[{}] -> total: {} ms, business: {} ms.", GlobalStatus.getRequest().
                    getRequestURI(), (GlobalStatus.getEndTime() - GlobalStatus.getStartTime()), (GlobalStatus.getBusinessEndTime() - GlobalStatus.getBusinessStartTime()));
            GlobalStatus.destroy();
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 输出消息到客户端 .
     *
     * @param respEntity 消息对象
     * @return void
     * @author andy.sher
     * @date 2019/11/19 13:39
     */
    private void write(RespEntity respEntity, ServletResponse response) throws UnauthenticatedException {
        response.setCharacterEncoding(CommonConstants.Character.UTF_8);
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        try (PrintWriter pw = response.getWriter()) {
            pw.print(JSONObject.toJSONString(respEntity));
        } catch (Exception e) {
            log.error(FilterManager.class.getName(), e);
        }
    }

}
