package com.jeff.chapter1.framework.aop;

/**
 * Created by zhangying on 2018/5/6.
 */
public class ProxyCreatorSupport extends AdvisedSupport {

    private AopProxyFactory aopProxyFactory;

    public void setAopProxyFactory(AopProxyFactory aopProxyFactory) {
        this.aopProxyFactory = aopProxyFactory;
    }

    public AopProxyFactory getAopProxyFactory() {
        return aopProxyFactory;
    }

    protected AopProxy createAopProxy(){
        return aopProxyFactory.createAopProxy(this);
    }

}
