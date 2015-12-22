package com.greengrowapps.ggaforms.validation;

import com.greengrowapps.ggaforms.validation.validator.ValidationResult;

public interface TypedFormValidator<T> {

    ValidationResult validate(T object, ValidationResult result);

}
