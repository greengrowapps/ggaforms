package com.greengrowapps.ggaforms.dto;


import com.greengrowapps.ggaforms.validation.annotations.NotNull;

public class NestedObj {

    @NotNull
    private String petName;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}
