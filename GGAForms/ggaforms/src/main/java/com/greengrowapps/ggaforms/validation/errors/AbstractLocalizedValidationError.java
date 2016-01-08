package com.greengrowapps.ggaforms.validation.errors;

import android.content.res.Resources;

import com.greengrowapps.ggaforms.R;
import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.validator.ValidationErrorImpl;

public abstract class AbstractLocalizedValidationError extends ValidationErrorImpl {

    public AbstractLocalizedValidationError( Resources resources, int stringId ) {
        super(resources.getString( stringId ));
    }
}
