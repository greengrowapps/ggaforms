package com.greengrowapps.ggaformsui.textinputlayout;


import android.support.design.widget.TextInputLayout;

public class StringTextInputLayout extends AsbstractInputLayoutField<String> {

    public StringTextInputLayout(TextInputLayout textInputLayout) {
        super(String.class, textInputLayout);
    }

    @Override
    protected String textToObj(String text) {
        return text;
    }

    @Override
    protected CharSequence objToText(String text) {
        return text;
    }
}
