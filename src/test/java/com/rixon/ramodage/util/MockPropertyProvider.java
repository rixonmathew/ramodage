package com.rixon.ramodage.util;

import java.util.Properties;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 2:06 PM
 *
 * This class is responsible for creating mock properties to be used to test
 * PropertyValidator
 */
public class MockPropertyProvider {
    public static Properties propertiesSetForFile() {
        Properties properties = new Properties();
        properties.setProperty(Constants.SCHEMA,"./src/test/resources/com/rixon/ramodage/util/positions.json");
        properties.setProperty(Constants.DESTINATION_TYPE,"FILE");
        return properties;
    }


    public static Properties propertiesWithoutSchemaSet() {
        Properties properties = new Properties();
        properties.setProperty(Constants.DESTINATION_TYPE,"FILE");
        return properties;
    }

    public static Properties propertiesSetForInMemory() {
        Properties properties = new Properties();
        properties.setProperty(Constants.SCHEMA_CONTENT,"testing1234");
        properties.setProperty(Constants.DESTINATION_TYPE,"IN_MEMORY");
        properties.setProperty(Constants.OBJECT_CLASS_NAME,"com.rixon.ramodage.util.DummyClass");
        return properties;
    }

    public static Properties propertiesWithoutDestination() {
        Properties properties = new Properties();
        properties.setProperty(Constants.SCHEMA_CONTENT,"testing1234");
        return properties;
    }

    public static Properties propertiesWithInvalidObjectClassName() {
        Properties properties = new Properties();
        properties.setProperty(Constants.SCHEMA_CONTENT,"testing1234");
        properties.setProperty(Constants.DESTINATION_TYPE,"IN_MEMORY");
        properties.setProperty(Constants.OBJECT_CLASS_NAME,"com.rixon.ramodage.util.DummyClass123");
        return properties;
    }
}
