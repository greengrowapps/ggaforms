package com.greengrowapps.ggaforms.listeners;

import com.greengrowapps.ggaforms.SimpleForm;

public interface OnSimpleFormListener {
    void onFormValid(SimpleForm form);
    void onFormInvalid(SimpleForm form);
}
