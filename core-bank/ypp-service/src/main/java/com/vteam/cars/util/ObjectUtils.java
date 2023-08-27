package com.vteam.cars.util;

import java.lang.reflect.Field;

public class ObjectUtils {


    /**
     * 给Java对象增加或修改属性
     * @param object Java对象
     * @param fieldName 属性名
     * @param fieldValue 属性值
     */
    public static void setProperty(Object object, String fieldName, Object fieldValue) {
        try {
            Class<?> clazz = object.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给Java对象的子对象增加属性
     * @param parentObj 父对象
     * @param childFieldName 子对象的属性名
     * @param childPropertyName 子对象要增加的属性名
     * @param childPropertyValue 子对象要增加的属性值
     */
    public static void addChildProperty(Object parentObj, String childFieldName, String childPropertyName, Object childPropertyValue) {
        try {
            Class<?> parentClass = parentObj.getClass();
            Field childField = parentClass.getDeclaredField(childFieldName);
            childField.setAccessible(true);
            Object childObj = childField.get(parentObj);

            if (childObj != null) {
                Field propertyField = childObj.getClass().getDeclaredField(childPropertyName);
                propertyField.setAccessible(true);
                propertyField.set(childObj, childPropertyValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改Java对象中的子对象属性
     * @param object Java对象
     * @param fieldName 属性名
     * @param fieldValue 属性值
     */
    public static void modifyChildProperty(Object object, String fieldName, Object fieldValue) {
        try {
            Class<?> clazz = object.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object fieldValueObject = field.get(object);

            if (fieldValueObject != null) {
                modifyObjectProperties(fieldValueObject, fieldName, fieldValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归修改对象属性
     * @param object Java对象
     * @param fieldName 属性名
     * @param fieldValue 属性值
     */
    private static void modifyObjectProperties(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                if (field.getName().equals(fieldName)) {
                    field.set(object, fieldValue);
                } else {
                    Object fieldValueObject = field.get(object);

                    if (fieldValueObject != null) {
                        modifyObjectProperties(fieldValueObject, fieldName, fieldValue);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

