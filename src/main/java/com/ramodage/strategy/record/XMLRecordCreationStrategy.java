package com.ramodage.strategy.record;

import com.ramodage.configuration.Field;
import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;

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
            record.append("<"+schema.getName()+"/>");
            return record.toString();
        }
        record.append("<"+schema.getName()+">\n");
        for (int j = 0; j < fields.size(); j++) {
            Field field = fields.get(j);
            String value = determineFieldValue(field,overriddenFields);
            String nodeValue = "\t<"+field.getName()+">"+value+"</"+field.getName()+">\n";
            record.append(nodeValue);
        }
        record.append("</"+schema.getName()+">");
        return record.toString();
    }
}
