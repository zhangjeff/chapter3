package com.jeff.spring.framework;

import com.jeff.spring.framework.helper.BeanHelper;
import com.jeff.spring.framework.helper.ClassHelper;
import com.jeff.spring.framework.helper.ControllerHelper;
import com.jeff.spring.framework.helper.IocHelper;
import com.jeff.spring.framework.util.ClassUtil;

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
