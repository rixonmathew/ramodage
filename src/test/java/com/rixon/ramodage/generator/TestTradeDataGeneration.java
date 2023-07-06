package com.rixon.ramodage.generator;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.configuration.SchemaParser;
import com.rixon.ramodage.util.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * This is the test class for testing the TradeDataGenerationStrategy
 * User: rixon
 * Date: 11/3/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestTradeDataGeneration {

    File directory;

    @Test
    public void testTradeDataGenerationStrategy() {

        String quotesSchemaName = "daily_trades.json";
        Schema schema = SchemaParser.parseFromFile(TestUtil.getFullPathForFile(quotesSchemaName));
        String outputDirectory = "mock_trades";
        Options options = createMockOptions(outputDirectory,"class","com.rixon.ramodage.strategy.marketdata.TradesGenerationStrategy");
        FileGenerator fileGenerator = new FileGenerator();
        assertFiles(schema,options,fileGenerator);
    }

    @AfterEach
    public void cleanup() {
        if(directory!=null) {
            TestUtil.removeDirectory(directory);
        }
    }

    private Options createMockOptions(final String outputDirectory, String generationType,String generationClass) {
        Options options = new Options();
        options.setGenerationType(generationType);
        options.setDataGenerationStrategyClassName(generationClass);
        options.setNumberOfFileSplits(3);
        options.setNumberOfRecordsPerSplit(5000);
        options.setOutputDirectory(outputDirectory);
        return options;
    }
    private void assertFiles(Schema schema,Options options, FileGenerator fileGenerator) {
        fileGenerator.generateData(schema,options);
        File file = new File(options.getOutputDirectory());
        directory = file;
        assertThat(file.isDirectory(), is(true));
        File[] files = file.listFiles();
        assertNotNull(files);
        assertThat((long) files.length, is(options.getNumberOfFileSplits()));
        for (File file1:files) {
            System.out.println("file1 = " + file1.getName());
        }
        int expectedMockLength = 68;
        try {
            assertRecordLength(options,expectedMockLength);
        } catch (IOException e) {
            throw new RuntimeException("Error in IO ",e);
        }
    }

    private void assertRecordLength(Options options,int expectedMockLength) throws IOException {
        File file = new File(options.getOutputDirectory());
        assertThat(file.isDirectory(),is(true));
        File[] files = file.listFiles();
        assertNotNull(files);
        for(File file1:files) {
            assertTrue("File is empty "+file1, file1.length() > 0);
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String record;
            while ((record=reader.readLine())!=null){
                assertThat(expectedMockLength,is(record.length()));
            }
        }
    }

}
