package com.vteam.cars.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * 校验工具类.<br>
 *
 * @author shiping.song
 * @date 2023/2/11 15:35
 */
public class ValidateUtil {

    private ValidateUtil() {

    }

    /**
     * 校验当前数据是否为有效数据
     *
     * @param currentValue      当前待校验值
     * @param ancillaryFunction 辅助校验函数
     * @return 校验结果
     * @author shiping.song
     * @date 2023/2/11 15:44
     */
    @SuppressWarnings("all")
    public static <T> boolean isValidData(T currentValue, Function<T, Boolean> ancillaryFunction) {
        if (Objects.isNull(currentValue)) {
            return false;
        }
        boolean validDataFlag = true;
        if (currentValue instanceof String) {
            validDataFlag = StringUtils.isNotBlank(currentValue.toString());
        } else if (currentValue instanceof Collection) {
            validDataFlag = CollectionUtils.isNotEmpty((Collection<?>) currentValue);
        } else if (currentValue instanceof Map) {
            validDataFlag = MapUtils.isNotEmpty((Map<?, ?>) currentValue);
        } else if (currentValue instanceof Integer) {
            validDataFlag = Integer.valueOf(currentValue.toString()) > 0;
        } else if (currentValue instanceof BigDecimal) {
            validDataFlag = BigDecimal.ZERO.compareTo((BigDecimal) currentValue) < 0;
        } else if (currentValue instanceof int[]) {
            validDataFlag = ArrayUtils.isNotEmpty((int[]) currentValue);
        } else if (currentValue instanceof String[]) {
            validDataFlag = ((String[]) currentValue).length > 0;
        }

        if (Objects.nonNull(ancillaryFunction)) {
            validDataFlag = validDataFlag && ancillaryFunction.apply(currentValue);
        }
        return validDataFlag;
    }

    public static boolean isNotValidData(Object currentValue, Function<Object, Boolean> ancillaryFunction) {
        return !isValidData(currentValue, ancillaryFunction);
    }

    public static boolean isValidData(Object currentValue) {
        return isValidData(currentValue, null);
    }

    public static boolean isNotValidData(Object currentValue) {
        return !isValidData(currentValue);
    }

    public static void main(String[] args) {
        System.out.println(ValidateUtil.isValidData(Arrays.asList("1", "2", "3")));
    }
}
