package com.greengrowapps.ggaformsui.textview;


import android.widget.EditText;
import android.widget.TextView;

import com.greengrowapps.ggaformsui.edittext.AbstractEditTextField;

public class DoubleTextViewField extends AbstractTextViewField<Double> {

    public DoubleTextViewField(TextView textView) {
        super(Double.class, textView);
    }

    @Override
    protected Double textToObj(String text) {
        try {
            return Double.parseDouble(text);
        }
        catch (NumberFormatException e){
            return 0.0;
        }
    }

    @Override
    protected CharSequence objToText(Double value) {
        return value+"";
    }
}
