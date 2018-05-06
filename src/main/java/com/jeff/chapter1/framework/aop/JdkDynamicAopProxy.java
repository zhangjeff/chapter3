package com.jeff.chapter1.framework.aop;

import com.jeff.chapter1.framework.Exception.AopConfigException;

/**
 * Created by zhangying on 2018/5/6.
 */
public class JdkDynamicAopProxy {

    private AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport config) throws AopConfigException{
        if(config.getTargetSource() == null){
            throw new AopConfigException("No advisors and no TargetSource specified");
        }
        advised = config;
    }

    public Object getProxy(){
        return null;
    }
}
