package com.vteam.cars.plugin.excel.exp.base;

import com.vteam.cars.annotation.ExcelTable;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.plugin.excel.exp.model.ExcelCell;
import com.vteam.cars.plugin.excel.exp.model.ExcelData;
import com.vteam.cars.util.DateUtils;
import com.vteam.cars.util.FileUtils;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.cache.StringValueContainer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.io.ClassPathResource;

import jakarta.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Excel数据导出工具基类 .<br>
 *
 * @author oscar.yu
 * @date 2019/8/7 9:54
 */
@Slf4j
public abstract class BaseExcelExport {

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    /**
     * 模板路径
     */
    protected ThreadLocal<String> templatePath = new ThreadLocal<>();

    /**
     * 模板行单元格信息
     */
    protected ThreadLocal<List<ExcelCell>> excelCellList = new ThreadLocal<>();

    /**
     * 导出文件后缀
     */
    protected static final String SUFFIX = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.XLSX;

    @Resource(type = SmeConfiguration.class)
    protected SmeConfiguration smeConfiguration;

    /**
     * 获取指定workBook
     *
     * @param templateName
     * @return
     * @author zhuang.shao
     * @date 2019年3月26日 下午1:15:00
     */
    public Workbook getWorkBook(String templateName) {
        // 计算真实模板文件名
        String templatePath = this.getTemplatePath(templateName);

        return this.getWorkbookByTemplatePath(templatePath);
    }

    /**
     * 解析Excel数据 .<br>
     *
     * @param modelList 数据列表
     * @param clazz     数据类型
     * @return com.vteam.sme.plugin.excel.exp.model.ExcelData
     * @author andy.sher
     * @date 2018/7/27 13:27
     */
    protected <T> ExcelData resolveExcelData(List<T> modelList, Class<T> clazz) {
        // 初始化excel数据
        ExcelData excelData = new ExcelData();
        Set<String> titles = new LinkedHashSet<>();
        // 获取excel模型属性值
        ExcelTable excelModel = clazz.getAnnotation(ExcelTable.class);
        final Integer startRow = excelModel.startRow();
        final Integer startColumn = excelModel.startColumn();
        final Integer timeRow = excelModel.timeRow();
        final String exportFileName = excelModel.exportFileName();
        // 自定义字段排序
        final String[] columns = excelModel.columns();
        final String[] cellFormulaVar = excelModel.cellFormulaVar();
        // 获取数据
        List<List<Object>> rows = new ArrayList<List<Object>>();
        List<Object> row = null;
        if (CollectionUtils.isNotEmpty(modelList)) {
            for (Object data : modelList) {
                row = new ArrayList<Object>();
                BeanWrapper beanWrapper = new BeanWrapperImpl(data);
                for (String column : columns) {
                    Object value = beanWrapper.getPropertyValue(column);
                    row.add(value);
                }
                // 添加一行数据
                rows.add(row);
            }
        }
        // 设置标题
        excelData.setTitles(titles);
        // 设置行数据
        excelData.setRows(rows);
        // 设置模型属性值
        excelData.setStartRow(startRow);
        excelData.setStartColumn(startColumn);
        excelData.setTimeRow(timeRow);
        excelData.setColumns(columns);
        excelData.setExportFileName(exportFileName);
        excelData.setCellFormulaVar(cellFormulaVar);
        // 设置循环内容的配置信息
        excelData.setCurrRow(startRow);
        excelData.setCurrColumn(startColumn);
        return excelData;
    }

    /**
     * 写入Excel .<br>
     *
     * @param wb        工作簿
     * @param sheet     工作表
     * @param excelData Excel数据
     * @return void
     * @author andy.sher
     * @date 2018/7/26 22:14
     */
    protected void writeExcel(Workbook wb, Sheet sheet, ExcelData excelData) {
        // 写入行数据
        if (null != excelData) {
            this.writeRowsToExcel(wb, sheet, excelData);
        }
        // 添加制表时间 时间戳(仅XSSFWorkbook时处理)
        if (wb instanceof XSSFWorkbook) {
            this.addTabulationTime(sheet, excelData.getTimeRow());
        }
    }

    /**
     * 写入行数据
     *
     * @param wb        工作簿
     * @param sheet     工作表
     * @param excelData Excel数据
     * @return int 行下标
     * @author andy.sher
     * @date 2018/7/26 22:16
     */
    protected void writeRowsToExcel(Workbook wb, Sheet sheet, ExcelData excelData) {
        int startRow = excelData.getStartRow();
        // 模板行单元格信息
        List<ExcelCell> excelCellList = this.excelCellList.get();
        // 兼容普通导出时，未提前获取模板行单元格信息的情况
        if (CollectionUtils.isEmpty(excelCellList)) {
            excelCellList = this.getSheetFirstRowCell(wb, startRow, excelData);
        }
        // 循环写入数据
        final List<List<Object>> rows = excelData.getRows();
        int rowIndex = startRow;
        Row dataRow;
        if (CollectionUtils.isNotEmpty(rows)) {
            int colLen = rows.get(0).size();
            for (List<Object> rowData : rows) {
                // 创建新行
                dataRow = this.createRow(sheet, excelCellList, rowIndex, excelData);
                for (int colIndex = 0; colIndex < colLen; colIndex++) {
                    Cell cell = dataRow.getCell(colIndex);
                    // 修复公式不被执行的问题
                    if (null != cell && cell.getCellTypeEnum() == CellType.FORMULA) {
                        continue;
                    }
                    // 设置单元格数据
                    Object cellData = rowData.get(colIndex);
                    this.setCellValue(cell, cellData);
                }
                rowIndex++;
            }
        }
    }

    /**
     * 获取模板行对应单元格信息
     *
     * @param wb
     * @param baseRowNum
     * @return
     * @author oscar.yu
     * @date 2019/8/7 11:23
     */
    protected List<ExcelCell> getSheetFirstRowCell(Workbook wb, int baseRowNum, ExcelData excelData) {
        int lastRowNum = wb.getSheetAt(0).getLastRowNum();
        SmeAssert.ge(lastRowNum, 2, "模板格式不正确，至少需要两行！");
        Sheet sheet = wb.getSheetAt(0);
        Row baseRow = sheet.getRow(baseRowNum);
        List<ExcelCell> excelCellList = new ArrayList<ExcelCell>();
        ExcelCell cellBean;
        for (Iterator<Cell> it = baseRow.cellIterator(); it.hasNext(); ) {
            Cell baseCell = it.next();
            cellBean = new ExcelCell();
            cellBean.setCellType(baseCell.getCellTypeEnum());
            cellBean.setCellStyle(baseCell.getCellStyle());
            // 修复公式不被执行的问题
            if (baseCell.getCellTypeEnum() == CellType.FORMULA && StringUtils.isNotBlank(baseCell.getCellFormula())) {
                cellBean.setCellFormula(baseCell.getCellFormula());
            }
            excelCellList.add(cellBean);
        }
        // 记录默认行高
        float defaultHeight = baseRow.getHeightInPoints();
        excelData.setDefaultHeight(defaultHeight);
        return excelCellList;
    }

    /**
     * Excel的工作表里复制行
     *
     * @param sheet      Excel的工作表
     * @param destRowNum 目标行号（0开始）
     * @param excelData  Excel数据
     * @return row 目标行
     * @author oscar.yu
     * @date 2019/8/7 11:23
     */
    protected Row createRow(Sheet sheet, List<ExcelCell> excelCellList, int destRowNum, ExcelData excelData) {
        ExcelCell excelCell;
        final Row destRow = sheet.createRow(destRowNum); // 目标行
        int lastCellNum = excelCellList.size();
        for (int i = 0; i < lastCellNum; i++) {
            Cell destCell = destRow.getCell(i); // 目标单元格
            if (excelCellList.get(i) != null) {
                if (destCell == null) {
                    destCell = destRow.createCell(i);
                }
                excelCell = excelCellList.get(i);
                // TODO 这里这么改不会报错，但是是否妥当？
                if (CellType.FORMULA.getClass() == excelCell.getCellType().getClass()) {
                    destCell.setCellFormula(excelCell.getCellFormula());
                } else {
                    destCell.setCellType(excelCell.getCellType());
                }
                destCell.setCellStyle(excelCell.getCellStyle());
                // 修复公式不被执行的问题
                String cellFormula = excelCell.getCellFormula();
                if (StringUtils.isNotBlank(cellFormula)) {
                    // 处理公式参数替换
                    String[] cellFormulaVarArr = excelData.getCellFormulaVar();
                    if (null != cellFormulaVarArr && cellFormulaVarArr.length > 0) {
                        String newFormula = cellFormula;
                        for (String cellFormulaVar : cellFormulaVarArr) {
                            // 公式替换变量数组，格式参考：{ "I4&I&4" }，前面为替换变量，中间为固定列信息，后面为变量信息
                            String[] varArr = cellFormulaVar.split(GlobalConstants.Symbol.AND);
                            if (null == varArr || varArr.length != 3) {
                                continue;
                            }
                            Integer newVar = Integer.parseInt(varArr[2]) + (destRowNum - excelData.getStartRow());
                            String newFormulaVar = varArr[1].concat(newVar.toString());
                            newFormula = newFormula.replaceAll(varArr[0], newFormulaVar);
                        }
                        destCell.setCellFormula(newFormula);
                    }
                }
            }
        }
        destRow.setHeightInPoints(excelData.getDefaultHeight());
        return destRow;
    }

    /**
     * 设置单元格数据
     *
     * @param cell
     * @param cellData
     * @author oscar.yu
     * @date 2019/8/7 11:13
     */
    protected void setCellValue(Cell cell, Object cellData) {
        if (cellData != null) {
            if (cellData instanceof BigDecimal) {
                cell.setCellValue(((BigDecimal) cellData).doubleValue());
            } else if (cellData instanceof String) {
                cell.setCellValue(cellData.toString());
            } else if (cellData instanceof LocalDateTime) {
                cell.setCellValue((LocalDateTime) cellData);
            } else if (cellData instanceof Integer) {
                cell.setCellValue(((Integer) cellData).doubleValue());
            }
        } else {
            cell.setCellValue(StringUtils.EMPTY);
        }
    }

    /**
     * 将工作簿对象写入文件
     *
     * @param wb
     * @param filePath
     * @author oscar.yu
     * @date 2019/8/7 10:46
     */
    protected void writeWorkbookToFile(Workbook wb, String filePath) {
        // 目标文件
        File dest = new File(smeConfiguration.getTmpPath() + filePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();// 新建文件夹
        }
        try (FileOutputStream fout = new FileOutputStream(smeConfiguration.getTmpPath() + filePath);) {
            wb.setForceFormulaRecalculation(true);
            wb.write(fout);
        } catch (IOException e) {
            log.error(this.getClass().getName(), e);
        }
    }

    /**
     * 添加制表时间 .
     *
     * @param sheet
     * @param timeRow
     * @return void
     * @author zack.yin
     * @date 2018/7/28 10:34
     */
    protected void addTabulationTime(Sheet sheet, Integer timeRow) {
        final int tabulationTimeCell = timeRow;
        // tabulationTimeCell < 0 设置为没有制表时间 不做处理
        if (tabulationTimeCell >= 0) {
            Row row = sheet.getRow(tabulationTimeCell);
            // 制表时间为第一列
            Cell styCell = row.getCell(0);
            styCell.setCellValue(styCell.getStringCellValue() + LocalDateTime.now().toLocalDate().toString());
        }
    }

    /**
     * 获取导出文件路径
     *
     * @param exportFileName 导出文件名称
     * @param fileIndex      拆分多文件索引
     * @return
     */
    protected String getExportFilePath(String exportFileName, Integer fileIndex) {
        // uuid生成的存储文件夹
        String uuid = UUID.randomUUID().toString();
        // 导出文件名
        String fileName = exportFileName + LocalDateTime.now().format(DateUtils.FMT_DATE_N) + "%s" + SUFFIX;
        fileName = String.format(fileName, null != fileIndex ? String.format("(%d)", fileIndex) : StringUtils.EMPTY);
        // 导出文件存储路径
        String filePath = uuid + GlobalConstants.Symbol.SLASH + fileName;
        return filePath;
    }

    /**
     * 获取模板相对路径
     *
     * @param templateName 模板名称
     * @return
     */
    protected String getTemplatePath(String templateName) {
        // 计算真实模板文件名
        final String base = smeConfiguration.getExportPath();
        String suffix = templateName.substring(templateName.lastIndexOf(GlobalConstants.Symbol.SPOT));
        String prefix = templateName.substring(0, templateName.lastIndexOf(GlobalConstants.Symbol.SPOT));
        String realName = String.format("%s%s%s%s", base, prefix, GlobalConstants.Symbol.MINUS, suffix);

        String templatePath = StringUtils.EMPTY;
        // 判断是否存在多品牌，如果不存在则使用默认模板，存在则使用品牌模板
        if (FileUtils.exist(realName, false)) {
            templatePath = realName;
        } else {
            templatePath = base + templateName;
        }
        return templatePath;
    }

    /**
     * 消除线程变量
     *
     * @author oscar.yu
     * @date 2019/8/7 14:04
     */
    protected void destoryLocal() {
        this.templatePath.set(null);
        this.excelCellList.set(new ArrayList<>());
    }

    /**
     * 根据模板路径获取相应的工作簿 .<br>
     *
     * @param templatePath 模板路径
     * @return Workbook
     * @author zheng.zhang
     * @date 2022/9/5 14:55
     */
    protected Workbook getWorkbookByTemplatePath(String templatePath) {
        Workbook workbook = null;
        ClassPathResource resource = new ClassPathResource(templatePath);
        boolean isExcel2003 = templatePath.toLowerCase().endsWith("xls") ? true : false;
        try {
            if (isExcel2003) {
                workbook = new HSSFWorkbook(resource.getInputStream());
            } else {
                workbook = new XSSFWorkbook(resource.getInputStream());
            }
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
        }
        return workbook;
    }
}
