package com.vteam.vtarm.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneAutoConfigure {

    /**
     * 标准分词器 .
     *
     * @param
     * @return org.apache.lucene.analysis.standard.StandardAnalyzer 标准分词器实例
     * @author andy.sher
     * @date 2018/10/31 10:01
     */
    @Bean
    public StandardAnalyzer standardAnalyzer() {
        return new StandardAnalyzer();
    }

    @Bean
    public IndexManager indexManager() {
        return new IndexManager();
    }

    @Bean
    public LuceneDispatcher luceneDispatcher() {
        return new LuceneDispatcher();
    }

    @Bean
    public QueryManager queryManager() {
        return new QueryManager();
    }

}
