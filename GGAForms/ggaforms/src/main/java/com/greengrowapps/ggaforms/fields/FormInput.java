package com.greengrowapps.ggaforms.fields;

import com.greengrowapps.ggaforms.listeners.OnInputChangedListener;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;

public interface FormInput<T> {

    void setOnInputChangedListener(OnInputChangedListener listener);

    void setError(ValidationError error);

    Class<T> getType();
    T getValue();
    void setValue(T value);
}
