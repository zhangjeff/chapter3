package com.jeff.chapter1.framework.aop;

import java.lang.reflect.Method;

/**
 * Created by zhangying on 2018/4/9.
 */
public interface MethodBeforeAdviceBB {

    void before(Method method, Object[] args, Object target) throws Throwable;
}
