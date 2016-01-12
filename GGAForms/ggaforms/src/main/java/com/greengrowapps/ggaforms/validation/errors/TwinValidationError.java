package com.greengrowapps.ggaforms.validation.errors;

import android.content.res.Resources;

import com.greengrowapps.ggaforms.R;

public class TwinValidationError extends AbstractLocalizedValidationError{
    public TwinValidationError(Resources resources) {
        super(resources, R.string.fieldsAreNotEqual);
    }
}
