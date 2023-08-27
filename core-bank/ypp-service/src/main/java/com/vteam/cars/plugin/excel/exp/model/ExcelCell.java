package com.vteam.cars.plugin.excel.exp.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;

/**
 * Excel模板单元格信息 .<br>
 *
 * @author oscar.yu
 * @date 2019/8/7 9:54
 */
@Getter
@Setter
public class ExcelCell {

    /**
     * 单元格类型
     */
    private CellType cellType;

    /**
     * 单元格样式
     */
    private CellStyle cellStyle;

    /**
     * 单元格公式
     */
    private String cellFormula;

}
