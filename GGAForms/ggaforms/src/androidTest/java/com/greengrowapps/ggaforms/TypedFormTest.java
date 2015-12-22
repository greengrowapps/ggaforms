package com.greengrowapps.ggaforms;


import android.test.AndroidTestCase;

import com.greengrowapps.ggaforms.dto.MainObj;
import com.greengrowapps.ggaforms.dto.NestedObj;
import com.greengrowapps.ggaforms.fields.BooleanFormInput;
import com.greengrowapps.ggaforms.fields.StringFormInput;
import com.greengrowapps.ggaforms.validation.AnnotatedValidator;

public class TypedFormTest extends AndroidTestCase{


    public void testFillsObject(){

        StringFormInput nameField = new StringFormInput();
        BooleanFormInput subscribedField = new BooleanFormInput();

        StringFormInput petName = new StringFormInput();


        TypedForm<MainObj> form = GGAForm.start()
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

        MainObj obj = form.getObject();

        assertEquals("Joselito", obj.getName());
        assertTrue(obj.isSubscribed());
        assertEquals( "Perro", obj.getNested().getPetName() );
    }

    public void testFillsSetObject(){

        StringFormInput nameField = new StringFormInput();
        BooleanFormInput subscribedField = new BooleanFormInput();

        StringFormInput petName = new StringFormInput();

        MainObj mainObj = new MainObj();

        TypedForm<MainObj> form = GGAForm.start()
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

        TypedForm<MainObj> form = GGAForm.start()
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


        TypedForm<MainObj> form = GGAForm.start()
                .appendField("name", nameField)
                .appendField("subscribed", subscribedField)
                .appendField("nested", GGASection.start()
                                .appendField("petName", petName)
                                .build()
                )
                .buildTyped(MainObj.class)
                .addValidator(AnnotatedValidator.buildFor(MainObj.class));

        assertFalse(form.isValid());

        nameField.setText("Joselito");
        subscribedField.setChecked(true);
        petName.setText("Perro");

        assertTrue(form.isValid());

    }
}
