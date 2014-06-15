package com.ramodage.generator;

import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;
import com.ramodage.model.RandomData;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 6:01 PM
 *
 * This interface represents the entity responsible for generating random data based on the
 * schema and other options provided
 */
public interface DataGenerator<TYPE> {

    /**
     * This method will generate data
     * @param schema
     * @param options
     * @return
     */
    public RandomData<TYPE> generateData(Schema schema,Options options);
}
