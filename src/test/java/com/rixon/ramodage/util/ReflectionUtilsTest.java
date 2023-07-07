package com.rixon.ramodage.util;

import com.rixon.ramodage.configuration.Field;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 11:57 PM
 */
public class ReflectionUtilsTest {

    @Test
    public void testObjectCreationUsingReflection() {
        DummyClass instance = ReflectionUtils.createObject(DummyClass.class.getName());
        assertNotNull(instance);
    }

    @Test
    public void testMethodInvocationUsingReflection() {
        DummyClass instance = ReflectionUtils.createObject(DummyClass.class.getName());
        assertNotNull(instance);
        Field<String> nameField = new Field<>();
        nameField.setName("name");
        nameField.setType("String");
        final String expectedName = "rixon";
        ReflectionUtils.setValue(instance, expectedName,nameField);
        assertThat("Name is not same ", instance.getName(), is(expectedName));

        String expectedId="100";
        Field<Integer> idField = new Field<>();
        idField.setName("id");
        idField.setType("int");
        ReflectionUtils.setValue(instance,expectedId,idField);
        assertThat("Id is not same",instance.getId(),is(Integer.valueOf(expectedId)));

        String dateString = "01/01/2001";
        String formatMask = "dd/MM/yyyy";
        final Date expectedDate = DateUtil.getFormattedDate(dateString,formatMask);
        Field<Date> dateField = new Field<>();
        dateField.setName("dateOfBirth");
        dateField.setFormatMask(formatMask);
        dateField.setType("Date");
        ReflectionUtils.setValue(instance,dateString,dateField);
        assertThat("Date of birth is not same",instance.getDateOfBirth(),is(expectedDate));

    }

}
