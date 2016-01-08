package com.greengrowapps.ggaformsui.edittext;


import android.widget.EditText;

public class IntegerEditTextField extends AbstractEditTextField<Integer>{

    public IntegerEditTextField( EditText editText) {
        super(Integer.class, editText);
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
}
