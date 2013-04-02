package com.ramodage.strategy;

import com.ramodage.configuration.Schema;
import com.ramodage.configuration.Options;
import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;

/**
 * User: rixonmathew
 * Date: 20/01/13
 * Time: 1:58 PM
 * This interface is responsible for generating file as per a given strategy
 */
public interface FileGenerationStrategy {


    /**
     * This method is responsible for generating the data required for the files as per the strategy
     * @param schema the schema that represents the structure of the file
     * @param options options required for generating file
     */
    public void generateFileData(Schema schema,Options options);
}
