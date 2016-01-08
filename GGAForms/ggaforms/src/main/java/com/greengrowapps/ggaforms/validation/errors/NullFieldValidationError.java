package com.greengrowapps.ggaforms.validation.errors;

import android.content.res.Resources;

import com.greengrowapps.ggaforms.R;

public class NullFieldValidationError extends AbstractLocalizedValidationError {

    public NullFieldValidationError(Resources resources) {
        super(resources, R.string.fillThisField);
    }
}
