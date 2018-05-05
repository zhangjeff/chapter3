package com.jeff.chapter1.framework.aop;

/**
 * Created by zhangying on 2018/4/9.
 */
public class SingletonTargetSource implements TargetSource {

    private final Object target;

    public SingletonTargetSource(Object target){
        this.target = target;
    }

    @Override
    public Object getTarget() throws Exception {
        return target;
    }
}
