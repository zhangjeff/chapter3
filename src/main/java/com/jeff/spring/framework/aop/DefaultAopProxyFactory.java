package com.jeff.spring.framework.aop;

import com.jeff.spring.framework.Exception.AopConfigException;

/**
 * Created by zhangying on 2018/5/6.
 */
public class DefaultAopProxyFactory implements AopProxyFactory {

    @Override
    public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
        if (config.isOptimize() || hasNoUserSuppliedProxyInterfaces(config)) {
          return new JdkDynamicAopProxy(config);
        } else {
            return new ObjenesisCglibAopProxy(config);
        }
    }

    private boolean hasNoUserSuppliedProxyInterfaces(AdvisedSupport config) {
        Class<?>[] ifcs = config.getProxiedInterfaces();
        return (ifcs.length == 0 || (ifcs.length == 1 && SpringProxy.class.isAssignableFrom(ifcs[0])));
    }
}
