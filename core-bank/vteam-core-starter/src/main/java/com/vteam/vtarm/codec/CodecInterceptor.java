package com.vteam.vtarm.codec;

import com.vteam.vtarm.utils.SpringContextUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

/**
 * 数据安全查询拦截器 .<br>
 *
 * @author andy.sher
 * @date 2018/8/14 9:14
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
public class CodecInterceptor implements Interceptor {

    private boolean isInitConfig;

    private boolean isEnable;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object object = invocation.proceed();

        if (Boolean.FALSE.equals(this.isInitConfig)) {
            // 如果开启数据安控则进行解密操作
            CodecProperties codecProperties = SpringContextUtils.getBean(CodecProperties.class);
            this.isEnable = codecProperties.isDbEnable();
            this.isInitConfig = Boolean.TRUE;
        }

        // 如果开启数据安控则进行解密操作
        if (this.isEnable) {
            // 返回值为集合类型
            if (object instanceof Collection<?>) {
                Collection<?> collection = (Collection<?>) object;
                Iterator<?> iterator = collection.iterator();
                Object item;
                DataCodecExecutor dataCodecExecutor = SpringContextUtils.getBean(DataCodecExecutor.class);
                while (iterator.hasNext()) {
                    item = iterator.next();
                    dataCodecExecutor.decrypt(item);
                }
            }
            // 返回值类型为单个类型
            else if (object instanceof Serializable) {
                DataCodecExecutor dataCodecExecutor = SpringContextUtils.getBean(DataCodecExecutor.class);
                dataCodecExecutor.decrypt(object);
            }
        }

        return object;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
