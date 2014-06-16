package com.rixon.ramodage.strategy;

import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.destination.DataDestination;

/**
 * This interface represents the strategy for generating file data.
 * User: rixon
 * Date: 20/01/13
 * Time: 1:58 PM
 */
public interface DataGenerationStrategy<TYPE> {
    /**
     * This method is responsible for generating the data required for the files as per the strategy
     * @param schema the schema that represents the structure of the file
     * @param options options required for generating file
     * @param dataDestination represents the destination where the generated data should be placed
     */
    public void generateData(Schema schema, Options options,DataDestination<TYPE> dataDestination);
}
