package com.rixon.ramodage.factory;

import com.rixon.ramodage.destination.DestinationType;
import com.rixon.ramodage.generator.DataGenerator;
import com.rixon.ramodage.generator.FileGenerator;
import com.rixon.ramodage.generator.InMemoryDataGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * User: rixonmathew
 * Date: 13/04/14
 * Time: 7:22 PM
 * This class is the factory that provides the data generator
 */
public class DataGenerationFactory {

    private static final Map<DestinationType,DataGenerator> dataGeneratorMap = new HashMap<>();
    static {
        dataGeneratorMap.put(DestinationType.FILE,new FileGenerator());
        dataGeneratorMap.put(DestinationType.IN_MEMORY, new InMemoryDataGenerator());
    }
    public static <TYPE> DataGenerator<TYPE> getDataGeneratorFor(DestinationType destinationType) {
        if (dataGeneratorMap.containsKey(destinationType)){
            return dataGeneratorMap.get(destinationType);
        }
        throw new IllegalArgumentException("No DataGenerator found for destination type "+destinationType);
    }
}
