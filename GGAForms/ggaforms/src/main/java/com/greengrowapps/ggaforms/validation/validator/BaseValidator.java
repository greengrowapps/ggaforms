package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;


public abstract class BaseValidator<T extends ValidationError> implements ValueValidator{

    private FormInput formInput;

    public BaseValidator(FormInput input){
        this.formInput = input;
    }

    @Override
    public boolean validate(Object parent, Object value, ValidationResult result) {
        if(!isValidValue(parent,value)){
            ValidationError validationError = getValidationError(value);
            result.appendError(validationError) ;
            populateError(validationError);
            return false;
        }
        else{
            populateNoError();
            return true;
        }
    }

    protected void populateNoError(){
        if(formInput!=null){
            formInput.setError(null);
        }
    }

    private void populateError(ValidationError validationError) {
        if(formInput!=null) {
            formInput.setError( validationError );
        }
    }

    protected abstract boolean isValidValue(Object parent,Object value);

    protected abstract ValidationError getValidationError(Object value);

    public FormInput getFormInput() {
        return formInput;
    }
}
