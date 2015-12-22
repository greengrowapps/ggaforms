package com.greengrowapps.ggaforms;

import com.greengrowapps.ggaforms.fields.FormInput;

public interface FormBuilder {

    FormBuilder appendField(String propertyName, FormInput formInput);

    SimpleForm buildSimple();
    <T> TypedForm<T> buildTyped(Class<T> clazz);

}
