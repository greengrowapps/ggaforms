package com.greengrowapps.ggaforms.validation;


import com.greengrowapps.ggaforms.validation.annotations.NotNull;
import com.greengrowapps.ggaforms.validation.annotations.True;
import com.greengrowapps.ggaforms.validation.validator.NotNullValidator;
import com.greengrowapps.ggaforms.validation.validator.TrueValidator;
import com.greengrowapps.ggaforms.validation.validator.ValidationResult;
import com.greengrowapps.ggaforms.validation.validator.ValueValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AnnotatedValidator<T> implements TypedFormValidator<T>{

    Map<String,ValueValidator> validatorMap = new HashMap<>();

    private AnnotatedValidator(){
        validatorMap.put(NotNull.class.getCanonicalName(), new NotNullValidator());
        validatorMap.put(True.class.getCanonicalName(), new TrueValidator());
    }

    public static <T> AnnotatedValidator<T> buildFor(Class<T> clazz){
        return new AnnotatedValidator<>();
    }

    @Override
    public ValidationResult validate(Object object, ValidationResult result) {

        if(object==null){
            return result;
        }
        Class clazz = object.getClass();

        for(Field field : clazz.getDeclaredFields()){
            for ( Annotation annotation : field.getAnnotations()){
                if(hasValidator(annotation)){
                    validate(field, object, annotation, result);
                }
            }
            Class fieldClass = field.getType();
            if(!fieldClass.isPrimitive() &&
                    !fieldClass.equals(String.class) &&
                    !fieldClass.equals(Boolean.class) &&
                    !fieldClass.equals(Integer.class) &&
                    !fieldClass.equals(Long.class) &&
                    !fieldClass.equals(Float.class) &&
                    !fieldClass.equals(Double.class) &&
                    !fieldClass.equals(Object.class)){
                try {
                    field.setAccessible(true);
                    Object subObject = field.get(object);
                    result = validate(subObject,result);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    private boolean hasValidator(Annotation annotation) {
        String annotationClass = annotation.annotationType().getCanonicalName();
        return validatorMap.containsKey(annotationClass);
    }

    private void validate(Field field, Object object, Annotation annotation, ValidationResult result) {
        field.setAccessible(true);
        try {
            Object value = field.get(object);
            getValidator(annotation).validate(value, result);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private ValueValidator getValidator(Annotation annotation) {
        return validatorMap.get(annotation.annotationType().getCanonicalName());
    }
}
