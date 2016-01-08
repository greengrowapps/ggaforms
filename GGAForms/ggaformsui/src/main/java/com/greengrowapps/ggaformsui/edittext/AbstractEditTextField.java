package com.greengrowapps.ggaformsui.edittext;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.greengrowapps.ggaforms.fields.BaseFormInput;
import com.greengrowapps.ggaformsui.common.AbstractUiField;
import com.greengrowapps.ggaformsui.common.DisplayErrorListener;

public abstract class AbstractEditTextField<T> extends AbstractUiField<T> implements TextWatcher {

    private boolean firstEdit = true;

    public AbstractEditTextField(Class<T> clazz, EditText editText) {
        super(clazz);
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean gainFocus) {
                if (!gainFocus) {
                    onFocusLost();
                }
            }
        });
    }

    protected void onFocusLost(){
        firstEdit = false;
        displayError();
    }

    protected abstract T textToObj(String text);

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //Not used
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setValue(textToObj(charSequence.toString()));
    }

    @Override
    public void afterTextChanged(Editable editable) {
        //not used
    }

    protected boolean isFirstEdit() {
        return firstEdit;
    }



    @Override
    protected void displayError() {
        if(!isFirstEdit()){
            super.displayError();
        }
    }




}
