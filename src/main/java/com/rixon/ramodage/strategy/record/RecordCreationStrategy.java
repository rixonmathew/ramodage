package com.rixon.ramodage.strategy.record;

import com.rixon.ramodage.configuration.Field;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.configuration.Options;

import java.util.Map;

/**
 * This interface implements a strategy for creating records based on file type ( delimited,fixedWidht).
 * User: rixon
 * Date: 25/1/13
 * Time: 10:28 AM
 *
 */
public interface RecordCreationStrategy<TYPE> {
    /**
     * This method will create a record in the file as per the file type.
     * @param schema represents the schema of the file to be generated
     * @param options represents the options for creating the file
     * @param recordCounter the recordCounter.
     * @return fully formed record
     */
    public String createRecord(Schema schema,Options options,long recordCounter);

    /**
     * This method will allow creation of record with facility to override random values. This is useful in scenarios where data generation
     * needs to be controlled like in a series. In this scenario the File generation strategy can decide which all fields value should be
     * overridden with the provided value
     * @param schema represents the schema of the file to be generated
     * @param options represents the options for creating the file
     * @param recordCounter the recordCounter.
     * @param overriddenFields  a list of FieldValueHolders that contains overridden values
     * @return fully formed record
     */
    public String createRecordWithOverrides(Schema schema,Options options,long recordCounter,Map<Field,String> overriddenFields);

    /**
     * This method is used for getting an object mapped with Random values
     * @param schema represents the schema of the file to be generated
     * @param options represents the options for creating the file
     * @param recordCounter the recordCounter.
     * @return the object instance with fields populated
     */
    public TYPE createRecordAsObject(Schema schema,Options options,long recordCounter);

    /**
     *
     * This method will allow creation of record with facility to override random values. This is useful in scenarios where data generation
     * needs to be controlled like in a series. In this scenario the File generation strategy can decide which all fields value should be
     * overridden with the provided value
     * @param schema represents the schema of the file to be generated
     * @param options represents the options for creating the file
     * @param recordCounter the recordCounter.
     * @param overriddenFields  a list of FieldValueHolders that contains overridden values
     * @return fully formed object instance
     */
    public TYPE createRecordAsObjectWithOverrides(Schema schema,Options options,long recordCounter,Map<Field,String> overriddenFields);

}
