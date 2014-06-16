package com.rixon.ramodage.provider;

import com.rixon.ramodage.configuration.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 12/2/13
 * Time: 3:26 PM
 * Test class for Timestamp Valueprovider
 */
public class TestTimestampValueProvider {

    private ValueProvider<Date> timeStampValueProvider;

    @Before
    public void setup() {
        timeStampValueProvider = TypeValueProviders.valueProviderFor("Timestamp");
    }

    @After
    public void tearDown() {
        timeStampValueProvider = null;
    }

    @Test
    public void testBasicRandomValues() {
        for (int i=0;i<1000;i++) {
            Date value = timeStampValueProvider.randomValue();
            //System.out.println("value = " + value);
        }
    }

    @Test
    public void testStringRepresentationOfRandomValues() {
        Field<Date> dateField = mockField();
        for (int i=0;i<1000;i++){
            String timeStampValue = timeStampValueProvider.randomValueAsString(dateField);
            //System.out.println("timeStampValue = " + timeStampValue);
        }
    }

    @Test
    public void testTimeStampWithMilliSeconds() {
        Field<Date> dateField = mockFieldForMillisecond();
        for (int i=0;i<1000;i++){
            String timeStampValue = timeStampValueProvider.randomValueAsString(dateField);
            //System.out.println("timeStampValue = " + timeStampValue);
        }

    }

    private Field<Date> mockField() {
        Field<Date> dateField = new Field<Date>();
        dateField.setFormatMask("yyyyMMddHHmmss");
        return dateField;
    }

    private Field<Date> mockFieldForMillisecond() {
        Field<Date> dateField = new Field<Date>();
        dateField.setFormatMask("yyyyMMddHHmmssSSSS");
        return dateField;
    }



}
