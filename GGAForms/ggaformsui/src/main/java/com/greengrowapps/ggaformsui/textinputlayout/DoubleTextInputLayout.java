package com.greengrowapps.ggaformsui.textinputlayout;


import android.support.design.widget.TextInputLayout;

public class DoubleTextInputLayout extends AsbstractInputLayoutField<Double> {

    public DoubleTextInputLayout(TextInputLayout textInputLayout) {
        super(Double.class, textInputLayout);
    }

    @Override
    protected Double textToObj(String text) {

        try {
            return Double.parseDouble(text);
        }
        catch (NumberFormatException e){
            return null;
        }
    }
}
