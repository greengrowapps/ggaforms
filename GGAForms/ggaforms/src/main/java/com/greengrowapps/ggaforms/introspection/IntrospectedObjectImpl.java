package com.greengrowapps.ggaforms.introspection;

import com.greengrowapps.ggaforms.fields.FormInput;
import com.greengrowapps.ggaforms.validation.InputBundle;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class IntrospectedObjectImpl<T> implements IntrospectedObject<T> , Asignator<T,InputBundle>{

    private T object;
    private final Map<String,Asignator> asignatorMap = new HashMap<>();
    private final InputBundle fields;

    public IntrospectedObjectImpl(T object, InputBundle fields, AsignatorFactory asignatorFactory) throws FieldNotFoundException, NonBuildableException {

        this.object = object;
        this.fields = fields;

        for(String property : fields.getPropertiesNames() ){

            FormInput formInput = fields.getInputNamed(property);

            if(formInput.getType().isAssignableFrom(InputBundle.class)){
                Object subObject = getObjectProperty(property);
                asignatorMap.put(property, new IntrospectedObjectImpl(subObject, (InputBundle) formInput.getValue(), asignatorFactory));
            }
            else{
                asignatorMap.put( property, asignatorFactory.buildForType( formInput.getType(), property, object));
            }
        }
    }

    private Object getObjectProperty(String property) throws FieldNotFoundException, NonBuildableException {

        Class subClass = null;

        try {

            Class<?> clazz = object.getClass();
            Field field = clazz.getDeclaredField(property);
            field.setAccessible(true);
            Object value = field.get(object);
            if(value==null){
                subClass = field.getType();
                value = subClass.newInstance();
                field.set(object,value);
            }
            return value;

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new FieldNotFoundException(property);
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

                if(assignator instanceof IntrospectedObject){
                    asignatorMap.get(property).setTarget( getObjectProperty(property) );
                }
                else{
                    asignatorMap.get(property).setTarget(target);
                    fields.getInputNamed(property).setValue( getObjectProperty(property) );
                }
            }
        } catch (FieldNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NonBuildableException e) {
            throw new RuntimeException(e);
        }
    }
}
