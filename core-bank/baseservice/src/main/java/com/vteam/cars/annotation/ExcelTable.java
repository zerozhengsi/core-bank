package com.vteam.cars.annotation;

import java.lang.annotation.*;

/**
 * Excel模型 .<br>
 *
 * @author andy.sher
 * @date 2018/7/26 20:08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelTable {

    /**
     * 文件名称
     *
     * @return 文件名称
     */
    String exportFileName();

    /**
     * 开始行
     *
     * @return 开始行
     */
    int startRow();
    
    /** 开始列，默认第一列 */
    int startColumn() default 0;

    /**
     * 制表时间行
     * -1 为没有制表时间
     *
     * @return 制表时间行
     */
    int timeRow();

    /**
     * 字段集合及排序
     *
     * @return
     */
    String[] columns();
    
    /** 公式替换变量数组，格式参考：{ "I4&I&4" }，前面为替换变量，中间为固定列信息，后面为变量信息 */
    String[] cellFormulaVar() default "";

}
