package com.jeff.chapter1.framework.aop;

/**
 * Created by zhangying on 2018/4/9.
 */
public interface TargetSource {

    Object getTarget() throws  Exception;
}
