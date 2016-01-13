package com.greengrowapps.ggaforms.dto;

import com.greengrowapps.ggaforms.validation.annotations.Regex;
import com.greengrowapps.ggaforms.validation.validator.regex.RegexProvider;

public class EmailRegexObj {


    @Regex( key = RegexProvider.EMAIL )
    String email;

}
