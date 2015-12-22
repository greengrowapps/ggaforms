package com.greengrowapps.ggaforms.introspection;

public class AsignatorFactory {


    public <S,T> Asignator<S,T> buildForType(Class<T> type, String property, S target) throws FieldNotFoundException {

        return new SimpleAssignator<S,T>(target,property);
    }
}
