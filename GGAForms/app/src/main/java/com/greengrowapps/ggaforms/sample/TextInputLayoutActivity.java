package com.greengrowapps.ggaforms.sample;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.greengrowapps.ggaforms.GGAForm;
import com.greengrowapps.ggaforms.SimpleForm;
import com.greengrowapps.ggaforms.TypedForm;
import com.greengrowapps.ggaforms.listeners.OnSimpleFormListener;
import com.greengrowapps.ggaforms.sample.dto.SimpleLoginForm;
import com.greengrowapps.ggaforms.validation.validator.AnnotatedValidator;
import com.greengrowapps.ggaformsui.Inputs;

public class TextInputLayoutActivity extends AppCompatActivity {

    private TypedForm<SimpleLoginForm> outForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

        TextInputLayout email = (TextInputLayout)findViewById(R.id.username);
        TextInputLayout password = (TextInputLayout)findViewById(R.id.password);
        final Button submit = (Button)findViewById(R.id.submit);

        final TypedForm<SimpleLoginForm> inputForm = GGAForm.startWithContext(this)
                .appendField("email", Inputs.newString(email))
                .appendField("password", Inputs.newString(password))
                .buildTyped(SimpleLoginForm.class)
                .addValidator(AnnotatedValidator.newInstance());

        inputForm.setOnValidListener(new OnSimpleFormListener() {
            @Override
            public void onFormValid(SimpleForm form) {
                submit.setEnabled(true);
            }

            @Override
            public void onFormInvalid(SimpleForm form) {
                submit.setEnabled(false);
            }
        });

        SimpleLoginForm login = new SimpleLoginForm();
        login.setEmail("user@mail.com");
        inputForm.setObject(login);

        submit.setEnabled(inputForm.isValid());

        TextView outEmail = (TextView)findViewById(R.id.tUsername);
        TextView outPassword = (TextView)findViewById(R.id.tPassword);

        outForm = GGAForm.startWithContext(this)
                .appendField("email", Inputs.newString(outEmail))
                .appendField("password", Inputs.newString(outPassword))
                .buildTyped(SimpleLoginForm.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit(inputForm.getObject());
            }
        });
    }

    private void onSubmit(SimpleLoginForm object) {
        outForm.setObject(object);
    }
}
