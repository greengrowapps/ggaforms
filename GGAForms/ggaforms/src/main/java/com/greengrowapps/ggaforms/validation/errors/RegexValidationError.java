package com.greengrowapps.ggaforms.validation.errors;


import android.content.res.Resources;

import java.security.InvalidParameterException;

public class RegexValidationError extends ValidationErrorImpl{

    public RegexValidationError(CharSequence localizedMessage) {
        super(localizedMessage);
    }

    public static ValidationError buildFrom(Resources resources, Object[] params) {
        if(params.length<1 || !(params[0] instanceof Integer)){
            throw new InvalidParameterException("Params must be an integer with the resource id");
        }
        return new RegexValidationError(resources.getString((int)params[0]));
    }
}
