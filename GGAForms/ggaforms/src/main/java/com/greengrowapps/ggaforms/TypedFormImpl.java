package com.greengrowapps.ggaforms;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.introspection.FieldNotFoundException;
import com.greengrowapps.ggaforms.introspection.IntrospectedObject;
import com.greengrowapps.ggaforms.introspection.IntrospectionTools;
import com.greengrowapps.ggaforms.introspection.NonBuildableException;
import com.greengrowapps.ggaforms.listeners.OnValidTypedFormListener;
import com.greengrowapps.ggaforms.validation.InputBundle;
import com.greengrowapps.ggaforms.validation.TypedFormValidator;
import com.greengrowapps.ggaforms.validation.validator.ValidationResult;
import com.greengrowapps.ggaforms.validation.validator.ValidationResultImpl;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;


public class TypedFormImpl<T> extends SimpleFormImpl implements TypedForm<T> {

    private final IntrospectedObject<T> introspectedObject;
    private final Set<TypedFormValidator<T>> validators = new HashSet<>();
    private OnValidTypedFormListener<T> listener;

    protected TypedFormImpl(InputBundle fields, Class<T> clazz, IntrospectionTools tools){
        super(fields);
        try {
            introspectedObject = tools.build(clazz,fields);
        } catch (NonBuildableException e) {
            throw new InvalidParameterException("Typed form need to use a class with an empty constructor");
        } catch (FieldNotFoundException e) {
            throw new InvalidParameterException("Not found object field named "+e.getFieldName());
        }
    }

    @Override
    public TypedForm<T> addValidator(TypedFormValidator<T> validator) {
        validators.add(validator);
        validate();
        return this;
    }

    @Override
    public synchronized TypedForm<T> setOnValidListener(OnValidTypedFormListener<T> listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void setObject(T object) {
        introspectedObject.setTarget(object);
    }

    @Override
    public T getObject() {
        return introspectedObject.getObject();
    }

    @Override
    protected  boolean isValidApplyingValidators() {
        ValidationResult result = new ValidationResultImpl();
        for(TypedFormValidator<T> validator : validators){
            result = ( validator.validate(introspectedObject.getObject(),result) );
        }

        return result.isValid() && super.isValidApplyingValidators();
    }

    @Override
    protected synchronized void onFieldChanged(String key, FormInput formInput) {
        introspectedObject.assignValue(key, formInput.getValue());
        super.onFieldChanged(key, formInput);
    }

    @Override
    protected synchronized void notifyListener() {
        super.notifyListener();
        if(listener!=null){
            if(isValid()){
                listener.onFormValid( this , getObject() );
            }
            else{
                listener.onFormInvalid( this );
            }
        }
    }
}
