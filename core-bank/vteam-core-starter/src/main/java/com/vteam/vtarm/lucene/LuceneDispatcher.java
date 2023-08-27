package com.vteam.vtarm.lucene;

import com.alibaba.fastjson.JSONObject;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import java.util.*;

/**
 * Lucene统一调度器 .<br>
 *
 * @author andy.sher
 * @date 2018/10/31 13:17
 */
@Slf4j
public class LuceneDispatcher {

    private Map<String, LuceneStrategy> strategyMap = new HashMap<>();

    @Resource
    private IndexManager indexManager;

    @Resource
    private QueryManager queryManager;

    @PostConstruct
    public void initHandler() {
        // 获取所有数据处理器集合并设置处理器链
        Map<String, LuceneStrategy> tempMap = SpringContextUtils.getBeansOfType(LuceneStrategy.class);
        Iterator<Map.Entry<String, LuceneStrategy>> iterator = tempMap.entrySet().iterator();
        LuceneStrategy luceneStrategy;
        while (iterator.hasNext()) {
            luceneStrategy = iterator.next().getValue();
            strategyMap.put(luceneStrategy.getId().getSimpleName(), luceneStrategy);
        }
    }

    /**
     * 添加索引 .
     *
     * @param target 对象
     * @return void
     * @author andy.sher
     * @date 2018/10/31 13:30
     */
    public void add(Object[] target, Class<?> clazz) {
        if (null != target) {
            LuceneStrategy luceneStrategy = null;
            // 设置Lucene策略
            if (strategyMap.containsKey(clazz.getSimpleName())) {
                luceneStrategy = strategyMap.get(clazz.getSimpleName());
            } else if (strategyMap.containsKey(clazz.getSuperclass().getSimpleName())) {
                luceneStrategy = strategyMap.get(clazz.getSuperclass().getSimpleName());
            }
            // 当索引管理器准备就绪时添加目标
            if (null != luceneStrategy) {
                indexManager.add(luceneStrategy, target);
                log.info(String.format("添加索引：%s, 索引策略：%s.", JSONObject.toJSONString(target), clazz));
            }
        }
    }

    /**
     * 获取主键数组 .
     *
     * @param queries 查询值
     * @param fields  查询字段
     * @param clazz   查询目标类型
     * @return java.lang.String[] 主键数组
     * @author andy.sher
     * @date 2018/10/31 13:52
     */
    public <T> String[] getRefcodes(@NotNull String queries, @NotNull String fields, @NotNull Class<T> clazz) {
        // 获取符合条件的拆箱后的数据
        List<T> list = this.search(queries, fields, clazz);
        BeanWrapper beanWrapper;
        Object refcode;
        List<String> refcodeList;
        if (CollectionUtils.isNotEmpty(list)) {
            // 遍历收集流水号
            refcodeList = new ArrayList<>(list.size());
            for (T t : list) {
                beanWrapper = new BeanWrapperImpl(t);
                refcode = beanWrapper.getPropertyValue("refcode");
                refcodeList.add(refcode.toString());
            }

            String[] refcodes = new String[refcodeList.size()];
            return refcodeList.toArray(refcodes);
        }

        return null;
    }

    /**
     * 搜索符合条件的对象集合（多个条件时各条件之间按与查询） .
     *
     * @param queries 查询值（多个时请以逗号分开）
     * @param fields  查询字段（多个时请以逗号分开）
     * @param clazz   查询目标对象类型
     * @return java.util.List<T> 符合条件的对象集合
     * @author andy.sher
     * @date 2018/10/31 15:03
     */
    public <T> List<T> search(@NotNull String queries, @NotNull String fields, @NotNull Class<T> clazz) {
        List<T> targetList = null;
        if (StringUtils.isNotEmpty(queries) && StringUtils.isNotEmpty(fields)) {
            LuceneStrategy luceneStrategy = null;
            // 设置Lucene策略
            if (strategyMap.containsKey(clazz.getSimpleName())) {
                luceneStrategy = strategyMap.get(clazz.getSimpleName());
            } else if (strategyMap.containsKey(clazz.getSuperclass().getSimpleName())) {
                luceneStrategy = strategyMap.get(clazz.getSuperclass().getSimpleName());
            }
            // 当查询起准备就绪时进行数据获取
            if (null != luceneStrategy) {
                // 获取结果集合
                List<Object> objectList = queryManager.get(luceneStrategy, queries.toLowerCase(), fields);
                // 当集合不为空的情况下进行拆箱
                if (CollectionUtils.isNotEmpty(objectList)) {
                    targetList = new ArrayList<>(objectList.size());
                    T t;
                    try {
                        for (Object object : objectList) {
                            if (clazz.isInstance(object)) {
                                t = clazz.newInstance();
                                BeanUtils.copyProperties(object, t);
                                targetList.add(t);
                            }
                        }
                    } catch (InstantiationException | IllegalAccessException e) {
                        log.error(LuceneDispatcher.class.getName(), e);
                    } catch (Exception e) {
                        log.error(LuceneDispatcher.class.getName(), e);
                    }
                }
            }
        }
        return targetList;
    }

    /**
     * 校验是否存在Lucene策略
     *
     * @param clazz
     * @return boolean
     * @author jiangming.huang
     * @date 2019/7/23 18:07
     */
    public boolean hasStrategy(Class<?> clazz) {
        return strategyMap.containsKey(clazz.getSimpleName()) || strategyMap.containsKey(clazz.getSuperclass().getSimpleName());
    }

}
