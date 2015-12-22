package com.greengrowapps.ggaforms.introspection;

import com.greengrowapps.ggaforms.validation.InputBundle;

public class IntrospectionToolsImpl implements IntrospectionTools {

    private AsignatorFactory asignatorFactory = new AsignatorFactory();

    @SuppressWarnings("TryWithIdenticalCatches")
    @Override
    public <T> IntrospectedObject<T> build(Class<T> clazz, InputBundle fields) throws NonBuildableException, FieldNotFoundException {

        try {
            return new IntrospectedObjectImpl<>(clazz.newInstance(), fields, asignatorFactory);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new NonBuildableException(clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NonBuildableException(clazz);
        }
    }
}
