package com.greengrowapps.ggaforms;


import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.listeners.OnSimpleFormListener;
import com.greengrowapps.ggaforms.validation.SimpleFormValidator;

public interface SimpleForm {
    SimpleForm addValidator(SimpleFormValidator validator);
    SimpleForm clearValidators();
    SimpleForm setOnValidListener(OnSimpleFormListener listener);

    FormInput getField(String propertyName);

    boolean isValid();
}
