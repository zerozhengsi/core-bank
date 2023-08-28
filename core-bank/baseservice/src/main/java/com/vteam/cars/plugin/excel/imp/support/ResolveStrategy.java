package com.vteam.cars.plugin.excel.imp.support;

import com.vteam.cars.util.ExcelUtils;
import com.vteam.cars.util.SmeAssert;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解析策略 .<br>
 *
 * @author andy.sher
 * @date 2018/7/13 14:43
 */
public interface ResolveStrategy<T> {

    /**
     * 解析工作表 .<br>
     *
     * @param workbook 数据簿
     * @return java.util.List 数据列表
     * @ 解析异常
     * @author andy.sher
     * @date 2018/8/3 10:56
     */
    default List<T> resolve(Workbook workbook) {
        return orientedResolveTemplate(workbook);
    }

    /**
     * 流程化解析，规范流程化过程
     *
     * @param workbook 当前操作工作目录
     * @return 解析结果
     * @author shiping.song
     * @date 2023/1/17 11:24
     */
    default List<T> orientedResolveTemplate(Workbook workbook) {
        // 未开启流程化解析调用本方法则直接抛出错误
        SmeAssert.isTrue(this.oriented(), "未开启流程化解析功能");
        List<T> resolveList = new ArrayList<>();
        Sheet sheet;
        Row row;
        Iterator<Sheet> iterator = workbook.sheetIterator();
        int rowCount = this.rowCount();
        // 计数器
        int counter = 0;
        AtomicInteger columnCount = new AtomicInteger(0);
        int initColumnCount = this.columnCount();
        // 错误信息数组，只有一个元素，其含义为解析行错误信息
        String[] errorMsgArr = new String[1];
        try {
            sheet = iterator.next();
            while (rowCount <= sheet.getLastRowNum()) {
                row = sheet.getRow(rowCount);
                if (isEndLine(row, columnCount, counter)) {
                    break;
                }
                // 使用EexcelUtils工具类读取值时会默认对columnCount进行加一处理
                columnCount.set(initColumnCount > 0 ? initColumnCount - 1 : initColumnCount);
                T resolveObj = this.resolve(row, columnCount, errorMsgArr);
                // 如果解析出来的数据为空则直接报错
                if (Objects.isNull(resolveObj)) {
                    throw new IllegalStateException();
                }
                resolveList.add(resolveObj);
                rowCount++;
                counter++;
            }
        } catch (IllegalStateException e) {
            ExcelUtils.throwException(e, rowCount + 1, columnCount.get(), errorMsgArr[0]);
        } catch (Exception e) {
            SmeAssert.isNull(e, "解析有误，填写数据时请勿改变原模板格式。");
        }
        return resolveList;
    }

    /**
     * 校验当前行是否为终止行
     *
     * @param row         当前行
     * @param columnIndex 全局列索引
     * @param rowCount    有效数据行数
     * @return 校验结果
     * @author shiping.song
     * @date 2023/1/17 11:32
     */
    default boolean isEndLine(Row row, AtomicInteger columnIndex, int rowCount) {
        return true;
    }

    /**
     * 有效数据起始行索引
     *
     * @return 有效数据起始索引
     * @author shiping.song
     * @date 2023/1/17 10:36
     */
    default int rowCount() {
        return 0;
    }

    /**
     * 有效数据起始列(非索引)，从第1列开始，默认第一列为序号列
     *
     * @return 有效数据起始列
     * @author shiping.song
     * @date 2023/1/18 9:18
     */
    default int columnCount() {
        return 2;
    }

    /**
     * 解析表格行数据
     *
     * @param row         当前解析行
     * @param columnIndex 全局列索引
     * @param errorMsgArr 错误信息数组(只有一个元素)
     * @return 当前行解析出来的对象
     * @author shiping.song
     * @date 2023/1/17 11:09
     */
    default T resolve(Row row, AtomicInteger columnIndex, String[] errorMsgArr) {
        return null;
    }

    /**
     * 是否启用流程化解析
     * 本方法与resolve(row, columnIndex,errorMsgArr)
     * rowCount() 及 isEndLine(row, columnIndex)等方法联动使用，达到流程化解析的效果
     *
     * @return 配置结果
     * @author shiping.song
     * @date 2023/1/17 10:37
     */
    default boolean oriented() {
        return false;
    }

}
