package com.jeff.spring.framework.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author Youpeng.Zhang on 2018/3/15.
 */
public final class ArrayUtil {

    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return ArrayUtil.isEmpty(array);
    }
}
