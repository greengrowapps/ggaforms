package com.greengrowapps.ggaforms.validation.validator;

public class NotNullValidator extends BaseValidator {
    @Override
    protected boolean isValidValue(Object value) {
        return value!=null;
    }
}
