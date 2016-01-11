package com.greengrowapps.ggaforms.validation;


import com.greengrowapps.ggaforms.introspection.IntrospectedObject;
import com.greengrowapps.ggaforms.validation.annotations.MaxLength;
import com.greengrowapps.ggaforms.validation.annotations.NotNull;
import com.greengrowapps.ggaforms.validation.annotations.True;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProviderImpl;
import com.greengrowapps.ggaforms.validation.validator.MaxLengthValidator;
import com.greengrowapps.ggaforms.validation.validator.NotNullValidator;
import com.greengrowapps.ggaforms.validation.validator.TrueValidator;
import com.greengrowapps.ggaforms.validation.validator.ValidationResult;
import com.greengrowapps.ggaforms.validation.validator.ValueValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AnnotatedValidator implements TypedFormValidator{

    Map<String,ValueValidator> validatorMap = new HashMap<>();

    private AnnotatedValidator(ValidationErrorProvider errorProvider){
        validatorMap.put(NotNull.class.getCanonicalName(), new NotNullValidator(errorProvider));
        validatorMap.put(True.class.getCanonicalName(), new TrueValidator(errorProvider));
        validatorMap.put(MaxLength.class.getCanonicalName(), new MaxLengthValidator(errorProvider));
    }

    public static AnnotatedValidator newInstance(){
        return new AnnotatedValidator(ValidationErrorProviderImpl.getInstance());
    }

    @Override
    public ValidationResult validate(IntrospectedObject introspectedObject, ValidationResult result) {

        Object object = introspectedObject.getObject();

        return validateObject(introspectedObject, object, result);
    }

    private ValidationResult validateObject(IntrospectedObject introspectedObject, Object object, ValidationResult result) {
        if(object==null){
            return result;
        }
        Class clazz = object.getClass();

        for(Field field : clazz.getDeclaredFields()){
            for ( Annotation annotation : field.getAnnotations()){
                if(hasValidator(annotation)){
                    if(!validate(introspectedObject,field, object, annotation, result)){
                        break;
                    }
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
                    result = validateObject(introspectedObject, subObject, result);
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

    private boolean validate(IntrospectedObject introspectedObject, Field field, Object object, Annotation annotation, ValidationResult result) {
        field.setAccessible(true);
        try {
            Object value = field.get(object);
            ValueValidator validator = getValidator(annotation);
            validator.setFormInput(introspectedObject.getInputFrom(field));
            validator.setAnnotation(annotation);
            return validator.validate(value, result);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private ValueValidator getValidator(Annotation annotation) {
        return validatorMap.get(annotation.annotationType().getCanonicalName());
    }

    public AnnotatedValidator registerAnnotation(Class clazz, ValueValidator validator){
        validatorMap.put(clazz.getCanonicalName(), validator);
        return this;
    }
}
