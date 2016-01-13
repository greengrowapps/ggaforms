package com.greengrowapps.ggaforms.dto;

import com.greengrowapps.ggaforms.validation.annotations.Regex;
import com.greengrowapps.ggaforms.validation.validator.regex.RegexProvider;

public class CreditCardRegexObj {

    @Regex( key = RegexProvider.CREDIT_CARD)
    String cardNr;

}
