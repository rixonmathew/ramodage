package com.rixon.ramodage.main;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.destination.DestinationType;
import com.rixon.ramodage.factory.DataGenerationFactory;
import com.rixon.ramodage.generator.DataGenerator;
import com.rixon.ramodage.model.RandomData;
import com.rixon.ramodage.validator.PropertyValidator;

import java.util.Properties;

/**
 * User: rixonmathew
 * Date: 13/04/14
 * Time: 7:20 PM
 * This class represents the client interface to be used by consumers
 */
public class Ramodage {

    public <TYPE> RandomData<TYPE> generateData(Properties properties) {
        PropertyValidator propertyValidator = new PropertyValidator();
        boolean arePropertiesValid = propertyValidator.arePropertiesValid(properties);
        if(!arePropertiesValid) {
            throw new IllegalArgumentException("All required properties are not specified for data generation");
        }
        OptionsGenerator optionsGenerator = new OptionsGenerator();
        Options options =  optionsGenerator.generate(properties);
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        Schema schema = schemaGenerator.generate(properties);
        DestinationType destinationType = options.getDestinationType();
        DataGenerator<TYPE> dataGenerator = DataGenerationFactory.getDataGeneratorFor(destinationType);
        RandomData<TYPE> randomData = dataGenerator.generateData(schema,options);
        return randomData;
    }
}
