package com.rixon.ramodage.provider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 3:14 PM
 *
 * This class is used for testing the Integer Value provider
 */
public class TestIntegerValueProvider {

    private IntegerValueProvider integerValueProvider;

    @Before
    public void setup() {
        integerValueProvider = new IntegerValueProvider();
    }

    @After
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
