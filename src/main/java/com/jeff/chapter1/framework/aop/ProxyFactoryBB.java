package com.jeff.chapter1.framework.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Youpeng.Zhang on 2018/4/8.
 */
public class ProxyFactoryBB  {

    private Object advice;

    private TargetSource targetSource;
    public void setAdvice(Object advice) {
        this.advice = advice;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }


    private Object jdkDynamicAopProxy(){

        try {
            DynamicProxy dynamicProxy = new DynamicProxy();
            GreetingBeforeAdvice greetingBeforeAdvice = new GreetingBeforeAdvice();
            dynamicProxy.setMethodBeforeAdviceBB(greetingBeforeAdvice);

            dynamicProxy.setTargetSource(targetSource);
            return Proxy.newProxyInstance(
                    targetSource.getTarget().getClass().getClassLoader(),
                    targetSource.getTarget().getClass().getInterfaces(),
                    dynamicProxy

            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object objectCglibAopProxy(){
        CGLibProxy beanProxy = new CGLibProxy();
        beanProxy.setMethodBeforeAdviceBB((MethodBeforeAdviceBB) advice);
        beanProxy.setTargetSource(targetSource);
        return beanProxy.getProxy();
    }

    public Object getProxy() {
        try {
            Class cls = targetSource.getTarget().getClass();
            if (cls.isInterface()) {
                return jdkDynamicAopProxy();
            } else {
                return objectCglibAopProxy();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
