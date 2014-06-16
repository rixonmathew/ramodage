package com.rixon.ramodage.validator;

import com.rixon.ramodage.destination.DestinationType;
import com.rixon.ramodage.util.Constants;
import com.rixon.ramodage.util.ReflectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
        boolean valid;
        List<String> issues = new ArrayList<>();
        valid = validateSchema(properties,issues);
        if (valid)
            valid = validateDestinationType(properties,issues);
        if(!valid) {
            StringBuilder stringBuilder = new StringBuilder("Following issues have been discovered in properties \n");
            int counter = 1;
            for(String issue:issues) {
                stringBuilder.append(counter++).append(") ").append(issue).append("\n");
            }
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        return valid;
    }


    private boolean validateSchema(Properties properties, List<String> issues) {
        boolean valid = true;
        String schemaFileName = properties.getProperty(Constants.SCHEMA);
        if(schemaFileName==null||schemaFileName.isEmpty()){
            valid = validateSchemaContent(properties);
            if(!valid) {
                issues.add("Neither "+Constants.SCHEMA+" nor "+Constants.SCHEMA_CONTENT+" has been set.");
            }
        } else {
            File file = new File(schemaFileName);
            if (!file.exists()||!file.canRead()){
                valid = false;
                issues.add("Filed does not exist or permissions are not there for reading "+schemaFileName);
            }
        }
        return valid;
    }

    private boolean validateSchemaContent(Properties properties) {
        boolean valid = true;
        String schemaContent = properties.getProperty(Constants.SCHEMA_CONTENT);
        if ((schemaContent==null) || schemaContent.isEmpty()) {
            valid = false;
        }
        return valid;
    }

    private boolean validateDestinationType(Properties properties, List<String> issues) {
        boolean valid;
        String destinationTypeString = properties.getProperty(Constants.DESTINATION_TYPE);
        if (destinationTypeString==null||destinationTypeString.isEmpty())
        {
            valid = false;
            issues.add(Constants.DESTINATION_TYPE+ " had not been specified");
        } else {
            DestinationType destinationType = DestinationType.valueOf(destinationTypeString);
            if(destinationType==null) {
                valid = false;
                issues.add("A valid DestinationType has not been specified "+destinationTypeString);
            } else {
                valid = validateObjectClassForDestinationType(properties,destinationType,issues);
            }
        }
        return valid;
    }

    private boolean validateObjectClassForDestinationType(Properties properties, DestinationType destinationType, List<String> issues) {
        boolean valid = true;
        if (destinationType==DestinationType.IN_MEMORY) {
            String objectClassName = properties.getProperty(Constants.OBJECT_CLASS_NAME);
            if (objectClassName==null||objectClassName.isEmpty()){
                valid = false;
                issues.add(Constants.OBJECT_CLASS_NAME+" is expeceted when DestinationType is "+destinationType);
            } else {
                valid = ReflectionUtils.isValidClass(objectClassName);
                if(!valid){
                    issues.add("A valid class has not been specified "+objectClassName);
                }
            }
        }
        return valid;
    }
}
