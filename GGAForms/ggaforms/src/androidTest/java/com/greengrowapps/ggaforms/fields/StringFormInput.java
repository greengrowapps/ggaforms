package com.greengrowapps.ggaforms.fields;

public class StringFormInput extends TestFormInput<String> {

    public StringFormInput() {
        super(String.class);
    }

    public void setText(String text) {
        setValue(text);
    }
}
