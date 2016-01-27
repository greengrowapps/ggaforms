### GgaForms
![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.greengrowapps/ggaforms/badge.svg?style=flat)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ggaforms-green.svg?style=true)](https://android-arsenal.com/details/1/3068)

### Introduction
This library makes it easy to work with forms in Android. How many times have you take the text from edittexts or have populated an object to the UI. And how many times have you validated this object and populated the error. With this library you have a comfortable builder interface that allows to easily define and connect your object with the UI. You will be able to retrieve and populate your data saving hundrets of boilerplate code.
This library comes too with a annotation-based validation tool. Just annotate your object fields with @NotNull, @MinLength, @Regex(pattern)... and the library will take care of the validation and the error population.

### Usage

This is an example of how read a big form and save it in a complex object. It is being validated with the AnnotatedValidator. And when the form is fully valid the onFormValid method will be triggered to enable the submission button. 

```java
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
```
You can use the library just to populate objects in the UI avoiding boilerplate:

```java
        TextView outUsername = (TextView)findViewById(R.id.tUsername);
        TextView outEmail = (TextView)findViewById(R.id.tEmail);

        TypedForm<User> outForm = GGAForm.startWithContext(this)
                .appendField("username", Inputs.newString(outUsername))
                .appendField("email", Inputs.newString(outEmail))
                .buildTyped(User.class);
                
        outForm.setObject(myUser);
```
You can use the predefined annotations to validate or implement your custom:

```java
public class SimpleLoginForm {

    @Regex( key = RegexProvider.EMAIL)
    @NotNull
    private String email;

    @NotNull
    @MinLength(length = 6)
    private String password;
    
    @True
    private boolean acceptedTermsAndConditions;
    
    @ProductReference
    private String associatedProduct;
```

```java
TypedForm<LoginForm> registerForm = GGAForm.startWithContext(this)
        .appendField("email", Inputs.newString(emailEditText))
        .appendField("password", Inputs.newString(passwordEditText))
        .appendField("acceptedTermsAndConditions", Inputs.newBoolean(termsCheckBox))
        .appendField("associatedProduct",Inputs.newString(producRefEditText))
        .buildTyped(LoginForm.class)
        .addValidator( 
                AnnotatedValidator.newInstance()
                .registerAnnotation(ProductReference.class,new ProductReferenceValidatorProvider())
        );
```
Like the most comon validation is a Regex validation, you have avaliable the @Regex(key) annotation. With the key you specify wich kind of validator want to use for this field. You have already defined keys in the RegexProvider class, but you can replace this regex or add new custom keys:

Using a defined key:
```java
@Regex( key = RegexProvider.EMAIL )
String email;
```
Implementing a custom key and using it:
```java
@Regex( key = GITHUB_EMAIL )
String email;
```
```java
        RegexProvider.getInstance()
        .registerRegex(RegisterForm.GITHUB_EMAIL, "^[a_Z0-9]*@github.com$", new ErrorBuilder(){
            @Override
            public ValidationError build(Object... params) {
                return new ValidationErrorImpl(getString(R.string.validGithubEmail));
            }
        });
```
Or just replace a existing:
```java
        RegexProvider.getInstance()
                .replaceRegex(RegexProvider.EMAIL, "^[a_Z0-9]*@github.com$");
```
    
### Integration

Add as a dependency to your ``build.gradle``:

```groovy
dependencies {
    compile 'com.greengrowapps:ggaformsui:0.3'
}
```
    
### Things to develop
This version is still not 1.0, which means api changes could happen. It needs some test and development.

If you want to collaborate, here is a checklist of planned features you can help working on :
+ More validation annotation and validators
+ More tests

### License

```
   Apache License Version 2.0, January 2004

   Copyright 2015 Green Grow Apps SC

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```
    


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/greengrowapps/ggaforms/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

