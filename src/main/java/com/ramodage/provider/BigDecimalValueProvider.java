package com.ramodage.provider;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 12/2/13
 * Time: 11:00 AM
 * This class provides random values of type BigDecimal
 */
public class BigDecimalValueProvider extends AbstractValueProvider<BigDecimal>{

    @Override
    BigDecimal randomValue(long minLength, long maxLength) {
        BigDecimal value;
        BigDecimal minimumValue,maxValue;
        maxValue = BigDecimal.valueOf(Math.pow(10,maxLength)-1);
        minimumValue = BigDecimal.valueOf(Math.pow(10,(minLength-1))+1);
        BigDecimal augend = maxValue.subtract(minimumValue).multiply(BigDecimal.valueOf(Math.random())).add(BigDecimal.valueOf(1));
        value = minimumValue.add(augend);
        return roundedValue(value);
    }

    @Override
    public BigDecimal randomValue() {
        return roundedValue(BigDecimal.valueOf(Math.abs(random.nextLong())));
    }

    @Override
    protected BigDecimal randomValueFromRange(BigDecimal minValue, BigDecimal maxValue) {
        BigDecimal value = minValue.add(maxValue.subtract(minValue).multiply(BigDecimal.valueOf(Math.random())));
        return roundedValue(value);
   }

    @Override
    protected BigDecimal valueFromString(String value) {
        return BigDecimal.valueOf(Double.valueOf(value));
    }

    private BigDecimal roundedValue(BigDecimal inputValue) {
       return inputValue.setScale(0,BigDecimal.ROUND_HALF_UP);
   }


}
