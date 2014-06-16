package com.rixon.ramodage.strategy.record;

import com.rixon.ramodage.configuration.Field;
import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;

import java.util.List;
import java.util.Map;

/**
 * This strategy is used for creating records in the json format
 * User: rixon
 * Date: 12/4/13
 * Time: 8:46 PM
 */
public class JSONRecordCreationStrategy<TYPE> extends AbstractRecordCreationStrategy<TYPE> {
    @Override
    public String createRecordWithOverrides(Schema schema, Options options, long recordCounter, Map<Field, String> overriddenFields) {
        StringBuilder record = new StringBuilder();
        List<Field> fields = schema.getFields();

        record.append("{\n");
        for (int j = 0; j < fields.size(); j++) {
            Field field = fields.get(j);
            String value = determineFieldValue(field,overriddenFields);
            String nodeValue = "\t\""+field.getName()+"\":\""+value+"\"";
            record.append(nodeValue);
            if(j!=(fields.size()-1)){
               record.append(",\n");
            } else {
               record.append("\n");
            }
        }
        record.append("},");
        return record.toString();
    }
}
