package com.ramodage.generator;

import com.ramodage.configuration.Schema;
import com.ramodage.configuration.SchemaParser;
import com.ramodage.generator.FileGenerator;
import com.ramodage.configuration.Options;
import com.ramodage.util.TestUtil;
import org.junit.After;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        Schema schema = SchemaParser.parse(TestUtil.getFullPathForFile(quotesSchemaName));
        String outputDirectory = "mock_trades";
        Options options = createMockOptions(outputDirectory,"class","com.ramodage.strategy.marketdata.TradesGenerationStrategy");
        FileGenerator fileGenerator = new FileGenerator(options,schema);
        assertFiles(options,fileGenerator);
    }

    @After
    public void cleanup() {
        if(directory!=null) {
            TestUtil.removeDirectory(directory);
        }
    }

    private Options createMockOptions(final String outputDirectory, String generationType,String generationClass) {
        Options options = new Options();
        options.setGenerationType(generationType);
        options.setGenerationClass(generationClass);
        options.setNumberOfFileSplits(3);
        options.setNumberOfRecordsPerSplit(5000);
        options.setOutputDirectory(outputDirectory);
        return options;
    }
    private void assertFiles(Options options, FileGenerator fileGenerator) {
        fileGenerator.generateFiles();
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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void assertRecordLength(Options options,int expectedMockLength) throws IOException {
        File file = new File(options.getOutputDirectory());
        assertThat(file.isDirectory(),is(true));
        File[] files = file.listFiles();
        assertNotNull(files);
        for(File file1:files) {
            assertTrue("File is empty", file1.length() > 0);
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String record;
            while ((record=reader.readLine())!=null){
                assertThat(expectedMockLength,is(record.length()));
            }
        }
    }

}
