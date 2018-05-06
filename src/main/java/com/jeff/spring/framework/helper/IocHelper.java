package com.jeff.spring.framework.helper;

import com.jeff.spring.framework.annotation.Inject;
import com.jeff.spring.framework.util.ReflectionUtil;
import org.smart4j.framework.util.ArrayUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Youpeng.Zhang on 2018/3/15.
 */
public final class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (beanMap != null && beanMap.size()> 0) {
            for (Map.Entry<Class<?>, Object>beanEntry : beanMap.entrySet()) {
                Class<?>beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFiledClass = beanField.getType();
                            Object beanFiledInstance = beanMap.get(beanFiledClass);

                            if (beanFiledInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFiledInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
