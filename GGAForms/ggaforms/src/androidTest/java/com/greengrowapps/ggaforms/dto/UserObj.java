package com.greengrowapps.ggaforms.dto;

import com.greengrowapps.ggaforms.validation.annotations.MaxLength;
import com.greengrowapps.ggaforms.validation.annotations.NotNull;

public class UserObj {

    @NotNull
    @MaxLength(length = 10)
    private String username;

    public String getUsername() {
        return username;
    }
}
