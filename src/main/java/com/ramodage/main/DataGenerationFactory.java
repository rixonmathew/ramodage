package com.ramodage.main;

/**
 * User: rixonmathew
 * Date: 13/04/14
 * Time: 7:22 PM
 * This class is the factory that provides the data generator
 */
public class DataGenerationFactory {
    public static Ramodage getInMemoryDataGenerator() {
        return new Ramodage();
    }
}
