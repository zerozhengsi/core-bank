package com.vteam.cars.plugin.excel.exp;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.plugin.excel.exp.base.BaseExcelExport;
import com.vteam.cars.plugin.excel.exp.model.ExcelCell;
import com.vteam.cars.plugin.excel.exp.model.ExcelData;
import com.vteam.cars.util.SmeAssert;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * Excel大量数据导出工具类 .<br>
 *
 * @author oscar.yu
 * @date 2019/8/6 17:33
 */
@Slf4j
@Service
@Scope
public class SXSSFExportExecutor extends BaseExcelExport {
    
    @Resource(name = "smeTaskExecutor")
    private TaskExecutor taskExecutor;
    
    public SXSSFExportExecutor() {
        log.info("Excel大量数据导出执行器初始化成功。");
    }
    
    /**
     * 导出文件到本地
     * @param modelList 导出数据列表
     * @param clazz 类型
     * @param templateName 模板名称
     * @return
     * @author oscar.yu
     * @date 2019/8/7 11:02
     */
    public <T> String executeForLocal(List<T> modelList, Class<T> clazz, String templateName) {
        // 获取模板XSSFWorkbook对象
        XSSFWorkbook templateWb = (XSSFWorkbook) super.getWorkBook(templateName);
        return executeForLocal(modelList, clazz, templateWb);
    }
    
    /**
     * 导出文件到本地
     * @param modelList 导出数据列表
     * @param clazz 类型
     * @param templateWb 模板对象
     * @return
     * @author oscar.yu
     * @date 2019/8/20 18:38
     */
    public <T> String executeForLocal(List<T> modelList, Class<T> clazz, XSSFWorkbook templateWb) {
        // 解析Excel数据
        final ExcelData excelData = super.resolveExcelData(modelList, clazz);
        // 导出文件存储路径
        final String filePath = super.getExportFilePath(excelData.getExportFileName(), null);
        // 起始行
        final int startRow = excelData.getStartRow();
        // 添加制表时间 时间戳
        this.addTabulationTime(templateWb.getSheetAt(0), excelData.getTimeRow());
        // 获取模板行单元格信息
        List<ExcelCell> excelCellList = this.getSheetFirstRowCell(templateWb, startRow, excelData);
        this.excelCellList.set(excelCellList);
        // 创建SXSSFWorkbook对象
        SXSSFWorkbook wb = this.createWorkbook(templateWb, startRow);
        SXSSFSheet sheet = wb.getSheetAt(0);
        // 写入数据
        super.writeExcel(wb, sheet, excelData);
        // 写入文件
        super.writeWorkbookToFile(wb, filePath);
        // 消除线程变量
        super.destoryLocal();
        // 返回路径
        return filePath.replaceAll(GlobalConstants.Symbol.SLASH, GlobalConstants.Symbol.UNDERLINE);
    }

    /**
     * 根据模板创建workbook
     * @param wb
     * @param baseRowNum
     * @return
     * @author oscar.yu
     * @date 2019/8/7 10:28
     */
    private SXSSFWorkbook createWorkbook(XSSFWorkbook wb, final int baseRowNum) {
        int lastRowNum = wb.getSheetAt(0).getLastRowNum();
        SmeAssert.ge(lastRowNum, 2, "模板格式不正确，至少需要两行！");
        Sheet sheet0 = wb.getSheetAt(0);
        for (int i = lastRowNum; i >= baseRowNum; i--) {
            Row baseRow = sheet0.getRow(i);
            sheet0.removeRow(baseRow);
        }
        SXSSFWorkbook xwb = new SXSSFWorkbook(wb, 1000);
        return xwb;
    }
}
