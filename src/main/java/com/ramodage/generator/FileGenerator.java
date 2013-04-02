package com.ramodage.generator;

import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;
import com.ramodage.strategy.FileGenerationContext;
import com.ramodage.strategy.FileGenerationStrategy;
import com.ramodage.configuration.Schema;
import org.apache.log4j.Logger;

/**
 * User: rixonmathew
 * Date: 20/01/13
 * Time: 1:17 PM
 * This class is responsible for creating the actual output files based on options
 */
public class FileGenerator {

    private final Options options;
    private final Schema schema;
    private FileGenerationStrategy strategy;
    private final Logger LOG = Logger.getLogger(FileGenerator.class);

    public FileGenerator(Options options, Schema schema) {
        this.options = options;
        this.schema = schema;
    }

    public void generateFiles() {
        if (options.getGenerationType().equals("class")){
            strategy = createStrategyFromClass();
        } else {
            strategy = FileGenerationContext.strategyForType(options.getGenerationType());
        }
        strategy.generateFileData(schema,options);
    }

    private FileGenerationStrategy createStrategyFromClass() {
        try {
            strategy = (FileGenerationStrategy)Class.forName(options.getGenerationClass()).newInstance();
        } catch (InstantiationException e) {
            LOG.error("Error instantiating class:"+options.getGenerationClass());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            LOG.error("Error accessing class:"+options.getGenerationClass());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOG.error("Invalid class specified:"+options.getGenerationClass());
            e.printStackTrace();
        }
        return strategy;
    }
}
