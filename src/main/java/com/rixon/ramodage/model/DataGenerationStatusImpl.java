package com.rixon.ramodage.model;

import com.rixon.ramodage.destination.DataDestination;
import com.rixon.ramodage.strategy.ProgressReporter;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 8:17 PM
 */
public class DataGenerationStatusImpl<TYPE> implements DataGenerationStatus<TYPE> {

    private boolean dataGenerationComplete;
    private ProgressReporter progressReporter;
    private DataDestination<TYPE> dataDestination;

    /**
     * This method will indicate if the data generation is complete
     *
     * @return
     */
    @Override
    public boolean isDataGenerationComplete() {
        return dataGenerationComplete;
    }

    /**
     * This method will set the value for the flag
     *
     * @param dataGenerationComplete
     */
    @Override
    public void setDatatGenerationComplete(boolean dataGenerationComplete) {
        this.dataGenerationComplete = dataGenerationComplete;
    }

    /**
     * This method will report the overall progress of the DataGenerationStatus
     *
     * @return
     */
    @Override
    public String getProgress() {
        return String.format("%.2f",progressReporter.overallProgress());
    }

    /**
     * This method will return the RandomData.
     *
     * @return
     */
    @Override
    public RandomData<TYPE> getRandomData() {
        return dataDestination.getRandomData();
    }

    /**
     * This method will set the progressReporter
     *
     * @param progressReporter the entity responsible for reporting the progress of the
     *                         data generation
     */
    @Override
    public void setProgressReporter(ProgressReporter progressReporter) {
        this.progressReporter=progressReporter;
    }

    @Override
    public void setDataDestination(DataDestination<TYPE> dataDestination) {
        this.dataDestination = dataDestination;
    }
}
