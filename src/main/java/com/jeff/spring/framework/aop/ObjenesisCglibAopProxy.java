package com.jeff.spring.framework.aop;

/**
 * Created by zhangying on 2018/5/6.
 */
public class ObjenesisCglibAopProxy extends CglibAopProxy{

    public ObjenesisCglibAopProxy(AdvisedSupport config) {
        super(config);
    }
}
