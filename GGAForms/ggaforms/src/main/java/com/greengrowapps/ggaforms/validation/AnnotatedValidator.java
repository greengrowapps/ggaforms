package com.greengrowapps.ggaforms.validation;


import com.greengrowapps.ggaforms.validation.validator.ValidationResult;
import com.greengrowapps.ggaforms.validation.validator.ValueValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AnnotatedValidator<T> implements TypedFormValidator<T>{

    Map<Class,ValueValidator> validatorMap = new HashMap<>();

    private AnnotatedValidator(){

    }

    public static <T> AnnotatedValidator<T> buildFor(Class<T> clazz){
        return new AnnotatedValidator<>();
    }

    @Override
    public ValidationResult validate(T object, ValidationResult result) {

        Class clazz = object.getClass();

        for(Field field : clazz.getDeclaredFields()){
            for ( Annotation annotation : field.getAnnotations()){
                if(hasValidator(annotation)){
                    validate(field, object, annotation, result);
                }
            }
        }
        return result;
    }

    private boolean hasValidator(Annotation annotation) {
        return validatorMap.containsKey(annotation.getClass());
    }

    private void validate(Field field, T object, Annotation annotation, ValidationResult result) {
        field.setAccessible(true);
        try {
            Object value = field.get(object);
            validatorMap.get(annotation.getClass()).validate(value,result);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
