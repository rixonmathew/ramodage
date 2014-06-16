package com.rixon.ramodage.provider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

/**
 * User: rixonmathew
 * Date: 20/01/13
 * Time: 11:19 PM
 */
public class TestStringValueProvider {

    private StringValueProvider stringValueProvider;

    @Before
    public void setup(){
        stringValueProvider = new StringValueProvider();
    }

    @After
    public void tearDown(){
        stringValueProvider = null;
    }

    @Test
    public void testRandomValueWithinLimits() {
        int minLength = 2;
        int maxLength = 25;
        for (int i=0;i<100;i++) {
            String randomValue = stringValueProvider.randomValue(minLength, maxLength);
            assertThat(randomValue.length(), is(greaterThanOrEqualTo(minLength)));
            assertThat(randomValue.length(),is(lessThanOrEqualTo(maxLength)));
        }
    }

    @Test
    public void testRandomValueWithinRange() {
        List<String> randomValues = Arrays.asList("hello","world","meter","counter","laughter");
        for (int i=0;i<100;i++){
            String value = stringValueProvider.randomValueFromRange(randomValues);
            assertTrue(randomValues.contains(value));
        }
    }

    @Test
    public void testRandomValueWithPrefix() {
        String prefix="CUSIP";
        final int expectedLength = 10;
        for (int i=0;i<100;i++) {
            String value = stringValueProvider.randomValueWithPrefix(expectedLength,expectedLength,prefix);
            assertThat(value.length(),is(expectedLength));
        }
    }

    @Test
    public void testRandomValueForFixedLength() {
        int expectedLength = 15;
        for (int i=0;i<100;i++) {
            String value = stringValueProvider.randomValue(expectedLength,expectedLength);
            assertThat(value.length(),is(expectedLength));
        }
    }

    @Test
    public void testRandomValueWithSuffix() {
        String suffix = "PPTY";
        final int expectedLength = 15;
        for(int i=0;i<100;i++){
            String value = stringValueProvider.randomValueWithSuffix(expectedLength,expectedLength,suffix);
            assertThat(value.length(),is(expectedLength));
            assertTrue(value.endsWith(suffix));
        }
    }

    @Test
    public void testRandomValueWithSuffixLongerThanValue() {
        String suffix="ACTMETERPOTER";
        final int expectedLength = 10;
        for (int i=0;i<100;i++) {
            String value = stringValueProvider.randomValueWithSuffix(expectedLength,expectedLength,suffix);
            assertThat(value.length(),is(expectedLength));
        }

    }
}
