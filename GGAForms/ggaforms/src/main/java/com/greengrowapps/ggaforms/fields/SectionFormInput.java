package com.greengrowapps.ggaforms.fields;


import com.greengrowapps.ggaforms.listeners.OnInputChangedListener;
import com.greengrowapps.ggaforms.listeners.OnValidSectionListener;
import com.greengrowapps.ggaforms.validation.InputBundle;

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
    public void setError(CharSequence error) {
        if(listener!=null){
            listener.onSectionInvalid(error);
        }
    }

    @Override
    public void setValid() {
        if(listener!=null){
            listener.onSectionValid();
        }
    }

    @Override
    public void onFieldChanged(FormInput formInput) {
        super.onFieldChanged(fields);
    }
}
