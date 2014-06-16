package com.rixon.ramodage.provider;

import com.rixon.ramodage.configuration.Field;

/**
 * This interface is used for providing a random value of the given type
 * User: rixon
 * Date: 20/01/13
 * Time: 9:38 PM
 */
public interface ValueProvider<TYPE> {

    /**
     * This method will provide a random value. This is the simplest method that provides a type value
     * without check for min,max or a constant seed value
     * @return the random value;
     */
    public TYPE randomValue();

    /**
     * This method will provide a random value based on properties of field like
     * minLength,maxLength,prefix,suffix,format mask,default value etc. The intent of this method is to provide
     * a simple interface for the consumer to get a random value based on the constraints that have been defined
     * in the field instance
     * @param field the field property
     * @return the generated random value
     */
    public TYPE randomValue(Field<TYPE> field);

    /**
     * This method will format the random value as per constraints defined in the field. In scenarios where random
     * values needs to be written to a file the random value is needed as String and not as Type.
     * @param field the field property
     * @return the generated random value;
     */
    public String randomValueAsString(Field<TYPE> field);
}
