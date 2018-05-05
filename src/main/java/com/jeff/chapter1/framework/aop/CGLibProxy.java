package com.jeff.chapter1.framework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Youpeng.Zhang on 2018/3/27.
 */
public class CGLibProxy implements MethodInterceptor {

    private MethodBeforeAdviceBB methodBeforeAdviceBB;

    private TargetSource targetSource;

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public void setMethodBeforeAdviceBB(MethodBeforeAdviceBB methodBeforeAdviceBB) {
        this.methodBeforeAdviceBB = methodBeforeAdviceBB;
    }

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }


    public <T> T getProxy() {
        try {
            Class cls = targetSource.getTarget().getClass();
            return (T) Enhancer.create(cls, this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        methodBeforeAdviceBB.before(method, objects, o);
        Object result = methodProxy.invokeSuper(o, objects);
        return result;
    }
}
