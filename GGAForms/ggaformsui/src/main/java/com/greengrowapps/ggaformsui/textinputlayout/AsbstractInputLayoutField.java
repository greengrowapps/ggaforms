package com.greengrowapps.ggaformsui.textinputlayout;

import android.support.design.widget.TextInputLayout;
import android.text.TextWatcher;

import com.greengrowapps.ggaforms.validation.errors.ValidationError;
import com.greengrowapps.ggaformsui.edittext.AbstractEditTextField;

public abstract class AsbstractInputLayoutField<T> extends AbstractEditTextField<T> implements TextWatcher {

    private final TextInputLayout textInputLayout;

    public AsbstractInputLayoutField(Class<T> clazz, TextInputLayout textInputLayout) {
        super(clazz, textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    @Override
    protected void onDisplayError(ValidationError currentError) {
        super.onDisplayError(currentError);
        textInputLayout.setError(currentError!=null? currentError.getLocalizedMessage() : null);
    }
}
