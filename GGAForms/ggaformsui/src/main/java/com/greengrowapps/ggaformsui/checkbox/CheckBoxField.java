package com.greengrowapps.ggaformsui.checkbox;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.greengrowapps.ggaformsui.common.AbstractUiField;


public class CheckBoxField extends AbstractUiField<Boolean> implements CompoundButton.OnCheckedChangeListener {

    public CheckBoxField(CheckBox checkBox) {
        super(Boolean.class);
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        setValue(Boolean.valueOf(b));
    }
}
