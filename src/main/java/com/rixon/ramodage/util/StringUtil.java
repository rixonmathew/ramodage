package com.rixon.ramodage.util;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 25/1/13
 * Time: 11:52 AM
 * A simple utility class for String functions
 */
public class StringUtil {

    /**
     *  This method will replace the beginning characters of the string with prefix
     *  values such that overall length of the string is same. If prefix is longer than
     *  value then prefix will be chopped till length. If prefix is empty or null then value
     *  is returned
     * @param value the string value
     * @param prefix the value to be prefixed
     * @return the modified string
     */
    public static String replacePrefix(String value,String prefix) {
        if (prefix==null||prefix.isEmpty()){
            return value;
        }
        int maxLength = value.length();
        if (prefix.length()>maxLength){
            return prefix.substring(0,maxLength);
        }
        return prefix+value.substring(prefix.length(),value.length());
    }

    public static String replaceSuffix(String value,String suffix) {
        if(suffix==null||suffix.isEmpty()){
            return value;
        }
        int maxLength=value.length();
        int suffixLength = suffix.length();
        if(suffixLength>=maxLength){
            return suffix.substring(suffixLength-maxLength,suffixLength);
        }
        return value.substring(0,maxLength-suffixLength)+suffix;

    }
}
