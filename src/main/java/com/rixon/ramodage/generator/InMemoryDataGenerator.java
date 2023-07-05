package com.rixon.ramodage.generator;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.destination.DataDestination;
import com.rixon.ramodage.destination.DestinationType;
import com.rixon.ramodage.destination.InMemoryDestination;
import com.rixon.ramodage.model.DataGenerationStatus;
import com.rixon.ramodage.model.RandomData;
import com.rixon.ramodage.strategy.DataGenerationStrategy;
import com.rixon.ramodage.strategy.DataGenerationStrategyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 6:09 PM
 *
 * This class represents the generator for generating data in memory
 */
public class InMemoryDataGenerator<TYPE> implements DataGenerator<TYPE> {

    private final Logger LOG = LoggerFactory.getLogger(FileGenerator.class);
    /**
     * This method will generate data based on the schema and options
     *
     * @param schema defines the constraints for the data to be generated
     * @param options the various options to use
     * @return RandomData which is an interface to query the underlying the data generated;
     */
    @Override
    public RandomData<TYPE> generateData(Schema schema, Options options) {
        DataDestination<TYPE> dataDestination = new InMemoryDestination(schema, options);
        DataGenerationStrategy strategy = DataGenerationStrategyContext.strategyForOptions(options.getGenerationType(),
                                                                              DestinationType.IN_MEMORY.getDescription(),
                                                                              options.getDataGenerationStrategyClassName());
        strategy.generateData(schema,options,dataDestination);
        return dataDestination.getRandomData();
    }

    /**
     * This method is used for generating data in a separate thread. This is especially
     * useful when huge data is to be generated. This method will return immediately.
     * RandomData can be queried to find out the progress of the data generation.
     *
     * @param schema  represents the constraints and the definition of Random data
     * @param options represents the various options used in generating random
     * @return an instance of RandomData
     */
    @Override
    public DataGenerationStatus<TYPE> generateDataAsynchronously(Schema schema, Options options) {
        DataDestination<TYPE> dataDestination = new InMemoryDestination(schema, options);
        DataGenerationStrategy strategy = DataGenerationStrategyContext.strategyForOptions(options.getGenerationType(),
                                                                        DestinationType.IN_MEMORY.getDescription(),
                                                                        options.getDataGenerationStrategyClassName());
        return strategy.generateDataAsynchronously(schema,options,dataDestination);
    }
}
