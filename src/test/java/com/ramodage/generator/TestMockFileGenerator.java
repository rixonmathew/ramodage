package com.ramodage.generator;

import com.ramodage.configuration.Schema;
import com.ramodage.configuration.SchemaParser;
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
 * This class is used for testing the FileGeneration piece
 * User: rixon
 * Date: 20/01/13
 * Time: 12:31 PM
 */
public class TestMockFileGenerator {


    File directory;

    @Test
    public void testMockDelimitedFileGeneration() {
        String schemaName = "positions.json";
        String schemaPath = TestUtil.getFullPathForFile(schemaName);
        validateMockFileCreation(schemaPath);
    }


    @Test
    public void testMockFixedWidthFileGeneration() throws IOException {
        String schemaName = "instruments.json";
        String schemaPath = TestUtil.getFullPathForFile(schemaName);
        validateFixedWidthMockFileCreation(schemaPath);
    }

    @Test
    public void testMockPositionsFixedWidthFileGeneration() throws IOException{
        String schemaName = "rp_positions_fw.json";
        String schemaPath = TestUtil.getFullPathForFile(schemaName);
        validatePositionsFixedWidthMockFileCreation(schemaPath);

    }


    @After
    public void cleanup() {
        if(directory!=null)
            TestUtil.removeDirectory(directory);
    }


    private void validatePositionsFixedWidthMockFileCreation(String schemaName) throws IOException {
        int expectedMockLength = 287;
        Schema schema = SchemaParser.parse(schemaName);
        final String outputDirectory = schemaName.substring(0,schemaName.indexOf("."));
        Options options = createMockOptions(outputDirectory, "random");
        FileGenerator fileGenerator = new FileGenerator(options,schema);
        assertFiles(options, fileGenerator);
        assertRecordLength(options,expectedMockLength);
    }


    private void validateFixedWidthMockFileCreation(String schemaName) throws IOException {
        int expectedMockLength = 148;
        Schema schema = SchemaParser.parse(schemaName);
        final String outputDirectory = schemaName.substring(0,schemaName.indexOf("."));
        Options options = createMockOptions(outputDirectory, "random");
        FileGenerator fileGenerator = new FileGenerator(options,schema);
        assertFiles(options, fileGenerator);
        assertRecordLength(options,expectedMockLength);
    }



    private void validateMockFileCreation(String schemaName) {
        Schema schema = SchemaParser.parse(schemaName);
        final String outputDirectory = schemaName.substring(0,schemaName.indexOf("."));
        Options options = createMockOptions(outputDirectory, "random");
        FileGenerator fileGenerator = new FileGenerator(options,schema);
        assertFiles(options, fileGenerator);
    }


    private void assertFiles(Options options, FileGenerator fileGenerator) {
        fileGenerator.generateFiles();
        directory = new File(options.getOutputDirectory());
        File file = new File(options.getOutputDirectory());
        assertThat(file.isDirectory(),is(true));
        File[] files = file.listFiles();
        assertNotNull(files);
        assertThat((long) files.length,is(options.getNumberOfFileSplits()));
//        for (File file1:files) {
//            System.out.println("file1 = " + file1.getName());
//        }
    }


    private void assertRecordLength(Options options,int expectedMockLength) throws IOException {
        File file = new File(options.getOutputDirectory());
        assertThat(file.isDirectory(), is(true));
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

    private Options createMockOptions(final String outputDirectory, String generationType) {
        Options options = new Options();
        options.setGenerationType(generationType);
        options.setNumberOfFileSplits(3);
        options.setNumberOfRecordsPerSplit(5000);
        options.setOutputDirectory(outputDirectory);
        return options;
    }
}
