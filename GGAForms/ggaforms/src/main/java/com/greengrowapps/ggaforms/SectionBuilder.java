package com.greengrowapps.ggaforms;


import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.listeners.OnValidSectionListener;

public interface SectionBuilder {
    SectionBuilder appendField(String propertyName, FormInput formInput);
    SectionBuilder listenValidity(OnValidSectionListener listener);
    FormInput build();
}
