package com.vteam.cars.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;

public class FastjsonUtils {



    /**
     * 将JSON字符串转换为JSONObject
     * @param jsonString JSON字符串
     * @return 转换后的JSONObject
     */
    public static JSONObject toJsonObject(String jsonString) {
        return JSON.parseObject(jsonString);
    }

    /**
     * 将Java对象转换为JSON字符串
     * @param object 要转换的Java对象
     * @return JSON字符串
     */
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将JSON字符串转换为Java对象
     * @param jsonString 要转换的JSON字符串
     * @param clazz 目标Java对象的类
     * @return 转换后的Java对象
     */
    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * 将Java对象转换为JSONObject
     * @param object 要转换的Java对象
     * @return 转换后的JSONObject
     */
    public static JSONObject toJsonObject(Object object) {
        return (JSONObject) JSON.toJSON(object);
    }

    /**
     * 将JSONObject转换为Java对象
     * @param jsonObject 要转换的JSONObject
     * @param clazz 目标Java对象的类
     * @return 转换后的Java对象
     */
    public static <T> T fromJsonObject(JSONObject jsonObject, Class<T> clazz) {
        return JSON.toJavaObject(jsonObject, clazz);
    }

    /**
     * 将Java对象的属性值设置到JSONObject中
     * @param object Java对象
     * @param jsonObject JSONObject
     */
    public static void setPropertiesToObject(Object object, JSONObject jsonObject) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                Object value = field.get(object);
                jsonObject.put(fieldName, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将JSONObject的属性值设置到Java对象中
     * @param jsonObject JSONObject
     * @param object Java对象
     */
    public static void setPropertiesFromObject(JSONObject jsonObject, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (jsonObject.containsKey(fieldName)) {
                Object value = jsonObject.get(fieldName);
                try {
                    field.set(object, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
