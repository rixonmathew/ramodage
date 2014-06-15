package com.ramodage.provider;

import com.ramodage.configuration.Field;
import com.ramodage.util.DateUtil;

import java.util.Date;

/**
 * This class represents a random Date provider
 * User: rixon
 * Date: 20/01/13
 * Time: 9:43 PM
 */
public class DateValueProvider extends AbstractValueProvider<Date> {

    private IDValueProvider longValueProvider = new IDValueProvider();

    @Override
    public Date randomValue() {
        return DateProvider.randomDate();
    }

    @Override
    public Date randomValue(long minLength, long maxLength) {
        return randomValue();
    }

    /**
     * This method is used to convert from String to specific type to avoid casting errors
     * @param value
     * @return the value as a specific type
     */
    @Override
    protected Date valueFromString(String value) {
        return DateUtil.getFormattedDate(value);
    }


    @Override
    protected Date valueFromString(String value,String formatMask) {
        return DateUtil.getFormattedDate(value,formatMask);
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
    Date randomValueFromRange(Date minValue, Date maxValue) {
        return getDateInRange(minValue,maxValue);
    }

    @Override
    public String randomValueAsString(Field<Date> field) {
        Date randomValue;
        if (field.getMinValue()!=null || field.getMaxValue()!=null) {
           randomValue = randomValueWithRange(field);
        } else {
           randomValue = randomValue();
        }

        String formatMask = field.getFormatMask()!=null?field.getFormatMask():"YmdHMS:L:N";
        return DateUtil.getFormattedDate(randomValue,formatMask);
    }


    private Date randomValueWithRange(Field field) {
        Date minDate = DateUtil.getFormattedDate(field.getMinValue(),field.getFormatMask());
        Date maxDate = DateUtil.getFormattedDate(field.getMaxValue(),field.getFormatMask());
        return getDateInRange(minDate, maxDate);
    }

    private Date getDateInRange(Date minDate, Date maxDate) {
        long minValue = minDate.getTime();
        long maxValue = maxDate.getTime();
        long dateAsLongValue = longValueProvider.randomValueFromRange(minValue,maxValue);
        return DateUtil.getDateFromLongValue(dateAsLongValue);
    }

}
