package com.greengrowapps.ggaforms.validation.errors;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.Map;


public class ValidationErrorProviderImpl implements ValidationErrorProvider {

    private static ValidationErrorProviderImpl instance;
    private final Resources resources;
    private Map<Class<? extends ValidationError>, ErrorBuilder> validationErrorMap = new HashMap<>();

    private ValidationErrorProviderImpl( Resources res ){
        this.resources = res;
        registerErrorForClass(NullFieldValidationError.class, new ErrorBuilder() {
            @Override
            public ValidationError build( Object... params ) {
                return new NullFieldValidationError(resources);
            }
        });
        registerErrorForClass(NotCheckedValidationError.class, new ErrorBuilder() {
            @Override
            public ValidationError build( Object... params ) {
                return new NotCheckedValidationError(resources);
            }
        });
        registerErrorForClass(ExceedsMaxLengthValidationError.class, new ErrorBuilder() {
            @Override
            public ValidationError build( Object... params ) {
                return new ExceedsMaxLengthValidationError(resources, params);
            }
        });
        registerErrorForClass(ExceedsMinLengthValidationError.class, new ErrorBuilder() {
            @Override
            public ValidationError build( Object... params ) {
                return new ExceedsMinLengthValidationError(resources, params);
            }
        });
        registerErrorForClass(TwinValidationError.class, new ErrorBuilder() {
            @Override
            public ValidationError build( Object... params ) {
                return new TwinValidationError(resources);
            }
        });
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
    public ValidationError getValidationError(Class<? extends ValidationError> clazz, Object ... params) {
        return validationErrorMap.get(clazz).build(params);
    }

    public void registerErrorForClass(Class<? extends ValidationError> clazz, ErrorBuilder error){
        validationErrorMap.put(clazz,error);
    }
}
