package com.rixon.ramodage.provider;

import com.rixon.ramodage.configuration.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 12/2/13
 * Time: 10:54 AM
 * Test class for testing BigDecimalValueProvider
 */
public class TestBigDecimalValueProvider {

    private ValueProvider<BigDecimal> valueProvider;

    @BeforeEach
    public void setup(){
        valueProvider = TypeValueProviders.valueProviderFor("BigDecimal");
    }

    @AfterEach
    public void teardown() {
        valueProvider = null;
    }

    @Test
    public void testBasicValueProvider() {
        Field<BigDecimal> numberField = mockField();
        BigDecimal minValue = BigDecimal.valueOf(Double.valueOf(numberField.getMinValue()));
        BigDecimal maxValue = BigDecimal.valueOf(Double.valueOf(numberField.getMaxValue()));
        for (int i=0;i<1000;i++) {
            BigDecimal randomValue = valueProvider.randomValue(numberField);
            int compareToMin = randomValue.compareTo(minValue);
            int compareToMax = randomValue.compareTo(maxValue);
            assertThat(compareToMin,is(1));
            assertThat(compareToMax,is(-1));
            //System.out.println("randomValue = " + randomValue);
        }
    }

    @Test
    public void testStringValueFromProvider() {
        Field<BigDecimal> numberField = mockField();
        for (int i=0;i<1000;i++) {
            String stringValue = valueProvider.randomValueAsString(numberField);
            assertNotNull(stringValue);
            //System.out.println("stringValue = " + stringValue);
        }
    }

    private Field<BigDecimal> mockField() {
        Field<BigDecimal> numberField = new Field<>();
        numberField.setFixedLength(25);
        numberField.setMinValue("1000");
        numberField.setMaxValue("10000000000000000");
        numberField.setPadding("0");
        return numberField;
    }
}
