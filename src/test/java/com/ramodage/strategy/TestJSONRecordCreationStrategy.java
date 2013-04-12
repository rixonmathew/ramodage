package com.ramodage.strategy;

import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;
import com.ramodage.strategy.record.RecordCreationContext;
import com.ramodage.strategy.record.RecordCreationStrategy;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * This file is used for testing JSON Record creating strategy
 * User: rixon
 * Date: 12/4/13
 * Time: 8:39 PM
 */
public class TestJSONRecordCreationStrategy {

    @Test
    public void testJSONRecordCreation() {
        Schema schema = MockDataProvider.createMockSchema();
        Options options = MockDataProvider.createMockOptions();
        RecordCreationStrategy recordCreationStrategy = RecordCreationContext.strategyFor("json");
        assertNotNull(recordCreationStrategy);
        String recordString = recordCreationStrategy.createRecord(schema,options,1);
        System.out.println(recordString);
        //assertThat(recordString,is(expectedString));
    }

}
