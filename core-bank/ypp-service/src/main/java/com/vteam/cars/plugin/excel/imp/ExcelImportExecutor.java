package com.vteam.cars.plugin.excel.imp;

import com.vteam.cars.plugin.excel.imp.support.ResolveStrategy;
import com.vteam.cars.util.ExcelUtils;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.Part;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Excel导入执行器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/13 14:28
 */
@Slf4j
@Setter
@Service
@Scope
public class ExcelImportExecutor<T> {

    private ResolveStrategy<T> resolveStrategy;

    private ExcelImportExecutor() {
        log.info("Excel导入执行器初始化成功。");
    }

    /**
     * 执行导入 .<br>
     *
     * @param file 导入Excel文件
     * @return void
     * @author andy.sher
     * @date 2018/7/13 14:48
     */
    public List<T> execute(File file) {
        try {
            Workbook workbook = getWorkbook(new FileInputStream(file), file.getName());
            return resolveStrategy.resolve(workbook);
        } catch (IOException e) {
            SmeAssert.isNull(e, "文件解析失败。");
        }
        return null;
    }

    /**
     * 执行导入 .<br>
     *
     * @param file 导入Excel文件
     * @return void
     * @author andy.sher
     * @date 2018/7/13 14:48
     */
    public List<T> execute(MultipartFile file) {
        try {
            Workbook workbook = getWorkbook(file.getInputStream(), file.getOriginalFilename());
            return resolveStrategy.resolve(workbook);
        } catch (IOException e) {
            SmeAssert.isNull(e, "文件解析失败。");
        }
        return null;
    }

    /**
     * 执行导入 .<br>
     *
     * @param part 导入Excel文件
     * @return void
     * @author andy.sher
     * @date 2018/7/13 14:48
     */
    public List<T> execute(Part part) {
        try {
            Workbook workbook = getWorkbook(part.getInputStream(), part.getSubmittedFileName());
            return resolveStrategy.resolve(workbook);
        } catch (IOException e) {
            SmeAssert.isNull(e, "文件解析失败。");
        }
        return null;
    }

    /**
     * 获取工作簿 .<br>
     *
     * @param inputStream Excel文件流
     * @param fileName    文件名
     * @return org.apache.poi.ss.usermodel.Workbook 工作簿
     * @author andy.sher
     * @date 2018/7/13 14:48
     */
    private Workbook getWorkbook(InputStream inputStream, String fileName) {
        SmeAssert.isFalse(!ExcelUtils.isExcel2003(fileName) && !ExcelUtils.isExcel2007(fileName), "上传文件格式不正确");

        Workbook wb = null;
        try (InputStream is = inputStream) {
            if (ExcelUtils.isExcel2007(fileName)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = new HSSFWorkbook(is);
            }
        } catch (Exception e) {
            SmeAssert.isNull(e, "读取Excel文件失败。");
        }
        return wb;
    }

    public boolean checkFileTitle(MultipartFile file, String fileTitle, int rowNum, int cellNum) {
        boolean isRight = true;
        try {
            Workbook workbook = getWorkbook(file.getInputStream(), file.getOriginalFilename());
            Sheet sheet = workbook.getSheet(fileTitle);
            Row row = null;
            if (null != sheet) {
                row = sheet.getRow(rowNum);
                if (null != row) {
                    String impFileTitle = row.getCell(cellNum) != null ? ExcelUtils.getCellValue(row.getCell(cellNum)) : "";
                    if (StringUtils.isBlank(impFileTitle)) {
                        log.info("导入模板的title为空");
                        isRight = false;
                    } else if (!fileTitle.equals(impFileTitle)) {
                        log.info("导入模板的title为" + impFileTitle);
                        isRight = false;
                    }
                } else {
                    log.info("获取sheet" + fileTitle + "的row为空");
                    isRight = false;
                }
            } else {
                log.info("获取sheet" + fileTitle + "为空");
                isRight = false;
            }
        } catch (Exception e) {
            SmeAssert.isNull(e, "读取Excel文件失败。");
        }
        return isRight;
    }

    /**
     * 校验模型
     */
    @Getter
    @Setter
    @ToString
    @Builder
    public static class CheckModel implements Serializable {
        private static final long serialVersionUID = -2731357308069836131L;
        /**
         * 待校验sheet索引
         */
        private Integer sheetIndex;
        /**
         * 待校验行索引
         */
        private Integer rowIndex;
        /**
         * 待校验单元格索引
         */
        private Integer cellIndex;
        /**
         * 待校验内容
         */
        private String content;
        /**
         * 错误信息
         */
        private String errorMsg;
    }

    /**
     * 校验文件是否正确
     *
     * @param file         待校验文件
     * @param checkModel   校验模型
     * @param failConsumer 结果异常处理
     * @author shiping.song
     * @date 2023/2/2 14:21
     */
    public void checkFile(MultipartFile file, CheckModel checkModel, Consumer<Boolean> failConsumer) {
        SmeAssert.notNull(file, "待校验文件为空");
        SmeAssert.notNull(checkModel, "待校验模型为空");
        int sheetIndex = Objects.isNull(checkModel.getSheetIndex()) ? 0 : checkModel.getSheetIndex();
        Integer cellIndex = checkModel.getCellIndex();
        Integer rowIndex = checkModel.getRowIndex();
        String content = checkModel.getContent();
        SmeAssert.isFalse(Objects.isNull(cellIndex) || Objects.isNull(rowIndex), "待校验索引为空");
        SmeAssert.notBlank(content, "待比对文本为空");
        boolean isRight = true;
        try {
            Workbook workbook = getWorkbook(file.getInputStream(), file.getOriginalFilename());
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row;
            if (null != sheet) {
                row = sheet.getRow(rowIndex);
                if (null != row) {
                    String validatedContent = ExcelUtils.getStringCellValue(row.getCell(cellIndex));
                    if (StringUtils.isBlank(validatedContent)) {
                        isRight = false;
                    } else if (!content.equals(validatedContent)) {
                        log.info("当前校验的单元格文本为：" + validatedContent);
                        isRight = false;
                    }
                } else {
                    log.info("获取sheet的row为空");
                    isRight = false;
                }
            } else {
                log.info("获取sheet为空");
                isRight = false;
            }
        } catch (Exception e) {
            SmeAssert.isNull(e, "读取Excel文件失败。");
        }
        if (Objects.nonNull(failConsumer)) {
            failConsumer.accept(isRight);
        }
    }

    /**
     * 校验文件是否正确，默认处理为如果不正确将抛出错误
     *
     * @param file       待校验文件
     * @param checkModel 校验模型
     * @author shiping.song
     * @date 2023/2/2 14:28
     */
    public void checkFile(MultipartFile file, CheckModel checkModel) {
        SmeAssert.notNull(checkModel, "待校验模型为空");
        String errorMsg = StringUtils.isNotBlank(checkModel.getErrorMsg()) ? checkModel.getErrorMsg() : "导入的文件模板不正确";
        this.checkFile(file, checkModel, checkResult -> SmeAssert.isTrue(checkResult, errorMsg));
    }
}
