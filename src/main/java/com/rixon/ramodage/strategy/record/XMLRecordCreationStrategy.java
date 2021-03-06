package com.rixon.ramodage.strategy.record;

import com.rixon.ramodage.configuration.Field;
import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;

import java.util.List;
import java.util.Map;

/**
 * This strategy is used for creating records in XML format
 * User: rixon
 * Date: 12/4/13
 * Time: 7:25 PM
 */
public class XMLRecordCreationStrategy<TYPE> extends AbstractRecordCreationStrategy<TYPE> {
    @Override
    public String createRecordWithOverrides(Schema schema, Options options, long recordCounter, Map<Field, String> overriddenFields) {
        StringBuilder record = new StringBuilder();
        List<Field> fields = schema.getFields();
        if (fields.size()==0) {
            record.append("<").append(schema.getName()).append("/>");
            return record.toString();
        }
        record.append("<").append(schema.getName()).append(">\n");
        for (Field field : fields) {
            String value = determineFieldValue(field, overriddenFields);
            String nodeValue = "\t<" + field.getName() + ">" + value + "</" + field.getName() + ">\n";
            record.append(nodeValue);
        }
        record.append("</").append(schema.getName()).append(">");
        return record.toString();
    }
}
