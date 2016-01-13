package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.errors.ErrorBuilder;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;

import java.util.regex.Pattern;


public class RegexValidator extends BaseValidator {

    private final String regex;
    private final ErrorBuilder errorBuilder;

    public RegexValidator(FormInput input, ErrorBuilder errorBuilder, String regex) {
        super(input);
        this.regex = regex;
        this.errorBuilder = errorBuilder;
    }

    @Override
    protected boolean isValidValue(Object parent, Object value) {
        if(value instanceof CharSequence){
            String string = ((CharSequence)value).toString();
            return Pattern.matches(regex, string);
        }
        return false;
    }

    @Override
    protected ValidationError getValidationError(Object value) {
        return errorBuilder.build();
    }

}
