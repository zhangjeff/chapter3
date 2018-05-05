package com.jeff.chapter1.framework.aop;

import java.lang.reflect.Method;

/**
 * @author Youpeng.Zhang on 2018/4/3.
 */
public class BeforeAdvice {
    private Object object;
    private Method method;
    private String[] args;

    public BeforeAdvice(Object object, Method method, String[] args){
        this.object= object;
        this.method=method;
        this.args =args;
    }

    public void invokeMethod(){
        beforeAdvice(object, method, args);
    }

    private void beforeAdvice(Object object, Method method, String...args){
        try{
            method.invoke(object, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        HelloImpl helloImpl = new HelloImpl();
//        Method[] method = helloImpl.getClass().getDeclaredMethods();
//        String[] parms = {"-------------jack before-----"};
//        BeforeAdvice beforeAdvice = new BeforeAdvice(helloImpl,method[0], parms);
//        beforeAdvice.invokeMethod();
//    }
}
