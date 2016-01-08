package com.greengrowapps.ggaformsui.edittext;

import android.widget.EditText;

public class StringEditTextField extends AbstractEditTextField<String> {

    public StringEditTextField(EditText editText) {
        super(String.class, editText);
    }

    @Override
    protected String textToObj(String text) {
        return text;
    }

}
