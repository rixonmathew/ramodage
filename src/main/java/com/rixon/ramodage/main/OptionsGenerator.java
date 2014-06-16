package com.rixon.ramodage.main;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.util.Constants;

import java.util.Properties;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 10:02 AM
 *
 * This class is used for generating the options from the properties
 */
public class OptionsGenerator {

    public Options generate(Properties properties) {
        Options options = new Options();
        setGenerationType(properties, options);
        setNumberOfSplits(properties, options);
        setOutputDirectory(properties, options);
        setRecordCount(properties, options);
        setNumberOfThreads(properties,options);
        setObjectClassName(properties,options);
        return options;
    }

    private void setNumberOfThreads(Properties properties, Options options) {
        String threadCount = properties.getProperty(Constants.THREADS);
        int numberOfThread = Constants.DEFAULT_THREADS;
        if (threadCount!=null && !threadCount.isEmpty()){
            numberOfThread = Integer.valueOf(threadCount);
        }
        options.setNumberOfThreads(numberOfThread);
    }

    private void setRecordCount(Properties properties, Options options) {
        String recordCount = properties.getProperty(Constants.RECORDS_PER_SPLIT);
        long numberOfRecords;
        if(recordCount==null||recordCount.isEmpty()){
            numberOfRecords = Constants.DEFAULT_RECORDS_PER_SPLIT;
        } else {
            numberOfRecords = Long.valueOf(recordCount);
        }
        options.setNumberOfRecordsPerSplit(numberOfRecords);
    }

    private void setOutputDirectory(Properties properties, Options options) {
        String outputDirectory = properties.getProperty(Constants.OUTPUT_DIRECTORY);
        if(outputDirectory==null||outputDirectory.isEmpty()){
            outputDirectory = Constants.DEFAULT_OUTPUT_DIR;
        }
        options.setOutputDirectory(outputDirectory);
    }

    private void setNumberOfSplits(Properties properties, Options options) {
        String splits = properties.getProperty(Constants.NUMBER_OF_SPLITS);
        int numberOfSplits;
        if(splits==null || splits.isEmpty()){
            numberOfSplits = Constants.DEFAULT_NUMBER_OF_SPLITS;
        } else {
            numberOfSplits = Integer.valueOf(splits);
        }
        options.setNumberOfFileSplits(numberOfSplits);
    }

    private void setGenerationType(Properties properties, Options options) {
        String generationType = properties.getProperty(Constants.GENERATION_TYPE);
        if (generationType==null||generationType.isEmpty()) {
            generationType= Constants.DEFAULT_GENERATION_TYPE;
        }
        options.setGenerationType(generationType);
    }

    private void setObjectClassName(Properties properties, Options options) {
        String objectClassName = properties.getProperty(Constants.OBJECT_CLASS_NAME);
        if (objectClassName!=null) {
            options.setObjectClassName(objectClassName);
        }
    }


}
