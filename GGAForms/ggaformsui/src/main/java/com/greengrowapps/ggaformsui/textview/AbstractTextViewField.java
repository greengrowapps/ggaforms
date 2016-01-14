package com.greengrowapps.ggaformsui.textview;

import android.widget.TextView;

import com.greengrowapps.ggaformsui.common.AbstractUiField;


public abstract class AbstractTextViewField<T> extends AbstractUiField<T> {

    private TextView textView;

    public AbstractTextViewField(Class<T> clazz, TextView textView) {
        super(clazz);
        this.textView = textView;
    }

    protected abstract T textToObj(String text);
    protected abstract CharSequence objToText(T value);

    @Override
    public void setValue(T value) {
        super.setValue(value);
        if(!isEqual(value, textToObj(textView.getText().toString()))) {
            textView.setText(objToText(value));
        }

    }
}
