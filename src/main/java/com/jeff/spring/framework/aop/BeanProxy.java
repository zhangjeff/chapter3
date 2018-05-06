package com.jeff.spring.framework.aop;

import com.jeff.spring.framework.aopnew.HelloImpl;

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
//        Class cls = Class.forName("com.jeff.spring.framework.aop.HelloImpl");

//        Hello helloProxy = (Hello)beanProxy.getProxy(beanProxygetClass());
//        helloProxy.say(" jeff!");
    }

    //    public Object setAdvice(Object advice){
//        this.advice = advice;
//    }
}
