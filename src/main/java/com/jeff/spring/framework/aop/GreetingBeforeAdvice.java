package com.jeff.spring.framework.aop;

import java.lang.reflect.Method;

/**
 * Created by zhangying on 2018/4/9.
 */
public class GreetingBeforeAdvice implements MethodBeforeAdviceBB {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        String clinetname = (String)args[0];

        System.out.println("How are you ! Mr." + clinetname + ".");
    }
}
