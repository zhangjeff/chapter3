package com.jeff.chapter1.framework.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Youpeng.Zhang on 2018/4/8.
 */
public class ProxyFactoryBB  extends AdvisedSupport{

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
            return dynamicProxy.getProxy();
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
            if (!hasNoUserSuppliedProxyInterfaces(this)) {
                return objectCglibAopProxy();
            } else {
                return jdkDynamicAopProxy();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean hasNoUserSuppliedProxyInterfaces(AdvisedSupport config) {
        Class<?>[] ifcs = config.getProxiedInterfaces();
        return (ifcs.length == 0 || (ifcs.length == 1 && SpringProxy.class.isAssignableFrom(ifcs[0])));
    }

}
