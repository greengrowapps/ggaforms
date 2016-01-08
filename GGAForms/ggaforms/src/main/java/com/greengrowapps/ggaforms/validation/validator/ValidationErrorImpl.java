package com.greengrowapps.ggaforms.validation.validator;


public class ValidationErrorImpl implements ValidationError {
    private CharSequence localizedMessage;

    public ValidationErrorImpl(CharSequence localizedMessage){
        this.localizedMessage = localizedMessage;
    }

    @Override
    public CharSequence getLocalizedMessage() {
        return localizedMessage;
    }

}
