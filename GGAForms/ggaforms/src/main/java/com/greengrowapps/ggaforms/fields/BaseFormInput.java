package com.greengrowapps.ggaforms.fields;

import com.greengrowapps.ggaforms.listeners.OnInputChangedListener;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;


public abstract class BaseFormInput<T> implements FormInput<T> {

    private final Class<T> clazz;
    private OnInputChangedListener listener;
    private T value;
    private ValidationError error;

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
        if(!isEqual(this.value,value)){
            updateValue(value);
        }
    }

    protected boolean isEqual(T value1, T value2){
        if(value1==null){
            return value2==null;
        }
        else{
            return value1.equals(value2);
        }
    }

    private void updateValue(T value){
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

    @Override
    public void setError(ValidationError error) {
        this.error = error;
    }

    @Override
    public ValidationError getError() {
        return error;
    }
}
