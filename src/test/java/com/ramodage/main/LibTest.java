package com.ramodage.main;

import com.ramodage.factory.RamodageFactory;
import com.ramodage.model.RandomData;
import com.ramodage.util.Constants;
import com.ramodage.util.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 4:07 PM
 *
 * This class is used for testing the API behavior of ramodage
 */
public class LibTest {

    private Ramodage ramodage;

    @Before
    public void setup() {
        ramodage = RamodageFactory.getWithDefaultOptions();
    }

    @After
    public void teardown() {
        ramodage = null;
    }

    @Test
    public void testAPIBehaviour() {
       Properties properties = createProperties();
       RandomData<DailyTrade> randomData = ramodage.generateData(properties);
       assertNotNull(randomData);
       List<DailyTrade> allRecords = randomData.getAllRecords();
       assertNotNull(allRecords);
       int expectedSize=500;
       assertThat("Size is not as expected",allRecords.size(),is(expectedSize));
       for (DailyTrade dailyTrade:allRecords) {
           System.out.println("dailyTrade = " + dailyTrade);
       }
    }

    private Properties createProperties() {
        Properties properties = new Properties();
        properties.setProperty(Constants.SCHEMA_CONTENT,getJSONRepresentationOfSchema());
        properties.setProperty(Constants.GENERATION_TYPE,"random");
        properties.setProperty(Constants.OBJECT_CLASS_NAME,DailyTrade.class.getName());
        return properties;
    }

    private String getJSONRepresentationOfSchema() {
        return TestUtil.getFileContents("daily_trades.json");
    }

}
