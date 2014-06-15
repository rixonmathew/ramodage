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
 * Time: 10:39 AM
 * This strategy creates records when the record is delimited
 */
public class DelimitedRecordCreationStrategy<TYPE> extends AbstractRecordCreationStrategy<TYPE> {

    @Override
    public String createRecordWithOverrides(Schema schema, Options options, long recordCounter, Map<Field, String> overriddenFields) {
        StringBuilder record = new StringBuilder();
        List<Field> fields = schema.getFields();
        for (int j = 0; j < fields.size(); j++) {
            Field field = fields.get(j);
            String value = determineFieldValue(field,overriddenFields);
            record.append(value);
            if (j != fields.size() - 1) {
                record.append(schema.getSeparator());
            }
        }
        return record.toString();
    }
}
