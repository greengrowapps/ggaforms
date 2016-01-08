package com.greengrowapps.ggaforms.validation.errors;


import android.content.res.Resources;

import com.greengrowapps.ggaforms.R;

public class NotCheckedValidationError extends AbstractLocalizedValidationError {
    public NotCheckedValidationError(Resources resources) {
        super(resources, R.string.notCheckedError);
    }
}
