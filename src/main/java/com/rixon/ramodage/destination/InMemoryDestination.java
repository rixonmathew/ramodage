package com.rixon.ramodage.destination;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.model.RandomData;
import com.rixon.ramodage.model.RandomDataImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 8:19 PM
 */
public class InMemoryDestination<TYPE> extends AbstractDataDestination<TYPE> {

    public InMemoryDestination(Schema schema,Options options) {
        super(schema,options);

    }

    /**
     * This method is used for preparing the destination to accept the random data
     */
    @Override
    public void prepareForDataGeneration() {
        List<String> splitNames = new ArrayList<>();
        for (int i=0;i<options.getNumberOfFileSplits();i++) {
            splitNames.add(Integer.toString(i));
        }
        destinationMetaData = new DestinationMetaData(DestinationType.IN_MEMORY,null,splitNames);
    }

    /**
     * This method will provide the RandomData object. The RandomData is used by clients to access their random data
     *
     * @return random data object
     */
    @Override
    public RandomData<TYPE> getRandomData() {
        List<TYPE> objects = new ArrayList<>();
        for (List<TYPE> objectsPerSplit:objectHolder.values()) {
            objects.addAll(objectsPerSplit);
        }
        return new RandomDataImpl<>(objects);
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

}
