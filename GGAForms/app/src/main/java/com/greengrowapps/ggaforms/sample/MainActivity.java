package com.greengrowapps.ggaforms.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.greengrowapps.ggaforms.GGAForm;
import com.greengrowapps.ggaforms.GGASection;
import com.greengrowapps.ggaforms.TypedForm;
import com.greengrowapps.ggaforms.fields.Inputs;
import com.greengrowapps.ggaforms.listeners.OnValidTypedFormListener;
import com.greengrowapps.ggaforms.validation.AnnotatedValidator;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        EditText rpassword = (EditText)findViewById(R.id.rpassword);
        EditText email = (EditText)findViewById(R.id.email);

        CheckBox terms = (CheckBox)findViewById(R.id.terms);
        CheckBox subscribe = (CheckBox)findViewById(R.id.newsletter);

        CheckBox sport = (CheckBox)findViewById(R.id.sport);
        CheckBox cars = (CheckBox)findViewById(R.id.cars);
        CheckBox science = (CheckBox)findViewById(R.id.science);

        final Button submit = (Button)findViewById(R.id.submit);

        final TypedForm<RegisterForm> form =
                GGAForm.start()
                .appendField("username", Inputs.newString(username))
                .appendField("password", Inputs.newString(password))
                .appendField("rpassword", Inputs.newString(rpassword))
                .appendField("acceptedTerms", Inputs.newBoolean(terms))
                .appendField("wantsSubscribe", Inputs.newBoolean(subscribe))
                .appendField("newsletter", GGASection.start()
                                .appendField("email", Inputs.newString(email))
                                .appendField("interests", GGASection.start()
                                        .appendField("likesCars", Inputs.newBoolean(cars))
                                        .appendField("likesSport", Inputs.newBoolean(sport))
                                        .appendField("likesScience", Inputs.newBoolean(science))
                                        .build()
                                )
                                .build()
                )
                .buildTyped(RegisterForm.class)
                .addValidator( AnnotatedValidator.buildFor(RegisterForm.class) )
                .setOnValidListener(new OnValidTypedFormListener<RegisterForm>() {
                    @Override
                    public void onFormValid(TypedForm<RegisterForm> form, RegisterForm object) {
                        submit.setEnabled(true);
                    }

                    @Override
                    public void onFormInvalid(TypedForm<RegisterForm> form) {
                        submit.setEnabled(false);
                    }
                });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit(form.getObject());
            }
        });

    }

    private void submit(RegisterForm object) {

    }
}
