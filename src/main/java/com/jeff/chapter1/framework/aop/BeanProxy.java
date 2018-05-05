package com.jeff.chapter1.framework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Youpeng.Zhang on 2018/4/3.
 */
public class BeanProxy  {

    private Object advice;

    private Object targetObject;

    public void setAdvice(Object advice) {
        this.advice = advice;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public static void main(String[] args) throws Exception{
        CGLibProxy beanProxy = new CGLibProxy();
        HelloImpl helloImpl = new HelloImpl();
        Method[] method = helloImpl.getClass().getDeclaredMethods();
        String[] parms = {"-------------jack----------before ---------------"};
        BeforeAdvice beforeAdvice = new BeforeAdvice(helloImpl, method[0], parms);
//        beanProxy.setBeforeAdvice(beforeAdvice);
//        Object targetName = "HelloImpl.class";
//        Class cls = Class.forName("com.jeff.chapter1.framework.aop.HelloImpl");

//        Hello helloProxy = (Hello)beanProxy.getProxy(beanProxygetClass());
//        helloProxy.say(" jeff!");
    }

    //    public Object setAdvice(Object advice){
//        this.advice = advice;
//    }
}
