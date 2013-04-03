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
//TODO Add functionality that can provide random dates based on input constraint and not from files
public class DateValueProvider extends AbstractValueProvider<Date> {
    @Override
    public Date randomValue() {
        return DateProvider.randomDate();
    }

    @Override
    public Date randomValue(long minLength, long maxLength) {
        return randomValue();
    }

    @Override
    public String randomValueAsString(Field<Date> field) {
        Date randomValue = randomValue();
        String formatMask = field.getFormatMask()!=null?field.getFormatMask():"YmdHMS:L:N";
        return DateUtil.getFormattedDate(randomValue,formatMask);
    }

    @Override
    protected Date valueFromString(String value) {
        return DateUtil.getFormattedDate(value);
    }
}
