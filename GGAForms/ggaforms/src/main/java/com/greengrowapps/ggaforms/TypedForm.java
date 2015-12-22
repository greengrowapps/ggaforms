package com.greengrowapps.ggaforms;


import com.greengrowapps.ggaforms.listeners.OnValidTypedFormListener;
import com.greengrowapps.ggaforms.validation.TypedFormValidator;

public interface TypedForm<T> extends SimpleForm{

    TypedForm<T> addValidator(TypedFormValidator<T> validator);
    TypedForm<T> setOnValidListener(OnValidTypedFormListener<T> listener);

    void setObject(T object);
    T getObject();
}
