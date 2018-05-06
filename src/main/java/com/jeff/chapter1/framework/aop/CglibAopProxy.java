package com.jeff.chapter1.framework.aop;

import com.jeff.chapter1.framework.Exception.AopConfigException;

/**
 * Created by zhangying on 2018/5/6.
 */
public class CglibAopProxy implements AopProxy {

    protected AdvisedSupport advised;

    public CglibAopProxy(AdvisedSupport config) throws AopConfigException{
        if (config.getTargetSource() == null) {
            throw new AopConfigException("No advisors and no TargetSource specified");
        }
        advised = config;
    }

    @Override
    public Object getProxy() {
        return null;
    }
}
