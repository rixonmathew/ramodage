package com.rixon.ramodage.strategy;

import com.rixon.ramodage.destination.DestinationType;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * User: rixonmathew
 * Date: 20/01/13
 * Time: 8:26 PM
 * This class represents the context  for different FileGeneration strategies
 */
public class DataGenerationStrategyContext {

    private static final String RANDOM="random";
    private static final Logger LOG = Logger.getLogger(DataGenerationStrategyContext.class);

    private static final Map<String,DataGenerationStrategy> strategies;
    static {
        strategies = new HashMap<>();
        strategies.put(createKey(RANDOM, DestinationType.FILE.getDescription()),new RandomFileGenerationStrategy());
        strategies.put(createKey(RANDOM, DestinationType.IN_MEMORY.getDescription()),new RandomInMemoryGenerationStrategy());
    }

    public static DataGenerationStrategy strategyForType(String dataGenerationType,String destinationType) {
        String key = createKey(dataGenerationType,destinationType);
        if (!strategies.containsKey(key)) {
            LOG.debug("Invalid file generation type specified :"+dataGenerationType);
        }
        return strategies.get(key);
    }

    private static String createKey(String dataGenerationType, String destinationType) {
        return dataGenerationType+":"+destinationType;
    }
}
