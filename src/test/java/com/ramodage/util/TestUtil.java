package com.ramodage.util;

import java.io.File;
import java.net.URL;

/**
 * This is util class that contains common methods used in testing
 * User: rixon
 * Date: 2/4/13
 * Time: 3:25 PM
 */
public class TestUtil {

    public static String getFullPathForFile(String fileName) {
        URL fileURL = TestUtil.class.getResource(fileName);
        return fileURL.getPath();
    }

    public static void removeDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
             removeDirectory(file);
            }
            directory.delete();
        } else {
            directory.delete();
        }
    }
}

