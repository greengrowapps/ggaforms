package com.greengrowapps.ggaforms.validation.validator;


import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.introspection.IntrospectedObject;
import com.greengrowapps.ggaforms.validation.TypedFormValidator;
import com.greengrowapps.ggaforms.validation.annotations.MaxLength;
import com.greengrowapps.ggaforms.validation.annotations.MinLength;
import com.greengrowapps.ggaforms.validation.annotations.NotNull;
import com.greengrowapps.ggaforms.validation.annotations.Regex;
import com.greengrowapps.ggaforms.validation.annotations.True;
import com.greengrowapps.ggaforms.validation.annotations.Twin;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProviderImpl;
import com.greengrowapps.ggaforms.validation.validator.regex.RegexProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AnnotatedValidator implements TypedFormValidator {

    Map<String,ValidatorProvider> validatorMap = new HashMap<>();

    private AnnotatedValidator(final ValidationErrorProvider errorProvider){
        validatorMap.put(NotNull.class.getCanonicalName(), new ValidatorProvider() {
            @Override
            public ValueValidator buildValidator(Annotation annotation, FormInput input) {
                return new NotNullValidator(input,errorProvider);
            }
        });
        validatorMap.put(True.class.getCanonicalName(), new ValidatorProvider() {
            @Override
            public ValueValidator buildValidator(Annotation annotation, FormInput input) {
                return new TrueValidator(input,errorProvider);
            }
        });
        validatorMap.put(MaxLength.class.getCanonicalName(), new ValidatorProvider() {
            @Override
            public ValueValidator buildValidator(Annotation annotation, FormInput input) {
                return new MaxLengthValidator(input,errorProvider,((MaxLength)annotation).length());
            }
        });
        validatorMap.put(MinLength.class.getCanonicalName(), new ValidatorProvider() {
            @Override
            public ValueValidator buildValidator(Annotation annotation, FormInput input) {
                return new MinLengthValidator(input,errorProvider,((MinLength)annotation).length());
            }
        });
        validatorMap.put(Twin.class.getCanonicalName(), new ValidatorProvider() {
            @Override
            public ValueValidator buildValidator(Annotation annotation, FormInput input) {
                return new TwinValidator(input,errorProvider,((Twin)annotation).id());
            }
        });
        validatorMap.put(Regex.class.getCanonicalName(), new ValidatorProvider() {
            @Override
            public ValueValidator buildValidator(Annotation annotation, FormInput input) {
                String key = ((Regex)annotation).key();
                return RegexProvider.getInstance().getValidator(key,input);

            }
        });

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
            ValueValidator validator = getValidator(annotation,introspectedObject.getInputFrom(field));

            return validator.validate(object, value, result);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private ValueValidator getValidator(Annotation annotation, FormInput input) {
        return validatorMap.get(annotation.annotationType().getCanonicalName()).buildValidator(annotation,input);
    }

    public AnnotatedValidator registerAnnotation(Class clazz, ValidatorProvider validator){
        validatorMap.put(clazz.getCanonicalName(), validator);
        return this;
    }
}
