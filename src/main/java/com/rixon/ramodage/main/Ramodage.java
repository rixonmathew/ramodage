package com.rixon.ramodage.main;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.factory.DataGenerationFactory;
import com.rixon.ramodage.generator.DataGenerator;
import com.rixon.ramodage.model.DataGenerationStatus;
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
        validateProperties(properties);
        Options options = generateOptions(properties);
        Schema schema = generateSchema(properties);
        DataGenerator<TYPE> dataGenerator = DataGenerationFactory.getDataGeneratorFor(options.getDestinationType());
        return dataGenerator.generateData(schema,options);
    }

    public <TYPE> DataGenerationStatus<TYPE> generateDataAsynchronously(Properties properties) {
        validateProperties(properties);
        Options options = generateOptions(properties);
        Schema schema = generateSchema(properties);
        DataGenerator<TYPE> dataGenerator = DataGenerationFactory.getDataGeneratorFor(options.getDestinationType());
        return dataGenerator.generateDataAsynchronously(schema, options);

    }

    private void validateProperties(Properties properties) {
        PropertyValidator propertyValidator = new PropertyValidator();
        boolean arePropertiesValid = propertyValidator.arePropertiesValid(properties);
        if(!arePropertiesValid) {
            throw new IllegalArgumentException("All required properties are not specified for data generation");
        }
    }

    private Options generateOptions(Properties properties) {
        OptionsGenerator optionsGenerator = new OptionsGenerator();
        return optionsGenerator.generate(properties);
    }

    private Schema generateSchema(Properties properties) {
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.generate(properties);
    }

}
