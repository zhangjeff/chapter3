package com.jeff;

import java.lang.reflect.Method;

/**
 * @author Youpeng.Zhang on 2018/3/24.
 */
public class ReflectionTest {
    public String a() {
        System.out.println("a");
        return "aaaa";
    }

    public static Object getInvokeMethod(Object obj, Method method, Object...args ){
        try{
            Object result = method.invoke(obj, args);
            return  result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("hi jeff!");
//        ReflectionTest obj = new ReflectionTest();
        Class objClass = Class.forName("com.jeff.ReflectionTest");
        Method m = objClass.getMethod("a");
//        String cc = (String)m.invoke(objClass.newInstance(), null);
        String dd = (String)getInvokeMethod(objClass.newInstance(), m,null);
//        System.out.println("cc = " + cc);
        System.out.println("dd = " + dd);
//        m.invoke(obj, (Object) new String[] {});
//        m.invoke(obj, new String[1]);  // new String[1] 其实是null，是一个object
//        m.invoke(obj, (Object) new String[] {}); // 这里强制转成了object，所以也是object
//        m.invoke(obj, new String[] {}); // 报错，这里是个数组，是个object数组，a方法只有一个参数，所以报错

    }
}
