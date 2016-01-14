package com.greengrowapps.ggaformsui.textview;

import android.widget.TextView;

import com.greengrowapps.ggaforms.validation.errors.ValidationError;
import com.greengrowapps.ggaformsui.common.DisplayErrorListener;

public class TextViewErrorHolder implements DisplayErrorListener {

    private final TextView textView;

    public TextViewErrorHolder(TextView textView){
        this.textView = textView;
    }

    @Override
    public void onHideError() {
        textView.setText("");
    }

    @Override
    public void onShowError(ValidationError currentError) {
        textView.setText(currentError.getLocalizedMessage());
    }
}
