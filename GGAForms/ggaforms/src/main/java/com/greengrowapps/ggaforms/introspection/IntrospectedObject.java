package com.greengrowapps.ggaforms.introspection;

import com.greengrowapps.ggaforms.validation.InputBundle;

public interface IntrospectedObject<T>{

    T getObject();
    void assignValue(String property, Object value);
    void setTarget(T object);
}
