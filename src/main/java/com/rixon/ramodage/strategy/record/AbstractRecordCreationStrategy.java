package com.rixon.ramodage.strategy.record;

import com.rixon.ramodage.configuration.Field;
import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.provider.TypeValueProviders;
import com.rixon.ramodage.provider.ValueProvider;
import com.rixon.ramodage.util.ReflectionUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 7/3/13
 * Time: 7:55 PM
 * This is the base class for record creation strategy
 */
public abstract class AbstractRecordCreationStrategy<TYPE> implements RecordCreationStrategy<TYPE> {

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

    /**
     * This method is used for getting an object mapped with Random values
     *
     * @param schema        represents the schema of the file to be generated
     * @param options       represents the options for creating the file
     * @param recordCounter the recordCounter.
     * @return
     */
    @Override
    public TYPE createRecordAsObject(Schema schema, Options options, long recordCounter) {
        return createRecordAsObjectWithOverrides(schema,options,recordCounter,null);
    }

    /**
     * This method will allow creation of record with facility to override random values. This is useful in scenarios where data generation
     * needs to be controlled like in a series. In this scenario the File generation strategy can decide which all fields value should be
     * overridden with the provided value
     *
     * @param schema           represents the schema of the file to be generated
     * @param options          represents the options for creating the file
     * @param recordCounter    the recordCounter.
     * @param overriddenFields a list of FieldValueHolders that contains overridden values
     * @return fully formed object instance
     */
    @Override
    public TYPE createRecordAsObjectWithOverrides(Schema schema, Options options, long recordCounter, Map<Field, String> overriddenFields) {
        TYPE object = ReflectionUtils.createObject(options.getObjectClassName());
        List<Field> fields = schema.getFields();
        for (Field field : fields) {
            String value = determineFieldValue(field,overriddenFields);
            ReflectionUtils.setValue(object,value,field);
        }
        return object;
    }
}

