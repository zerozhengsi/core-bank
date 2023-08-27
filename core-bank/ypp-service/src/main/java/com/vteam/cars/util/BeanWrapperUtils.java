package com.vteam.cars.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.beans.PropertyDescriptor;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: BeanWrapperUtils
 * @Description: 处理SPRING对象及子对象工具类
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/5/28 6:45
 */
public class BeanWrapperUtils {

    public static void setPropertyValue(Object object, String propertyName, Object value) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        beanWrapper.setPropertyValue(propertyName, value);
    }

    public static Object getPropertyValue(Object object, String propertyName) {
        try{
            BeanWrapper beanWrapper = new BeanWrapperImpl(object);
            if(beanWrapper.getWrappedInstance() instanceof JSONObject){
                return ((JSONObject) beanWrapper.getWrappedInstance()).get(propertyName);
            }
            return beanWrapper.getPropertyValue(propertyName);
        }catch (InvalidPropertyException e){
            return null;
        }
    }

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }


    /**
     * 递归处理子对象
     * @param object
     * @param propertyName 多级子对象使用(father.child.xxx)方式访问
     * @param value
     */
    public static void setNestedPropertyValue(Object object, String propertyName, Object value) {
        String[] nestedProperties = propertyName.split("\\.");
        Object currentObject = object;

        for (int i = 0; i < nestedProperties.length - 1; i++) {
            String currentPropertyName = nestedProperties[i];
            Object nestedObject = getPropertyValue(currentObject, currentPropertyName);

            if (nestedObject == null) {
                // 子对象不存在时创建
                nestedObject = instantiateNestedObject(currentObject, currentPropertyName);
                setPropertyValue(currentObject, currentPropertyName, nestedObject);
            }

            currentObject = nestedObject;
        }

        String finalPropertyName = nestedProperties[nestedProperties.length - 1];
        setPropertyValue(currentObject, finalPropertyName, value);
    }

    private static Object instantiateNestedObject(Object object, String propertyName) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        Class<?> nestedPropertyType = beanWrapper.getPropertyType(propertyName);

        try {
            return nestedPropertyType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // Handle the exception according to your requirement
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 递归获取子对象
     * @param object
     * @param propertyName 多级子对象使用(father.child.xxx)方式访问
     */
    public static Object getNestedPropertyValue(Object object, String propertyName) {
        String[] nestedProperties = propertyName.split("\\.");
        Object currentObject = object;

        for (String currentPropertyName : nestedProperties) {
            currentObject = getPropertyValue(currentObject, currentPropertyName);

            if (currentObject == null) {
                return null;
            }
        }

        return currentObject;
    }

    public static void copyMapToBean(Map<String, Object> map, Object targetBean) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(targetBean);
        ConversionService conversionService = new DefaultConversionService();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String propertyName = entry.getKey();
            Object propertyValue = entry.getValue();

            if(propertyValue == null || "".equals(propertyValue)){
                continue;
            }

            //LocalDateTime在默认转换器无法处理，这里单独设置
            if (beanWrapper.getPropertyType(propertyName).equals(LocalDateTime.class) && propertyValue instanceof String) {
                LocalDateTime localDateTime = LocalDateTime.parse((String) propertyValue, DateUtils.FMT_SECOND);
                beanWrapper.setPropertyValue(propertyName, localDateTime);
            } else {
                beanWrapper.setPropertyValue(propertyName, conversionService.convert(propertyValue, beanWrapper.getPropertyType(propertyName)));
            }
        }
    }

    public static Map<String, Object> copyBeanToMap(Object object) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        Map<String, Object> map = new HashMap<>();

        for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            if (!propertyName.equals("class")) {
                Object propertyValue = beanWrapper.getPropertyValue(propertyName);
                map.put(propertyName, propertyValue);
            }
        }

        return map;
    }



}