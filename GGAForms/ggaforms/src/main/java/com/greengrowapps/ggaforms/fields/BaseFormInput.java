package com.greengrowapps.ggaforms.fields;

import com.greengrowapps.ggaforms.listeners.OnInputChangedListener;


public abstract class BaseFormInput<T> implements FormInput<T> {

    private final Class<T> clazz;
    private OnInputChangedListener listener;
    private T value;

    public BaseFormInput(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public void setOnInputChangedListener(OnInputChangedListener listener) {
        this.listener = listener;
    }

    protected void onFieldChanged(T value){
        if(listener!=null){
            listener.onFieldChanged(this);
        }
    }

    @Override
    public void setValue(T value){
        this.value = value;
        onFieldChanged(value);
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Class<T> getType() {
        return clazz;
    }
}
