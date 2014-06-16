package com.rixon.ramodage.main;

import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.configuration.SchemaParser;
import com.rixon.ramodage.util.Constants;

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
        Schema schema = null;
        if (schemaName==null) {
            String schemaContent = properties.getProperty(Constants.SCHEMA_CONTENT);
            if (schemaContent!=null) {
                schema = SchemaParser.parseFromContent(schemaContent);
            }
        } else {
            schema = SchemaParser.parseFromFile(schemaName);
        }
        return schema;
    }
}
