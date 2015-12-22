package com.greengrowapps.ggaforms.introspection;


import java.lang.reflect.Field;
import java.security.InvalidParameterException;

public class SimpleAssignator<S,T> implements Asignator<S,T> {

    private final Field field;
    private S object;

    public SimpleAssignator(S object, String fieldName) throws FieldNotFoundException {
        this.object = object;
        Class<?> clazz = object.getClass();
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new FieldNotFoundException(fieldName);
        }
    }

    @Override
    public void assignValue( T value ) {
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setTarget(S target) {
        if( !object.getClass().isAssignableFrom(target.getClass()) ){
            throw new InvalidParameterException( object.getClass().getCanonicalName()+" is not assignable from "+target.getClass().getCanonicalName());
        }
        this.object = target;
    }
}
