package com.ramodage.strategy.record;

import com.ramodage.configuration.Field;
import com.ramodage.configuration.Schema;
import com.ramodage.configuration.Options;
import com.ramodage.configuration.Field;
import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 25/1/13
 * Time: 10:53 AM
 * This strategy is responsible for creating fixedWidthRecord based on the fields
 */
public class FixedWidthRecordCreationStrategy extends AbstractRecordCreationStrategy {

    @Override
    public String createRecordWithOverrides(Schema schema, Options options, long recordCounter, Map<Field, String> overriddenFields) {
        StringBuilder record = new StringBuilder();
        List<Field> fields = schema.getFields();
        for (Field field : fields) {
            String value = determineFieldValue(field,overriddenFields);
            record.append(value);
        }
        return record.toString();
    }
}
