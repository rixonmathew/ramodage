package com.rixon.ramodage.util;

import com.rixon.ramodage.configuration.Field;

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
            throw new IllegalArgumentException("Class not found "+objectClassName,e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("Class could not be instantiated "+objectClassName,e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Class could not be accessed "+objectClassName,e);
        }
    }

    public static <TYPE> void setValue(TYPE object, String value, Field field) {
        Class<TYPE> objectClass = (Class<TYPE>) object.getClass();
        String methodName = "set"+toProperCase(field.getName());
        try {
            Method methodToInvoke = objectClass.getMethod(methodName,new Class[]{getClassForField(field)});
            methodToInvoke.invoke(object,getValueCastedToType(field,value));
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Method not found "+methodName,e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException("Could not invoke method "+methodName,e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Could not access "+methodName,e);
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

    public static boolean isValidClass(String objectClassName) {
        boolean valid = true;
        Object object = ReflectionUtils.createObject(objectClassName);
        if (object==null){
            valid=false;
        }
        return valid;
    }
}
