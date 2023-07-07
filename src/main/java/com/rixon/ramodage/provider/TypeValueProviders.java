package com.rixon.ramodage.provider;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * User: rixonmathew
 * Date: 20/01/13
 * Time: 11:43 PM
 */
public class TypeValueProviders {

    private final static Logger LOG = LoggerFactory.getLogger(TypeValueProviders.class);
    private static final Map<String,ValueProvider> valueProviders;

    static {
        valueProviders = new HashMap<>();
        valueProviders.put("int",new IDValueProvider());
        valueProviders.put("String",new StringValueProvider());
        valueProviders.put("Date",new DateValueProvider());
        valueProviders.put("BigDecimal",new BigDecimalValueProvider());
        valueProviders.put("Timestamp",new TimestampValueProvider());
        //TODO add AlphaNumeric
    }


    public static ValueProvider valueProviderFor(String type){
        if (!valueProviders.containsKey(type)) {
            LOG.debug("Invalid value type specified "+type);
        }
        return valueProviders.get(type);
    }
}
