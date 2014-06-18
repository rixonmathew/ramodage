package com.rixon.ramodage.destination;

import com.rixon.ramodage.model.RandomData;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 8:12 PM
 *
 * This interface represents the entity that abstracts the destination of the random data that has been generated.
 */
public interface DataDestination<TYPE> {

    /**
     * This method is used for preparing the destination to accept the random data
     */
    public void prepareForDataGeneration();


    /**
     * This method is used for adding a record in String format to the data destination
     * @param split the split in which data is being added
     * @param record the record representing the random data
     */
    public void addRecord(String split,String record);

    /**
     * This method is used for adding a record as object to data destination
     * @param split the split in which data is being added
     * @param object the object representing the random data
     */
    public void addObject(String split,TYPE object);

    /**
     * This method will provide the RandomData object. The RandomData is used by clients to access their random data
     * @return random data object
     */
    public RandomData<TYPE> getRandomData();

    /**
     * This method will provide with the meta data about the destination
     * @return destination meta data;
     */
    public DestinationMetaData getDestinationMetaData();

    /**
     * This method is used to clear the state of data generation
     */
    public void clear();
}
