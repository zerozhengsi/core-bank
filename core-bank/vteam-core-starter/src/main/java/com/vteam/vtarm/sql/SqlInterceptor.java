package com.vteam.vtarm.sql;

import com.vteam.vtarm.utils.SpringContextUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * SQL适配拦截器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/12 11:16
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class SqlInterceptor implements Interceptor {


    private static int MAPPED_STATEMENT_INDEX = 0;
    private static int PARAMETER_INDEX = 1;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement mappedStatement = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[PARAMETER_INDEX];
        final BoundSql boundSql = mappedStatement.getBoundSql(parameter);

        // 获取SQL适配器进行SQL适配
        HandlerManager handlerManager = SpringContextUtils.getBean(HandlerManager.class);
        SQLModel sqlModel = new SQLModel(boundSql.getSql());
        handlerManager.execute(sqlModel);

        // 把新的查询SQL放回statement
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sqlModel.getSql(), boundSql.getParameterMappings(), boundSql.getParameterObject());
        MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        queryArgs[MAPPED_STATEMENT_INDEX] = newMs;

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 复制映射语句 .<br>
     *
     * @param mappedStatement 映射语句
     * @param newSqlSource    新的SQL
     * @return org.apache.ibatis.mapping.MappedStatement
     * @author andy.sher
     * @date 2018/7/12 11:25
     */
    private MappedStatement copyFromMappedStatement(MappedStatement mappedStatement, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), newSqlSource, mappedStatement.getSqlCommandType());
        builder.resource(mappedStatement.getResource());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.statementType(mappedStatement.getStatementType());
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        if (mappedStatement.getKeyProperties() != null && mappedStatement.getKeyProperties().length > 0) {
            builder.keyProperty(mappedStatement.getKeyProperties()[0]);
        }
        builder.timeout(mappedStatement.getTimeout());
        builder.parameterMap(mappedStatement.getParameterMap());
        builder.resultMaps(mappedStatement.getResultMaps());
        builder.resultSetType(mappedStatement.getResultSetType());
        builder.cache(mappedStatement.getCache());
        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
        builder.useCache(mappedStatement.isUseCache());
        return builder.build();
    }

    /**
     * SQLSource .<br>
     *
     * @author andy.sher
     * @date 2018/7/12 11:24
     */
    public static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}
