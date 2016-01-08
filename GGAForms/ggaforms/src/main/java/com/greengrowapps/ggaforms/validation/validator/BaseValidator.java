package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.fields.FormInput;

public abstract class BaseValidator<T extends ValidationError> implements ValueValidator{

    private FormInput formInput;


    @Override
    public void validate(Object value, ValidationResult result) {
        if(!isValidValue(value)){
            ValidationError validationError = getValidationError(value);
            result.appendError( validationError ) ;
            populateError(validationError);
        }
        else{
            populateNoError();
        }
    }

    protected void populateNoError(){
        if(formInput!=null){
            formInput.setError(null);
        }
    }

    private void populateError(ValidationError validationError) {
        if(formInput!=null) {
            formInput.setError(validationError.getLocalizedMessage());
        }
    }

    protected abstract boolean isValidValue(Object value);

    protected abstract ValidationError getValidationError(Object value);

    public void setFormInput(FormInput formInput){
        this.formInput = formInput;
    }

    public FormInput getFormInput() {
        return formInput;
    }
}
