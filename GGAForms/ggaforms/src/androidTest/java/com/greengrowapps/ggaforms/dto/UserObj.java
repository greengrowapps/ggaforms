package com.greengrowapps.ggaforms.dto;

import com.greengrowapps.ggaforms.validation.annotations.MaxLength;
import com.greengrowapps.ggaforms.validation.annotations.MinLength;
import com.greengrowapps.ggaforms.validation.annotations.NotNull;
import com.greengrowapps.ggaforms.validation.annotations.Twin;

public class UserObj {

    @NotNull
    @MaxLength(length = 10)
    private String username;

    @MinLength(length = 5)
    private String password;
    @MinLength(length = 5)
    private String rPassword;

    public UserObj() {
    }

    public String getUsername() {
        return username;
    }
}
