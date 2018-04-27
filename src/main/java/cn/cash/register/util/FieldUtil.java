/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 对象字段工具类
 * @author HuHui
 * @version $Id: FieldUtil.java, v 0.1 2018年4月27日 下午8:33:41 HuHui Exp $
 */
public class FieldUtil {

    /**
     * 调用字段的get方法返回字段值
     */
    public static String invokeGetter(Object obj, Class<?> clazz, Field field) {
        String fieldName = field.getName();
        String methodEnd = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        String methodName = "get" + methodEnd;

        try {
            Method getMethod = clazz.getDeclaredMethod(methodName, new Class[] {});
            return (String) getMethod.invoke(obj, new Object[] {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
