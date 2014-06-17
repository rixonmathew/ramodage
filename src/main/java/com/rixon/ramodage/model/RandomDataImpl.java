package com.rixon.ramodage.model;

import com.rixon.ramodage.strategy.ProgressReporter;

import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 7:07 PM
 */
public class RandomDataImpl<TYPE> implements RandomData<TYPE> {

    private List<TYPE> records;
    private StringWriter progressDestination;
    private Random random = new Random();
    private boolean dataGenerationComplete=false;
    private ProgressReporter progressReporter;

    public RandomDataImpl(List<TYPE> records) {
        this.records = records;
    }

    /**
     * This method will return all the underlying data generated. Here TYPE represents the class
     * of the object which contains the random data. The class must have setters/getters as defined in the
     * schema else parsing will fail
     *
     * @return list of all records
     */
    @Override
    public List<TYPE> getAllRecords() {
        return Collections.unmodifiableList(records);
    }

    /**
     * This method will provide a record randomly from the list of records generated. The record may
     * be repeated
     *
     * @return a single record
     */
    @Override
    public TYPE getRandomRecord() {
        if (records==null || records.isEmpty())
            return null;
        return records.get(random.nextInt(records.size()));
    }

    /**
     * This flag can be queried to indicate that data generation is completed
     *
     * @return true if data generation is complete else false
     */
    @Override
    public boolean isDataGenerationComplete() {
        return dataGenerationComplete;
    }

    @Override
    public void setDataGenerationComplete(boolean dataGenerationComplete) {
        this.dataGenerationComplete = dataGenerationComplete;
    }

    /**
     * This method is used to show the progress;
     *
     * @return a string indicating the progress;
     */
    @Override
    public String getProgress() {
        if (progressReporter==null)
            return "0.0";
        return String.format("%.2f",progressReporter.overallProgress());
    }

    /**
     * This method will inject the progress reporter to RandomData to be able to
     * report the progress
     *
     * @param progressReporter
     */
    @Override
    public synchronized void setProgressReporter(ProgressReporter progressReporter) {
        this.progressReporter = progressReporter;
    }


}
