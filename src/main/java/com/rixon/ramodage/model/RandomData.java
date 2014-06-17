package com.rixon.ramodage.model;

import com.rixon.ramodage.strategy.ProgressReporter;

import java.util.List;

/**
 * User: rixonmathew
 * Date: 13/04/14
 * Time: 7:34 PM
 * This interface represents the entity that provides access to the Random Data that has been generated
 */
public interface RandomData <TYPE> {
    /**
     * This method will return all the underlying data generated. Here TYPE represents the class
     * of the object which contains the random data. The class must have setters/getters as defined in the
     * schema else parsing will fail
     * @return list of all records
     */
    public List<TYPE> getAllRecords();


    /**
     * This method will provide a record randomly from the list of records generated. The record may
     * be repeated
     * @return a single record
     */
    public TYPE getRandomRecord();

    /**
     * This flag can be queried to indicate that data generation is completed
     * @return true if data generation is complete else false
     */
    public boolean isDataGenerationComplete();

    /**
     * This method is used to update the completion flag
     * @param dataGenerationComplete
     */
    public void setDataGenerationComplete(boolean dataGenerationComplete);


    /**
     * This method is used to show the progress;
     * @return a string indicating the progress;
     */
    public String getProgress();

    /**
     * This method will inject the progress reporter to RandomData to be able to
     * report the progress
     * @param progressReporter
     */
    public void setProgressReporter(ProgressReporter progressReporter);
}
