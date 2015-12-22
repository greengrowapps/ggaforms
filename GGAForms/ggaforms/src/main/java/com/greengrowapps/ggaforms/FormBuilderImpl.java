package com.greengrowapps.ggaforms;


import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.introspection.IntrospectionToolsImpl;
import com.greengrowapps.ggaforms.validation.InputBundle;

public class FormBuilderImpl implements FormBuilder{

    InputBundle fields = new InputBundle();

    @Override
    public FormBuilder appendField(String propertyName, FormInput formInput) {
        fields.put(propertyName, formInput);
        return this;
    }

    @Override
    public SimpleForm buildSimple() {
        return new SimpleFormImpl(fields);
    }

    @Override
    public <T> TypedForm<T> buildTyped(Class<T> clazz) {
        return new TypedFormImpl<T>(fields,clazz, new IntrospectionToolsImpl());
    }
}
