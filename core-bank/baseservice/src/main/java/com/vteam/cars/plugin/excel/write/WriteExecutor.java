package com.vteam.cars.plugin.excel.write;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.cars.plugin.excel.exp.model.ExcelPicturePosition;
import com.vteam.cars.plugin.excel.write.support.WriteStrategy;
import com.vteam.cars.plugin.file.upload.FileManager;
import com.vteam.cars.service.fbtx.FbtxApxMService;
import com.vteam.cars.util.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Excel写入数据执行器
 *
 * @author fu.tong
 * @date 2018/8/9 14:55
 */
@Setter
@Service
@Scope
@Slf4j
public class WriteExecutor<T> {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;
    
    @Resource(type = FbtxApxMService.class)
    private FbtxApxMService fbtxApxMService;
    
    @Resource(type = FileManager.class)
    private FileManager fileManager;
    
    private WriteStrategy<T> strategy;

    private static final String EXTENSION_XLS = "xls";
    private static final String EXTENSION_XLSX = "xlsx";
    
    /**
     * 写入到Excel .
     *
     * @param filePath 文件路径
     * @param list     数据
     * @param rowId    开始行
     * @return com.vteam.sme.entity.model.FbtxApxM
     * @author fu.tong
     * @date 2018/8/16 15:00
     */
    public FbtxApxM writeToExcel(String filePath, List<T> list, int rowId, String fileName) {
        // 核实所有的导出代码 
        return this.writeToExcel(filePath, list, rowId, fileName, false);
    }
    
    /**
     * 写入到Excel .
     * @param filePath 文件路径
     * @param list 数据
     * @param rowId 开始行
     * @param fileName 文件名称
     * @param ignoreShiftRows 是否忽略公共移动行处理，默认为否(不忽略)
     * @return
     * @author oscar.yu
     * @date 2019/10/11 17:12
     */
    public FbtxApxM writeToExcel(String filePath, List<T> list, int rowId, String fileName, boolean ignoreShiftRows) {
        try {
            InputStream fis = this.getClass().getResourceAsStream(filePath);
            String uuid = UUID.randomUUID().toString();
            File file = new File(smeConfiguration.getTmpPath() + uuid + fileName);
            FileUtils.copy(fis, file);
            Workbook workbook = null;
            if (fileName.endsWith(EXTENSION_XLS)) {
                workbook = new HSSFWorkbook(new FileInputStream(file));
            } else if (fileName.endsWith(EXTENSION_XLSX)) {
                workbook = new XSSFWorkbook(new FileInputStream(file));
            }
            Sheet sheet = workbook.getSheetAt(0);
            if (!ignoreShiftRows) {
                // 判断最后一行的序号,如果小于数据的总数，则将列表复制下移(同时复制起始行的样式)
                // 下移逻辑：将起始行下一行至结束行移动（数据笔数）行，增加的行复制起始行样式
                int lastRowNum = sheet.getLastRowNum();
                if (lastRowNum < list.size() + rowId) {
                    final int shiftNum = list.size() - 1; // 减一代表模板行
                    final int startRow = rowId + 1;
                    ExcelUtils.shiftRowsByTemplateRow(sheet, rowId, startRow, lastRowNum, shiftNum);
                }
            }
            return strategy.write(sheet, file, workbook, list, rowId);
        } catch (Exception e) {
            log.error(WriteExecutor.class.getName(), e);
			SmeAssert.isNull(e, "错误清单生成失败");
        }
        return null;
    }

    /**
     * 写入到Excel并上传 .
     *
     * @param filePath 文件路径
     * @param clearList 需要清除样式及内容的单元格占位符列表(默认清除上、左、右边框及内容)
     * @param dataMap  填充数据集合
     * @param list     填充数据列表
     * @param startId    开始行占位符
	 * @param beginId    模板描述开始行占位符(针对订单和发票 唛头所在行不自适应行高)
     * @param excelPicturePosition 插入图片坐标
     * 		  String pictureUuid 图片uuid
     * 		  short colStart 第一个单元格的列【从0开始,默认 0】
     * 		  int rowStart 第一个单元格的行【从0开始,默认 0】
     * 		  short colEnd 最后一个单元格的列【从0开始,默认2(注：xls包含,xlsx不包含)】
     * 		  int rowEnd 最后一个单元格的行【从0开始,默认2(注：xls包含,xlsx不包含)】
     * 		  int dxStart 第一个单元格内的x坐标【xls： 0 ~ 1023,默认 0】【xlsx：0~10*列宽,默认 0】
     * 		  int dyStart 第一个单元格内的y坐标【xls： 0 ~ 255,默认 0】【xlsx：0~行高/0.75,默认 0】
     * 		  int dxEnd 最后一个单元格中的x坐标【xls： 0 ~ 1023,默认 1023】【xlsx： 0~10*列宽,默认 0】
     * 		  int dyEnd 最后一个单元格中的y坐标【xls： 0 ~ 255,默认 255】【xlsx：0~行高/0.75,默认 0】
     * @param fileName 文件名   								 
     * @return com.vteam.cars.entity.model.FbtxApxM 附件
     * @author olivia.liu 
     * @date 2022/8/5 16:21
     */
    public FbtxApxM writeToExcelForUpload(String filePath, List<String> clearList, Map<String,Object> dataMap, List<T> list, String startId,String beginId, ExcelPicturePosition excelPicturePosition, String fileName) {
        // 核实所有的导出代码
        return this.writeToExcelForUpload(filePath, clearList, dataMap, list, startId,beginId, excelPicturePosition, fileName, false);
    }
    
    /**
     * 写入到Excel并上传 .
     *
     * @param filePath 文件路径
     * @param clearList 需要清除样式及内容的单元格占位符列表(默认清除上、左、右边框及内容)
     * @param dataMap  填充数据集合
     * @param list     填充数据列表
     * @param startId    开始行占位符
	 * @param beginId    模板描述开始行占位符(针对订单和发票 唛头所在行不自适应行高)
     * @param excelPicturePosition 插入图片坐标
     * @param fileName 文件名   								
     * @param ignoreShiftRows 是否忽略公共移动行处理，默认为否(不忽略)
     * @return com.vteam.cars.entity.model.FbtxApxM 附件
     * @author olivia.liu 
     * @date 2022/8/5 16:21
     */
    public FbtxApxM writeToExcelForUpload(String filePath, List<String> clearList, Map<String,Object> dataMap, List<T> list, String startId, String beginId, ExcelPicturePosition excelPicturePosition, String fileName, boolean ignoreShiftRows) {
    	try {
    		InputStream fis = this.getClass().getResourceAsStream(filePath);
    		String uuid = UUID.randomUUID().toString();
    		File file = new File(smeConfiguration.getTmpPath() + uuid + fileName);
    		FileUtils.copy(fis, file);
    		Workbook workbook = null;
    		if (fileName.endsWith(EXTENSION_XLS)) {
    			workbook = new HSSFWorkbook(new FileInputStream(file));
    		} else if (fileName.endsWith(EXTENSION_XLSX)) {
    			workbook = new XSSFWorkbook(new FileInputStream(file));
    		}
    		Sheet sheet = workbook.getSheetAt(0);
    		// 清除样式&获取列表起始行
    		int rowId = NumberUtils.INTEGER_ZERO;// 模板行
			int markRowId = NumberUtils.INTEGER_ZERO; //订单和发票唛头所在行的上一行
    		for (Row row : sheet) {
	            for (Cell cell : row) {
	            	String value = StringUtils.EMPTY;
	            	if (cell.getCellType().equals(CellType.NUMERIC)) {
	            		value = String.valueOf(cell.getNumericCellValue()).trim();
	            	} else {
	            		value = cell.getStringCellValue().trim();
	            	}
	            	if (StringUtils.isNotBlank(value)) {
	            		// 清除单元格样式
	            		boolean clearflag = CollectionUtils.isNotEmpty(clearList) && clearList.contains(value);
	            		if (clearflag) {
		            		ExcelUtils.clearCellAndBorder(sheet, cell, ExcelUtils.CLEAR_CELL_BORDER_ALL);
	            		}
	            		// 获取列表起始行
		            	if (StringUtils.isNotBlank(startId) && startId.equals(value)) {
		            		rowId = row.getRowNum();
		            	}
						if (StringUtils.isNotBlank(beginId) && beginId.equals(value)) {
							markRowId = row.getRowNum() + 1;
						}
	            	}
	            }
    		}    		

			// 插入图片
    		if (null != excelPicturePosition) {
                this.addPictureToExcel(workbook, filePath, excelPicturePosition);
    		}

    		// 填充VO数据
    		if (null != dataMap) {
        		this.replaceValueByDataMap(sheet, dataMap);
				// 内容填充后，设置行高自适应(不含列表)
        		List<Integer> noneRowIdList = new ArrayList<Integer>();
        		noneRowIdList.add(rowId);
				noneRowIdList.add(markRowId);
				ExcelUtils.setSheetRowHeightAuto(sheet, filePath, noneRowIdList);
    		}
            
    		// 根据列表长度复制行
    		if (!ignoreShiftRows && CollectionUtils.isNotEmpty(list)) {
    			// 判断最后一行的序号,如果小于数据的总数，则将列表复制下移(同时复制起始行的样式)
    			// 下移逻辑：将起始行下一行至结束行移动（数据笔数）行，增加的行复制起始行样式
    			int lastRowNum = sheet.getLastRowNum();// 结束行
    			if (list.size() > 1) {
    				final int shiftNum = list.size() - 1; // 减一代表需要新增行数
    				final int startRow = rowId + 1;// 复制模板起始行
    				ExcelUtils.shiftRowsByTemplateRowWithValue(sheet, rowId, startRow, lastRowNum, shiftNum);
    			}
    		}
            return strategy.write(sheet, file, workbook, list, rowId);
    	} catch (Exception e) {
    		log.error(WriteExecutor.class.getName(), e);
    	}
    	return null;
    }
    
    /**
     * 根据数据集合替换单元格内容 
     * @param sheet sheet
     * @param dataMap 数据集合
     * @author olivia.liu 
     * @date 2022/8/5 16:21
     */
    private void replaceValueByDataMap(Sheet sheet, Map<String,Object> dataMap) {
    	String dataKey = null;
    	Object dataValue = null;
    	if (dataMap != null) {
    		Iterator<String> keyIterator = dataMap.keySet().iterator();
    		while (keyIterator.hasNext()) {
    			dataKey = keyIterator.next();
    			dataValue = dataMap.get(dataKey);
    			// #xxxx封装参数key
    			dataKey = GlobalConstants.Symbol.COMMENT_SIGN + dataKey;
    			for (Row row : sheet) {
    				for (Cell cell : row) {
    	            	String value = StringUtils.EMPTY;
    	            	if (cell.getCellType().equals(CellType.NUMERIC)) {
    	            		value = String.valueOf(cell.getNumericCellValue()).trim();
    	            	} else {
    	            		value = cell.getStringCellValue().trim();
    	            	}
    					// 填充数据
    					if (StringUtils.isNotBlank(value) && value.contains(dataKey)) {
    						if (null != dataValue) {
    							if (dataValue instanceof LocalDateTime) {
    								LocalDateTime time = (LocalDateTime) dataValue;
    								value = value.replaceAll(dataKey, time.format(DateUtils.FMT_ENGLISH));
    							} else if (dataValue instanceof BigDecimal) {
    								BigDecimal amt = (BigDecimal) dataValue;
    								value = value.replaceAll(dataKey, NumberUtils.doTransConversionFourDecimal(amt));
    							} else {
    								value = value.replaceAll(dataKey, String.valueOf(dataValue));
    							} 
    						} else {
    							value = StringUtils.EMPTY;
    						}
    						cell.setCellValue(value);
    					}
    				}
    			}
    		}
    	}
    }
    
    /**
     * 将图片写入excel
     * @param workbook 工作簿
     * @param filePath excel文件路径
     * @param picturePosition 图片坐标
     * 		  String pictureUuid 图片uuid
     * 		  short colStart 第一个单元格的列【从0开始,默认 0】
     * 		  int rowStart 第一个单元格的行【从0开始,默认 0】
     * 		  short colEnd 最后一个单元格的列【从0开始,默认2(注：xls包含,xlsx不包含)】
     * 		  int rowEnd 最后一个单元格的行【从0开始,默认2(注：xls包含,xlsx不包含)】
     * 		  int dxStart 第一个单元格内的x坐标【xls： 0 ~ 1023,默认 0】【xlsx：0~10*列宽,默认 0】
     * 		  int dyStart 第一个单元格内的y坐标【xls： 0 ~ 255,默认 0】【xlsx：0~行高/0.75,默认 0】
     * 		  int dxEnd 最后一个单元格中的x坐标【xls： 0 ~ 1023,默认 1023】【xlsx： 0~10*列宽,默认 0】
     * 		  int dyEnd 最后一个单元格中的y坐标【xls： 0 ~ 255,默认 255】【xlsx：0~行高/0.75,默认 0】
     * @author olivia.liu 
     * @date 2022/8/5 16:21
     */
    private void addPictureToExcel(Workbook workbook, String filePath, ExcelPicturePosition picturePosition) {
    	BufferedImage bufferImg = null;
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        if (StringUtils.isBlank(picturePosition.getPictureUuid())) {
        	log.error("未获取到待插入图片uuid.");
        	return;
        }
        FbtxApxM fbtxApxM = fbtxApxMService.getByResourceUuid(picturePosition.getPictureUuid());
        // 设置附件信息条件
        FbtxApxMVo condition = new FbtxApxMVo();
        BeanUtils.copyProperties(fbtxApxM, condition);
        // 获取图片文件流
        File imgfile = fileManager.download(condition);
        bufferImg = ImageIO.readByToolkit(new File(imgfile.getPath()));
        ImageIO.write(bufferImg, fbtxApxM.getFileType(), byteArrayOutput);
        
        // 画图的顶级管理器
        ExcelUtils.addPictureToExcel(workbook, byteArrayOutput, picturePosition, filePath);
	}
}
