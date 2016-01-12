package com.greengrowapps.ggaforms.validation.errors;

import android.content.res.Resources;

import com.greengrowapps.ggaforms.R;


public class ExceedsMinLengthValidationError extends AbstractLocalizedValidationError {

    public ExceedsMinLengthValidationError(Resources res, Object... params) {
        super(res, R.string.exceedsMinLength, params);
    }
}
