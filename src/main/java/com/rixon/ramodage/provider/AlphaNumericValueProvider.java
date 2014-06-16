package com.rixon.ramodage.provider;

/**
 * This class represents a random AlphaNumeric provider
 * User: rixon
 * Date: 22/1/13
 * Time: 2:10 PM
 * This class represents the ValueProvider for AlphaNumeric Type of data
 */
public class AlphaNumericValueProvider extends AbstractValueProvider<String> {

    @Override
    public String randomValue() {
        return randomAlphaNumericValue(300);
    }

    @Override
    protected String valueFromString(String value) {
        return value;
    }

    @Override
    public String randomValue(long minLength, long maxLength) {
        int length = (int)minLength +(int)(Math.random()*(maxLength-minLength));
        return randomAlphaNumericValue(length);
    }

    private String randomAlphaNumericValue(int length) {
        char[] chars = new char[length];
        for  (int i=0;i<length;i++){
            int ASCII_A = 65;
            if(random.nextInt(2)==0){
                chars[i] = (char)(ASCII_A +random.nextInt(26));
            } else {
                chars[i] = Character.forDigit(random.nextInt(10),10);
            }
        }
        return new String(chars);
    }


}
