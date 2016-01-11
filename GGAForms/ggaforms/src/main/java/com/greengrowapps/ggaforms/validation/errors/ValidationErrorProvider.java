package com.greengrowapps.ggaforms.validation.errors;


public interface ValidationErrorProvider {

    ValidationError getValidationError( Class<? extends ValidationError> clazz, Object ... params);

}
