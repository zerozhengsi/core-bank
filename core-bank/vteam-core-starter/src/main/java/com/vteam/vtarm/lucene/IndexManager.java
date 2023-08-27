package com.vteam.vtarm.lucene;

import com.vteam.vtarm.utils.RichTextUtils;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.annotation.Resource;
import java.nio.file.Paths;

/**
 * 索引添加处理器
 *
 * @author zack.yin
 * @date 2018/7/18 9:34
 */
@Slf4j
public class IndexManager {

    @Resource
    private StandardAnalyzer standardAnalyzer;

    /**
     * 添加索引
     *
     * @param objects 数据列表
     * @return void
     * @author zack.yin
     * @date 2018/7/19 14:43
     */
    public void add(@NotNull LuceneStrategy luceneStrategy, @NotNull Object... objects) {
        // 打开索引文件
        try (Directory dir = FSDirectory.open(Paths.get(luceneStrategy.getRepoPath()))) {
            IndexWriterConfig iwc = new IndexWriterConfig(standardAnalyzer);
            IndexWriter writer = new IndexWriter(dir, iwc);
            String[] fields = luceneStrategy.getFields();
            BeanWrapper beanWrapper;
            Document document;
            // 根据策略进行索引添加
            for (Object object : objects) {
                beanWrapper = new BeanWrapperImpl(object);
                document = new Document();
                for (String field : fields) {
                    document.add(new StringField(field, null != beanWrapper.getPropertyValue(field) ? RichTextUtils.stripHtml(beanWrapper.getPropertyValue(field).toString()) : StringUtils.EMPTY, Field.Store.YES));
                }
                writer.addDocument(document);
            }
            writer.close();
        } catch (Exception e) {
            log.error(IndexManager.class.getName(), e);
        }
    }

}