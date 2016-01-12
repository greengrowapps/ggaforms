package com.greengrowapps.ggaforms.validation.validator;


import com.greengrowapps.ggaforms.fields.FormInput;

import java.lang.annotation.Annotation;

public interface ValidatorProvider {
    ValueValidator buildValidator(Annotation annotation, FormInput input);
}
