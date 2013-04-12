package com.ramodage.strategy;

import com.ramodage.configuration.Field;
import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockDataProvider {

    public static Schema createMockSchema() {
        Schema schema = new Schema();
        schema.setName("position");
        schema.setType("xml");
        List<Field> fields = new ArrayList<Field>();
        Field<String> name = new Field<String>();
        name.setType("String");
        name.setName("name");
        name.setMinLength(40);
        name.setMaxLength(60);
        fields.add(name);
        schema.setFields(fields);
        return schema;
    }

    public static Options createMockOptions() {
        return Mockito.mock(Options.class);
    }
}