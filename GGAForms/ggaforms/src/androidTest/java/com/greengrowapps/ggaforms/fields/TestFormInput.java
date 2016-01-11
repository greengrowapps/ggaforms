package com.greengrowapps.ggaforms.fields;


import com.greengrowapps.ggaforms.validation.errors.ValidationError;

public abstract class TestFormInput<T> extends BaseFormInput<T> {

    String error;

    public TestFormInput(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void setError(ValidationError error) {
        this.error = error==null? null : error.getLocalizedMessage().toString();
    }

    public String getError() {
        return error;
    }
}
