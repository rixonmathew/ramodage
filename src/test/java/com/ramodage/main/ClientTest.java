package com.ramodage.main;

import com.ramodage.util.TestUtil;
import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.fail;

/**
 * This class is the test file for testing Client
 * User: rixon
 * Date: 6/2/13
 * Time: 10:41 AM
 */
public class ClientTest {

    @Test
    public void testDriver() {
        String[] args = mockArguments(TestUtil.getFullPathForFile("mockProperties.properties"));
        executeMain(args);
    }

    @Test
    public void testDriverWithMinOptions() {
        String[] args = mockArguments(TestUtil.getFullPathForFile("minProperties.properties"));
        executeMain(args);
    }


    @Test
    public void testRecordDataGeneration() {
        //This method is used for testing the record based data generation
        Ramodage ramodage = DataGenerationFactory.getInMemoryDataGenerator();

        Properties properties = createMockProperties();
        RandomData randomData =  ramodage.generateData(properties);
        assertNotNull(randomData);
    }

    private Properties createMockProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(TestUtil.getFullPathForFile("mockProperties.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @After
    public void cleanup() {
        File file = new File(System.getProperty("user.dir"));
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.startsWith("output") || name.endsWith("output"))
                    return true;
                return false;
            }
        });
        for (File file1:files){
            System.out.println("file1 = " + file1);
            TestUtil.removeDirectory(file1);
        }
    }

    private String[] mockArguments(String propertyFileName) {
        String[] args = new String[2];
        args[0] = "--file";
        args[1] = propertyFileName;
        return args;
    }

    private void executeMain(String[] args) {
        try {
            MockGenerationClient.main(args);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            fail("an exception occurred executing the driver");
        }
    }
}
