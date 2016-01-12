package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.errors.NullFieldValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;

public class NotNullValidator extends ErrorProvidedValidator<NullFieldValidationError> {

    public NotNullValidator(FormInput input, ValidationErrorProvider errorProvider) {
        super(input,errorProvider);
    }

    @Override
    protected boolean isValidValue(Object parent, Object value) {
        return value!=null;
    }

    @Override
    protected Class<NullFieldValidationError> getErrorClass() {
        return NullFieldValidationError.class;
    }


}
