package com.greengrowapps.ggaformsui.common;


import com.greengrowapps.ggaforms.validation.errors.ValidationError;

public interface DisplayErrorListener {
    void onHideError();
    void onShowError(ValidationError currentError);
}
