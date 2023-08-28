package com.vteam.cars.plugin.excel.exp;

import com.vteam.cars.annotation.ExcelTable;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.plugin.excel.exp.base.BaseExcelExport;
import com.vteam.cars.plugin.excel.exp.model.ExcelData;
import com.vteam.cars.util.DateUtils;
import com.vteam.cars.util.FileUtils;
import com.vteam.cars.util.NumberUtils;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.thread.SmeThreadLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Excel导出工具 .<br>
 *
 * @author andy.sher
 * @date 2018/7/26 20:33
 */
@Slf4j
@Service
@Scope
public class ExcelExportExecutor extends BaseExcelExport {

    /**
     * 压缩文件后缀
     */
    private static final String ZIP_SUFFIX = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.ZIP;

    /**
     * 每个excel最大行数
     */
    private static final int MAX_PAGE_SIZE = 3000;

    @Resource(name = "smeTaskExecutor")
    private TaskExecutor taskExecutor;

    public ExcelExportExecutor() {
        log.info("Excel导出执行器初始化成功。");
    }

    /**
     * 执行导出 .<br>
     *
     * @param response  http响应对象
     * @param modelList 导出数据列表
     * @param clazz     导出数据类型
     * @return void
     * @author andy.sher
     * @date 2018/7/26 22:08
     */
    public <T> void execute(HttpServletResponse response, List<T> modelList, Class<T> clazz, String templateName) {
        // 计算真实模板文件名
        String tplPath = super.getTemplatePath(templateName);
        this.templatePath.set(tplPath);
        // 解析数据
        ExcelData excelData = super.resolveExcelData(modelList, clazz);
        // 导出excel(浏览器导出)
        try {
            final String now = LocalDateTime.now().format(DateUtils.FMT_DATE_N);
            this.exportExcel(response, excelData.getExportFileName() + now + SUFFIX, excelData);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
        } finally {
            // 消除线程变量
            super.destoryLocal();
        }
    }

    /**
     * 导出文件到本地 .
     *
     * @param modelList
     * @param clazz
     * @return java.lang.String
     * @author zack.yin
     * @date 2018/8/2 10:54
     */
    public <T> String executeForLocal(List<T> modelList, Class<T> clazz, String templateName) {
        return executeForLocal(modelList, clazz, templateName, true);
    }

    /**
     * 导出文件到本地 .
     *
     * @param modelList    导出数据列表
     * @param clazz        类型
     * @param templateName 模板名称
     * @param isPage       是否分页
     * @return java.lang.String 文件
     * @author andy.sher
     * @date 2019/4/8 11:57
     */
    public <T> String executeForLocal(List<T> modelList, Class<T> clazz, String templateName, boolean isPage) {
        if (isPage && modelList.size() > MAX_PAGE_SIZE) {
            Set<String> fileNames = new HashSet<>();
            // 计算分页笔数
            int pageCount = (int) Math.ceil(((float) modelList.size() / MAX_PAGE_SIZE));
            pageCount = pageCount == 0 ? 1 : pageCount;
            // 计算分页信息
            for (int i = 0; i < pageCount; i++) {
                List<T> tempList = new ArrayList<>();
                if (MAX_PAGE_SIZE * (i + 1) > modelList.size()) {
                    tempList.addAll(modelList.subList(i * MAX_PAGE_SIZE, modelList.size()));
                } else {
                    tempList.addAll(modelList.subList(i * MAX_PAGE_SIZE, MAX_PAGE_SIZE * (i + 1)));
                }
                // 分批导出数据至Excel中
                String tempName = this.writeExportFileToLocal(tempList, clazz, templateName, (i + 1));
                tempName = smeConfiguration.getTmpPath() + tempName.replaceAll(GlobalConstants.Symbol.UNDERLINE, GlobalConstants.Symbol.SLASH);
                fileNames.add(tempName);
            }

            // 压缩文件集合
            List<File> fileList = new ArrayList<>();
            for (String fileName : fileNames) {
                fileList.add(new File(fileName));
            }
            String uuid = UUID.randomUUID().toString();
            final String exportFileName = clazz.getAnnotation(ExcelTable.class).exportFileName();
            String zipFileName = exportFileName + LocalDateTime.now().format(DateUtils.FMT_DATE_N) + ZIP_SUFFIX;
            String zipFilePath = uuid + GlobalConstants.Symbol.SLASH + zipFileName;
            FileUtils.doZip(fileList, smeConfiguration.getTmpPath() + zipFilePath, false);
            return zipFilePath.replaceAll(GlobalConstants.Symbol.SLASH, GlobalConstants.Symbol.UNDERLINE);
        } else {
            // 导出数据至Excel中
            return this.writeExportFileToLocal(modelList, clazz, templateName, null);
        }
    }

    /**
     * 写入导出文件到本地 .
     *
     * @param modelList    数据列表
     * @param clazz        类型
     * @param templateName 模板名称
     * @return java.lang.String
     * @author andy.sher
     * @date 2019/4/8 11:48
     */
    private <T> String writeExportFileToLocal(List<T> modelList, Class<T> clazz, String templateName, Integer index) {
        // 解析Excel数据
        final ExcelData excelData = super.resolveExcelData(modelList, clazz);
        // 导出文件存储路径
        String filePath = super.getExportFilePath(excelData.getExportFileName(), index);
        // 创建XSSFWorkbook对象
        Workbook wb = this.getWorkBook(templateName);
        Sheet sheet = wb.getSheetAt(0);
        // 写入数据
        super.writeExcel(wb, sheet, excelData);
        // 写入文件
        super.writeWorkbookToFile(wb, filePath);
        // 返回路径
        return filePath.replaceAll(GlobalConstants.Symbol.SLASH, GlobalConstants.Symbol.UNDERLINE);
    }

    /**
     * 导出excel到本地
     *
     * @param showName excel显示的名称
     * @param workbook
     * @param replace 正斜杠替换
     * @return
     * @author zhuang.shao
     * @date 2019年3月12日 下午5:55:44
     */
    public String executeLocalTemplateByShowName(Workbook workbook, String showName, boolean replace) {
        Map<String, String> map = new HashMap<>();
        // 获取线程锁
        SmeThreadLock smeThreadLock = SmeThreadLock.getInstance();
        taskExecutor.execute(() -> {
            try {
                // 导出文件存储路径
                String filePath = super.getExportFilePath(showName, null);
                map.put("filePath", filePath);

                // 写入文件
                super.writeWorkbookToFile(workbook, filePath);
            } catch (Exception e) {
                log.error(this.getClass().getName(), e);
            } finally {
                // 解锁主线程
                smeThreadLock.unlock();
            }
        });
        // 锁住主线程
        smeThreadLock.lock();
        return replace ? map.get("filePath").replaceAll(GlobalConstants.Symbol.SLASH, GlobalConstants.Symbol.UNDERLINE) : map.get("filePath");
    }

    /**
     * 导出excel到本地
     *
     * @param showName excel显示的名称
     * @param workbook
     * @return
     * @author zhuang.shao
     * @date 2019年3月12日 下午5:55:44
     */
    public String executeLocalTemplateByShowName(Workbook workbook, String showName) {
        return executeLocalTemplateByShowName(workbook, showName, true);
    }

    /**
     * 替换指定单元格的内容
     *
     * @param sheet
     * @param rowIndex
     * @param cellIndex
     * @param value
     * @author zhuang.shao
     * @date 2019年3月26日 下午1:28:08
     */
    public void replaceFind(Sheet sheet, int rowIndex, int cellIndex, String value) {
        Row row = sheet.getRow(rowIndex);
        if (null != row && null != value) {
            Cell cell = row.getCell(cellIndex);
            if (null != cell) {
                this.replaceCellValue(cell, value);
            }
        }
    }

    /**
     * 替换单元格类容 .
     *
     * @param sheet
     * @param rowIndex
     * @param cellIndex
     * @param value
     * @return void
     * @author zack.yin
     * @date 2019/5/10 9:48
     */
    public void replaceContent(Sheet sheet, int rowIndex, int cellIndex, Object value) {
        Row row = sheet.getRow(rowIndex);
        if (null != row && null != value) {
            Cell cell = row.getCell(cellIndex);
            if (null != cell) {
                // 设置单元格数据
                super.setCellValue(cell, value);
            }
        }
    }

    /**
     * 替换单元格内容
     *
     * @param cell
     * @param value
     * @author zhuang.shao
     * @date 2019年3月12日 下午4:34:20
     */
    private void replaceCellValue(Cell cell, String value) {
        // 修复数字类型显示格式问题
        if (NumberUtils.isCreatable(value)) {
            if (NumberUtils.isInteger(value)) { // 整数
                cell.setCellValue(NumberUtils.toInt(value, 0));
            } else { // 浮点数
                cell.setCellValue(NumberUtils.toDouble(value, 0));
            }
        } else { // 字符串
            cell.setCellValue(value);
        }
    }

    /**
     * 移除对应Sheet页
     *
     * @param workbook
     * @param sheetIndex
     */
    public void removeSheet(Workbook workbook, int sheetIndex) {
        workbook.removeSheetAt(sheetIndex);
    }

    /**
     * 在线导出Excel .<br>
     *
     * @param response http响应对象
     * @param fileName 文件名称
     * @param data     Excel数据
     * @return void
     * @author andy.sher
     * @date 2018/7/26 22:12
     */
    private void exportExcel(HttpServletResponse response, String fileName, ExcelData data) {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 创建XSSFWorkbook对象
        Workbook wb = this.getWorkBook(templatePath.get());
        Sheet sheet = wb.getSheetAt(0);
        // 下载文件的默认名称
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            final String encodeFileName = URLEncoder.encode(fileName, smeConfiguration.getEncoding());
            response.setHeader("Content-Disposition", "attachment;filename=" + encodeFileName);
            // 写入Excel
            super.writeExcel(wb, sheet, data);
            wb.write(outputStream);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
        }

    }

    /**
     * 刷新所有公式单元格
     *
     * @param sheet
     * @author zhuang.shao
     * @date 2019年3月12日 下午5:23:57
     */
    public void refreshCellFormula(Sheet sheet) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                // 修复公式不被执行的问题
                if (cell.getCellTypeEnum() == CellType.FORMULA) {
                    cell.setCellFormula(cell.getCellFormula());
                }
                continue;
            }
        }
    }

    /**
     * 获取指定workBook的对应sheet
     *
     * @param workbook
     * @param sheetname
     * @param sheetindex
     * @return
     * @author zhuang.shao
     * @date 2019年3月26日 下午1:26:00
     */
    public Sheet getSheet(Workbook workbook, String sheetname, int sheetindex) {
        if (StringUtils.isNotBlank(sheetname)) {
            workbook.setSheetName(sheetindex, sheetname);
        }
        Sheet sheet = workbook.getSheetAt(sheetindex);
        return sheet;
    }

}
