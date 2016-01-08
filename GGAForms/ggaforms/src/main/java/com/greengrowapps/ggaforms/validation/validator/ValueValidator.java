package com.greengrowapps.ggaforms.validation.validator;


import com.greengrowapps.ggaforms.fields.FormInput;

public interface ValueValidator {
    void validate(Object value, ValidationResult result);
    void setFormInput(FormInput input);
}
