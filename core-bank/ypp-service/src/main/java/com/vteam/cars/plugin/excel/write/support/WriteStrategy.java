package com.vteam.cars.plugin.excel.write.support;

import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.util.ExcelUtils;
import com.vteam.cars.util.SmeAssert;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 写入策略
 *
 * @author fu.tong
 * @date 2018/8/9 15:20
 */
public interface WriteStrategy<T> {

    /**
     * 写入到Excel .
     *
     * @param sheet    数据表
     * @param file     文件
     * @param workbook 数据簿
     * @param list     数据
     * @param rowId    开始行
     * @return com.vteam.sme.entity.model.FbtxApxM
     * @author fu.tong
     * @date 2018/8/16 15:00
     */
    default FbtxApxM write(Sheet sheet, File file, Workbook workbook, List<T> list, int rowId) {
        return orientedWriteTemplate(sheet, file, workbook, list);
    }

    /**
     * 流程化写表
     *
     * @param sheet    当前操作表
     * @param file     当前操作文件
     * @param workbook 当前操作workbook
     * @param list     数据源对象
     * @return 操作结果
     * @author shiping.song
     * @date 2023/1/18 10:55
     */
    default FbtxApxM orientedWriteTemplate(Sheet sheet, File file, Workbook workbook, List<T> list) {
        // 未开启流程化写表调用本方法则直接抛出错误
        SmeAssert.isTrue(this.oriented(), "未开启流程化写表功能");
        int rowId = this.rowId();
        int colId = this.colId();
        int counter = 1;
        boolean writeErrorListFlag = this.isWriteErrorList();
        if (writeErrorListFlag) {
            Cell newCell = sheet.getRow(rowId).createCell(colId + 1);
            newCell.setCellValue(this.errorColumnName());
            // 标题行错误列前一列的样式
            CellStyle style = sheet.getRow(rowId).getCell(colId).getCellStyle();
            newCell.setCellStyle(style);
        }
        Row titleRow = sheet.getRow(rowId);
        if (Objects.nonNull(titleRow)) {
            for (T t : list) {
                Row newRow = this.createRowAndCopyCellStyle(sheet, ++rowId);
                String errorInfo = this.write(sheet, newRow, t, counter);
                counter++;
                if (writeErrorListFlag) {
                    Cell cell = newRow.createCell(colId + 1);
                    cell.setCellStyle(newRow.getCell(colId, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellStyle());
                    cell.setCellValue(errorInfo);
                }
            }
            // 输出文件
            return ExcelUtils.writeList(workbook, file, this.fileName());
        }
        return null;
    }

    /**
     * 创建行并拷贝样式
     *
     * @param sheet           表格
     * @param currentRowIndex 行索引
     * @return 创建的新行
     * @author shiping.song
     * @date 2023/2/9 9:34
     */
    default Row createRowAndCopyCellStyle(Sheet sheet, int currentRowIndex) {
        // 模板行为标题行后的第一行
        int templateRowIndex = this.rowId() + 1;
        Row templateRow = sheet.getRow(templateRowIndex);
        // 模板行不能为空
        SmeAssert.notNull(templateRow, "错误清单模板行为空");
        if (currentRowIndex == templateRowIndex) {
            // 如果是模板行则直接返回
            return templateRow;
        } else if (Objects.nonNull(sheet.getRow(currentRowIndex))) {
            return sheet.getRow(currentRowIndex);
        }
        int maxColIndex = this.colId();
        Row newRow = sheet.createRow(currentRowIndex);
        // 拷贝样式
        for (int i = 0; i < maxColIndex; i++) {
            Cell cell = newRow.createCell(i);
            cell.setCellStyle(templateRow.getCell(i).getCellStyle());
        }
        return newRow;
    }

    /**
     * 写数据，如果有错误信息则返回错误数据，如果没有错误信息则不返回
     *
     * @param sheet   当前操作sheet
     * @param row     当前操作行
     * @param t       数据源对象
     * @param counter 计数器，标识当前处理的是第几行的数据
     * @return 错误信息
     * @author shiping.song
     * @date 2023/1/18 10:57
     */
    default String write(Sheet sheet, Row row, T t, int counter) {
        return null;
    }

    /**
     * 标题行索引
     *
     * @return 标题行索引
     * @author shiping.song
     * @date 2023/1/18 10:53
     */
    default int rowId() {
        return 0;
    }

    /**
     * 标题行有效数据最大列索引
     *
     * @return 标题行有效数据最大列索引
     * @author shiping.song
     * @date 2023/1/18 11:11
     */
    default int colId() {
        return 0;
    }

    /**
     * 当前操作是否为输出错误清单，默认为true
     *
     * @return 配置结果
     * @author shiping.song
     * @date 2023/1/18 11:03
     */
    default boolean isWriteErrorList() {
        return true;
    }

    /**
     * 异常列名称，当isWriteErrorList方法值为true时，此配置生效
     *
     * @return 默认为错误原因
     * @author shiping.song
     * @date 2023/1/18 11:07
     */
    default String errorColumnName() {
        return "错误原因";
    }

    /**
     * 输出的文件名称
     *
     * @return 文件名称
     * @author shiping.song
     * @date 2023/1/18 11:05
     */
    default String fileName() {
        return "错误信息";
    }

    /**
     * 是否启用流程化写表，如果为true，则与rowId、write、
     *
     * @return 配置结果
     * @author shiping.song
     * @date 2023/1/18 10:59
     */
    default boolean oriented() {
        return false;
    }

    /**
     * 设置单元格的值
     *
     * @param localRow   当前行
     * @param cellIndex  单元格索引
     * @param localValue 待设置的值
     * @author shiping.song
     * @date 2023/2/9 10:04
     */
    default void setCellValue(Row localRow, int cellIndex, int localValue) {
        SmeAssert.notNull(localRow, "写入行为空");
        mergeCellAsBlank(localRow, cellIndex).setCellValue(localValue);
    }

    default void setCellValue(Row localRow, int cellIndex, String localValue) {
        mergeCellAsBlank(localRow, cellIndex).setCellValue(localValue);
    }

    default void setCellValue(Row localRow, int cellIndex, double localValue) {
        mergeCellAsBlank(localRow, cellIndex).setCellValue(localValue);
    }

    default void setCellValue(Row localRow, int cellIndex, Date localValue) {
        mergeCellAsBlank(localRow, cellIndex).setCellValue(localValue);
    }

    /**
     * 如果单元格为空则默认解析为""
     *
     * @param localRow  当前行
     * @param cellIndex 单元格索引
     * @author shiping.song
     * @date 2023/2/9 10:08
     */
    default Cell mergeCellAsBlank(Row localRow, int cellIndex) {
        return localRow.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
    }
}
