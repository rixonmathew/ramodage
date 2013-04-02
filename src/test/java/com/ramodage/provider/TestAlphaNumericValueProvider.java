package com.ramodage.provider;

import com.ramodage.provider.AlphaNumericValueProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 22/1/13
 * Time: 1:28 PM
 * This is the test class for generating
 */
public class TestAlphaNumericValueProvider {


    private AlphaNumericValueProvider valueProvider;

    @Before
    public void setUp() throws Exception {
        valueProvider = new AlphaNumericValueProvider();
    }

    @After
    public void tearDown() {
        valueProvider = null;
    }

//    @Test
//    public void testValues() {
//        for (int i=0;i<1000;i++) {
//            System.out.println("valueProvider = " + valueProvider.randomValue());
//        }
//    }

    @Test
    public void testValuesWithinSpecifiedLength() {
        for(int i=0;i<1000;i++) {
            int minLength = 10;
            int maxLength = 20;
            String randomValue = valueProvider.randomValue(minLength, maxLength);
            //System.out.println("randomValue = " +i+":"+randomValue+" length : "+randomValue.length());
            assertThat(randomValue.length(), is(greaterThanOrEqualTo(minLength)));
            assertThat(randomValue.length(),is(lessThanOrEqualTo(maxLength)));
        }
    }

    @Test
    public void testValuesWithinRange() {
        List<String> randomValues = Arrays.asList("11232ASDADASd","asdas123123213","ads1123","1123123A");
        for (int i=0;i<100;i++) {
            String value = valueProvider.randomValueFromRange(randomValues);
            assertTrue(randomValues.contains(value));
//            System.out.println("value = " + value);
        }
    }

}
