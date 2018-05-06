package com.jeff.chapter1.framework.Exception;

/**
 * Created by zhangying on 2018/5/6.
 */
public class AopConfigException extends RuntimeException {

    public static String buildMessage(String message, Throwable cause) {
        if (cause != null) {
            StringBuilder sb = new StringBuilder();
            if (message != null) {
                sb.append(message).append("; ");
            }
            sb.append("nested exception is ").append(cause);
            return sb.toString();
        }
        else {
            return message;
        }
    }

    @Override
    public String getMessage() {
        return buildMessage(super.getMessage(), getCause());
    }

    public AopConfigException(String message) {
        super(message);
    }
}
