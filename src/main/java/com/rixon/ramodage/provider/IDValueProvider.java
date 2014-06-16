package com.rixon.ramodage.provider;

/**
 * This class represents a random Long provider
 * User: rixon
 * Date: 20/01/13
 * Time: 9:42 PM
 */
public class IDValueProvider extends AbstractValueProvider<Long> {

    @Override
    public Long randomValue() {
        return Math.abs(random.nextLong());
    }

    @Override
    protected Long valueFromString(String value) {
        //TODO add check to determine if value is more than capacity of Long
        return Long.valueOf(value);
    }

    /**
     * This method will generate random value based on minimum and maximum value specified in the range.
     * The default implementation in the Abstract class will simply return either min or max value.
     * This needs to be overridden in the subclasses in case a different behavior is required
     *
     * @param minValue the starting value in the range
     * @param maxValue the ending value in the range
     * @return the random value
     */
    @Override
    Long randomValueFromRange(Long minValue, Long maxValue) {
        return minValue+ (long)(Math.random()*(maxValue-minValue));
    }

    @Override
    public Long randomValue(long minLength, long maxLength) {
        long value;
        long minimumValue,maxValue;
        maxValue = (long)Math.pow(10,maxLength)-1;
        minimumValue = (long)Math.pow(10,(minLength-1))+1;
        value = minimumValue+ (long)(Math.random()*(maxValue-minimumValue))+1;
        return value;
    }

    @Override
    public Long randomValueWithPrefix(long minLength, long maxLength, Long prefix) {
        return randomValue(minLength,maxLength);
    }
}
