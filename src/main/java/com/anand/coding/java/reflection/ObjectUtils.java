package com.anand.coding.java.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Date;

/**
 *
 */
public class ObjectUtils {

    /**
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        Class clazz = obj.getClass();
        String displayString = clazz.getName();

        do {
            displayString += "[";
            Field[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields,true);

            for(int i=0; i<fields.length; i++) {
                Field field = fields[i];
                displayString += field.getName() + "=";

                try {
                    Object val = field.get(obj);
                    displayString += val;

                } catch(Exception e) {
                    e.printStackTrace();
                }

                if(i < fields.length-1) {
                    displayString += ", ";
                }
            }
            displayString += "]";
            clazz = clazz.getSuperclass();

        } while(clazz != Object.class);

        return displayString;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(Object a , Object b)
    {
        if(a == b) {
            return true;
        }

        if(a == null || b == null){
            return false;
        }

        Class clazzA = a.getClass();
        Class clazzB = b.getClass();

        if(clazzA != clazzB) {
            return false;
        }

        do {
            Field[] fields = clazzA.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);

            for (int i = 0; i < fields.length; i++) {

                Field field = fields[i];
                try {
                    if (!field.get(a).equals(field.get(b))) {
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                clazzA = clazzA.getSuperclass();
            }
        } while(clazzA != Object.class);
        return true;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        Date date = new Date();
        System.out.println(date);

        System.out.println();

        System.out.println(toString(date));
    }
}



