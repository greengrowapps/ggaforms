package com.greengrowapps.ggaforms.validation.validator;

public class TrueValidator extends BaseValidator {
    @Override
    protected boolean isValidValue(Object value) {
        return ((Boolean) value);
    }
}
