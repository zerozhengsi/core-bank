package com.vteam.vtarm.lucene;

import com.vteam.vtarm.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.constraints.NotNull;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索索引处理器
 *
 * @author zack.yin
 * @date 2018/7/18 16:30
 */
@Slf4j
public class QueryManager {

    private int SEARCH_NUMBER = 1000;

    public QueryManager() {
        log.info("Lucene索引查询器初始化成功");
    }

    /**
     * 获取索引文档 .
     *
     * @param queries 查询值
     * @param field   查询字段
     * @return org.apache.lucene.search.TopDocs 符合条件的文档
     * @author andy.sher
     * @date 2018/10/31 12:45
     */
    public List<Object> get(LuceneStrategy luceneStrategy, @NotNull String queries, @NotNull String field) {
        synchronized (this) {
            List<Object> objectList = null;
            // 打开索引文件
            try (Directory dir = FSDirectory.open(Paths.get(luceneStrategy.getRepoPath())); IndexReader reader = DirectoryReader.open(dir);) {
                IndexSearcher is = new IndexSearcher(reader);

                Query query = getWildcardQuery(queries, field);
                // 查询所有的索引
                TopDocs hits = is.search(query, SEARCH_NUMBER);
                int count = hits.totalHits;

                String[] fields = luceneStrategy.getFields();
                // 根据策略进行索引查询
                if (count > 0) {
                    objectList = new ArrayList<>(hits.totalHits);
                    BeanWrapper beanWrapper;
                    Document document;
                    Object object;

                    for (ScoreDoc scoreDoc : hits.scoreDocs) {
                        document = is.doc(scoreDoc.doc);
                        object = luceneStrategy.getId().newInstance();
                        beanWrapper = new BeanWrapperImpl(object);
                        for (String fieldName : fields) {
                            String text = document.get(fieldName);
                            beanWrapper.setPropertyValue(fieldName, text);
                        }
                        objectList.add(object);
                    }
                }
            } catch (Exception e) {
                log.error(QueryManager.class.getName(), e);
            }
            return objectList;
        }
    }

    /**
     * 获取通配符查询器 .
     *
     * @param queries 查询关键词（多个请以逗号分开）
     * @param field   查询字段（多个请以逗号分开）
     * @return org.apache.lucene.search.Query
     * @author andy.sher
     * @date 2018/11/1 13:51
     */
    public Query getWildcardQuery(String queries, String field) {
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        // 如果条件中包含逗号，则表示为多条件按与查询
        if (queries.contains(CommonConstants.Symbol.COMMENT_SIGN)) {
            String[] queriesArray = queries.split(CommonConstants.Symbol.COMMENT_SIGN);
            String[] fieldArray = field.split(CommonConstants.Symbol.COMMENT_SIGN);
            Term term;
            WildcardQuery wildcardQuery;
            for (int i = 0, size = queriesArray.length; i < size; i++) {
                term = new Term(fieldArray[i], CommonConstants.Symbol.ASTERISK + queriesArray[i] + CommonConstants.Symbol.ASTERISK);
                wildcardQuery = new WildcardQuery(term);
                builder.add(wildcardQuery, BooleanClause.Occur.MUST);
            }
            BooleanQuery booleanQuery = builder.build();

            return booleanQuery;
        } else {
            Term term = new Term(field, CommonConstants.Symbol.ASTERISK + queries + CommonConstants.Symbol.ASTERISK);
            WildcardQuery wildcardQuery = new WildcardQuery(term);
            return wildcardQuery;
        }
    }

}