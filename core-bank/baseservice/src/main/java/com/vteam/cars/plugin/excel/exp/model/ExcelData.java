package com.vteam.cars.plugin.excel.exp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Excel数据 .<br>
 *
 * @author andy.sher
 * @date 2018/7/26 19:39
 */
@Getter
@Setter
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 4022114295956285797L;

    /**
     * 注解参数：开始行
     */
    private Integer startRow;

    /**
     * 注解参数：开始列
     */
    private Integer startColumn;

    /**
     * 注解参数：制表时间行
     */
    private Integer timeRow;

    /**
     * 注解参数：字段集合及排序
     */
    private String[] columns;

    /**
     * 注解参数：文件名称
     */
    private String exportFileName;

    /** 公式替换变量数组，格式参考：{ "I4&I&4" }，前面为替换变量，后面为固定列信息 */
    private String[] cellFormulaVar;

    /**
     * 表头
     */
    private Set<String> titles;

    /**
     * 数据
     */
    private List<List<Object>> rows;

    /** 当前行 */
    private int currRow;

    /** 当前列 */
    private int currColumn;

    /** 最后一行 */
    private int lastRowIndex;

    /** 默认行高 */
    private Float defaultHeight;

}
