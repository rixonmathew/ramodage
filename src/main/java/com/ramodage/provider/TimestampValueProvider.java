package com.ramodage.provider;

import com.ramodage.configuration.Field;
import com.ramodage.util.DateUtil;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 12/2/13
 * Time: 3:31 PM
 * This class provides random values of type Timestamp
 */
public class TimestampValueProvider extends AbstractValueProvider<Date> {

    @Override
    Date randomValue(long minLength, long maxLength) {
        return randomValue();
    }

    @Override
    public Date randomValue() {
        long newTime = System.currentTimeMillis()-random.nextInt(100000);
        return new Date(newTime);
    }

    @Override
    public String randomValueAsString(Field<Date> field) {
        Date randomValue = super.randomValue(field);
        return DateUtil.getFormattedDate(randomValue, field.getFormatMask());
    }

    @Override
    protected Date valueFromString(String value) {
        return DateUtil.getFormattedDate(value);
    }
}
