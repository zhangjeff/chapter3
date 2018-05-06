package com.jeff.spring.framework.aopnew;

/**
 * @author Youpeng.Zhang on 2018/3/27.
 */
public class HelloImpl implements Hello {

    @Override
    public void say(String name) {
        System.out.println("Hello!" + name);
    }
}
