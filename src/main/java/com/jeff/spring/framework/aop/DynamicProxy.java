package com.jeff.spring.framework.aop;

import com.jeff.spring.framework.aopnew.Hello;
import com.jeff.spring.framework.aopnew.HelloImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Youpeng.Zhang on 2018/3/27.
 */
public class DynamicProxy implements InvocationHandler, AopProxy {

    //    private Object target;
    private MethodBeforeAdviceBB methodBeforeAdviceBB;

    private TargetSource targetSource;

    public void setMethodBeforeAdviceBB(MethodBeforeAdviceBB methodBeforeAdviceBB) {
        this.methodBeforeAdviceBB = methodBeforeAdviceBB;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

//    public DynamicProxy(Object target){
//        this.target = target;
//    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        before();
//        methodBeforeAdviceBB.before(method,args,targetSource.getTarget());
        methodBeforeAdviceBB.before(method, args, proxy);
        Object result = method.invoke(targetSource.getTarget(), args);
//        after();
        return result;
    }

//    public void before(){
//        System.out.println("-------before------------");
//    }
//
//    public void after(){
//        System.out.println("---------after-------------");
//    }

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy();

        GreetingBeforeAdvice greetingBeforeAdvice = new GreetingBeforeAdvice();
        dynamicProxy.setMethodBeforeAdviceBB(greetingBeforeAdvice);

        dynamicProxy.setTargetSource(new SingletonTargetSource(hello));
        Hello helloProxy = (Hello) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                dynamicProxy
        );

        helloProxy.say("jack");
    }

    @Override
    public Object getProxy() {
        try {
            return Proxy.newProxyInstance(
                    targetSource.getTarget().getClass().getClassLoader(),
                    targetSource.getTarget().getClass().getInterfaces(),
                    this

            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
