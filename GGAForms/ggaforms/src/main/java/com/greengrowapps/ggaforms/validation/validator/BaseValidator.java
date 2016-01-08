package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;

public abstract class BaseValidator<T extends ValidationError> implements ValueValidator{

    ValidationErrorProvider errorProvider;

    public BaseValidator(ValidationErrorProvider errorProvider){
        this.errorProvider = errorProvider;
    }

    @Override
    public void validate(Object value, ValidationResult result) {
        if(!isValidValue(value)){
            result.appendError( errorProvider.getValidationError( getErrorClass() )) ;
        }
    }

    protected abstract boolean isValidValue(Object value);

    protected abstract Class<T> getErrorClass();
}
