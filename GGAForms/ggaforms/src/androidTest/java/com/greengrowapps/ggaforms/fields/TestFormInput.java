package com.greengrowapps.ggaforms.fields;


public abstract class TestFormInput<T> extends BaseFormInput<T> {

    String error;

    public TestFormInput(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void setError(CharSequence error) {
        this.error = error==null? null : error.toString();
    }

    public String getError() {
        return error;
    }
}
