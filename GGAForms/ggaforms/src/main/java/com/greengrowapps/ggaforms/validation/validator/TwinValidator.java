package com.greengrowapps.ggaforms.validation.validator;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.annotations.Twin;
import com.greengrowapps.ggaforms.validation.errors.NullFieldValidationError;
import com.greengrowapps.ggaforms.validation.errors.TwinValidationError;
import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProvider;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwinValidator extends ErrorProvidedValidator<TwinValidationError> {

    private static final Map<Object,Map<String,List<FormInput>>> twinMap = new HashMap<>();
    private String id;

    public TwinValidator(FormInput input, ValidationErrorProvider errorProvider, String id) {
        super(input,errorProvider);
        this.id = id;
    }

    @Override
    protected boolean isValidValue(Object parent, Object value) {

        List<FormInput> twinValidators = getCurrentIdMap(parent);

        if(!twinValidators.contains(getFormInput())){
            twinValidators.add(getFormInput());
        }

        boolean result = isValid(twinValidators);

        if(isThisLast(twinValidators)){
            if(result){
                populateMissingSuccess(twinValidators);
            }
            else{
                populateOtherErrors(twinValidators);
            }
        }

        return result;

    }


    private boolean isThisLast(List<FormInput> currentIdMap) {
        return currentIdMap.size()-1 == currentIdMap.indexOf(this);
    }

    private void populateMissingSuccess(List<FormInput> twinValidators) {
        for(int i=0; i<twinValidators.size() - 1; i++) {
            FormInput input = twinValidators.get(i);
            if(input.getError()!=null){
                input.setError(null);
                input.setValue(input.getValue());
            }
        }
    }

    private void populateOtherErrors(List<FormInput> twinValidators) {
        for(int i=0; i<twinValidators.size() - 1; i++) {
            twinValidators.get(i).setError(getValidationError(null));
        }
    }

    private boolean isValid(List<FormInput> twinValidators) {
        if(twinValidators.size()<2){
            return true;
        }

        Object lastValue = twinValidators.get(0).getValue();
        for(int i=1; i<twinValidators.size(); i++ ){
            Object value = twinValidators.get(i).getValue();
            if(!isEquals(lastValue,value)){
                return false;
            }
            lastValue = value;
        }
        return true;
    }

    private boolean isEquals(Object value1, Object value2) {
        if(value1==null){
            return value2==null;
        }
        return value1.equals(value2);
    }


    private List<FormInput> getCurrentIdMap(Object parent) {
        Map<String, List<FormInput>> objMap = getCurrentObjMap(parent);

        if(!objMap.containsKey(id)){
            objMap.put(id,new ArrayList<FormInput>());
        }

        return objMap.get(id);
    }

    private Map<String, List<FormInput>> getCurrentObjMap(Object parent) {
        Map<String,List<FormInput>> currentObjMap = twinMap.get(parent);
        if(currentObjMap==null){
            currentObjMap = new HashMap<>();
            twinMap.put(parent,currentObjMap);
        }
        return currentObjMap;
    }

    @Override
    protected Class<TwinValidationError> getErrorClass() {
        return TwinValidationError.class;
    }

}
