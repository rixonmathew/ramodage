package com.ramodage.strategy;

import com.ramodage.configuration.Field;
import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;
import com.ramodage.strategy.record.RecordCreationContext;
import com.ramodage.strategy.record.RecordCreationStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * This class is used for testing the XMLRecordCreationStrategy.
 * User: rixon
 * Date: 12/4/13
 * Time: 7:13 PM
 */
public class TestXMLRecordCreationStrategy {

    @Test
    public void testXMLRecordCreation() {
        Schema schema = createMockSchema();
        Options options = createMockOptions();
        RecordCreationStrategy recordCreationStrategy = RecordCreationContext.strategyFor("xml");
        assertNotNull(recordCreationStrategy);
        //String expectedString = "<position>\n</position>";
        String recordString = recordCreationStrategy.createRecord(schema,options,1);
        //assertThat(recordString,is(expectedString));
    }

    private Schema createMockSchema() {
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

    private Options createMockOptions() {
        return mock(Options.class);
    }

}
