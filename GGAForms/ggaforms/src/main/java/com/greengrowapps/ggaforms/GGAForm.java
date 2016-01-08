package com.greengrowapps.ggaforms;

import android.content.Context;

import com.greengrowapps.ggaforms.validation.errors.ValidationErrorProviderImpl;

public class GGAForm {

    private GGAForm(){

    }

    public static FormBuilder startWithContext(Context context){
        if(!ValidationErrorProviderImpl.isInit()){
            ValidationErrorProviderImpl.init( context.getResources() );
        }

        return new FormBuilderImpl();
    }
}
