package com.jeff.chapter1.framework.aop;

/**
 * Created by zhangying on 2018/5/5.
 */
public class ProxyConfig {
    private boolean optimize = false;

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }

    public boolean isOptimize() {
        return optimize;
    }
}
