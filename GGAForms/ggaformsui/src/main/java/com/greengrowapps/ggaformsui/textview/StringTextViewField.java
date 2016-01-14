package com.greengrowapps.ggaformsui.textview;

import android.widget.TextView;

public class StringTextViewField extends AbstractTextViewField<String> {

    public StringTextViewField(TextView textView) {
        super(String.class, textView);
    }

    @Override
    protected String textToObj(String text) {
        return text;
    }

    @Override
    protected CharSequence objToText(String value) {
        return value;
    }
}
