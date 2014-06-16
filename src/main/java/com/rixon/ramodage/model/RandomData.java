package com.rixon.ramodage.model;

import java.util.List;

/**
 * User: rixonmathew
 * Date: 13/04/14
 * Time: 7:34 PM
 * This interface represents the entity that provde
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
}
