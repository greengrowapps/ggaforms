package com.greengrowapps.ggaforms;

public class GGAForm {

    private GGAForm(){

    }

    public static FormBuilder start(){
        return new FormBuilderImpl();
    }
}
