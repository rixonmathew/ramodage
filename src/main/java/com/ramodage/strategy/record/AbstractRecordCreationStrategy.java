package com.ramodage.strategy.record;

import com.ramodage.configuration.Field;
import com.ramodage.configuration.Schema;
import com.ramodage.configuration.Options;
import com.ramodage.provider.TypeValueProviders;
import com.ramodage.provider.ValueProvider;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 7/3/13
 * Time: 7:55 PM
 * This is the base class for record creation strategy
 */
public abstract class AbstractRecordCreationStrategy implements RecordCreationStrategy {

    private static final Logger LOG = Logger.getLogger(AbstractRecordCreationStrategy.class);
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
            LOG.error("ValueProvider is null for field" + field);
            return "";
        }
        return valueProvider.randomValueAsString(field);
    }
}
