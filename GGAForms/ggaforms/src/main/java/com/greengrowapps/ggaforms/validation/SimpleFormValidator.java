package com.greengrowapps.ggaforms.validation;

import com.greengrowapps.ggaforms.validation.validator.ValidationResult;

public interface SimpleFormValidator {

    ValidationResult validate( InputBundle fields, ValidationResult result );

}
