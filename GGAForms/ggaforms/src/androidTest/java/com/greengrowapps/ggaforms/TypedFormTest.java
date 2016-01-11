package com.greengrowapps.ggaforms;


import android.test.AndroidTestCase;

import com.greengrowapps.ggaforms.annotations.NotNumbersValidationError;
import com.greengrowapps.ggaforms.annotations.OnlyNumbers;
import com.greengrowapps.ggaforms.annotations.OnlyNumbersValidator;
import com.greengrowapps.ggaforms.dto.MainObj;
import com.greengrowapps.ggaforms.dto.NestedObj;
import com.greengrowapps.ggaforms.dto.UserObj;
import com.greengrowapps.ggaforms.fields.BooleanFormInput;
import com.greengrowapps.ggaforms.fields.StringFormInput;
import com.greengrowapps.ggaforms.validation.AnnotatedValidator;

public class TypedFormTest extends AndroidTestCase{


    public void testFillsObject(){

        StringFormInput nameField = new StringFormInput();
        BooleanFormInput subscribedField = new BooleanFormInput();

        StringFormInput petName = new StringFormInput();


        TypedForm<MainObj> form = GGAForm.startWithContext(getContext())
                .appendField("name", nameField)
                .appendField("subscribed", subscribedField)
                .appendField("nested", GGASection.start()
                                .appendField("petName", petName)
                                .build()
                )
                .buildTyped(MainObj.class);

        nameField.setText("Joselito");
        subscribedField.setChecked(true);
        petName.setText("Perro");

        assertEquals("Joselito", form.getObject().getName());
        assertTrue(form.getObject().isSubscribed());
        assertEquals( "Perro", form.getObject().getNested().getPetName() );
    }

    public void testFillsSetObject(){

        StringFormInput nameField = new StringFormInput();
        BooleanFormInput subscribedField = new BooleanFormInput();

        StringFormInput petName = new StringFormInput();

        MainObj mainObj = new MainObj();

        TypedForm<MainObj> form = GGAForm.startWithContext(getContext())
                .appendField("name", nameField)
                .appendField("subscribed", subscribedField)
                .appendField("nested", GGASection.start()
                                .appendField("petName", petName)
                                .build()
                )
               .buildTyped(MainObj.class);

        form.setObject(mainObj);

        nameField.setText("Joselito");
        subscribedField.setChecked(true);
        petName.setText("Perro");

        assertEquals("Joselito", mainObj.getName());
        assertTrue(mainObj.isSubscribed());
        assertEquals( "Perro", mainObj.getNested().getPetName() );
    }

    public void testFillsInputsWhenSetObject(){

        StringFormInput nameField = new StringFormInput();
        BooleanFormInput subscribedField = new BooleanFormInput();

        StringFormInput petName = new StringFormInput();

        NestedObj nested = new NestedObj();
        nested.setPetName("Perro");

        MainObj mainObj = new MainObj();
        mainObj.setName("Joselito");
        mainObj.setSubscribed(true);
        mainObj.setNested(nested);

        TypedForm<MainObj> form = GGAForm.startWithContext(getContext())
                .appendField("name", nameField)
                .appendField("subscribed", subscribedField)
                .appendField("nested", GGASection.start()
                                .appendField("petName", petName)
                                .build()
                )
                .buildTyped(MainObj.class);

        form.setObject(mainObj);

        assertEquals("Joselito", nameField.getValue());
        assertTrue( subscribedField.getValue() );
        assertEquals( "Perro", petName.getValue() );
    }


    public void testAnnotatedValidator(){

        StringFormInput nameField = new StringFormInput();
        BooleanFormInput subscribedField = new BooleanFormInput();

        StringFormInput petName = new StringFormInput();


        TypedForm<MainObj> form = GGAForm.startWithContext(getContext())
                .appendField("name", nameField)
                .appendField("subscribed", subscribedField)
                .appendField("nested", GGASection.start()
                                .appendField("petName", petName)
                                .build()
                )
                .buildTyped(MainObj.class)
                .addValidator(AnnotatedValidator.newInstance());

        assertFalse(form.isValid());

        nameField.setText("Joselito");
        subscribedField.setChecked(true);

        assertFalse(form.isValid());

        petName.setText("Perro");
        assertTrue(form.isValid());

    }

    public void testAnnotatedValidatorNotBreaksGetObject(){

        StringFormInput nameField = new StringFormInput();
        BooleanFormInput subscribedField = new BooleanFormInput();

        StringFormInput petName = new StringFormInput();


        TypedForm<MainObj> form = GGAForm.startWithContext(getContext())
                .appendField("name", nameField)
                .appendField("subscribed", subscribedField)
                .appendField("nested", GGASection.start()
                                .appendField("petName", petName)
                                .build()
                )
                .buildTyped(MainObj.class)
                .addValidator(AnnotatedValidator.newInstance());

        nameField.setText("Joselito");
        subscribedField.setChecked(true);
        petName.setText("Perro");

        assertEquals("Joselito", form.getObject().getName());
        assertTrue(form.getObject().isSubscribed());
        assertEquals( "Perro", form.getObject().getNested().getPetName() );
    }

    public void testLocalizedErrors(){
        StringFormInput nameField = new StringFormInput();
        BooleanFormInput subscribedField = new BooleanFormInput();

        StringFormInput petName = new StringFormInput();


        TypedForm<MainObj> form = GGAForm.startWithContext(getContext())
                .appendField("name", nameField)
                .appendField("subscribed", subscribedField)
                .appendField("nested", GGASection.start()
                                .appendField("petName", petName)
                                .build()
                )
                .buildTyped(MainObj.class)
                .addValidator(AnnotatedValidator.newInstance());

        nameField.setText("Joselito");
        subscribedField.setChecked(true);

        assertFalse(form.isValid());
        assertEquals(getContext().getResources().getString(R.string.fillThisField),
                petName.getError());
    }

    public void testCustomAnnotation(){
        StringFormInput nameField = new StringFormInput();
        BooleanFormInput subscribedField = new BooleanFormInput();

        StringFormInput petName = new StringFormInput();
        StringFormInput idField = new StringFormInput();


        TypedForm<MainObj> form = GGAForm.startWithContext(getContext())
                .appendField("name", nameField)
                .appendField("subscribed", subscribedField)
                .appendField("nested", GGASection.start()
                                .appendField("petName", petName)
                                .appendField("id",idField)
                                .build()
                )
                .buildTyped(MainObj.class)
                .addValidator(AnnotatedValidator.newInstance()
                        .registerAnnotation(OnlyNumbers.class, new OnlyNumbersValidator()));

        nameField.setText("Joselito");
        subscribedField.setChecked(true);
        petName.setText("Perro");
        idField.setText("abc");

        assertFalse(form.isValid());
        assertEquals(new NotNumbersValidationError().getLocalizedMessage(), idField.getError());

        idField.setText("123");
        assertTrue(form.isValid());
    }

    public void testAnnotationWithParameters(){
        StringFormInput usernameField = new StringFormInput();

        TypedForm<UserObj> form = GGAForm.startWithContext(getContext())
                .appendField("username", usernameField)
                .buildTyped(UserObj.class)
                .addValidator(AnnotatedValidator.newInstance());

        usernameField.setText("1234567890123");

        assertEquals("1234567890123", form.getObject().getUsername());

        assertFalse(form.isValid());
        assertEquals( getContext().getResources().getString(R.string.exceedsMaxLenght,10) , usernameField.getError() );

        usernameField.setText("username");
        assertTrue(form.isValid());
    }
}
