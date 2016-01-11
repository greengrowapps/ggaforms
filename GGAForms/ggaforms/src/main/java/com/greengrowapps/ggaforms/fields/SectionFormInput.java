package com.greengrowapps.ggaforms.fields;


import com.greengrowapps.ggaforms.listeners.OnInputChangedListener;
import com.greengrowapps.ggaforms.listeners.OnValidSectionListener;
import com.greengrowapps.ggaforms.validation.InputBundle;
import com.greengrowapps.ggaforms.validation.errors.ValidationError;

public class SectionFormInput extends BaseFormInput implements OnInputChangedListener {

    private final InputBundle fields;
    private final OnValidSectionListener listener;

    public SectionFormInput(InputBundle fields, OnValidSectionListener listener) {
        super(InputBundle.class);
        this.fields = fields;
        this.listener = listener;

        for(String key : fields.getPropertiesNames()){
            fields.getInputNamed(key).setOnInputChangedListener(this);
        }
        setValue(fields);
    }

    @Override
    public void setError(ValidationError error) {
        if(listener!=null){
            listener.onSectionInvalid( error!=null? error.getLocalizedMessage() : null );
        }
    }


    @Override
    public void onFieldChanged(FormInput formInput) {
        super.onFieldChanged(fields);
    }
}
