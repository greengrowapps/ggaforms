package com.greengrowapps.ggaforms.dto;

import com.greengrowapps.ggaforms.validation.annotations.Twin;


public class TwinFieldObj {
    @Twin
    private String password;
    @Twin
    private String rPassword;
}
