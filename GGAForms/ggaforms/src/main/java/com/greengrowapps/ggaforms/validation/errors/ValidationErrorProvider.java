package com.greengrowapps.ggaforms.validation.errors;

import com.greengrowapps.ggaforms.validation.validator.ValidationError;


public interface ValidationErrorProvider {

    ValidationError getValidationError( Class<? extends ValidationError> clazz);

}
