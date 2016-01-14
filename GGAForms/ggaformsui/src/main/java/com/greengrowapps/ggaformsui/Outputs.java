package com.greengrowapps.ggaformsui;

import android.widget.TextView;

import com.greengrowapps.ggaformsui.textview.TextViewErrorHolder;

public class Outputs {
    private Outputs(){

    }

    public static TextViewErrorHolder newError(TextView textView){
        return new TextViewErrorHolder(textView);
    }
}
