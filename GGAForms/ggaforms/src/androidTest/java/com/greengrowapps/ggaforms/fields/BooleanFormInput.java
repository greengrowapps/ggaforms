package com.greengrowapps.ggaforms.fields;


public class BooleanFormInput extends TestFormInput<Boolean> {

    public BooleanFormInput() {
        super(Boolean.class);
    }

    public void setChecked(boolean checked) {
        setValue(checked);
    }

    @Override
    public void setValid() {

    }
}
