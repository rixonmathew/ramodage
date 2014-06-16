package com.rixon.ramodage.provider;

import com.rixon.ramodage.configuration.Field;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class is the abstract implementation of the ValueProvider interface. All value providers should extend this abstract provider.
 * The abstract provider provides implementation for common methods like providing random Values from within a range.
 * class
 * User: rixon
 * Date: 22/1/13
 * Time: 3:19 PM
 */
abstract class AbstractValueProvider<TYPE> implements ValueProvider<TYPE> {

    final Random random = new Random();
    private final Logger LOG = Logger.getLogger(AbstractValueProvider.class);

    /**
     * This method allows the provider to fetch a random value from the given range that user has supplied.
     * @param values list of values that user has specified
     * @return a value from within the list
     */
    public TYPE randomValueFromRange(List<TYPE> values) {
        TYPE randomValue=null;
        if (values!=null && !values.isEmpty()){
            int size = values.size();
            randomValue = values.get(random.nextInt(size));
        }
        return randomValue;
    }

    /**
     * This method will generate random value based on minimum and maximum value specified in the range.
     * The default implementation in the Abstract class will simply return either min or max value.
     * This needs to be overridden in the subclasses in case a different behavior is required
     * @param minValue the starting value in the range
     * @param maxValue the ending value in the range
     * @return the random value
     */
    TYPE randomValueFromRange(TYPE minValue, TYPE maxValue){
        if(random.nextInt(2)==0) {
            return minValue;
        } else {
            return maxValue;
        }
    }

    @Override
    public TYPE randomValue(Field<TYPE> field) {
        TYPE value;
        if(field.getConstantValue()!=null){
            value= valueFromString(field.getConstantValue(),field.getFormatMask());
            return value;
        }

        if (field.getRange() != null) {
            List<TYPE> values = (List<TYPE>) Arrays.asList(field.getRange().split(","));
            value = randomValueFromRange(values);
            return value;
        }

        //If min max values are specified they take precedence over length
        if (field.getMinValue()!=null && field.getMaxValue()!=null){
            TYPE minValue = valueFromString(field.getMinValue(),field.getFormatMask());
            TYPE maxValue = valueFromString(field.getMaxValue(),field.getFormatMask());
            value = randomValueFromRange(minValue,maxValue);
            return value;
        }

        long minLength=field.getMinLength()!=0?field.getMinLength():field.getFixedLength();
        long maxLength=field.getMaxLength()!=0?field.getMaxLength():field.getFixedLength();

        if (field.getPrefix() != null) {
            value = randomValueWithPrefix(minLength, maxLength, field.getPrefix());
            return value;
        }

        if (field.getSuffix() != null) {
            value = randomValueWithSuffix(minLength,maxLength, field.getSuffix());
            return value;
        }

        value = randomValue(minLength,maxLength);
        return value;
    }

    @Override
    public String randomValueAsString(Field<TYPE> field) {
        TYPE randomValue = randomValue(field);
        String stringValue=randomValue.toString();
        if (field.getPadding()!=null){
            long maxLength=field.getMaxLength()!=0?field.getMaxLength():field.getFixedLength();
            stringValue =paddedRandomValue(field.getPadding(),randomValue,maxLength);
        }
        return stringValue;
    }

    /**
     * This method is used to apply padding to a random value to make it the required length
     * @param padding the value to be padded
     * @param value the generated random value
     * @param maxLength the maximum length of the random value
     * @return the padded random value as String
     */
    private String paddedRandomValue(String padding,TYPE value,long maxLength) {
        long paddingLength = maxLength - value.toString().length();
        String formattedValue = value.toString();
        if (paddingLength<=0){
            return formattedValue;
        }

        StringBuilder valueWithPadding = new StringBuilder(padding);
        while(valueWithPadding.length()<paddingLength){
            valueWithPadding.append(padding);
        }

        valueWithPadding.append(value.toString());
        if (valueWithPadding.length()>maxLength){
            formattedValue = valueWithPadding.substring(0,(int)maxLength);
        } else {
            formattedValue = valueWithPadding.toString();
        }
        return formattedValue;
    }

    /**
     * This method is used to convert from String to specific type to avoid casting errors
     * @param value
     * @return the value as a specific type
     */
    protected abstract TYPE valueFromString(String value);

    /**
     * This method is used to convert from String to specific type to avoid casting errors
     * @param value the value to be converted into the type
     * @param formatMask the formatMask to apply while converting the value to a specified type
     * @return the value as a specific type
     */
    protected TYPE valueFromString(String value,String formatMask) {
        return valueFromString(value);
    }

    /**
     * This overloaded method will generate a random value conforming to the input parameters passed
     * @param minLength the min length of the random value. a value of -1 means that length is ignored
     * @param maxLength the max length of the random value. a value of -1 means that length is ignored
     * @return the random value;
     */
    abstract TYPE randomValue(long minLength,long maxLength);

    /**
     * This method will generate a random value which begins with a given prefix.The default implementation
     * ignores the prefix. Specific value providers can override if required
     * @param minLength the min length of the random value. a value of -1 means that length is ignored
     * @param maxLength the max length of the random value. a value of -1 means that length is ignored
     * @param prefix value to be prefixed
     * @return the random value
     */
    TYPE randomValueWithPrefix(long minLength, long maxLength, TYPE prefix) {
        return randomValue(minLength,maxLength);
    }

    /**
     * This method will generate a random value which ends with a given suffix.The default implementation
     * ignores the prefix. Specific value providers can override if required
     * @param minLength the min length of the random value. a value of -1 means that length is ignored
     * @param maxLength the max length of the random value. a value of -1 means that length is ignored
     * @param suffix value to be suffixed
     * @return the random value
     */
    TYPE randomValueWithSuffix(long minLength, long maxLength, TYPE suffix) {
        return randomValue(minLength,maxLength);
    }

    /**
     * This method can be used to control the precision on the numbers
     * @param value
     * @param field
     * @return
     */
    //TODO introduce precision in Field.
    protected TYPE applyPrecisionForNumbers(TYPE value,Field<TYPE> field){
        return value;
    }
}
