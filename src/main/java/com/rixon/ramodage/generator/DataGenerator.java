package com.rixon.ramodage.generator;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.model.RandomData;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 6:01 PM
 *
 * This interface represents the entity responsible for generating random data
 * based on the schema and other options provided. Here TYPE represents generic
 * type used for holding the random data. When the random data is requested in an
 * object format it will be of instance TYPE.
 *
 */
public interface DataGenerator<TYPE> {

    /**
     * This method will generate random data.This is a blocking method
     * @param schema represents the constraints and the definition of Random data
     * @param options represents the various options used in generating random
     * @return an instance of RandomData
     */
    public RandomData<TYPE> generateData(Schema schema,Options options);
}
