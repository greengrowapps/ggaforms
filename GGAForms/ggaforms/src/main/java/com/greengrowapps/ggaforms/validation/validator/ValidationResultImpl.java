package com.greengrowapps.ggaforms.validation.validator;

import java.util.ArrayList;
import java.util.List;


public class ValidationResultImpl implements ValidationResult {
    final List<ValidationError> errors = new ArrayList<>();

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }

    @Override
    public void appendError(ValidationError error) {
        errors.add(error);
    }

    @Override
    public boolean isValid() {
        return errors.isEmpty();
    }
}
