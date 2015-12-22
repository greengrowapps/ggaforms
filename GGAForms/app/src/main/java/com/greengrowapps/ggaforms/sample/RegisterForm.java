package com.greengrowapps.ggaforms.sample;

public class RegisterForm {

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
