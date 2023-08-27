package com.vteam.vtarm.lucene;

/**
 * Lucene策略 .<br>
 *
 * @author andy.sher
 * @date 2018/11/7 13:54
 */
public interface LuceneStrategy {

    /**
     * 获取处理器标识 .
     *
     * @param
     * @return java.lang.Class<?> 处理器标识
     * @author andy.sher
     * @date 2018/10/31 13:20
     */
    Class<?> getId();

    /**
     * 需要索引的字段 .
     *
     * @param
     * @return java.lang.String[] 字段数组
     * @author andy.sher
     * @date 2018/11/7 13:37
     */
    String[] getFields();

    /**
     * 获取Lucene仓库路径 .
     *
     * @param
     * @return java.lang.String Lucene仓库路径
     * @author andy.sher
     * @date 2018/11/19 14:13
     */
    String getRepoPath();

}
