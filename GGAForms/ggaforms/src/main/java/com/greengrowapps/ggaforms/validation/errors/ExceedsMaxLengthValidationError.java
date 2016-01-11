package com.greengrowapps.ggaforms.validation.errors;

import android.content.res.Resources;

import com.greengrowapps.ggaforms.R;


public class ExceedsMaxLengthValidationError extends AbstractLocalizedValidationError {

    public ExceedsMaxLengthValidationError(Resources res, Object ... params) {
        super(res, R.string.exceedsMaxLenght, params);
    }
}
