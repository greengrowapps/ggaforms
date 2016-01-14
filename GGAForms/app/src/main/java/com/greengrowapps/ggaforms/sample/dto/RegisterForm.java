package com.greengrowapps.ggaforms.sample.dto;

import com.greengrowapps.ggaforms.validation.annotations.MinLength;

public class RegisterForm {

    @MinLength(length = 5)
    private String username;
    private String password;
    private String rPassword;
    private boolean acceptedTerms;
    private boolean wantSubscribe;
    private NewsletterSubscription subscription;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getrPassword() {
        return rPassword;
    }

    public boolean isAcceptedTerms() {
        return acceptedTerms;
    }

    public boolean isWantSubscribe() {
        return wantSubscribe;
    }

    public NewsletterSubscription getSubscription() {
        return subscription;
    }
}
