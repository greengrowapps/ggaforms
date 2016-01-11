package com.greengrowapps.ggaforms.validation.validator;


import com.greengrowapps.ggaforms.fields.FormInput;

import java.lang.annotation.Annotation;

public interface ValueValidator {
    boolean validate(Object value, ValidationResult result);
    void setFormInput(FormInput input);
    void setAnnotation(Annotation annotation);
}
