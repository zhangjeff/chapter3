package com.jeff.chapter1.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Youpeng.Zhang on 2018/3/14.
 */
public final class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls) {
        Object instance;
        try{
            instance = cls.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static Object invokeMethod(Object obj, Method method, Object...args) {
        Object result;
        try{
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
        return  result;
    }

    public static void  setField(Object obj, Field field, Object value){
        try{
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
