package com.jeff.chapter1.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Youpeng.Zhang on 2018/3/16.
 */
public final class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static<T> String toJson(T obj) {
        String json;
        try{
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
        return json;
    }

    public static<T> T fromJson(String json, Class<T>type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
