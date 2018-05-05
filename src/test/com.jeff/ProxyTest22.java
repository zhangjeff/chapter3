package com.jeff;

import org.junit.Test;

/**
 * Created by zhangying on 2018/4/11.
 */
public class ProxyTest22 {
//
//    @Test
//    public void testProxy() throws Throwable {
//        // 实例化目标对象
//        UserService userService = new UserServiceImpl();
//
//        // 实例化InvocationHandler
//        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
//
//        // 根据目标对象生成代理对象
//        UserService proxy = (UserService) invocationHandler.getProxy();
//
//        // 调用代理对象的方法
//        proxy.add();
//
//    }

    @Test
    public void testGenerateProxyClass() {
        ProxyGeneratorUtils.writeProxyClassToHardDisk("/Users/zhangying/excel/$Proxy13.class");
    }
}
