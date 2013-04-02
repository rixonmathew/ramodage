package com.ramodage.strategy.record;

import com.ramodage.configuration.Field;
import com.ramodage.configuration.Schema;
import com.ramodage.configuration.Options;
import com.ramodage.provider.TypeValueProviders;
import com.ramodage.provider.ValueProvider;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 7/3/13
 * Time: 7:55 PM
 * This is the base class for record creation strategy
 */
public abstract class AbstractRecordCreationStrategy implements RecordCreationStrategy {

    @Override
    public String createRecord(Schema schema, Options options, long recordCounter) {
        return createRecordWithOverrides(schema,options,recordCounter,null);
    }

    protected String determineFieldValue(Field field,Map<Field,String> overriddenFields) {
        if (overriddenFields!=null && overriddenFields.containsKey(field)){
            return overriddenFields.get(field);
        }
        ValueProvider valueProvider = TypeValueProviders.valueProviderFor(field.getType());
        if (valueProvider == null) {
            System.out.println("VP null for field" + field);
        }
        return valueProvider.randomValueAsString(field);
    }
}
