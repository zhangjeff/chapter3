package com.jeff.spring.framework;

import com.jeff.spring.util.PropsUtil;

import java.util.Properties;

/**
 * @author Youpeng.Zhang on 2018/3/13.
 */
public final class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
    }
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
    }

    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH);
    }

    public static String getAppAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH);
    }
}