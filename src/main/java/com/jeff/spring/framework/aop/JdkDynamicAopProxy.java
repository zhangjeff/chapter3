package com.jeff.spring.framework.aop;

import com.jeff.spring.framework.Exception.AopConfigException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangying on 2018/5/6.
 */
public class JdkDynamicAopProxy implements AopProxy,InvocationHandler {

    private AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport config) throws AopConfigException{
        if(config.getTargetSource() == null){
            throw new AopConfigException("No advisors and no TargetSource specified");
        }
        advised = config;
    }

    public Object getProxy(){
        return getProxy(Thread.currentThread().getContextClassLoader());
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        before();
//        methodBeforeAdviceBB.before(method,args,targetSource.getTarget());
//        methodBeforeAdviceBB.before(method, args, proxy);
//        GreetingBeforeAdvice greetingBeforeAdvice=(GreetingBeforeAdvice)advised.getAdvice();
        Object result = method.invoke(advised.getTargetSource().getTarget(), args);
//        after();
        return result;
    }

    public Object getProxy(ClassLoader classLoader){
        return Proxy.newProxyInstance(
                classLoader,
                advised.getClass().getInterfaces(),
                this
        );
    }
}
