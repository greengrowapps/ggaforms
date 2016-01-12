package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.errors.ExceedsMaxLengthValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;


public class MaxLengthValidator extends ErrorProvidedValidator<ExceedsMaxLengthValidationError> {

    int maxLength = Integer.MAX_VALUE;

    public MaxLengthValidator(FormInput formInput, ValidationErrorProvider errorProvider, int length) {
        super(formInput,errorProvider);
        maxLength = length;
    }

    @Override
    protected boolean isValidValue(Object parent, Object value) {

        if(value!=null && value instanceof CharSequence) {
            CharSequence toCheck = (CharSequence) value;
            return toCheck.length() <= maxLength;
        }

        return false;
    }

    @Override
    protected ValidationError getValidationError(Object value) {
        return getErrorProvider().getValidationError(ExceedsMaxLengthValidationError.class, maxLength);
    }

    @Override
    protected Class<? extends ValidationError> getErrorClass() {
        return ExceedsMaxLengthValidationError.class;
    }

}
