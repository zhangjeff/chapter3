package com.jeff.spring.servlet;

import com.jeff.spring.framework.ConfigHelper;
import com.jeff.spring.framework.HelperLoader;
import com.jeff.spring.framework.bean.Data;
import com.jeff.spring.framework.bean.Handler;
import com.jeff.spring.framework.bean.Param;
import com.jeff.spring.framework.bean.View;
import com.jeff.spring.framework.helper.BeanHelper;
import com.jeff.spring.framework.helper.ControllerHelper;
import com.jeff.spring.framework.util.ReflectionUtil;
import org.smart4j.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Youpeng.Zhang on 2018/3/15.
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        HelperLoader.init();
        ServletContext servletContext = servletConfig.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String>paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            String body = CodecUtil.urlDecode(StreamUtil.getString(req.getInputStream()));
            if (StringUtil.isNotEmpty(body)) {
                String[]params = StringUtil.splitString(body, "&");
                if (ArrayUtil.isNotEmpty(params)) {
                    for(String param : params) {
                        String[] array = StringUtil.splitString(param, "=");
                        if (array != null && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }

            Param param = new Param(paramMap);

            Method actionMethod = handler.getActionMethod();
            Object result = null;
            if (param.getMap().size() == 0) {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, null);
            } else {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            }


            if (result instanceof View) {
                View view = (View)result;
                String path = view.getPath();
                if (StringUtil.isNotEmpty(path)){
                    if (path.startsWith("/")) {
                        resp.sendRedirect(req.getContextPath() + path);
                    } else {
                        Map<String, Object>model = view.getModel();
                        for(Map.Entry<String, Object>entry:model.entrySet()){
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() +path).forward(req,resp);
                    }
                }
            } else if (result instanceof Data) {
                Data data = (Data)result;
                Object model = data.getModel();
                if (model != null) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer =resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}
