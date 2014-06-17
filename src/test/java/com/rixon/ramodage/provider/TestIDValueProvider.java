package com.rixon.ramodage.provider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * User: rixonmathew
 * Date: 20/01/13
 * Time: 10:14 PM
 */
public class TestIDValueProvider {

    private IDValueProvider valueProvider;

    @Before
    public void setup() {
       valueProvider = new IDValueProvider();
    }

    @After
    public void tearDown() {
        valueProvider = null;
    }

//    @Test
//    public void testRandomValueProviderWithNoLimits() {
//        int tries = 100;
//        for (int i=0;i<tries;i++){
//            System.out.println("valueProvider = " + valueProvider.randomValue());
//        }
//    }

    @Test
    public void testRandomValueProviderWithLimits() {
        int tries = 2000;
        int minLength = 2;
        int maxLength = 15;
        for (int i=0;i<tries;i++) {
            Long randomValue = valueProvider.randomValue(minLength, maxLength);
            //System.out.println("randomValue = " + randomValue);
            int valueLength = String.valueOf(randomValue).length();
            assertThat(valueLength,is(greaterThanOrEqualTo(minLength)));
            assertThat(valueLength,is(lessThanOrEqualTo(maxLength)));
        }
    }

    @Test
     public void testRandomValueProviderWithRange() {
        List<Long> randomValues = Arrays.asList(10l, 15l, 20l, 30l);
        for (int i=0;i<100;i++) {
            Long randomValue = valueProvider.randomValueFromRange(randomValues);
            assertTrue(randomValues.contains(randomValue));
            //System.out.println("randomValue = " + randomValue);
        }
    }

    @Test
    public void testRandomValueWithPrefix() {
        long prefix =101l;
        int expectedLength=9;
        for (int i=0;i<100;i++) {
            Long value = valueProvider.randomValueWithPrefix(expectedLength,expectedLength,prefix);
            assertNotNull(value);
            //System.out.println("value = " + value);
        }
    }

    @Test
    public void testRandomValueWithFixedLength(){
        int expectedLength=15;
        for (int i=0;i<100;i++) {
            Long value = valueProvider.randomValue(15,15);
            //System.out.println("value = " + value);
            assertThat(String.valueOf(value).length(),is(expectedLength));
        }
    }

}
