package com.greengrowapps.ggaforms;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.fields.SectionFormInput;
import com.greengrowapps.ggaforms.listeners.OnValidSectionListener;
import com.greengrowapps.ggaforms.validation.InputBundle;


public class SectionBuilderImpl implements SectionBuilder {

    private InputBundle fields = new InputBundle();
    private OnValidSectionListener listener;

    @Override
    public SectionBuilder appendField(String propertyName, FormInput formInput) {
        fields.put(propertyName, formInput);
        return this;
    }

    @Override
    public SectionBuilder listenValidity(OnValidSectionListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public FormInput build() {
        return new SectionFormInput(fields, listener);
    }
}
