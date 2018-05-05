package com.jeff.chapter1.framework;

import com.jeff.chapter1.framework.annotation.Controller;
import com.jeff.chapter1.framework.bean.Handler;
import com.jeff.chapter1.framework.helper.BeanHelper;
import com.jeff.chapter1.framework.helper.ClassHelper;
import com.jeff.chapter1.framework.helper.ControllerHelper;
import com.jeff.chapter1.framework.helper.IocHelper;
import com.jeff.chapter1.framework.util.ClassUtil;

/**
 * @author Youpeng.Zhang on 2018/3/15.
 */
public final class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?>cls : classList){
            ClassUtil.loadClass(cls.getName(), true);
        }
    }

    public static void main(String[] args) {
        System.out.println("------2222222222222222------");
        HelperLoader.init();
        System.out.println("------3333333333333333------");
    }

}
