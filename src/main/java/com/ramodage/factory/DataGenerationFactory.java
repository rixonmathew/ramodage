package com.ramodage.factory;

import com.ramodage.generator.DataGenerator;
import com.ramodage.generator.InMemoryDataGenerator;

/**
 * User: rixonmathew
 * Date: 13/04/14
 * Time: 7:22 PM
 * This class is the factory that provides the data generator
 */
public class DataGenerationFactory {

    public static <TYPE> DataGenerator<TYPE> getInMemoryDataGenerator() {
        return new InMemoryDataGenerator<TYPE>();
    }
}
