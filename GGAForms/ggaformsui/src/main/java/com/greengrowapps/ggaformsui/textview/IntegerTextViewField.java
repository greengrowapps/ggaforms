package com.greengrowapps.ggaformsui.textview;


import android.widget.TextView;

public class IntegerTextViewField extends AbstractTextViewField<Integer>{

    public IntegerTextViewField(TextView textView) {
        super(Integer.class, textView);
    }

    @Override
    protected Integer textToObj(String text) {
        try {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException e){
            return 0;
        }
    }

    @Override
    protected CharSequence objToText(Integer value) {
        return value+"";
    }
}
