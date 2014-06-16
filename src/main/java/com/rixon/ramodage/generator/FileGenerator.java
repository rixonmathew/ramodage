package com.rixon.ramodage.generator;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.destination.DataDestination;
import com.rixon.ramodage.destination.DestinationType;
import com.rixon.ramodage.destination.FileBasedDataDestination;
import com.rixon.ramodage.model.RandomData;
import com.rixon.ramodage.strategy.DataGenerationStrategy;
import com.rixon.ramodage.strategy.DataGenerationStrategyContext;
import org.apache.log4j.Logger;

/**
 * This class is responsible for creating the actual output files based on options
 * User: rixonmathew
 * Date: 20/01/13
 * Time: 1:17 PM
 */
public class FileGenerator<TYPE> implements DataGenerator<TYPE>{

    private Options options;
    private Schema schema;
    private DataDestination dataDestination;
    private DataGenerationStrategy strategy;
    private final Logger LOG = Logger.getLogger(FileGenerator.class);

    private void initializeDataDestination() {
        dataDestination = new FileBasedDataDestination(this.schema,this.options);
    }

    private void generateFiles() {
        if (options.getGenerationType().equals("class")){
            strategy = createStrategyFromClass();
        } else {
            strategy = DataGenerationStrategyContext.strategyForType(options.getGenerationType(), DestinationType.FILE.getDescription());
        }
        strategy.generateData(schema,options,dataDestination);
    }

    private DataGenerationStrategy createStrategyFromClass() {
        try {
            strategy = (DataGenerationStrategy)Class.forName(options.getDataGenerationStrategyClassName()).newInstance();
        } catch (InstantiationException e) {
            LOG.error("Error instantiating class:"+options.getDataGenerationStrategyClassName());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            LOG.error("Error accessing class:"+options.getDataGenerationStrategyClassName());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOG.error("Invalid class specified:"+options.getDataGenerationStrategyClassName());
            e.printStackTrace();
        }
        return strategy;
    }

    /**
     * This method will generate data
     *
     * @param schema the sch
     * @param options
     * @return
     */
    @Override
    public RandomData<TYPE> generateData(Schema schema, Options options) {
        this.schema = schema;
        this.options = options;
        initializeDataDestination();
        this.generateFiles();
        return dataDestination.getRandomData();
    }
}
