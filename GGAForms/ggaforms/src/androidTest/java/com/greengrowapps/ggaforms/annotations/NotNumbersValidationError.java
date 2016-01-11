package com.greengrowapps.ggaforms.annotations;

import com.greengrowapps.ggaforms.validation.errors.ValidationError;

public class NotNumbersValidationError implements ValidationError {
    @Override
    public CharSequence getLocalizedMessage() {
        return "Must be only numbers";
    }
}
