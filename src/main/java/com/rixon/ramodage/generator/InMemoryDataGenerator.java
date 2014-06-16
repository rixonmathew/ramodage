package com.rixon.ramodage.generator;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.destination.DataDestination;
import com.rixon.ramodage.destination.DestinationType;
import com.rixon.ramodage.destination.InMemoryDestination;
import com.rixon.ramodage.model.RandomData;
import com.rixon.ramodage.strategy.DataGenerationStrategy;
import com.rixon.ramodage.strategy.DataGenerationStrategyContext;
import org.apache.log4j.Logger;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 6:09 PM
 *
 * This class represents the generator for generating data in memory
 */
public class InMemoryDataGenerator<TYPE> implements DataGenerator<TYPE> {

    private final Logger LOG = Logger.getLogger(FileGenerator.class);
    private DataDestination dataDestination;


    /**
     * This method will generate data based on the schema and options
     *
     * @param schema defines the constraints for the data to be generated
     * @param options the various options to use
     * @return RandomData which is an interface to query the underlying the data generated;
     */
    @Override
    public RandomData<TYPE> generateData(Schema schema, Options options) {
        dataDestination = new InMemoryDestination(schema,options);
        DataGenerationStrategy strategy;
        //TODO add code to create strategy from class
        strategy = DataGenerationStrategyContext.strategyForType(options.getGenerationType(), DestinationType.IN_MEMORY.getDescription());
        strategy.generateData(schema,options,dataDestination);
        return dataDestination.getRandomData();
    }
}
