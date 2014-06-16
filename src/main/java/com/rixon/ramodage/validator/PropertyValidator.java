package com.rixon.ramodage.validator;

import com.rixon.ramodage.util.Constants;

import java.io.File;
import java.util.Properties;

/**
 * User: rixonmathew
 * Date: 15/06/14
 * Time: 9:47 AM
 *
 * This class is used for validating the input properties required for generating the random files
 */
public class PropertyValidator {

    public boolean arePropertiesValid(Properties properties) {
        boolean valid = true;
        String schemaFileName = properties.getProperty(Constants.SCHEMA);
        if(schemaFileName==null||schemaFileName.isEmpty()){
            valid = false;
        }
        File file = new File(schemaFileName);
        if (!file.exists()||!file.canRead()){
            valid = false;
        }
        return valid;
    }
}
