package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.validation.errors.NotCheckedValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;

public class TrueValidator extends ErrorProvidedValidator<NotCheckedValidationError> {
    public TrueValidator(ValidationErrorProvider errorProvider) {
        super(errorProvider);
    }

    @Override
    protected boolean isValidValue(Object value) {
        return ((Boolean) value);
    }

    @Override
    protected Class<NotCheckedValidationError> getErrorClass() {
        return NotCheckedValidationError.class;
    }
}
