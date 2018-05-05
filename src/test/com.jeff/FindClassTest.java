package com.jeff;

/**
 * Created by zhangying on 2018/4/10.
 */
public class FindClassTest {


    public static void main(String[] args) {
        System.out.println("hello");

//        Class aa = FindClassTest.defineClass0(FindClassTest.class.getClassLoader(),)
    }

    private static native Class<?> defineClass0(ClassLoader loader, String name,
                                                byte[] b, int off, int len);
}
