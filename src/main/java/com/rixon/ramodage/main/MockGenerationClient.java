package com.rixon.ramodage.main;

import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.generator.FileGenerator;
import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.validator.PropertyValidator;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This class represents the entry point
 * User: rixon
 * Date: 22/1/13
 * Time: 4:24 PM
 */
public class MockGenerationClient {

    public static void main(String[] args) throws IOException {
        if(args.length!=2) {
            System.err.println("Usage java -jar ramodage --file <properties.file>");
            return;
        }
        long startTime = System.currentTimeMillis();
        String inputPropertiesFileName = args[1];
        Properties properties = loadProperties(inputPropertiesFileName);
        PropertyValidator propertyValidator = new PropertyValidator();
        boolean isValid = propertyValidator.arePropertiesValid(properties);
        if(!isValid){
            System.err.println("All required properties are not specified.Please check the property file:"+inputPropertiesFileName);
            return;
        }
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        Schema schema = schemaGenerator.generate(properties);
        OptionsGenerator optionsGenerator = new OptionsGenerator();
        Options options = optionsGenerator.generate(properties);
        FileGenerator fileGenerator = new FileGenerator();
        fileGenerator.generateData(schema,options);
        long endTime = System.currentTimeMillis();
        System.out.println("\nTime taken = " + (endTime-startTime)/1000.0f+" seconds");
    }

    private static Properties loadProperties(String inputPropertiesFileName) throws IOException {
        FileReader propertiesFileReader = new FileReader(inputPropertiesFileName);
        Properties properties = new Properties();
        properties.load(propertiesFileReader);
        return properties;
    }

}
