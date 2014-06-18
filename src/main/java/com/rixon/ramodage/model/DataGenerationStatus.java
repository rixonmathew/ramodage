package com.rixon.ramodage.model;

import com.rixon.ramodage.destination.DataDestination;
import com.rixon.ramodage.strategy.ProgressReporter;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 7:57 PM
 *
 * This interface represents the progress of Random Data Generation
 * activity when its done asynchronously
 */
public interface DataGenerationStatus<TYPE> {

    /**
     * This method will indicate if the data generation is complete
     * @return
     */
    public boolean isDataGenerationComplete();


    /**
     * This method will set the value for the flag
     * @param datatGenerationComplete
     */
    public void setDatatGenerationComplete(boolean datatGenerationComplete);

    /**
     * This method will report the overall progress of the DataGenerationStatus
     * @return
     */
    public String getProgress();


    /**
     * This method will return the RandomData.
     * @return
     */
    public RandomData<TYPE> getRandomData();


    /**
     * This method sets the progress reporter
     * @param progressReporter
     */
    public void setProgressReporter(ProgressReporter progressReporter);

    /**
     * This methods sets the data destination
     * @param dataDestination
     */
    public void setDataDestination(DataDestination<TYPE> dataDestination);
}
