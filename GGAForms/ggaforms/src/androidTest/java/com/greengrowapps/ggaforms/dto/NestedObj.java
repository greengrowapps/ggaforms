package com.greengrowapps.ggaforms.dto;


import com.greengrowapps.ggaforms.annotations.OnlyNumbers;
import com.greengrowapps.ggaforms.validation.annotations.NotNull;

public class NestedObj {

    @NotNull
    private String petName;

    @OnlyNumbers
    private String id;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
