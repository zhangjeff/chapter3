package com.jeff.spring.framework.aopnew;

import com.jeff.spring.framework.aop.*;

/**
 * @author Youpeng.Zhang on 2018/4/8.
 */
public class ProxyTest {

    /**
     * test1
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

//        ProxyFactoryBB proxyFactoryBB = new  ProxyFactoryBB();
//        GreetingBeforeAdvice greetingBeforeAdvice = new GreetingBeforeAdvice();
//        proxyFactoryBB.setAdvice(greetingBeforeAdvice);
//        Hello hello1 = new HelloImpl();
//        proxyFactoryBB.setTargetSource(new SingletonTargetSource(hello1));
        AdvisedSupport config = new AdvisedSupport();
        Hello hello1 = new HelloImpl();
        config.setTargetSource(new SingletonTargetSource(hello1));
        GreetingBeforeAdvice greetingBeforeAdvice = new GreetingBeforeAdvice();
        config.setAdvice(greetingBeforeAdvice);
        AopProxyFactory aopProxyFactory = new DefaultAopProxyFactory();
        Hello hello = (Hello)aopProxyFactory.createAopProxy(config).getProxy();
        hello.say("jack");
    }
}
