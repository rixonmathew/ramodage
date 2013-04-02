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

/**
 * This class is used for testing the functionality of Custom file based generation strategy.
 * The use-case here is the quotes data where volume is required but data needs to follow a particular structure
 * where trades are done every 100 milli seconds from start time
 * User: rixon
 * Date: 7/3/13
 * Time: 6:02 PM
 */
public class TestQuotesGeneration {

    File directory;

    @Test
    public void testQuoteDataGenerationStrategy() {
        String quotesSchemaName = "quotes.json";
        Schema schema = SchemaParser.parse(TestUtil.getFullPathForFile(quotesSchemaName));
        String outputDirectory = "mock_quotes";
        Options options = createMockOptions(outputDirectory,"class","com.ramodage.strategy.marketdata.QuoteDataGenerationStrategy");
        FileGenerator fileGenerator = new FileGenerator(options,schema);
        assertFiles(options,fileGenerator);

    }

    @After
    public void cleanup() {
        if(directory!=null)
           TestUtil.removeDirectory(directory);
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
        int expectedMockLength = 96;
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
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String record;
            while ((record=reader.readLine())!=null){
                assertThat(expectedMockLength,is(record.length()));
            }
        }
    }


}
