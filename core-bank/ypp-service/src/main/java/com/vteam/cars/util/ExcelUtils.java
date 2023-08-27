package com.vteam.cars.util;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.MathConstants;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.plugin.excel.exp.model.ExcelPicturePosition;
import com.vteam.cars.plugin.file.upload.handler.LocalFileHandler;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.StringSubstitutor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBooleanProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRPrElt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Excel工具类 .<br>
 *
 * @author andy.sher
 * @date 2018/7/13 14:22
 */
@Slf4j
public final class ExcelUtils {

    private ExcelUtils() {
    }

    public static final String DATA_TYPE_STRING = "STRING";

    /**
     * 清除所有边框
     */
    public static final String CLEAR_CELL_BORDER_ALL = "0";

    /**
     * 保留下边框
     */
    public static final String KEEP_CELL_BORDER_BOTTOM = "1";

    /**
     * 保留左边框
     */
    public static final String KEEP_CELL_BORDER_LEFT = "2";

    /**
     * 保留右边框
     */
    public static final String KEEP_CELL_BORDER_RIGHT = "3";

    /**
     * 保留上边框
     */
    public static final String KEEP_CELL_BORDER_TOP = "4";

    /**
     * 清除下边框
     */
    public static final String CLEAR_CELL_BORDER_BOTTOM = "5";

    /**
     * 清除左边框
     */
    public static final String CLEAR_CELL_BORDER_LEFT = "6";

    /**
     * 清除右边框
     */
    public static final String CLEAR_CELL_BORDER_RIGHT = "7";

    /**
     * 清除上边框
     */
    public static final String CLEAR_CELL_BORDER_TOP = "8";

    /**
     * 合并单元格集合
     */
    private static ThreadLocal<List<CellRangeAddress>> cellRangeListLocal = new ThreadLocal<>();

    /**
     * 是否是2003的excel .<br>
     *
     * @param filePath 文件路径
     * @return boolean 返回true是2003
     * @author andy.sher
     * @date 2018/7/13 14:23
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 是否是2007的excel .<br>
     *
     * @param filePath 文件路径
     * @return boolean 返回true是2007
     * @author andy.sher
     * @date 2018/7/13 14:23
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 移动对应的行并复制模板行样式
     *
     * @param sheet
     * @param rowId
     * @param startRow
     * @param endRow
     * @param shiftNum
     * @author oscar.yu
     * @date 2019/10/11 17:21
     */
    public static void shiftRowsByTemplateRow(Sheet sheet, final int rowId, final int startRow, final int endRow,
                                              final int shiftNum) {
        // 获取合并单元格数据
        initCellRangeList(sheet);
        // 首先移动行
        sheet.shiftRows(startRow, endRow, shiftNum, true, false);
        // 复制模板行样式
        final Row rowSource = sheet.getRow(rowId);
        // 获取当前行样式
        final CellStyle rowStyle = rowSource.getRowStyle();
        for (int i = 0; i < shiftNum; i++) {
            int insertRowIndex = i + startRow;
            Row rowInsert = sheet.createRow(insertRowIndex);
            if (rowStyle != null) {
                rowInsert.setRowStyle(rowStyle);
            }
            rowInsert.setHeight(rowSource.getHeight());
            for (int colIndex = 0; colIndex < rowSource.getLastCellNum(); colIndex++) {
                Cell cellSource = rowSource.getCell(colIndex);
                // 兼容起始列不为第一列的情况
                if (null != cellSource) {
                    Cell cellInsert = rowInsert.createCell(colIndex);
                    CellStyle cellStyle = cellSource.getCellStyle();
                    // 设置单元格样式
                    if (null != cellStyle) {
                        cellInsert.setCellStyle(cellStyle);
                    }
                }
            }
            // 复制合并的单元格信息
            copyMergedRegion(sheet, rowSource, insertRowIndex);
        }
    }

    /**
     * 移动对应的行并复制模板行样式+value
     *
     * @param sheet
     * @param rowId
     * @param startRow
     * @param endRow
     * @param shiftNum
     * @author oscar.yu
     * @date 2019/10/11 17:21
     */
    public static void shiftRowsByTemplateRowWithValue(Sheet sheet, final int rowId, final int startRow, final int endRow,
                                                       final int shiftNum) {
        // 获取合并单元格数据
        initCellRangeList(sheet);
        // 首先移动行
        sheet.shiftRows(startRow, endRow, shiftNum, true, false);
        // 复制模板行样式
        final Row rowSource = sheet.getRow(rowId);
        // 获取当前行样式
        final CellStyle rowStyle = rowSource.getRowStyle();
        for (int i = 0; i < shiftNum; i++) {
            int insertRowIndex = i + startRow;
            Row rowInsert = sheet.createRow(insertRowIndex);
            if (rowStyle != null) {
                rowInsert.setRowStyle(rowStyle);
            }
            rowInsert.setHeight(rowSource.getHeight());
            for (int colIndex = 0; colIndex < rowSource.getLastCellNum(); colIndex++) {
                Cell cellSource = rowSource.getCell(colIndex);
                // 兼容起始列不为第一列的情况
                if (null != cellSource) {
                    Cell cellInsert = rowInsert.createCell(colIndex);
                    CellStyle cellStyle = cellSource.getCellStyle();
                    // 设置单元格样式
                    if (null != cellStyle) {
                        cellInsert.setCellStyle(cellStyle);
                    }
                    // 设置单元格内容
                    cellSource.getStringCellValue();
                    String value = null;
                    if (CellType.NUMERIC.equals(cellSource.getCellType())) {
                        value = String.valueOf(cellSource.getNumericCellValue()).trim();
                    } else {
                        value = cellSource.getStringCellValue().trim();
                    }
                    if (StringUtils.isNotBlank(value)) {
                        cellInsert.setCellValue(value);
                    }
                }
            }
            // 复制合并的单元格信息
            copyMergedRegion(sheet, rowSource, insertRowIndex);
        }
    }

    /**
     * 根据单元格获取单元格内容
     *
     * @param cell Excel单元格
     * @return
     */
    public static String getCellValue(Cell cell) {
        return getCellValue(cell, null);
    }

    /**
     * 根据单元格获取单元格内容
     *
     * @param cell     Excel单元格
     * @param dataType 系统数据实际类型
     * @return
     */
    public static String getCellValue(Cell cell, String dataType) {
        String cellvalue = "";
        DecimalFormat decimalFormat = new DecimalFormat("0.00"); // 格式化数字
        CellType cellType = cell.getCellType();
        if (cellType == CellType.STRING) {
            cellvalue = cell.getStringCellValue().trim();
        } else {
            String dataFormat = cell.getCellStyle().getDataFormatString();
            if (cellType == CellType.NUMERIC) {
                cellvalue = getCellValueForCellTypeNumeric(cell);
            } else if (cellType == CellType.FORMULA) {
                if (dataFormat.contains("#,##0.00") || dataFormat.contains("0.00")) { // 增加对不带分隔符数字的解析
                    cellvalue = decimalFormat.format(cell.getNumericCellValue());
                } else {
                    try {
                        cellvalue = cell.getStringCellValue().trim();
                        // 防止单元格内容不是数字
                        if (NumberUtils.isCreatable(cellvalue)) {
                            cellvalue = doubleToString(cell.getNumericCellValue(), dataFormat);
                        }
                    } catch (IllegalStateException e) {
                        getCellValueForCellTypeNumeric(cell);
                    }
                }
            } else if (cellType == CellType.BOOLEAN) {
                cellvalue = String.valueOf(cell.getBooleanCellValue());
            } else if (cellType == CellType.BLANK) {
                cellvalue = StringUtils.EMPTY;
            } else {
                cellvalue = cell.toString();
                if (cellvalue.indexOf(".0") > 0) {
                    cellvalue = cellvalue.substring(0, cellvalue.indexOf(".0"));
                }
            }
        }
        return StringUtils.isNotEmpty(cellvalue) ? cellvalue.trim() : cellvalue;
    }

    /**
     * 根据单元格及类型CellTypeNumeric获取单元格内容
     *
     * @param cell
     * @return
     */
    private static String getCellValueForCellTypeNumeric(Cell cell) {
        String cellvalue = "";
        DecimalFormat numberFormat = new DecimalFormat("0"); // 格式化number String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 格式化日期字符串
        DecimalFormat decimalFormat = new DecimalFormat("0.00"); // 格式化数字
        String dataFormat = cell.getCellStyle().getDataFormatString();
        if ("@".equals(dataFormat)) {
            cellvalue = numberFormat.format(cell.getNumericCellValue());
        } else if ("General".equals(dataFormat)) {
            cellvalue = doubleToString(cell.getNumericCellValue(), dataFormat.trim());
        } else if ("m/d/yy".equals(dataFormat) || "####\\-##\\-##".equals(dataFormat)) {
            if (String.valueOf(cell.getNumericCellValue()).indexOf("E") > 2) {
                String str = String.valueOf(cell.getNumericCellValue());
                String result = str.substring(0, 1) + str.substring(2, str.indexOf("E"));
                cellvalue = DateUtils.parseStringToDate(result).toString();
            } else {
                cellvalue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
            }
        } else if (dataFormat.contains("#,##0.00") || dataFormat.contains("0.00")) {
            cellvalue = decimalFormat.format(cell.getNumericCellValue());
        } else if ("00000000".equals(dataFormat)) {
            cellvalue = doubleToString(cell.getNumericCellValue(), dataFormat);
        } else {
            cellvalue = cell.toString();
            if (cellvalue.toUpperCase().indexOf("E") > 0) {

            } else {
                if (cellvalue.indexOf(".0") > 0) {
                    cellvalue = cellvalue.substring(0, cellvalue.indexOf(".0"));
                }
            }
        }
        return cellvalue;
    }

    /**
     * 复制合并的单元格信息
     *
     * @param sheet
     * @param sourceRow
     * @param curRowIndex
     * @author oscar.yu
     * @date 2022/2/9 17:07
     */
    public static void copyMergedRegion(Sheet sheet, Row sourceRow, int curRowIndex) {
        int lastCellNum = sourceRow.getLastCellNum(); // 获得单个工作表的最大单元格的索引
        if (lastCellNum > 0) {
            for (int colNum = 0; colNum < lastCellNum; colNum++) {
                // 获取单元格是否在合并单元格中，若存在则返回合并对象
                CellRangeAddress targetAddress = getCellRangeAddressByInitRow(sheet, sourceRow, curRowIndex, colNum);
                if (null != targetAddress) {
                    sheet.addMergedRegion(targetAddress);
                    // 跳过已经存在的合并单元格
                    colNum = targetAddress.getLastColumn();
                }
            }
        }
    }

    /**
     * 初始化合并单元格集合
     *
     * @param sheet
     * @author oscar.yu
     * @date 2022/2/9 17:08
     */
    private static void initCellRangeList(Sheet sheet) {
        // 首先清空线程变量
        cellRangeListLocal.set(null);
        System.out.println(cellRangeListLocal.get());
        List<CellRangeAddress> cellRangeList = new ArrayList<CellRangeAddress>();
        int sheetMergerCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            // 获得合并单元格加入list中
            CellRangeAddress cra = sheet.getMergedRegion(i);
            cellRangeList.add(cra);
        }
        cellRangeListLocal.set(cellRangeList);
    }

    /**
     * 获取单元格是否在合并单元格中(以模板行为参考)，若存在则返回新的合并对象
     *
     * @param sheet
     * @param sourceRow
     * @param curRowIndex
     * @param curColIndex
     * @return CellRangeAddress
     */
    private static CellRangeAddress getCellRangeAddressByInitRow(Sheet sheet, Row sourceRow, int curRowIndex, int curColIndex) {
        CellRangeAddress targetAddress = null;
        int lastCellNum = sourceRow.getLastCellNum(); // 获得单个工作表的最大单元格的索引
        if (lastCellNum > 0) {
            Cell sourceCell = sourceRow.getCell(curColIndex);
            CellRangeAddress cellRangeAddress = getCellRangeAddress(sourceCell);
            if (null != cellRangeAddress) {
                int firstCol = cellRangeAddress.getFirstColumn();
                int lastCol = cellRangeAddress.getLastColumn();
                targetAddress = new CellRangeAddress(curRowIndex, curRowIndex, firstCol, lastCol);
            }
        }
        return targetAddress;
    }

    /**
     * 获取单元格是否在合并单元格中
     *
     * @param cell
     * @return CellRangeAddress
     */
    private static CellRangeAddress getCellRangeAddress(Cell cell) {
        List<CellRangeAddress> cellRangeList = cellRangeListLocal.get();
        CellRangeAddress cellRangeAddress = null;
        int firstCol = 0, lastCol = 0, firstRow = 0, lastRol = 0;
        if (CollectionUtils.isNotEmpty(cellRangeList) && cell != null) {
            for (CellRangeAddress cra : cellRangeList) {
                // 获得合并单元格的起始行, 结束行, 起始列, 结束列
                firstCol = cra.getFirstColumn();
                lastCol = cra.getLastColumn();
                firstRow = cra.getFirstRow();
                lastRol = cra.getLastRow();
                if (cell.getColumnIndex() <= lastCol && cell.getColumnIndex() >= firstCol) {
                    if (cell.getRowIndex() <= lastRol && cell.getRowIndex() >= firstRow) {
                        cellRangeAddress = cra;
                        break;
                    }
                }
            }
        }
        return cellRangeAddress;
    }

    /**
     * 获取LocalDateTime格式数据(为空则返回null)
     *
     * @param cell
     * @return
     */
    public static LocalDateTime getLocalDateTime(Cell cell) {
        if (null == cell || null == cell.getDateCellValue()) {
            return null;
        }
        Instant instant = cell.getDateCellValue().toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 获取BigDecimal格式数据(为空则返回null)
     *
     * @param cell
     * @return
     */
    public static BigDecimal getBigDecimal(Cell cell) {
        if (null == cell || StringUtils.isBlank(getCellValue(cell))) {
            return null;
        }
        return new BigDecimal(getCellValue(cell));
    }

    /**
     * Double转换为String
     *
     * @param doubleValue
     * @param cellStyle
     * @return
     */
    private static String doubleToString(double doubleValue, String cellStyle) {
        String str = BigDecimal.valueOf(doubleValue).toString();
        String result = "";
        if (str.indexOf(".0") > 0 && str.indexOf("E") < 0) {
            result = str.substring(0, str.indexOf(".0"));
            if ("00000000".equals(cellStyle)) {
                if (result.length() < cellStyle.length()) {
                    result = StringUtils.fillStr(result, cellStyle.toString().length(), "0", false);
                }
            }
        } else {
            result = str;
        }
        return result;
    }

    /**
     * 清除单元格内容及边框样式
     *
     * @param sheet       excel sheet
     * @param cell        excel 单元格（可兼容合并单元格，传第一个单元格）
     * @param borderStyle 清除边框样式（默认清除所有边框，不需要清除边框则传null）
     *                    CLEAR_CELL_BORDER_ALL 清除所有边框
     *                    CLEAR_CELL_BORDER_BOTTOM 清除下边框
     *                    CLEAR_CELL_BORDER_LEFT 清除左边框
     *                    CLEAR_CELL_BORDER_RIGHT 清除右边框
     *                    CLEAR_CELL_BORDER_TOP 清除上边框
     *                    KEEP_CELL_BORDER_BOTTOM 保留下边框
     *                    KEEP_CELL_BORDER_LEFT 保留左边框
     *                    KEEP_CELL_BORDER_RIGHT 保留右边框
     *                    KEEP_CELL_BORDER_TOP 保留上边框
     * @author olivia.liu
     * @date 2022/8/5 16:21
     */
    public static void clearCellAndBorder(Sheet sheet, Cell cell, String borderStyle) {
        if (StringUtils.isNotBlank(borderStyle)) {
            // 获取合并单元格数据
            initCellRangeList(sheet);
            CellRangeAddress cellRangeAddress = getCellRangeAddress(cell);
            if (null != cellRangeAddress) {
                // 合并单元格
                if (KEEP_CELL_BORDER_BOTTOM.equals(borderStyle)) {
                    RegionUtil.setBorderTop(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderLeft(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderRight(BorderStyle.NONE, cellRangeAddress, sheet);
                } else if (KEEP_CELL_BORDER_LEFT.equals(borderStyle)) {
                    RegionUtil.setBorderTop(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderRight(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderBottom(BorderStyle.NONE, cellRangeAddress, sheet);
                } else if (KEEP_CELL_BORDER_RIGHT.equals(borderStyle)) {
                    RegionUtil.setBorderTop(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderLeft(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderBottom(BorderStyle.NONE, cellRangeAddress, sheet);
                } else if (KEEP_CELL_BORDER_TOP.equals(borderStyle)) {
                    RegionUtil.setBorderRight(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderLeft(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderBottom(BorderStyle.NONE, cellRangeAddress, sheet);
                } else if (CLEAR_CELL_BORDER_BOTTOM.equals(borderStyle)) {
                    RegionUtil.setBorderBottom(BorderStyle.NONE, cellRangeAddress, sheet);
                } else if (CLEAR_CELL_BORDER_LEFT.equals(borderStyle)) {
                    RegionUtil.setBorderLeft(BorderStyle.NONE, cellRangeAddress, sheet);
                } else if (CLEAR_CELL_BORDER_RIGHT.equals(borderStyle)) {
                    RegionUtil.setBorderRight(BorderStyle.NONE, cellRangeAddress, sheet);
                } else if (CLEAR_CELL_BORDER_TOP.equals(borderStyle)) {
                    RegionUtil.setBorderTop(BorderStyle.NONE, cellRangeAddress, sheet);
                } else if (CLEAR_CELL_BORDER_ALL.equals(borderStyle)) {
                    RegionUtil.setBorderTop(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderLeft(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderRight(BorderStyle.NONE, cellRangeAddress, sheet);
                    RegionUtil.setBorderBottom(BorderStyle.NONE, cellRangeAddress, sheet);
                }
            } else {
                // 非合并单元格
                CellStyle cellStyle = cell.getCellStyle();
                if (KEEP_CELL_BORDER_BOTTOM.equals(borderStyle)) {
                    cellStyle.setBorderTop(BorderStyle.NONE);
                    cellStyle.setBorderLeft(BorderStyle.NONE);
                    cellStyle.setBorderRight(BorderStyle.NONE);
                } else if (KEEP_CELL_BORDER_LEFT.equals(borderStyle)) {
                    cellStyle.setBorderTop(BorderStyle.NONE);
                    cellStyle.setBorderRight(BorderStyle.NONE);
                    cellStyle.setBorderBottom(BorderStyle.NONE);
                } else if (KEEP_CELL_BORDER_RIGHT.equals(borderStyle)) {
                    cellStyle.setBorderTop(BorderStyle.NONE);
                    cellStyle.setBorderLeft(BorderStyle.NONE);
                    cellStyle.setBorderBottom(BorderStyle.NONE);
                } else if (KEEP_CELL_BORDER_TOP.equals(borderStyle)) {
                    cellStyle.setBorderRight(BorderStyle.NONE);
                    cellStyle.setBorderLeft(BorderStyle.NONE);
                    cellStyle.setBorderBottom(BorderStyle.NONE);
                } else if (CLEAR_CELL_BORDER_BOTTOM.equals(borderStyle)) {
                    cellStyle.setBorderBottom(BorderStyle.NONE);
                } else if (CLEAR_CELL_BORDER_LEFT.equals(borderStyle)) {
                    cellStyle.setBorderLeft(BorderStyle.NONE);
                } else if (CLEAR_CELL_BORDER_RIGHT.equals(borderStyle)) {
                    cellStyle.setBorderRight(BorderStyle.NONE);
                } else if (CLEAR_CELL_BORDER_TOP.equals(borderStyle)) {
                    cellStyle.setBorderTop(BorderStyle.NONE);
                } else if (CLEAR_CELL_BORDER_ALL.equals(borderStyle)) {
                    cellStyle.setBorderTop(BorderStyle.NONE);
                    cellStyle.setBorderLeft(BorderStyle.NONE);
                    cellStyle.setBorderRight(BorderStyle.NONE);
                    cellStyle.setBorderBottom(BorderStyle.NONE);
                }
                cell.setCellStyle(cellStyle);
            }
        }
        // 清空单元格内容
        cell.setCellValue(StringUtils.EMPTY);
    }

    /**
     * 合并单元格,合并后的内容取决于合并区域的左上角单元格的值
     *
     * @param sheet     excel sheet
     * @param firstRow  起始行
     * @param lastRow   截止行
     * @param firstCell 起始列
     * @param lastCell  截止列
     * @author olivia.liu
     * @date 2022/8/5 16:21
     */
    public static void addMergedRegion(Sheet sheet, int firstRow, int lastRow, int firstCell, int lastCell) {
        // 合并单元格,合并后的内容取决于合并区域的左上角单元格的值
        CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCell, lastCell);
        sheet.addMergedRegion(region);
    }

    /**
     * 设置sheet中行高自适应（行高=文本所在合并单元格中最高的行高*文字行数/合并单元格行数）
     *
     * @param sheet         excel sheet
     * @param filePath      文件路径
     * @param noneRowIdList 不设置行高自适应行号集合
     * @author olivia.liu
     * @date 2022/8/29 16:21
     */
    public static void setSheetRowHeightAuto(Sheet sheet, String filePath, List<Integer> noneRowIdList) {
        for (Row row : sheet) {
            // 忽略不需要设置自适应的行
            if (!noneRowIdList.contains(row.getRowNum())) {
                String maxContent = StringUtils.EMPTY;
                Cell maxCell = null;
                for (Cell cell : row) {
                    String content = StringUtils.EMPTY;
                    if (cell.getCellType().equals(CellType.NUMERIC)) {
                        content = String.valueOf(cell.getNumericCellValue()).trim();
                    } else {
                        content = cell.getStringCellValue().trim();
                    }
                    if (StringUtils.isNotBlank(content)) {
                        String[] contentArray = content.split(GlobalConstants.Symbol.LINE_BREAK);
                        String[] maxContentArray = maxContent.split(GlobalConstants.Symbol.LINE_BREAK);
                        // 取一行中文本最长或行数最多的内容，计算行高
                        if (content.length() > maxContent.length() || contentArray.length > maxContentArray.length) {
                            maxContent = content;
                            maxCell = cell;
                        }
                    }
                }
                if (null != maxCell) {
                    setRowHeightAuto(sheet, row, maxCell, maxContent, filePath);
                }
            }
        }
    }

    /**
     * 行高自适应： 实际行高 = max(((文本内容所占行数*模板行高)/行数), 模板行高)
     *
     * @param sheet    excel sheet
     * @param row      行
     * @param cell     列
     * @param content  文本内容
     * @param filePath 文件路径
     * @author olivia.liu
     * @date 2022/8/29 16:21
     */
    public static void setRowHeightAuto(Sheet sheet, Row row, Cell cell, String content, String filePath) {
        int firstRow = cell.getRowIndex();
        int lastRow = cell.getRowIndex();
        int firstCell = cell.getColumnIndex();
        int lastCell = cell.getColumnIndex();
        // 获取字体高度
        int fontHeight = com.vteam.cars.util.NumberUtils.SHORT_ZERO;
        if (isExcel2007(filePath)) {
            fontHeight = ((XSSFCell) cell).getCellStyle().getFont().getFontHeightInPoints();
        } else if (isExcel2003(filePath)) {
            //计算字体的高度
            HSSFCellStyle cellStyle = (HSSFCellStyle) cell.getCellStyle();
            HSSFFont font = cellStyle.getFont(row.getSheet().getWorkbook());
            //字体的高度
            fontHeight = font.getFontHeightInPoints();
        }
        // 获取合并单元格数据
        initCellRangeList(sheet);
        // 内容所需总行数
        int valueRows = NumberUtils.INTEGER_ZERO;
        // 模板实际行数（含合并单元格）
        int rowCout = NumberUtils.INTEGER_ONE;
        // 模板合并单元格行数
        int rangeRowCout = NumberUtils.INTEGER_ZERO;
        // 每行原始高度
        short oldHeight = row.getHeight();
        // 获取单元格宽度（含合并单元格）
        int width = NumberUtils.INTEGER_ZERO;
        // 计算基准值1
        int one = NumberUtils.INTEGER_ONE;
        // 计算基准值2
        int two = NumberUtils.INTEGER_TWO;
        // 获取单元格高度
        BigDecimal height = new BigDecimal(row.getHeight()).divide(new BigDecimal(GlobalConstants.Excel.TO_POINTS));
        // 如果该单元格被合并了
        CellRangeAddress cellRangeAddress = getCellRangeAddress(cell);
        if (null != cellRangeAddress) {
            firstRow = cellRangeAddress.getFirstRow();
            lastRow = cellRangeAddress.getLastRow();
            firstCell = cellRangeAddress.getFirstColumn();
            lastCell = cellRangeAddress.getLastColumn();
            for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++) {
                // 如果是合并单元格，取其中行高最大的一行
                int rowHeight = sheet.getRow(rowIndex).getHeight();
                BigDecimal currentHeight = new BigDecimal(rowHeight).divide(new BigDecimal(GlobalConstants.Excel.TO_POINTS));
                if (currentHeight.compareTo(height) > 0) {
                    height = currentHeight;
                    oldHeight = sheet.getRow(rowIndex).getHeight();
                }
                rangeRowCout++;
            }
            // 获取合并单元格宽度
            for (int cellIndex = firstCell; cellIndex <= lastCell; cellIndex++) {
                width = width
                        + (sheet.getColumnWidth(cellIndex) / GlobalConstants.Excel.TO_POINTS)
                        + one;
            }
        } else {
            width = (row.getSheet().getColumnWidth(cell.getColumnIndex()) / GlobalConstants.Excel.TO_POINTS) + one;
        }
        // 计算每行可放多少字符基准字体
        int basicfont = GlobalConstants.Excel.FONT_SIZE_TEN;
        // 根据换行符分隔文本内容
        String[] strArray = content.split(GlobalConstants.Symbol.LINE_BREAK);
        for (String lineStr : strArray) {
            int valLength = NumberUtils.INTEGER_ZERO;
            char[] strs = lineStr.toCharArray();
            // 如果是汉字则为 两个字符
            for (char e : strs) {
                if (String.valueOf(e).matches(GlobalConstants.Excel.FONT_CN)) {
                    valLength = valLength + two;
                } else {
                    valLength = valLength + one;
                }
            }
            valLength = valLength * fontHeight / basicfont;
            // 计算文本内容需占行数
            int textLength = width * height.setScale(NumberUtils.INTEGER_ZERO, BigDecimal.ROUND_DOWN).intValue();
            if (textLength < valLength) {
                valueRows += (((valLength - one) / width) + one);
            } else {
                valueRows++;
            }
        }

        // 每行最终高度
        short finalHeight = oldHeight;
        // 判断文本实际需填充行数与模板行数
        if (rangeRowCout > rowCout) {
            rowCout = rangeRowCout;
        }
        if (valueRows > rowCout) {
            finalHeight = new BigDecimal(finalHeight * valueRows / rowCout).shortValue();
        }
        // 若填充行高>模板行高，调整高度
        if (finalHeight > oldHeight) {
            for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++) {
                Row currentRow = sheet.getRow(rowIndex);
                currentRow.setHeight(finalHeight);
            }
        }
    }

    /**
     * 设置值和样式，富文本 复合样式（一个单元格部分文字加粗）
     *
     * @param wb           工作簿
     * @param cell         当前单元格
     * @param wholeStr     整个字符串
     * @param splitStr     分隔符
     * @param boldFontList 加粗字符串集合
     * @param filePath     文件
     * @author olivia.liu
     * @date 2022/08/12 15:23
     */
    public static void setRichTextCellValueForBold(Workbook wb, Cell cell, String wholeStr, String splitStr, List<String> boldFontList, String filePath) {
        // 根据换行符切分字体样式
        if (StringUtils.isBlank(splitStr)) {
            splitStr = GlobalConstants.Symbol.LINE_BREAK;
        }
        String[] strArray = wholeStr.split(splitStr);
        if (isExcel2003(filePath)) {
            HSSFCellStyle cellStyle = (HSSFCellStyle) cell.getCellStyle();
            HSSFFont hfont = cellStyle.getFont(wb);
            hfont.setBold(true);
            HSSFRichTextString hssfRichTextString = new HSSFRichTextString(wholeStr);
            int startIndex = NumberUtils.INTEGER_ZERO;
            int endIndex = NumberUtils.INTEGER_ZERO;
            for (int i = 0; i < strArray.length; i++) {
                endIndex = startIndex + strArray[i].length();
                if (boldFontList.contains(strArray[i])) {
                    hfont.setBold(true);
                    hssfRichTextString.applyFont(startIndex, endIndex, hfont);
                } else {
                    hfont.setBold(false);
                    hssfRichTextString.applyFont(startIndex, endIndex, hfont);
                }
                // 换行符占位+1
                startIndex += strArray[i].length() + 1;
            }
            cell.setCellValue(hssfRichTextString);
        } else if (isExcel2007(filePath)) {
            // 20221114 olivia.liu mod 解决部分字体加粗后样式失效问题&wps加粗效果不显示问题
            // 1、获取单元格字体原始样式
            XSSFCellStyle cellStyle = (XSSFCellStyle) cell.getCellStyle();
            XSSFFont hfont = cellStyle.getFont();
            String fontname = hfont.getFontName();
            XSSFRichTextString xssfRichTextString = new XSSFRichTextString(wholeStr);
            int startIndex = NumberUtils.INTEGER_ZERO;
            int endIndex = NumberUtils.INTEGER_ZERO;
            for (int i = 0; i < strArray.length; i++) {
                // modify-20221020-shaoquan.wu 解决修改一个单元格样式会修改其他相同样式单元格的样式的问题
                XSSFFont font = (XSSFFont) wb.createFont();
                // 2、将原始样式赋值到新样式中
                font.setFontHeight(hfont.getFontHeight()); //add font size
                font.setFontName(fontname);
                endIndex = startIndex + strArray[i].length();
                if (boldFontList.contains(strArray[i])) {
                    //设置加粗属性
                    font.setBold(true);
                    xssfRichTextString.applyFont(startIndex, endIndex, font);
                    // 3、调整兼容wps加粗效果不显示问题
                    initCompatibilityForWpsBold(cellStyle, xssfRichTextString);
                } else {
                    //设置加粗属性
                    font.setBold(false);
                    xssfRichTextString.applyFont(startIndex, endIndex, font);
                }
                // 换行符占位+1
                startIndex += strArray[i].length() + 1;
            }
            cell.setCellValue(xssfRichTextString);
        }
    }

    /**
     * 画图的顶级管理器
     *
     * @param workbook
     * @param byteArrayOutput
     * @param picturePosition short colStart 第一个单元格的列【从0开始,默认 0】
     *                        int rowStart 第一个单元格的行【从0开始,默认 0】
     *                        short colEnd 最后一个单元格的列【从0开始,默认2(注：xls包含,xlsx不包含)】
     *                        int rowEnd 最后一个单元格的行【从0开始,默认2(注：xls包含,xlsx不包含)】
     *                        int dxStart 第一个单元格内的x坐标【xls： 0 ~ 1023,默认 0】【xlsx：0~10*列宽,默认 0】
     *                        int dyStart 第一个单元格内的y坐标【xls： 0 ~ 255,默认 0】【xlsx：0~行高/0.75,默认 0】
     *                        int dxEnd 最后一个单元格中的x坐标【xls： 0 ~ 1023,默认 1023】【xlsx： 0~10*列宽,默认 0】
     *                        int dyEnd 最后一个单元格中的y坐标【xls： 0 ~ 255,默认 255】【xlsx：0~行高/0.75,默认 0】
     * @param filePath        文件路径
     * @author olivia.liu
     * @date 2022/08/03 15:12
     */
    public static void addPictureToExcel(Workbook workbook, ByteArrayOutputStream byteArrayOutput, ExcelPicturePosition picturePosition, String filePath) {
        // 设置图片默认坐标
        // 第一个单元格的列（从0开始）
        final short colStart = NumberUtils.SHORT_ZERO;
        // 第一个单元格的行（从0开始）
        final int rowStart = NumberUtils.INTEGER_ZERO;
        // 最后一个单元格的列（从0开始）(注：xls包含,xlsx不包含)
        final short colEnd = Short.valueOf(NumberUtils.INTEGER_TWO.toString());
        // 最后一个单元格的行（从0开始）(注：xls包含,xlsx不包含)
        final int rowEnd = NumberUtils.INTEGER_TWO;
        // 第一个单元格内的x坐标【xls：0 ~ 1023,默认 0】【xlsx：0~10*列宽,默认 0】
        final int dxStart = NumberUtils.INTEGER_ZERO;
        // 第一个单元格内的y坐标【xls：0 ~ 255,默认 0】【xlsx：0~行高/0.75,默认 0】
        final int dyStart = NumberUtils.INTEGER_ZERO;
        // 最后一个单元格中的x坐标【xls：0 ~ 1023,默认 1023】【xlsx：0~10*列宽,默认 0】
        final int dxEnd = isExcel2007(filePath) ? NumberUtils.INTEGER_ZERO : GlobalConstants.Excel.MAX_COORD_X_SIZE;
        // 最后一个单元格中的y坐标【xls：0 ~ 255,默认 255】【xlsx：0~行高/0.75,默认 0】
        final int dyEnd = isExcel2007(filePath) ? NumberUtils.INTEGER_ZERO : GlobalConstants.Excel.MAX_COORD_Y_SIZE;

        // 设置图片插入位置
        ExcelPicturePosition defaultPicturePosition = new ExcelPicturePosition(dxStart, dyStart, dxEnd, dyEnd, colStart, rowStart, colEnd, rowEnd);
        BeanUtils.copyProperties(picturePosition, defaultPicturePosition);

        // 写入图片
        if (isExcel2003(filePath)) {
            addPictureToExcel2003(workbook, byteArrayOutput, defaultPicturePosition);
        } else if (isExcel2007(filePath)) {
            addPictureToExcel2007(workbook, byteArrayOutput, defaultPicturePosition);
        }
    }

    /**
     * 向EXCEL模板中动态添加图片.xlsx格式
     *
     * @param workbook              excel工作簿
     * @param byteArrayOutputStream 图片流
     * @param picturePosition       图片添加坐标
     *                              cell1 = CTMarker.Factory.newInstance();
     *                              cell1.setCol(col1);
     *                              cell1.setColOff(dx1);
     *                              cell1.setRow(row1);
     *                              cell1.setRowOff(dy1);
     *                              cell2 = CTMarker.Factory.newInstance();
     *                              cell2.setCol(col2);
     *                              cell2.setColOff(dx2);
     *                              cell2.setRow(row2);
     *                              cell2.setRowOff(dy2);
     * @author olivia.liu
     * @date 2022/08/03 15:12
     */
    private static void addPictureToExcel2007(Workbook workbook, ByteArrayOutputStream byteArrayOutputStream, ExcelPicturePosition picturePosition) {
        int sheetNumber = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetNumber; i++) {
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(i);
            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = new XSSFClientAnchor(Units.EMU_PER_PIXEL * picturePosition.getDxStart(), Units.EMU_PER_PIXEL * picturePosition.getDyStart(),
                    Units.EMU_PER_PIXEL * picturePosition.getDxEnd(), Units.EMU_PER_PIXEL * picturePosition.getDyEnd(), picturePosition.getColStart(),
                    picturePosition.getRowStart(), picturePosition.getColEnd(), picturePosition.getRowEnd());
            drawing.createPicture(anchor, workbook.addPicture(byteArrayOutputStream.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
        }
    }

    /**
     * 向EXCEL模板中动态添加图片.xls格式
     *
     * @param workbook              excel工作簿
     * @param byteArrayOutputStream 图片流
     * @param picturePosition       图片添加坐标
     *                              checkRange(dx1, 0, 1023, "dx1");
     *                              checkRange(dx2, 0, 1023, "dx2");
     *                              checkRange(dy1, 0, 255, "dy1");
     *                              checkRange(dy2, 0, 255, "dy2");
     *                              checkRange(col1, 0, MAX_COL, "col1");
     *                              checkRange(col2, 0, MAX_COL, "col2");
     *                              checkRange(row1, 0, MAX_ROW, "row1");
     *                              checkRange(row2, 0, MAX_ROW, "row2");
     *                              setCol1((short) Math.min(col1, col2));
     *                              setCol2((short) Math.max(col1, col2));
     *                              setRow1(Math.min(row1, row2));
     *                              setRow2(Math.max(row1, row2));
     * @author olivia.liu
     * @date 2022/08/03 15:12
     */
    private static void addPictureToExcel2003(Workbook workbook, ByteArrayOutputStream byteArrayOutputStream, ExcelPicturePosition picturePosition) {
        int sheetNumber = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetNumber; i++) {
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(i);
            HSSFPatriarch drawing = sheet.createDrawingPatriarch();
            HSSFClientAnchor anchor = new HSSFClientAnchor(picturePosition.getDxStart(), picturePosition.getDyStart(),
                    picturePosition.getDxEnd(), picturePosition.getDyEnd(), picturePosition.getColStart(),
                    picturePosition.getRowStart(), picturePosition.getColEnd(), picturePosition.getRowEnd());
            drawing.createPicture(anchor, workbook.addPicture(byteArrayOutputStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
        }
    }

    /**
     * wps加粗兼容性调整
     * 遍历sharedStrings.xml，获取<rPr>中加粗的属性将val="true"属性抹去
     * 例：<r><rPr><b val="true"/><sz val="11.0"/><rFont val="华文楷体"/></rPr><t>Remark</t></r>
     *
     * @param cellStyle          单元格样式
     * @param xssfRichTextString 富文本
     * @author olivia.liu
     * @date 2022/11/14 18:12
     */
    private static void initCompatibilityForWpsBold(XSSFCellStyle cellStyle, XSSFRichTextString xssfRichTextString) {
        CTRPrElt ctrElt = null;
        if (cellStyle.getFont() != null) {
            List<CTRElt> CTRElt = xssfRichTextString.getCTRst().getRList();
            if (CollectionUtils.isNotEmpty(CTRElt) && CTRElt.size() > 0) {
                for (int CTRindex = 0; CTRindex < CTRElt.size(); CTRindex++) {
                    ctrElt = CTRElt.get(CTRindex).getRPr();
                    if (ctrElt != null) {
                        List<CTBooleanProperty> CTBooleanPropertyList = ctrElt.getBList();
                        if (CollectionUtils.isNotEmpty(CTBooleanPropertyList) && CTBooleanPropertyList.size() > 0) {
                            for (int booleanIndex = 0; booleanIndex < CTBooleanPropertyList.size(); booleanIndex++) {
                                CTBooleanPropertyList.get(booleanIndex).unsetVal();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 以时间格式解析单元格中的值，当所解析的单元格不是时间格式的时候，将会默认以文本格式进行处理
     * 程序会尝试使用传入的formatters参数进行单元格值的转化
     *
     * @param formatters 格式化方案
     * @param cell       待解析单元格
     * @param required   是否为必校验字段
     * @return 解析结果
     * @author shiping.song
     * @date 2023/1/13 16:57
     */
    public static LocalDateTime getDateCellValue(Cell cell, boolean required, DateTimeFormatter... formatters) {
        return computeIfAbsent(cell, required, localCell -> {
            LocalDateTime resultLocalDateTime = null;
            LocalDateTime tempLocalDateTime = null;
            try {
                Date dateValue = cell.getDateCellValue();
                Instant instant = dateValue.toInstant();
                tempLocalDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
            } catch (Exception e) {
                log.info("尝试将以【时间】格式进行格式化，格式化失败！");
            }

            if (Objects.nonNull(tempLocalDateTime)) {
                // 日期转化正常时可能出现乱码的情况，例如：开票日期单元格的格式为yyyy/mm/dd，。但是用户输入的值为20221230
                // 这个时候转化时间会成功，但是转化的结果不是最终想要的，所以需要对转化的结果进行校验
                boolean checkFlag = false;
                String originalValue = StringUtils.EMPTY;
                try {
                    double originalNumericValue = cell.getNumericCellValue();
                    originalValue = new BigDecimal(originalNumericValue + "").toPlainString();
                    if (StringUtils.isNotBlank(originalValue)) {
                        checkFlag = true;
                    }
                } catch (Exception e) {
                    log.error("尝试以【numeric】格式进行格式化，格式化失败！");
                }
                if (checkFlag) {
                    // 超过8位则是非法数据不做处理
                    if (originalValue.length() > 8) {
                        throw new IllegalStateException();
                    }
                    try {
                        resultLocalDateTime = LocalDate.parse(originalValue, DateUtils.FMT_DATE_N).atStartOfDay();
                    } catch (Exception e) {
                        String oldDateStr = tempLocalDateTime.format(DateUtils.FMT_DATE_N);
                        boolean exist = RegularUtils.exist(oldDateStr, RegularUtils.DATE_FMT_N);
                        // 及时上游excel设置时间成功了，这里也需要判断其转化出的格式是否能够满足yyyymmdd
                        if (exist) {
                            resultLocalDateTime = tempLocalDateTime;
                        } else {
                            throw new IllegalStateException();
                        }
                    }
                }
            } else {
                // 如果用户调整了开票日期的格式，即单元格不是时间格式，则默认用户将格式调整为文本格式，然后直接进行格式化，其余所有情况均报错
                String stringDateValue;
                try {
                    stringDateValue = cell.getStringCellValue();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
                if (StringUtils.isNotBlank(stringDateValue)) {
                    boolean parseSuccessFlag = false;
                    if (ArrayUtils.isNotEmpty(formatters)) {
                        for (DateTimeFormatter formatter : formatters) {
                            if (!parseSuccessFlag) {
                                try {
                                    resultLocalDateTime = LocalDate.parse(stringDateValue, formatter).atStartOfDay();
                                    parseSuccessFlag = true;
                                } catch (Exception e) {
                                    log.error(ExcelUtils.class.getName(), e);
                                }
                            }
                        }
                    }

                    if (!parseSuccessFlag) {
                        // 如果没有解析成功则直接抛出异常
                        throw new IllegalStateException();
                    }
                }
            }
            return resultLocalDateTime;
        });
    }

    /**
     * 使用默认方式解析日期，支持:yyyy/mm/dd,yyyy-mm-dd,yyyymmdd，同时如果单元格的值为空时不作处理
     *
     * @param cell 待解析单元格
     * @author shiping.song
     * @date 2023/1/13 17:58
     */
    public static LocalDateTime getDateCellValue(Cell cell) {
        return getDateCellValue(cell, false, DateUtils.FMT_SLASH, DateUtils.FMT_DATE, DateUtils.FMT_DATE_N);
    }

    public static LocalDateTime getDateCellValue(Cell cell, AtomicInteger columnCount) {
        columnCount.incrementAndGet();
        return getDateCellValue(cell);
    }

    /**
     * 获取String类型的单元格值
     * 默认在处理时如果单元格不为空也不会抛错，直接返回空值
     *
     * @param cell 指定单元格
     * @author shiping.song
     * @date 2023/1/16 13:46
     */
    public static String getStringCellValue(Cell cell, boolean required) {
        return computeIfAbsent(cell, required, ExcelUtils::getCellValue);
    }

    public static String getStringCellValue(Cell cell) {
        return getStringCellValue(cell, false);
    }

    /**
     * 获取String类型的单元格值，同时获取成功时将全局列索引右移
     *
     * @param cell        指定单元格
     * @param columnCount 全局列索引
     * @author shiping.song
     * @date 2023/1/17 12:35
     */
    public static String getStringCellValue(Cell cell, AtomicInteger columnCount) {
        columnCount.incrementAndGet();
        return getStringCellValue(cell);
    }

    /**
     * 获取金额单元格的值
     *
     * @param cell 指定单元格
     * @return 结果值
     * @author shiping.song
     * @date 2023/1/16 14:23
     */
    public static BigDecimal getAmtValue(Cell cell, boolean required) {
        return computeIfAbsent(cell, required, localCell -> {
            double invoiceTotalAmt = localCell.getNumericCellValue();
            return BigDecimal.valueOf(invoiceTotalAmt).setScale(MathConstants.AMT_ROUNDING, RoundingMode.HALF_UP);
        });
    }

    public static BigDecimal getAmtValue(Cell cell) {
        return getAmtValue(cell, false);
    }

    /**
     * 获取利率单元格的值
     *
     * @param cell 指定单元格
     * @return 结果值
     */
    public static BigDecimal getRateValue(Cell cell, boolean required) {
        return computeIfAbsent(cell, required, localCell -> {
            double invoiceTotalAmt = localCell.getNumericCellValue();
            return BigDecimal.valueOf(invoiceTotalAmt).setScale(MathConstants.RATE_ROUNDING, RoundingMode.HALF_UP);
        });
    }

    public static BigDecimal getRateValue(Cell cell) {
        return getRateValue(cell, false);
    }

    public static BigDecimal getAmtValue(Cell cell, AtomicInteger columnCount) {
        columnCount.incrementAndGet();
        return getAmtValue(cell);
    }


    /**
     * 校验单元格是否为空，兼容CellType.STRING及CellType.NUMERIC
     *
     * @param cell 待校验单元格
     * @return 校验结果
     * @author shiping.song
     * @date 2023/1/16 13:17
     */
    public static boolean isNull(Cell cell) {
        if (null == cell) {
            return true;
        }
        boolean flag = true;
        CellType cellType = cell.getCellType();
        if (cellType == CellType.STRING) {
            flag = StringUtils.isBlank(cell.getStringCellValue().trim());
        } else {
            if (cellType == CellType.NUMERIC) {
                flag = cell.getNumericCellValue() <= 0;
            }
        }
        return flag;
    }

    /**
     * 计算required的值
     *
     * @param required 是否强校验
     * @return 为true时则抛错，为false时则返回空
     * @author shiping.song
     * @date 2023/1/16 13:53
     */
    private static <R> R computeRequired(boolean required) {
        if (required) {
            throw new IllegalStateException();
        } else {
            return null;
        }
    }

    /**
     * 如果localCell参数为空，则按照mergeFunction进行处理，如果不为空则按照mapperFunction计算最终值
     *
     * @param localCell      当前单元格
     * @param auxiliaryParam 空处理辅助参数
     * @param mapperFunction 单元格值计算
     * @param nullFunction   单元格空处理
     * @author shiping.song
     * @date 2023/1/16 14:07
     */
    public static <T extends Cell, R, V> R computeIfAbsent(T localCell, Function<T, R> mapperFunction, V auxiliaryParam, Function<V, R> nullFunction) {
        if (isNull(localCell)) {
            return nullFunction.apply(auxiliaryParam);
        } else {
            return mapperFunction.apply(localCell);
        }
    }

    /**
     * 如果localCell参数为空，则按照mergeFunction进行处理，如果不为空则按照mapperFunction计算最终值
     *
     * @param localCell
     * @param required       是否强校验
     * @param mapperFunction
     * @param <T>            入参泛型
     * @param <R>            响应参数泛型
     * @author shiping.song
     * @date 2023/1/16 14:29
     */
    public static <T extends Cell, R> R computeIfAbsent(T localCell, boolean required, Function<T, R> mapperFunction) {
        return computeIfAbsent(localCell, mapperFunction, required, ExcelUtils::computeRequired);
    }

    /**
     * 转化文本类型的值，如果cell是科学计数法形式则将它还原为文本格式
     *
     * @return 合并结果
     * @author shiping.song
     * @date 2023/1/16 15:29
     */
    public static String mergeStringValue(Cell cell, boolean required, Function<Cell, String> mergeFunction) {
        return computeIfAbsent(cell, required, mergeFunction);
    }

    /**
     * 转化文本类型的值，采用默认的合并处理
     *
     * @param cell
     * @param required
     * @return
     * @author shiping.song
     * @date 2023/1/16 15:34
     */
    public static String mergeStringValue(Cell cell, boolean required) {
        return computeIfAbsent(cell, required, localCell -> {
            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else {
                return new BigDecimal(cell.getNumericCellValue() + "").toPlainString();
            }
        });
    }

    public static String mergeStringValue(Cell cell) {
        return mergeStringValue(cell, false);
    }

    public static String mergeStringValue(Cell cell, AtomicInteger columnCount) {
        String result = mergeStringValue(cell);
        columnCount.incrementAndGet();
        return result;
    }


    /**
     * 抛出异常，默认信息格式为：${rowCount}行${colCount}列${errorMsg}
     *
     * @param illegalStateException 待抛出异常
     * @param rowCount              异常所对应的行
     * @param colCount              异常所对应的列
     * @param errorMsg              错误信息
     * @author shiping.song
     * @date 2023/1/16 14:52
     */
    public static void throwException(IllegalStateException illegalStateException, int rowCount, int colCount, String errorMsg) {
        Map<String, String> model = new HashMap<>(3);
        model.put("row", Integer.toString(rowCount));
        model.put("col", Integer.toString(colCount));
        model.put("errorMsg", StringUtils.isBlank(errorMsg) ? "数据格式错误" : errorMsg);
        String resultErrorMsgPattern = "${row}行${col}列${errorMsg}。";
        String resultErrorInfo = StringSubstitutor.replace(resultErrorMsgPattern, model);
        SmeAssert.isNull(illegalStateException, resultErrorInfo);
    }

    /**
     * 校验当前数据行是否为数据终止行
     *
     * @param row                 当前行
     * @param columnIndex         全局列索引
     * @param requiredColumnIndex 需要强校验的列索引
     * @return 校验结果
     * @author shiping.song
     * @date 2023/1/17 9:44
     */
    public static boolean isEndLine(Row row, AtomicInteger columnIndex, int... requiredColumnIndex) {
        if (Objects.isNull(row)) {
            return true;
        } else {
            AtomicBoolean isEndLine = new AtomicBoolean(true);
            for (int index : requiredColumnIndex) {
                // 设置错误行索引
                columnIndex.set(index + 1);
                isEndLine.set(isEndLine.get() && isNull(row.getCell(index)));
            }
            return isEndLine.get();
        }
    }

    /**
     * 设置时间单元格的值，默认会将时间格式化成yyyy/mm/dd这种
     *
     * @param cell              当前操作单元格
     * @param localDateTime     待填充值
     * @param consumer          填充值前置处理
     * @param dateTimeFormatter 格式化方式
     * @author shiping.song
     * @date 2023/1/18 14:25
     */
    public static void setDateCellValue(Cell cell, LocalDateTime localDateTime, Consumer<LocalDateTime> consumer, DateTimeFormatter dateTimeFormatter) {
        if (Objects.nonNull(consumer)) {
            consumer.accept(localDateTime);
        }
        if (Objects.nonNull(localDateTime)) {
            if (Objects.nonNull(dateTimeFormatter)) {
                cell.setCellValue(localDateTime.format(dateTimeFormatter));
            } else {
                cell.setCellValue(localDateTime.format(DateUtils.FMT_SLASH));
            }
        }
    }

    public static void setDateCellValue(Cell cell, LocalDateTime localDateTime, Consumer<LocalDateTime> consumer) {
        setDateCellValue(cell, localDateTime, consumer, null);
    }

    /**
     * 使用默认格式格式化时间
     *
     * @author shiping.song
     * @date 2023/1/29 17:08
     */
    public static void setDateCellValue(Cell cell, LocalDateTime localDateTime) {
        setDateCellValue(cell, localDateTime, DateUtils.FMT_SLASH);
    }

    /**
     * 使用指定格式格式化时间
     *
     * @param dateTimeFormatter 指定格式
     * @author shiping.song
     * @date 2023/1/29 17:08
     */
    public static void setDateCellValue(Cell cell, LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        setDateCellValue(cell, localDateTime, null, dateTimeFormatter);
    }

    /**
     * 设置bigDecimal格式的值
     *
     * @param cell     当前单元格
     * @param value    具体值
     * @param consumer 前置处理
     * @author shiping.song
     * @date 2023/1/18 14:52
     */
    public static void setBigDecimalValue(Cell cell, BigDecimal value, Consumer<BigDecimal> consumer) {
        if (null != consumer) {
            consumer.accept(value);
        }
        if (com.vteam.cars.util.NumberUtils.compute(value) != null) {
            cell.setCellValue(value.doubleValue());
        }
    }

    public static void setBigDecimalValue(Cell cell, BigDecimal value) {
        setBigDecimalValue(cell, value, null);
    }

    /**
     * 输出错误清单
     *
     * @param workbook 待处理工作目录
     * @param file     待处理文件
     * @param fileName 文件名称(无后缀)
     * @return 返回处理结果
     * @author shiping.song
     * @date 2023/1/18 10:29
     */
    public static FbtxApxM writeList(Workbook workbook, File file, String fileName) {
        LocalFileHandler localFileHandler = SpringContextUtils.getBean(LocalFileHandler.class);
        SmeAssert.notNull(localFileHandler, "文件处理器不存在");
        try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
            workbook.write(outputStream);
        } catch (IOException e) {
            log.error(ExcelUtils.class.getName(), e);
        }
        return localFileHandler.uploadApx(file, fileName);
    }

    /**
     * 设置模板行错误信息
     *
     * @param validateMap 辅助校验map
     * @param validateKey 辅助校验key
     * @param errorInfo   错误信息
     * @param success     已校验成功的数据
     * @param errorIndex 错误列索引，添加到错误列表的哪个位置
     * @author shiping.song
     * @date 2023/2/1 17:03
     */
    public static <T> void setTemplateErrorInfo(Map<String, Integer> validateMap, String validateKey, String errorInfo, List<T> success, Map<Integer, Integer> removeIndexToErrorIndex, int errorIndex) {
        if (MapUtils.isNotEmpty(validateMap)) {
            SmeAssert.notBlank(validateKey, "校验Key为空");
            Integer removeIndex = validateMap.get(validateKey);
            Object templateObj = success.get(removeIndex);
            BeanWrapper templateBeanWrapper = new BeanWrapperImpl(templateObj);
            templateBeanWrapper.setPropertyValue("errInfo", errorInfo);
            removeIndexToErrorIndex.put(removeIndex, errorIndex + 1);
        }
    }

    /**
     * 移除模板行数据
     * 适用范围：如果多行数据中关键数据重复，则多行均进入失败清单
     *
     * @param removeIndexToErrorIndex 模板行索引map，key为模板数据所在的索引，value为其所对应的错误清单中的位置
     * @param success                 成功列表
     * @param faild                   失败列表
     * @author shiping.song
     * @date 2023/2/3 10:43
     */
    public static <T> void removeTemplateData(Map<Integer, Integer> removeIndexToErrorIndex, List<T> success, List<T> faild) {
        if (MapUtils.isNotEmpty(removeIndexToErrorIndex)) {
            try {
                removeIndexToErrorIndex.forEach((removeIndex, errorIndex) -> {
                    T removeObj = success.get(removeIndex);
                    if (errorIndex > faild.size()) {
                        faild.add(removeObj);
                    } else {
                        faild.add(errorIndex, removeObj);
                    }
                });
                // 然后再从成功的清单中移除
                Integer[] removeIndexArr = removeIndexToErrorIndex.keySet().toArray(new Integer[0]);
                // 将数组翻转一下，从大到小排列
                ArrayUtils.reverse(removeIndexArr);
                Arrays.stream(removeIndexArr).forEach(removeIndex -> success.remove(removeIndex.intValue()));
            } catch (Exception e) {
                log.error(ExcelUtils.class.getName(), e);
                SmeAssert.isNull(e, "移除重复数据失败");
            }
        }
    }
}
