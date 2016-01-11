package com.greengrowapps.ggaforms.fields;

public class StringFormInput extends TestFormInput {

    public StringFormInput() {
        super(String.class);
    }

    public void setText(String text) {
        setValue(text);
    }
}
