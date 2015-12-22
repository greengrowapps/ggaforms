package com.greengrowapps.ggaforms.fields;

import com.greengrowapps.ggaforms.listeners.OnInputChangedListener;

public interface FormInput<T> {

    void setOnInputChangedListener(OnInputChangedListener listener);

    void setError(CharSequence error);
    void setValid();

    Class<T> getType();
    T getValue();
    void setValue(T value);
}
