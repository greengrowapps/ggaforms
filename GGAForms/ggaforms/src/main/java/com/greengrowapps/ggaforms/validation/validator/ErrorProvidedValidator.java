package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.validation.errors.ValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;

public abstract class ErrorProvidedValidator<T extends ValidationError> extends BaseValidator{

    ValidationErrorProvider errorProvider;

    public ErrorProvidedValidator(ValidationErrorProvider errorProvider){
        this.errorProvider = errorProvider;
    }

    @Override
    protected ValidationError getValidationError(Object value){
        return errorProvider.getValidationError( getErrorClass() );
    }

    protected abstract Class<? extends ValidationError> getErrorClass();

    protected ValidationErrorProvider getErrorProvider() {
        return errorProvider;
    }
}
