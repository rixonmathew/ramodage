package com.ramodage.main;

import com.ramodage.configuration.Schema;
import com.ramodage.configuration.SchemaParser;
import com.ramodage.util.Constants;

import java.util.Properties;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 9:51 AM
 *
 * This class is responsible for building a schema from the properties provided
 */
public class SchemaGenerator {

    public Schema generate(Properties properties) {
        String schemaName = properties.getProperty(Constants.SCHEMA);
        return SchemaParser.parse(schemaName);
    }
}
