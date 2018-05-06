package com.jeff.spring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Youpeng.Zhang on 2018/3/16.
 */
public final class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    public static String encodeURL(String source) {
        String target;
        try{
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return target;
    }

    public static String decodeUrl(String source) {
        String target;
        try{
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return target;
    }
}
