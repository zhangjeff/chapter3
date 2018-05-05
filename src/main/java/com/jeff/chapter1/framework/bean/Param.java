package com.jeff.chapter1.framework.bean;

import java.util.Map;

/**
 * @author Youpeng.Zhang on 2018/3/15.
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public long getLong(String name) {
        return Long.valueOf(name);
    }

    public Map<String, Object> getMap(){
        return paramMap;
    }
}
