package com.jeff.spring.framework.helper;

import com.jeff.spring.framework.annotation.Action;
import com.jeff.spring.framework.bean.Handler;
import com.jeff.spring.framework.bean.Request;
import com.jeff.spring.framework.util.ArrayUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Youpeng.Zhang on 2018/3/15.
 */
public final class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();

        for (Class<?>controllerClass : controllerClassSet) {
            Method[] methods = controllerClass.getDeclaredMethods();
            if (ArrayUtil.isNotEmpty(methods)){
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Action.class)) {
                        Action action = method.getAnnotation(Action.class);
                        String mapping = action.value();
                        if (mapping.matches("\\w+:/\\w*")) {
                            String[] array = mapping.split(":");
                            if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                String requestMethod = array[0];
                                String requestpath = array[1];
                                Request request = new Request(requestMethod, requestpath);
                                Handler handler = new Handler(controllerClass, method);
                                ACTION_MAP.put(request, handler);
                            }
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestpath){
        Request request = new Request(requestMethod, requestpath);
        return ACTION_MAP.get(request);
    }
}
