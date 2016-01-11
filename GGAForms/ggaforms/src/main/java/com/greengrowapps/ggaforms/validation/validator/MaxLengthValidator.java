package com.greengrowapps.ggaforms.validation.validator;

import android.content.res.Resources;

import com.greengrowapps.ggaforms.validation.annotations.MaxLength;
import com.greengrowapps.ggaforms.validation.annotations.MinLength;
import com.greengrowapps.ggaforms.validation.errors.ExceedsMaxLengthValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;

import java.lang.annotation.Annotation;


public class MaxLengthValidator extends ErrorProvidedValidator<ExceedsMaxLengthValidationError> {

    int maxLength = Integer.MAX_VALUE;

    public MaxLengthValidator(ValidationErrorProvider errorProvider) {
        super(errorProvider);
    }

    @Override
    protected boolean isValidValue(Object value) {

        if(value!=null && value instanceof CharSequence) {
            CharSequence toCheck = (CharSequence) value;
            return toCheck.length() <= maxLength;
        }

        return false;
    }

    @Override
    protected ValidationError getValidationError(Object value) {
        return getErrorProvider().getValidationError(ExceedsMaxLengthValidationError.class, maxLength);
    }

    @Override
    protected Class<? extends ValidationError> getErrorClass() {
        return ExceedsMaxLengthValidationError.class;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        super.setAnnotation(annotation);
        maxLength = ((MaxLength)annotation).length();
    }
}
