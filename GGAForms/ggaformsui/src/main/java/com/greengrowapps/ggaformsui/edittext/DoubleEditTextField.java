package com.greengrowapps.ggaformsui.edittext;


import android.widget.EditText;

public class DoubleEditTextField extends AbstractEditTextField<Double> {

    public DoubleEditTextField( EditText editText) {
        super(Double.class, editText);
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
}
