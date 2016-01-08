package com.greengrowapps.ggaforms.validation;


import com.greengrowapps.ggaforms.fields.FormInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InputBundle {

    Map<String,FormInput> fieldMap = new HashMap<>();

    public void put(String propertyName, FormInput formInput) {
        fieldMap.put(propertyName, formInput);
    }

    public Set<String> getPropertiesNames() {
        return fieldMap.keySet();
    }

    public FormInput getInputNamed(String key) {
        return fieldMap.get(key);
    }

    public List<FormInput> getFields() {
        return new ArrayList<FormInput>(fieldMap.values());
    }
}
