package com.ramodage.provider;

import com.ramodage.configuration.Field;
import com.ramodage.util.DateUtil;

import java.util.Date;

/**
 * This class represents a random timestamp provider
 * User: rixon
 * Date: 12/2/13
 * Time: 3:31 PM
 */
public class TimestampValueProvider extends DateValueProvider {

    @Override
    public Date randomValue() {
        long newTime = System.currentTimeMillis()-random.nextInt(100000);
        return new Date(newTime);
    }
}
