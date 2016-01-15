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
            if(field.getType().isPrimitive()){
                assignPrimitiveValue(value);
            }
            else {
                field.set(object, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void assignPrimitiveValue(T value) throws IllegalAccessException {
        if(value instanceof Boolean){
            field.setBoolean(object, (Boolean) value);
        }
        else if(value instanceof Integer){
            field.setInt(object, (Integer) value);
        }
        else if(value instanceof Long){
            field.setLong(object, (Long) value);
        }
        else if(value instanceof Float){
            field.setFloat(object, (Float) value);
        }
        else if(value instanceof Double){
            field.setDouble(object, (Double) value);
        }
        else if(value instanceof Character){
            field.setChar(object, (Character) value);
        }
        else if(value instanceof Byte){
            field.setByte(object, (Byte) value);
        }
        else if(value instanceof Short){
            field.setShort(object, (Short) value);
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
