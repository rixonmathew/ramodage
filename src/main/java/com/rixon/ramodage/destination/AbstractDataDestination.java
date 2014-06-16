package com.rixon.ramodage.destination;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 11:21 PM
 */
public abstract class AbstractDataDestination<TYPE> implements DataDestination<TYPE> {

    protected Map<String,List<TYPE>> objectHolder = new ConcurrentHashMap<>();
    protected Options options;
    protected Schema schema;
    protected DestinationMetaData destinationMetaData;

    protected AbstractDataDestination(Schema schema,Options options) {
        this.options = options;
        this.schema = schema;
    }

    /**
     * This method will provide with the meta data about the destination
     *
     * @return destination meta data;
     */
    @Override
    public DestinationMetaData getDestinationMetaData() {
        return destinationMetaData;
    }

    /**
     * This method is used for adding a record as object to data destination
     *
     * @param split  the split in which data is being added
     * @param object the object representing the random data
     */
    @Override
    public void addObject(String split, TYPE object) {
        if (objectHolder.containsKey(split)) {
            objectHolder.get(split).add(object);
        } else {
            List<TYPE> objects = new ArrayList<>();
            objects.add(object);
            objectHolder.put(split,objects);

        }
    }

    /**
     * This method is used for adding a record in String format to the data destination
     *
     * @param split  the split in which data is being added
     * @param record the record representing the random data
     */
    @Override
    public void addRecord(String split, String record) {

    }
}
