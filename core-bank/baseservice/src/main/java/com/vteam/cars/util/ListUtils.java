package com.vteam.cars.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * List工具类
 *
 * @author zack.yin
 * @date 2018/7/11 9:30
 */
@Slf4j
public final class ListUtils {

    private ListUtils() {
    }

    /**
     * 深拷贝的方法
     *
     * @param src
     * @return java.util.List<T>
     * @author zack.yin
     * @date 2018/7/11 9:32
     */
    public static List<?> deepCopy(List<?> src) {
        List<?> dest = null;
        if (CollectionUtils.isNotEmpty(src)) {
            dest = new ArrayList<>(src.size());
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out;
            try {
                out = new ObjectOutputStream(byteOut);
                out.writeObject(src);
                ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
                ObjectInputStream in = new ObjectInputStream(byteIn);
                dest = (List<?>) in.readObject();
            } catch (Exception e) {
                log.error(ListUtils.class.getName(), e);
            }
        }
        return dest;
    }

    /**
     * 判断指定的数组是否为空。
     *
     * @param array 待判断的数组
     * @return 返回指定的数组是否为空。
     */
    public static Boolean isEmpty(Object[] array) {
        return (null == array || array.length == 0);
    }

    /**
     * 判断指定的数组是否不为空。
     *
     * @param array 待判断的数组
     * @return 返回指定的数组是否不为空。
     */
    public static Boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

}
