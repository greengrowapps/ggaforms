package com.greengrowapps.ggaforms.dto;

import com.greengrowapps.ggaforms.validation.annotations.Regex;
import com.greengrowapps.ggaforms.validation.validator.regex.RegexProvider;

public class PhoneRegexObj {

    public static final String PHONE = "Phone";

    @Regex( key = PhoneRegexObj.PHONE)
    String phone;

}
