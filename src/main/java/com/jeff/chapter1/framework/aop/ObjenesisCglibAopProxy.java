package com.jeff.chapter1.framework.aop;

import com.jeff.chapter1.framework.Exception.AopConfigException;

/**
 * Created by zhangying on 2018/5/6.
 */
public class ObjenesisCglibAopProxy extends CglibAopProxy{

    public ObjenesisCglibAopProxy(AdvisedSupport config) {
        super(config);
    }
}
