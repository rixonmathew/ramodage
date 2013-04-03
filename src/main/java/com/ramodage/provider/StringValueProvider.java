package com.ramodage.provider;

import com.ramodage.util.StringUtil;

/**
 * This class will provide random string values.
 * User: rixon
 * Date: 20/01/13
 * Time: 11:21 PM
 */
public class StringValueProvider extends AbstractValueProvider<String> {

    @Override
    public String randomValue() {
        return randomStringValue(random.nextInt(300));
    }

    @Override
    protected String valueFromString(String value) {
        return value;
    }

    @Override
    public String randomValue(long minLength, long maxLength) {
        StringBuilder placeHolder = new StringBuilder();
        int length = (int)(minLength+(int)(Math.random()*(maxLength-minLength)));
        placeHolder.append(randomStringValue(length));
        return placeHolder.toString();
    }

    @Override
    public String randomValueWithPrefix(long minLength, long maxLength, String prefix) {
        String value = randomValue(minLength,maxLength);
        return StringUtil.replacePrefix(value,prefix);
    }

    @Override
    public String randomValueWithSuffix(long minLength, long maxLength, String suffix) {
        String value = randomValue(minLength,maxLength);
        return StringUtil.replaceSuffix(value,suffix);
    }

    private String randomStringValue(int length) {
        char[] chars = new char[length];
        for  (int i=0;i<length;i++){
            int ASCII_A = 65;
            chars[i] = (char)(ASCII_A +random.nextInt(26));
            if (random.nextInt(2)==1) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        return new String(chars);
    }
}
