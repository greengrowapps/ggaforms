package com.greengrowapps.ggaforms.validation.validator;


public interface ValueValidator {
    boolean validate(Object parent, Object value, ValidationResult result);
}
