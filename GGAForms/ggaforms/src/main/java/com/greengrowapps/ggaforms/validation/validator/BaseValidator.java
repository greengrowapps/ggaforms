package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;

import java.lang.annotation.Annotation;

public abstract class BaseValidator<T extends ValidationError> implements ValueValidator{

    private Annotation annotation;
    private FormInput formInput;

    @Override
    public boolean validate(Object value, ValidationResult result) {
        if(!isValidValue(value)){
            ValidationError validationError = getValidationError(value);
            result.appendError( validationError ) ;
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

    protected abstract boolean isValidValue(Object value);

    protected abstract ValidationError getValidationError(Object value);

    public void setFormInput(FormInput formInput){
        this.formInput = formInput;
    }

    public FormInput getFormInput() {
        return formInput;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Annotation getAnnotation() {
        return annotation;
    }
}
