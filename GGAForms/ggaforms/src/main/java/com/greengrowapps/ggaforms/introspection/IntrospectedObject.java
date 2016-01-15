package com.greengrowapps.ggaforms.introspection;

import com.greengrowapps.ggaforms.fields.FormInput;

import java.lang.reflect.Field;

public interface IntrospectedObject<T>{

    T getObject();
    void assignValue(String property, Object value);
    void setTarget(T object);
    FormInput getInputFrom(Field field);
}
