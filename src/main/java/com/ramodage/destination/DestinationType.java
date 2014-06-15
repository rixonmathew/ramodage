package com.ramodage.destination;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 8:18 PM
 */
public enum DestinationType {
    FILE("file"),
    IN_MEMORY("in_memory"),
    DATABASE("database");

    private String description;

    public String getDescription() {
        return description;
    }

    private DestinationType(String description) {
        this.description = description;
    }
}
