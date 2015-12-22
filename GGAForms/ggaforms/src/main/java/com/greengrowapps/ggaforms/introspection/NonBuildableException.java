package com.greengrowapps.ggaforms.introspection;

public class NonBuildableException extends Exception {

    private final Class clazz;

    public NonBuildableException(Class clazz){
        this.clazz = clazz;
    }
}
