package com.greengrowapps.ggaforms.validation.validator;

import java.util.List;


public interface ValidationResult {

    List<ValidationError> getErrors();
    void appendError(ValidationError error);
    boolean isValid();

}
