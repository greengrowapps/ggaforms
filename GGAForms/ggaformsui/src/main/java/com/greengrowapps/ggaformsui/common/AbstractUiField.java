package com.greengrowapps.ggaformsui.common;

import com.greengrowapps.ggaforms.fields.BaseFormInput;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;

public class AbstractUiField<T> extends BaseFormInput<T> {

    private ValidationError currentError = null;
    private DisplayErrorListener displayErrorListener;

    public AbstractUiField(Class<T> clazz) {
        super(clazz);
    }

    public AbstractUiField setDisplayErrorListener(DisplayErrorListener displayErrorListener) {
        this.displayErrorListener = displayErrorListener;
        return this;
    }

    @Override
    public void setError(ValidationError error) {
        this.currentError = error;
        displayError();
    }


    protected void displayError(){
        onDisplayError(currentError);
    }

    protected void onDisplayError(ValidationError currentError) {
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
