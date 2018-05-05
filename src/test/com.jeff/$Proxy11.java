package com.jeff;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Created by zhangying on 2018/4/11.
 */
public class $Proxy11 extends Proxy
        implements UserService   {

    // 构造方法，参数就是刚才传过来的MyInvocationHandler类的实例
    public $Proxy11(InvocationHandler invocationhandler)
    {
        super(invocationhandler);
    }

    public final boolean equals(Object obj)
    {
        try
        {
            return ((Boolean)super.h.invoke(this, m1, new Object[] {
                    obj
            })).booleanValue();
        }
        catch(Error _ex) { }
        catch(Throwable throwable)
        {
            throw new UndeclaredThrowableException(throwable);
        }
        return false;
    }

    /**
     * 这个方法是关键部分
     */
    public final void add()
    {
        try
        {
            // 实际上就是调用MyInvocationHandler的public Object invoke(Object proxy, Method method, Object[] args)方法，第二个问题就解决了
            super.h.invoke(this, m3, null);
            return;
        }
        catch(Error _ex) { }
        catch(Throwable throwable)
        {
            throw new UndeclaredThrowableException(throwable);
        }
        return;
    }

    public final int hashCode() {
        try
        {
            return ((Integer)super.h.invoke(this, m0, null)).intValue();
        }
        catch(Error _ex)
        { }
        catch(Throwable throwable)
        {
            throw new UndeclaredThrowableException(throwable);
        }
        return 0;
    }

    public final String toString()
    {
        try
        {
            return (String)super.h.invoke(this, m2, null);
        }
        catch(Error _ex) { }
        catch(Throwable throwable)
        {
            throw new UndeclaredThrowableException(throwable);
        }
        return null;
    }

    private static Method m1;
    private static Method m3;
    private static Method m0;
    private static Method m2;

    // 在静态代码块中获取了4个方法：Object中的equals方法、UserService中的add方法、Object中的hashCode方法、Object中toString方法
    static
    {
        try
        {
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] {
                    Class.forName("java.lang.Object")
            });
            m3 = Class.forName("dynamic.proxy.UserService").getMethod("add", new Class[0]);
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            throw new NoSuchMethodError(nosuchmethodexception.getMessage());
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }
}
