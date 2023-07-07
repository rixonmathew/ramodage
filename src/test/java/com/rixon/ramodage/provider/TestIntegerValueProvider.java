package com.rixon.ramodage.provider;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 3:14 PM
 *
 * This class is used for testing the Integer Value provider
 */
public class TestIntegerValueProvider {

    private IntegerValueProvider integerValueProvider;

    @BeforeEach
    public void setup() {
        integerValueProvider = new IntegerValueProvider();
    }

    @AfterEach
    public void tearDown() {
        integerValueProvider = null;
    }

    @Test
    public void testRandomValue() {
        for (int i=0;i<100;i++){
            int randomValue = integerValueProvider.randomValue();
            assertNotNull(randomValue);
        }
    }

    @Test
    public void testRandomValueWithinLength() {
        int minLength=1;
        int maxLength=5;
        for (int i=0;i<100;i++) {
            int randomValue = integerValueProvider.randomValue(minLength,maxLength);
            String value = String.valueOf(randomValue);
            assertThat("Size is less than min size "+value, value.length(), greaterThan(minLength));
            assertThat("Size is greater than max size "+value, value.length(), lessThanOrEqualTo(maxLength));
        }
    }


}
