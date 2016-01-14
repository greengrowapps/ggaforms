package com.greengrowapps.ggaforms.sample.dto;

import com.greengrowapps.ggaforms.validation.annotations.NotNull;
import com.greengrowapps.ggaforms.validation.annotations.Regex;
import com.greengrowapps.ggaforms.validation.validator.regex.RegexProvider;

public class SimpleLoginForm {

    @Regex( key = RegexProvider.EMAIL)
    @NotNull
    private String email;

    @NotNull
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
