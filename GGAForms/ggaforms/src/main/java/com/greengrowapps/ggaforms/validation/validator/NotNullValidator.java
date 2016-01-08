package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.validation.errors.NullFieldValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;

public class NotNullValidator extends BaseValidator<NullFieldValidationError> {

    public NotNullValidator(ValidationErrorProvider errorProvider) {
        super(errorProvider);
    }

    @Override
    protected boolean isValidValue(Object value) {
        return value!=null;
    }

    @Override
    protected Class<NullFieldValidationError> getErrorClass() {
        return NullFieldValidationError.class;
    }


}
