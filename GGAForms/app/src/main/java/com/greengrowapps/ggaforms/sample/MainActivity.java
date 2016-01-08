package com.greengrowapps.ggaforms.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.greengrowapps.ggaforms.GGAForm;
import com.greengrowapps.ggaforms.GGASection;
import com.greengrowapps.ggaforms.TypedForm;
import com.greengrowapps.ggaforms.listeners.OnValidTypedFormListener;
import com.greengrowapps.ggaforms.validation.AnnotatedValidator;
import com.greengrowapps.ggaformsui.Inputs;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "MainActivity";

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
                GGAForm.startWithContext(this)
                .appendField("username", Inputs.newString(username))
                .appendField("password", Inputs.newString(password))
                .appendField("rPassword", Inputs.newString(rpassword))
                .appendField("acceptedTerms", Inputs.newBoolean(terms))
                .appendField("wantSubscribe", Inputs.newBoolean(subscribe))
                .appendField("subscription", GGASection.start()
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
                .addValidator( AnnotatedValidator.newInstance() )
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
        Log.d(DEBUG_TAG, "REGISTER FORM");
        Log.d(DEBUG_TAG, object.getUsername()+"" );
        Log.d(DEBUG_TAG, object.getPassword()+"" );
        Log.d(DEBUG_TAG, object.getrPassword()+"" );
        Log.d(DEBUG_TAG, object.isAcceptedTerms()+"" );
        Log.d(DEBUG_TAG, object.isWantSubscribe()+"" );
        Log.d(DEBUG_TAG, object.getSubscription().getEmail()+"" );
        Log.d(DEBUG_TAG, object.getSubscription().getInterests().isLikesCars()+"");
        Log.d(DEBUG_TAG, object.getSubscription().getInterests().isLikesScience()+"");
        Log.d(DEBUG_TAG, object.getSubscription().getInterests().isLikesSport()+"");
    }
}
