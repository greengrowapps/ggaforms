package com.greengrowapps.ggaforms.annotations;

import com.greengrowapps.ggaforms.validation.validator.BaseValidator;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;

import java.util.regex.Pattern;

public class OnlyNumbersValidator extends BaseValidator<NotNumbersValidationError> {

    @Override
    protected boolean isValidValue(Object value) {
        return value!=null && Pattern.matches("^[0-9]*$",(CharSequence)value);
    }

    @Override
    protected ValidationError getValidationError(Object value) {
        return new NotNumbersValidationError();
    }
}
