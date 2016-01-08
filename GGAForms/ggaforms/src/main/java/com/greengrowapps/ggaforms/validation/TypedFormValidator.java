package com.greengrowapps.ggaforms.validation;

import com.greengrowapps.ggaforms.introspection.IntrospectedObject;
import com.greengrowapps.ggaforms.validation.validator.ValidationResult;

public interface TypedFormValidator {

    ValidationResult validate(IntrospectedObject object, ValidationResult result);

}
