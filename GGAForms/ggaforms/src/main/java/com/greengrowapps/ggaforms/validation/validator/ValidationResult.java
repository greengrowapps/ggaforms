package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.validation.errors.ValidationError;

import java.util.List;


public interface ValidationResult {

    List<ValidationError> getErrors();
    void appendError(ValidationError error);
    boolean isValid();

}
