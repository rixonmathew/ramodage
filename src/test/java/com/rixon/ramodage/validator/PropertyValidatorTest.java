package com.rixon.ramodage.validator;

import com.rixon.ramodage.util.MockPropertyProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 2:03 PM
 */
public class PropertyValidatorTest {

    private PropertyValidator propertyValidator;

    @BeforeEach
    public void setup() {
        propertyValidator = new PropertyValidator();
    }

    @AfterEach
    public void tearDown(){
        propertyValidator = null;
    }

    @Test
    public void test_scenario_where_all_properties_are_valid_for_file() {
        Properties properties = MockPropertyProvider.propertiesSetForFile();
        boolean valid = propertyValidator.arePropertiesValid(properties);
        assertTrue(valid);
    }

    @Test
    public void test_scenario_where_all_properties_are_valid_for_in_memory() {
        Properties properties = MockPropertyProvider.propertiesSetForInMemory();
        boolean valid = propertyValidator.arePropertiesValid(properties);
        assertTrue(valid);
    }

    @Test
    public void test_scenario_where_schema_is_not_set(){
        Properties properties = MockPropertyProvider.propertiesWithoutSchemaSet();
        assertThrows(IllegalArgumentException.class,()->propertyValidator.arePropertiesValid(properties));
    }


    @Test()
    public void test_scenario_where_destination_is_not_set() {
        Properties properties = MockPropertyProvider.propertiesWithoutDestination();
        assertThrows(IllegalArgumentException.class,()->propertyValidator.arePropertiesValid(properties));
    }


    @Test()
    public void test_scenario_where_object_class_name_is_invalid() {
        Properties properties = MockPropertyProvider.propertiesWithInvalidObjectClassName();
        assertThrows(IllegalArgumentException.class,()->propertyValidator.arePropertiesValid(properties));
    }

}
