package com.jeff.spring.framework.aop;

import com.jeff.spring.framework.Exception.AopConfigException;

/**
 * Created by zhangying on 2018/5/6.
 */
public class DefaultAopProxyFactory implements AopProxyFactory {

    @Override
    public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
        if (config.isOptimize()) {
          return new JdkDynamicAopProxy(config);
        }
        return null;
    }
}
