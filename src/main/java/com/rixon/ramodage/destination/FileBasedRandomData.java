package com.rixon.ramodage.destination;

import com.rixon.ramodage.model.RandomData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rixonmathew
 * Date: 18/06/14
 * Time: 8:44 AM
 */
public class FileBasedRandomData implements RandomData<String> {
    private File outputDirectory;

    public FileBasedRandomData(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    /**
     * This method will return all the underlying data generated. Here TYPE represents the class
     * of the object which contains the random data. The class must have setters/getters as defined in the
     * schema else parsing will fail
     *
     * @return list of all records
     */
    @Override
    public List<String> getAllRecords() {
        List<String> records = new ArrayList<>();
        File[] files = outputDirectory.listFiles();
        if (files==null)
            return records;
        for (File file:files) {
            addContentsToList(file, records);
        }
        return records;
    }

    private void addContentsToList(File file, List<String> records) {
        BufferedReader br;
        try {

            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line =br.readLine())!=null) {
              records.add(line);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("IO exception while reading from file "+file.getName(),e);
        }
    }

    /**
     * This method will provide a record randomly from the list of records generated. The record may
     * be repeated
     *
     * @return a single record
     */
    @Override
    public String getRandomRecord() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
