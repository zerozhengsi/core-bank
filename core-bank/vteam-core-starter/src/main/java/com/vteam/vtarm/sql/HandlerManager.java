package com.vteam.vtarm.sql;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;

import com.vteam.vtarm.utils.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * SQL适配器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/12 9:34
 */
@Slf4j
public class HandlerManager {

    private SQLHandler[] handlerChains;

    @Resource
    private SqlProperties sqlProperties;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        // 获取系统数据库类型
        SqlProperties.Type dbType = sqlProperties.getType();
        if (null == dbType) {
            log.error("请配置数据库类型。 -> spring.vtarm.sql.type");
        }
        // 获取所有的处理器并组装成处理器链
        if (SqlProperties.Type.MYSQL.equals(dbType)) {
            Map<String, BaseMYSQLHandler> sqlHandlerMap = SpringContextUtils.getBeansOfType(BaseMYSQLHandler.class);
            setSQLHandlers(sqlHandlerMap);
        } else if (SqlProperties.Type.ORACLE.equals(dbType)) {
            Map<String, BaseORACLEHandler> sqlHandlerMap = SpringContextUtils.getBeansOfType(BaseORACLEHandler.class);
            setSQLHandlers(sqlHandlerMap);
        } else if (SqlProperties.Type.MSSQL.equals(dbType)) {
            Map<String, BaseMSSQLHandler> sqlHandlerMap = SpringContextUtils.getBeansOfType(BaseMSSQLHandler.class);
            setSQLHandlers(sqlHandlerMap);
        } else if (SqlProperties.Type.DB2.equals(dbType)) {
            Map<String, BaseDB2Handler> sqlHandlerMap = SpringContextUtils.getBeansOfType(BaseDB2Handler.class);
            setSQLHandlers(sqlHandlerMap);
        } else if (SqlProperties.Type.KDB.equals(dbType)) {
            // 浪潮KDB数据库
            Map<String, BaseKDBHandler> sqlHandlerMap = SpringContextUtils.getBeansOfType(BaseKDBHandler.class);
            setSQLHandlers(sqlHandlerMap);
        } else if (SqlProperties.Type.DEFAULT.equals(dbType)) {
            Map<String, BaseDefaultHandler> sqlHandlerMap = SpringContextUtils.getBeansOfType(BaseDefaultHandler.class);
            setSQLHandlers(sqlHandlerMap);
        }
    }

    /**
     * 执行SQL适配 .<br>
     *
     * @param model sql模型
     * @return void
     * @author andy.sher
     * @date 2018/7/12 9:58
     */
    public void execute(SQLModel model) {
        if (null != this.handlerChains && this.handlerChains.length > 0) {
            if (CollectionUtils.isNotEmpty(Arrays.asList(this.handlerChains))) {
                if (null != model) {
                    this.handlerChains[0].doHandler(model);
                }
            }
        }
    }

    /**
     * 组装处理器链 .<br>
     *
     * @param handlerMap 处理器集合
     * @return void
     * @author andy.sher
     * @date 2018/7/12 9:49
     */
    private <T extends SQLHandler> void setSQLHandlers(Map<String, T> handlerMap) {
        handlerChains = new SQLHandler[handlerMap.entrySet().size()];
        Iterator<Map.Entry<String, T>> iterator = handlerMap.entrySet().iterator();

        // 装配处理器
        Map.Entry<String, T> entry;
        int i = 0;
        while (iterator.hasNext()) {
            entry = iterator.next();
            handlerChains[i] = entry.getValue();
            i++;
        }

        // 处理器按权重排序
        Arrays.sort(handlerChains);

        // 初始化处理器链
        SQLHandler nextHandler;
        for (int j = 0, len = this.handlerChains.length - 1; j < len; j++) {
            nextHandler = this.handlerChains[j + 1];
            this.handlerChains[j].setNextHandler(nextHandler);
        }
    }

}
