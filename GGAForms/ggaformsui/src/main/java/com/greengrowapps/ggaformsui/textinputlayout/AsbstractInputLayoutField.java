package com.greengrowapps.ggaformsui.textinputlayout;

import android.support.design.widget.TextInputLayout;
import android.text.TextWatcher;

import com.greengrowapps.ggaformsui.edittext.AbstractEditTextField;

public abstract class AsbstractInputLayoutField<T> extends AbstractEditTextField<T> implements TextWatcher {

    private final TextInputLayout textInputLayout;

    public AsbstractInputLayoutField(Class<T> clazz, TextInputLayout textInputLayout) {
        super(clazz, textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    @Override
    protected void onDisplayError(CharSequence currentError) {
        super.onDisplayError(currentError);
        textInputLayout.setError(currentError);
    }
}
