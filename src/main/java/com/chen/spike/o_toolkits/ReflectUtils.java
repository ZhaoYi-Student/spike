package com.chen.spike.o_toolkits;

import java.lang.reflect.Field;

public class ReflectUtils {

    public static void setFieldValue(Object object, String fieldName, Object value) {
        Class<?> clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
