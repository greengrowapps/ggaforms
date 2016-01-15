package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.errors.ExceedsMaxLengthValidationError;
import com.greengrowapps.ggaforms.validation.errors.ExceedsMinLengthValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;


public class MinLengthValidator extends ErrorProvidedValidator<ExceedsMinLengthValidationError> {

    int minLength = 0;

    public MinLengthValidator(FormInput input, ValidationErrorProvider errorProvider, int length) {
        super(input,errorProvider);
        minLength = length;
    }

    @Override
    protected boolean isValidValue(Object parent, Object value) {

        if(value!=null && value instanceof CharSequence) {
            CharSequence toCheck = (CharSequence) value;
            return toCheck.length() >= minLength;
        }

        return false;
    }

    @Override
    protected ValidationError getValidationError(Object value) {
        return getErrorProvider().getValidationError(ExceedsMinLengthValidationError.class, minLength);
    }

    @Override
    protected Class<? extends ValidationError> getErrorClass() {
        return ExceedsMaxLengthValidationError.class;
    }
}
