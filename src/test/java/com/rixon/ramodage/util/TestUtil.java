package com.rixon.ramodage.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            if (files==null)
                return;
            for (File file : files) {
                removeDirectory(file);
            }
            directory.delete();
        } else {
            directory.delete();
        }
    }

    public static String getFileContents(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {

            BufferedReader br = new BufferedReader(new FileReader(getFullPathForFile(fileName)));
            String line = br.readLine();
            stringBuilder.append(line);
            while (line != null) {
                line = br.readLine();
                if (line != null)
                    stringBuilder.append(line);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("IO exception while reading from file "+fileName,e);
        }

        return stringBuilder.toString();

    }
}

