package com.greengrowapps.ggaforms.introspection;

public class FieldNotFoundException extends Exception {

    private final String fieldName;

    FieldNotFoundException(String fieldName){
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
