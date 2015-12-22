package com.greengrowapps.ggaforms;

public class GGASection {

    private GGASection(){

    }

    public static SectionBuilder start(){
        return new SectionBuilderImpl();
    }
}
