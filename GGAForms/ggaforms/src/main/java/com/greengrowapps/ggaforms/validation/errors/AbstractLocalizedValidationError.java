package com.greengrowapps.ggaforms.validation.errors;

import android.content.res.Resources;

public abstract class AbstractLocalizedValidationError extends ValidationErrorImpl {

    public AbstractLocalizedValidationError( Resources resources, int stringId, Object ... params ) {
        super(resources.getString( stringId, params ));
    }
}
