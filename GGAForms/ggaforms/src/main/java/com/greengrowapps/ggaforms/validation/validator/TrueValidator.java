package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.errors.NotCheckedValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;

public class TrueValidator extends ErrorProvidedValidator<NotCheckedValidationError> {
    public TrueValidator(FormInput input, ValidationErrorProvider errorProvider) {
        super(input,errorProvider);
    }

    @Override
    protected boolean isValidValue(Object parent, Object value) {
        return ((Boolean) value);
    }

    @Override
    protected Class<NotCheckedValidationError> getErrorClass() {
        return NotCheckedValidationError.class;
    }
}
