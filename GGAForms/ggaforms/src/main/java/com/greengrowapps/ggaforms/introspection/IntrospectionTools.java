package com.greengrowapps.ggaforms.introspection;

import com.greengrowapps.ggaforms.validation.InputBundle;

public interface IntrospectionTools {
    <T> IntrospectedObject<T> build(Class<T> clazz, InputBundle fields) throws NonBuildableException, FieldNotFoundException;
}
