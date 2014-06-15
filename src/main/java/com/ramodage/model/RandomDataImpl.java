package com.ramodage.model;

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
    private Random random = new Random();

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
        TYPE record = records.get(random.nextInt(records.size()));
        return record;
    }
}
