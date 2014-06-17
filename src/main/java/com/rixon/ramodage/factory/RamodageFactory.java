package com.rixon.ramodage.factory;

import com.rixon.ramodage.main.Ramodage;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 4:14 PM
 *
 * This class represents the factory for getting instances of ramodage
 */
public class RamodageFactory {

    public static Ramodage getWithDefaultOptions() {
        return new Ramodage();
    }
}
