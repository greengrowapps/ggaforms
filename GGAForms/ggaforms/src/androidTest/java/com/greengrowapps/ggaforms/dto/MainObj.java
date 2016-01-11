package com.greengrowapps.ggaforms.dto;


import com.greengrowapps.ggaforms.validation.annotations.MaxLength;
import com.greengrowapps.ggaforms.validation.annotations.NotNull;
import com.greengrowapps.ggaforms.validation.annotations.True;

public class MainObj {

    @NotNull
    private String name;
    @True
    private boolean subscribed;

    private NestedObj nested;

    public String getName() {
        return name;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public NestedObj getNested() {
        return nested;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public void setNested(NestedObj nested) {
        this.nested = nested;
    }
}
