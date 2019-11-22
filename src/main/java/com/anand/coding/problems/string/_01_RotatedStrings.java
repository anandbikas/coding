package com.anand.coding.problems.string;

/**
 * Check if two strings are rotations of each other.
 */
public class _01_RotatedStrings {


    /**
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isRotation(String s1, String s2){

        return s1.length()==s2.length()
                    && (s1+s2).contains(s1);
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){

        String s1 = "bikasanand";
        String s2 = "anandbikas";

        System.out.println(isRotation(s1,s2));

    }
}
