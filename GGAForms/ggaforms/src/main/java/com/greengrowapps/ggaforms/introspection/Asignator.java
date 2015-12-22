package com.greengrowapps.ggaforms.introspection;

public interface Asignator<S,T> {
    void assignValue(T value);
    void setTarget(S target);
}
