package com.jeff.spring.framework.aop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 2018/5/5.
 */
public class AdvisedSupport extends ProxyConfig {

    TargetSource targetSource = null;

    private List<Class<?>> interfaces = new ArrayList<Class<?>>();

    /**
     * Set the interfaces to be proxied.
     */
    public void setInterfaces(Class<?>... interfaces) {
        this.interfaces.clear();
        for (Class<?> ifc : interfaces) {
            addInterface(ifc);
        }
    }

    public void addInterface(Class<?> ifc) {
        if (!ifc.isInterface()) {
            throw  new IllegalArgumentException("this is not a interface!");
        }

        if (!interfaces.contains(ifc)) {
            interfaces.add(ifc);
        }
    }

    public Class<?>[] getProxiedInterfaces(){
        return interfaces.toArray(new Class<?>[interfaces.size()]);
//        return (Class<?>[]) interfaces.toArray();
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public TargetSource getTargetSource(){
        return targetSource;
    }
}
