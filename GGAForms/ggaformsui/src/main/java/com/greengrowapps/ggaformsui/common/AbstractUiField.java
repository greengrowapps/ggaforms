package com.greengrowapps.ggaformsui.common;

import com.greengrowapps.ggaforms.fields.BaseFormInput;

public class AbstractUiField<T> extends BaseFormInput<T> {

    private CharSequence currentError = null;
    private DisplayErrorListener displayErrorListener;

    public AbstractUiField(Class<T> clazz) {
        super(clazz);
    }

    public void setDisplayErrorListener(DisplayErrorListener displayErrorListener) {
        this.displayErrorListener = displayErrorListener;
    }

    @Override
    public void setError(CharSequence error) {
        this.currentError = error;
        displayError();
    }

    @Override
    public void setValid() {
        currentError = null;
        displayError();
    }

    protected void displayError(){
        onDisplayError(currentError);
    }

    protected void onDisplayError(CharSequence currentError) {
        if(displayErrorListener!=null){
            if(currentError==null){
                displayErrorListener.onHideError();
            }
            else{
                displayErrorListener.onShowError(currentError);
            }
        }
    }
}
