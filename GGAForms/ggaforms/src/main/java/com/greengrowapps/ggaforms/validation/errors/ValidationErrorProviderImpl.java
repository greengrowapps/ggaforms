package com.greengrowapps.ggaforms.validation.errors;

import android.content.res.Resources;

import com.greengrowapps.ggaforms.validation.validator.ValidationError;

import java.util.HashMap;
import java.util.Map;


public class ValidationErrorProviderImpl implements ValidationErrorProvider {

    private static ValidationErrorProviderImpl instance;
    Map<Class<? extends ValidationError>, ValidationError> validationErrorMap = new HashMap<>();

    private ValidationErrorProviderImpl(Resources resources){
        registerErrorForClass(NullFieldValidationError.class, new NullFieldValidationError(resources));
        registerErrorForClass(NotCheckedValidationError.class, new NotCheckedValidationError(resources));
    }

    public static void init(Resources resources){
        instance = new ValidationErrorProviderImpl(resources);
    }

    public static boolean isInit(){
        return instance!=null;
    }

    public static ValidationErrorProviderImpl getInstance(){
        if(instance==null){
            throw new RuntimeException("Class must be initialized. Call init first");
        }
        return instance;
    }

    @Override
    public ValidationError getValidationError(Class<? extends ValidationError> clazz) {
        return validationErrorMap.get(clazz);
    }

    public void registerErrorForClass(Class<? extends ValidationError> clazz, ValidationError error){
        validationErrorMap.put(clazz,error);
    }
}
