package com.greengrowapps.ggaforms;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.listeners.OnInputChangedListener;
import com.greengrowapps.ggaforms.listeners.OnSimpleFormListener;
import com.greengrowapps.ggaforms.validation.InputBundle;
import com.greengrowapps.ggaforms.validation.SimpleFormValidator;
import com.greengrowapps.ggaforms.validation.validator.ValidationResult;
import com.greengrowapps.ggaforms.validation.validator.ValidationResultImpl;

import java.util.HashSet;
import java.util.Set;

public class SimpleFormImpl implements SimpleForm {

    private final InputBundle fields;
    private final Set<SimpleFormValidator> validators = new HashSet<>();
    private boolean valid = true;
    private OnSimpleFormListener listener;

    protected SimpleFormImpl(InputBundle fields){
        this.fields = fields;
        for(final String key : fields.getPropertiesNames()){
            fields.getInputNamed(key).setOnInputChangedListener(new OnInputChangedListener() {
                @Override
                public void onFieldChanged(FormInput formInput) {
                    SimpleFormImpl.this.onFieldChanged(key, formInput);
                }
            });
        }
    }

    protected boolean validate() {
        ValidationResult result = new ValidationResultImpl();
        for(SimpleFormValidator validator : validators){
            result = ( validator.validate(fields,result) );
        }
        return result.isValid();
    }

    private void onValidityChanged() {
        notifyListener();
    }

    protected synchronized void notifyListener() {
        if(listener!=null){
            if(valid){
                listener.onFormValid(this);
            }
            else{
                listener.onFormInvalid(this);
            }
        }
    }

    @Override
    public SimpleForm addValidator(SimpleFormValidator validator) {
        validators.add(validator);
        return this;
    }

    @Override
    public SimpleForm clearValidators() {
        validators.clear();
        return this;
    }

    @Override
    public synchronized SimpleForm setOnValidListener(OnSimpleFormListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public FormInput getField(String propertyName) {
        return fields.getInputNamed(propertyName);
    }

    protected synchronized void onFieldChanged(String property, FormInput formInput) {
        boolean newValid = validate();
        if(valid!=newValid){
            valid=newValid;
            onValidityChanged();
        }
    }

    @Override
    public synchronized boolean isValid() {
        return valid;
    }
}
