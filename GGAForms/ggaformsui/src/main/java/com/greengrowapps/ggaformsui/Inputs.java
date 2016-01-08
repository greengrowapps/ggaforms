package com.greengrowapps.ggaformsui;


import android.support.design.widget.TextInputLayout;
import android.widget.CheckBox;
import android.widget.EditText;

import com.greengrowapps.ggaformsui.checkbox.CheckBoxField;
import com.greengrowapps.ggaformsui.edittext.DoubleEditTextField;
import com.greengrowapps.ggaformsui.edittext.IntegerEditTextField;
import com.greengrowapps.ggaformsui.edittext.StringEditTextField;
import com.greengrowapps.ggaformsui.textinputlayout.DoubleTextInputLayout;
import com.greengrowapps.ggaformsui.textinputlayout.IntegerTextInputLayout;
import com.greengrowapps.ggaformsui.textinputlayout.StringTextInputLayout;

public class Inputs {

    private Inputs(){
        //Static class
    }

    public static StringTextInputLayout newString(TextInputLayout view){
        return new StringTextInputLayout(view);
    }
    public static IntegerTextInputLayout newInteger(TextInputLayout view){
        return new IntegerTextInputLayout(view);
    }
    public static DoubleTextInputLayout newDouble(TextInputLayout view){
        return new DoubleTextInputLayout(view);
    }

    public static StringEditTextField newString(EditText view){
        return new StringEditTextField(view);
    }
    public static IntegerEditTextField newInteger(EditText view){
        return new IntegerEditTextField(view);
    }
    public static DoubleEditTextField newDouble(EditText view){
        return new DoubleEditTextField(view);
    }
    public static CheckBoxField newBoolean(CheckBox view){
        return new CheckBoxField(view);
    }

}
