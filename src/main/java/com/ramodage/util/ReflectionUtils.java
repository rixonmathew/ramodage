package com.ramodage.util;

import com.ramodage.configuration.Field;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 12:28 AM
 *
 * This is a helper class that is used for creating objects and invoking method to set the values;
 */
public class ReflectionUtils {
    public static <TYPE> TYPE createObject(String objectClassName) {
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class<TYPE> objectClass = (Class<TYPE>) classLoader.loadClass(objectClassName);
            TYPE objectInstance = objectClass.newInstance();
            return objectInstance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public static <TYPE> void setValue(TYPE object, String value, Field field) {
        Class<TYPE> objectClass = (Class<TYPE>) object.getClass();
        String methodName = "set"+toProperCase(field.getName());
        try {
            Method methodToInvoke = objectClass.getMethod(methodName,new Class[]{getClassForField(field)});
            methodToInvoke.invoke(object,getValueCastedToType(field,value));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1);
    }

    private static Class getClassForField(Field field) {
        switch (field.getType()) {
            case "int":
                return Integer.class;
            case "String":
                return String.class;
            case "Date":
            case "Timestamp":
                return Date.class;
            case "BigDecimal":
                return BigDecimal.class;
            default:
                throw new IllegalArgumentException("Invalid type specified "+field.getType());
        }
    }

    private static Object getValueCastedToType(Field field,String stringValue) {
        switch (field.getType()) {
            case "int":
                return Integer.valueOf(stringValue);
            case "String":
                return stringValue;
            case "Date":
            case "Timestamp":
                return DateUtil.getFormattedDate(stringValue,field.getFormatMask());
            case "BigDecimal":
                return new BigDecimal(stringValue);
            default:
                throw new IllegalArgumentException("Invalid type specified "+field.getType());
        }

    }

}
