package com.greengrowapps.ggaforms.introspection;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.InputBundle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntrospectedObjectImpl<T> implements IntrospectedObject<T> , Asignator<T,InputBundle>{

    private T object;
    private final Map<String,Asignator> asignatorMap = new HashMap<>();
    private final Map<Field,FormInput> inputMap = new HashMap<>();
    private final InputBundle inputs;
    private final List<IntrospectedObjectImpl> innerObjects = new ArrayList<>();

    public IntrospectedObjectImpl(T object, InputBundle inputs, AsignatorFactory asignatorFactory) throws FieldNotFoundException, NonBuildableException {

        this.object = object;
        this.inputs = inputs;

        for(String property : inputs.getPropertiesNames() ){

            FormInput formInput = inputs.getInputNamed(property);
            Field field = getFieldNamed(property);
            inputMap.put(field, formInput);

            if(formInput.getType().isAssignableFrom(InputBundle.class)){

                Object subObject = getObjectFromField(field);
                IntrospectedObjectImpl introspectedObject = new IntrospectedObjectImpl(subObject, (InputBundle) formInput.getValue(), asignatorFactory);
                innerObjects.add(introspectedObject);
                asignatorMap.put(property, introspectedObject);
            }
            else{
                asignatorMap.put( property, asignatorFactory.buildForType( formInput.getType(), property, object));
            }
        }
    }

    private Field getFieldNamed(String property) throws FieldNotFoundException {
        Class<?> clazz = object.getClass();
        try {
            return clazz.getDeclaredField(property);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new FieldNotFoundException(property);
        }
    }

    private Object getObjectFromField(Field field) throws FieldNotFoundException, NonBuildableException {

        Class subClass = null;

        try {
            field.setAccessible(true);
            Object value = field.get(object);
            if(value==null){
                subClass = field.getType();
                value = subClass.newInstance();
                field.set(object,value);
            }
            return value;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new NonBuildableException(subClass);
        }
    }

    @Override
    public T getObject() {
        return object;
    }

    @Override
    public void assignValue(String property, Object value) {
        Asignator asignator = asignatorMap.get(property);
        asignator.assignValue(value);
    }

    public void setObject(T object){

    }

    @Override
    public void assignValue(InputBundle value) {
        for(String property : asignatorMap.keySet()){
            asignatorMap.get(property).assignValue(value.getInputNamed(property).getValue());
        }
    }

    @Override
    public void setTarget(T target) {
        this.object = target;
        try {
            for(String property : asignatorMap.keySet()){
                Asignator assignator = asignatorMap.get(property);
                Object objField = getObjectFromField(getFieldNamed(property));

                if(assignator instanceof IntrospectedObject){
                    asignatorMap.get(property).setTarget( objField );
                }
                else{
                    asignatorMap.get(property).setTarget(target);
                    inputs.getInputNamed(property).setValue( objField );
                }
            }
        } catch (FieldNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NonBuildableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FormInput getInputFrom(Field field) {
        FormInput input = inputMap.get(field);
        if(input!=null){
            return input;
        }
        for(IntrospectedObject inner : innerObjects){
            input = inner.getInputFrom(field);
            if(input!=null){
                return input;
            }
        }
        return null;
    }
}
