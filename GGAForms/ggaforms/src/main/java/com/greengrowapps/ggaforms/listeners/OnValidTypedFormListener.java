package com.greengrowapps.ggaforms.listeners;


import com.greengrowapps.ggaforms.TypedForm;

public interface OnValidTypedFormListener<T> {

    void onFormValid(TypedForm<T> form, T object);
    void onFormInvalid(TypedForm<T> form);

}
