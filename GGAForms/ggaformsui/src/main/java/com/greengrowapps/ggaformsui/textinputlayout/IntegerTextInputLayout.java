package com.greengrowapps.ggaformsui.textinputlayout;


import android.support.design.widget.TextInputLayout;

public class IntegerTextInputLayout extends AsbstractInputLayoutField<Integer> {

    public IntegerTextInputLayout(TextInputLayout textInputLayout) {
        super(Integer.class, textInputLayout);
    }

    @Override
    protected Integer textToObj(String text) {

        try {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException e){
            return null;
        }
    }
}
