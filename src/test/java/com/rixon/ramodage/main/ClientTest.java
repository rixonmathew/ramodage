package com.rixon.ramodage.main;

import com.rixon.ramodage.util.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.fail;

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


    private Properties createMockProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(TestUtil.getFullPathForFile("mockProperties.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @AfterEach
    public void cleanup() {
        File file = new File(System.getProperty("user.dir"));
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("output") || name.endsWith("output");
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
            MainClient.main(args);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            fail("an exception occurred executing the driver");
        }
    }
}
