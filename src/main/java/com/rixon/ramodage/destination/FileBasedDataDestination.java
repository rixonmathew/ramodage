package com.rixon.ramodage.destination;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.model.RandomData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 8:23 PM
 */
public class FileBasedDataDestination extends AbstractDataDestination<String> {

    private File outputDirectory;
    private final Map<Long, File> filesForSplit;
    private final Map<String,BufferedWriter> dataWriters= new ConcurrentHashMap<>();

    public FileBasedDataDestination(Schema schema,Options options) {
        super(schema,options);
        filesForSplit = new HashMap<>();
    }

    /**
     * This method is used for preparing the destination to accept the random data
     */
    @Override
    public void prepareForDataGeneration() {
        this.generateOutputDirectories();
        this.createFilesForSplits();

    }

    /**
     * This method will provide the RandomData object. The RandomData is used by clients to access their random data
     *
     * @return random data object
     */
    @Override
    public RandomData<String> getRandomData() {
        return new FileBasedRandomData(outputDirectory);
    }

    private void generateOutputDirectories() {
        if (options.getOutputDirectory() == null || options.getOutputDirectory().isEmpty()) {
            String name = "output_" + System.currentTimeMillis();
            outputDirectory = new File(name);
        } else {
            outputDirectory = new File(options.getOutputDirectory());
        }
        outputDirectory.mkdirs();
    }

    private void createFilesForSplits() {
        List<String> splitNames = new ArrayList<>();
        String fileName = schema.getName() + "-part";
        for (int split = 0; split < options.getNumberOfFileSplits(); split++) {
            String splitFileName = fileName + "-" + split;
            File outputFile = new File(outputDirectory.getAbsolutePath() + File.separator + splitFileName);
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("Could not created file in directory "+outputDirectory.getAbsolutePath()+" Check permissions",e);
            }
            filesForSplit.put((long) split, outputFile);
            splitNames.add(Integer.toString(split));
        }
        createDestinationMetaData(splitNames);
    }

    private void createDestinationMetaData(List<String> splitFileNames) {
        destinationMetaData = new DestinationMetaData(DestinationType.FILE,outputDirectory.getName(),splitFileNames);
    }

    /**
     * This method is used to clear the state of data generation
     */
    @Override
    public void clear() {
        for (Map.Entry<String,BufferedWriter> entry:dataWriters.entrySet()) {
            try {
                entry.getValue().close();
            } catch (IOException e) {
                throw new RuntimeException("Could not close file write for "+entry.getKey(),e);
            }
        }
        dataWriters.clear();
        filesForSplit.clear();
    }

    /**
     * This method is used for adding a record in String format to the data destination
     *
     * @param split  the split t
     * @param record the record representing the random data
     */
    @Override
    public void addRecord(String split, String record) {
        if (dataWriters.containsKey(split)) {
            BufferedWriter writer = dataWriters.get(split);
            try {
                writer.write(record);
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException("Could not write to file for split "+split,e);
            }

        } else {
            File file = filesForSplit.get(Long.valueOf(split));
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(record);
                writer.newLine();
                dataWriters.put(split,writer);
            } catch (IOException e) {
                throw new RuntimeException("Could not write to file for split "+split,e);
            }
        }

    }
}
