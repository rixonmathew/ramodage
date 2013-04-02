package com.ramodage.main;

import com.ramodage.configuration.Schema;
import com.ramodage.configuration.SchemaParser;
import com.ramodage.generator.FileGenerator;
import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;
import com.ramodage.configuration.SchemaParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This class represents the entry point
 * User: rixon
 * Date: 22/1/13
 * Time: 4:24 PM
 */
public class MockGenerationClient {

    private static final String DEFAULT_GENERATION_TYPE = "random";
    private static final int DEFAULT_NUMBER_OF_SPLITS = 5;
    private static final String DEFAULT_OUTPUT_DIR = "output_"+System.currentTimeMillis();
    private static final long DEFAULT_RECORDS_PER_SPLIT = 10000;
    private static final int DEFAULT_THREADS = 16;
    private static final String SCHEMA = "schema";
    private static final String RECORDS_PER_SPLIT = "recordsPerSplit";
    private static final String OUTPUT_DIRECTORY = "outputDirectory";
    private static final String NUMBER_OF_SPLITS = "numberOfSplits";
    private static final String GENERATION_TYPE = "generationType";
    private static final String THREADS = "threads";

    public static void main(String[] args) throws IOException {
        if(args.length!=2) {
            System.err.println("Usage java -jar ramodage --file <properties.file>");
            return;
        }
        long startTime = System.currentTimeMillis();
        String inputPropertiesFileName = args[1];
        Properties properties = loadProperties(inputPropertiesFileName);
        boolean isValid = checkProperties(properties);
        if(!isValid){
            System.err.println("All required properties are not specified.Please check the property file:"+inputPropertiesFileName);
            return;
        }

        Schema schema = createSchema(properties);
        Options options = createOptions(properties);
        FileGenerator fileGenerator = new FileGenerator(options,schema);
        fileGenerator.generateFiles();
        long endTime = System.currentTimeMillis();
        System.out.println("\nTime taken = " + (endTime-startTime)/1000.0f+" seconds");
    }

    private static boolean checkProperties(Properties properties) {
        boolean valid = true;
        String schemaFileName = properties.getProperty(SCHEMA);
        if(schemaFileName==null||schemaFileName.isEmpty()){
            valid = false;
        }
        File file = new File(schemaFileName);
        if (!file.exists()||!file.canRead()){
            valid = false;
        }
        return valid;
    }

    private static Properties loadProperties(String inputPropertiesFileName) throws IOException {
        FileReader propertiesFileReader = new FileReader(inputPropertiesFileName);
        Properties properties = new Properties();
        properties.load(propertiesFileReader);
        return properties;
    }

    private static Schema createSchema(Properties properties) {
        String schemaName = properties.getProperty(SCHEMA);
        return SchemaParser.parse(schemaName);
    }

    private static Options createOptions(Properties properties) {
        Options options = new Options();
        setGenerationType(properties, options);
        setNumberOfSplits(properties, options);
        setOutputDirectory(properties, options);
        setRecordCount(properties, options);
        setNumberOfThreads(properties,options);
        return options;
    }

    private static void setNumberOfThreads(Properties properties, Options options) {
        String threadCount = properties.getProperty(THREADS);
        int numberOfThread = DEFAULT_THREADS;
        if (threadCount!=null && !threadCount.isEmpty()){
         numberOfThread = Integer.valueOf(threadCount);
        }
        options.setNumberOfThreads(numberOfThread);
    }

    private static void setRecordCount(Properties properties, Options options) {
        String recordCount = properties.getProperty(RECORDS_PER_SPLIT);
        long numberOfRecords;
        if(recordCount==null||recordCount.isEmpty()){
            numberOfRecords = DEFAULT_RECORDS_PER_SPLIT;
        } else {
            numberOfRecords = Long.valueOf(recordCount);
        }
        options.setNumberOfRecordsPerSplit(numberOfRecords);
    }

    private static void setOutputDirectory(Properties properties, Options options) {
        String outputDirectory = properties.getProperty(OUTPUT_DIRECTORY);
        if(outputDirectory==null||outputDirectory.isEmpty()){
            outputDirectory = DEFAULT_OUTPUT_DIR;
        }
        options.setOutputDirectory(outputDirectory);
    }

    private static void setNumberOfSplits(Properties properties, Options options) {
        String splits = properties.getProperty(NUMBER_OF_SPLITS);
        int numberOfSplits;
        if(splits==null || splits.isEmpty()){
            numberOfSplits = DEFAULT_NUMBER_OF_SPLITS;
        } else {
            numberOfSplits = Integer.valueOf(splits);
        }
        options.setNumberOfFileSplits(numberOfSplits);
    }

    private static void setGenerationType(Properties properties, Options options) {
        String generationType = properties.getProperty(GENERATION_TYPE);
        if (generationType==null||generationType.isEmpty()) {
            generationType= DEFAULT_GENERATION_TYPE;
        }
        options.setGenerationType(generationType);
    }
}
