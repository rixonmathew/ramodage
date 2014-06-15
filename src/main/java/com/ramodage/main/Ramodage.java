package com.ramodage.main;

import java.util.Properties;

/**
 * User: rixonmathew
 * Date: 13/04/14
 * Time: 7:20 PM
 * This class represents the client interface to be used by consumers
 */
public class Ramodage {
    public RandomData generateData(Properties properties) {
        return new RandomData(); //TODO add functionality for data generation
    }
}
