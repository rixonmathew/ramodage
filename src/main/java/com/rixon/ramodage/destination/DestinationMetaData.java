package com.rixon.ramodage.destination;

import java.util.Collections;
import java.util.List;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 8:16 PM
 *
 * This entity represents the meta data of the destination
 */
public class DestinationMetaData {
    private DestinationType destinationType;
    private String directoryName;
    private List<String> splitNames;


    public DestinationMetaData(DestinationType destinationType, String directoryName, List<String> splitNames) {
        this.destinationType = destinationType;
        this.directoryName = directoryName;
        this.splitNames = splitNames;
    }

    public DestinationType getDestinationType() {
        return destinationType;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public List<String> getSplitNames() {
        return Collections.unmodifiableList(splitNames);
    }

}
