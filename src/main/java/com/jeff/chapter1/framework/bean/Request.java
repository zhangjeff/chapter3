package com.jeff.chapter1.framework.bean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Youpeng.Zhang on 2018/3/15.
 */
public class Request {

    private String requestMethod;

    private String getRequestPath;

    public Request(String requestMethod, String getRequestPath){
        this.requestMethod = requestMethod;
        this.getRequestPath = getRequestPath;
    }

    public void setGetRequestPath(String getRequestPath) {
        this.getRequestPath = getRequestPath;
    }

    public String getGetRequestPath() {
        return getRequestPath;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
