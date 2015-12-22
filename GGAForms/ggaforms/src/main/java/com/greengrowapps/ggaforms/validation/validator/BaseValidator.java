package com.greengrowapps.ggaforms.validation.validator;

public abstract class BaseValidator implements ValueValidator{
    @Override
    public void validate(Object value, ValidationResult result) {
        if(!isValidValue(value)){
            result.appendError(new ValidationErrorImpl());
        }
    }

    protected abstract boolean isValidValue(Object value);
}
