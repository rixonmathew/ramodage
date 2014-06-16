package com.rixon.ramodage.provider;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 10:59 AM
 */
public class IntegerValueProvider extends AbstractValueProvider<Integer> {
    /**
     * This method is used to convert from String to specific type to avoid casting errors
     *
     * @param value
     * @return the value as a specific type
     */
    @Override
    protected Integer valueFromString(String value) {
        return Integer.valueOf(value);
    }

    /**
     * This overloaded method will generate a random value conforming to the input parameters passed
     *
     * @param minLength the min length of the random value. a value of -1 means that length is ignored
     * @param maxLength the max length of the random value. a value of -1 means that length is ignored
     * @return the random value;
     */
    @Override
    Integer randomValue(long minLength, long maxLength) {
        int value;
        int minimumValue,maxValue;
        maxValue = (int)Math.pow(10,maxLength)-1;
        minimumValue = (int)Math.pow(10,(minLength-1))+1;
        value = minimumValue+ (int)(Math.random()*(maxValue-minimumValue))+1;
        return value;
    }

    /**
     * This method will provide a random value. This is the simplest method that provides a type value
     * without check for min,max or a constant seed value
     *
     * @return the random value;
     */
    @Override
    public Integer randomValue() {
        return Math.abs(random.nextInt());
    }
}
