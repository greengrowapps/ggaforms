package com.greengrowapps.ggaformsui.edittext;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.greengrowapps.ggaformsui.common.AbstractUiField;

public abstract class AbstractEditTextField<T> extends AbstractUiField<T> implements TextWatcher {

    private boolean firstEdit = true;
    private EditText editText;

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
        this.editText = editText;
    }

    protected void onFocusLost(){
        firstEdit = false;
        displayError();
    }

    protected abstract T textToObj(String text);
    protected abstract CharSequence objToText(T value);

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

    @Override
    public void setValue(T value) {
        super.setValue(value);
        if(!isEqual(value, textToObj(editText.getText().toString()))) {
            int cursorEnd = editText.getSelectionEnd();
            int cursorStart = editText.getSelectionStart();
            editText.setText(objToText(value));
            editText.setSelection(cursorStart, cursorEnd);
        }

    }
}
