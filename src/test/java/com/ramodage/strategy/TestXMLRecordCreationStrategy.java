package com.ramodage.strategy;

import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;
import com.ramodage.strategy.record.RecordCreationContext;
import com.ramodage.strategy.record.RecordCreationStrategy;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * This class is used for testing the XMLRecordCreationStrategy.
 * User: rixon
 * Date: 12/4/13
 * Time: 7:13 PM
 */
public class TestXMLRecordCreationStrategy {

    @Test
    public void testXMLRecordCreation() {
        Schema schema = MockDataProvider.createMockSchema();
        Options options = MockDataProvider.createMockOptions();
        RecordCreationStrategy recordCreationStrategy = RecordCreationContext.strategyFor("xml");
        assertNotNull(recordCreationStrategy);
        //String expectedString = "<position>\n</position>";
        String recordString = recordCreationStrategy.createRecord(schema,options,1);
        //assertThat(recordString,is(expectedString));
    }
}