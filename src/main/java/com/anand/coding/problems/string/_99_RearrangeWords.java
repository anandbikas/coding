package com.anand.coding.problems.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Rearrange words in a sentence with increasing order of length. Keep first letter capital.
 *
 */
public class _99_RearrangeWords {


    public static String arrangeWords(String text) {

        text = (char)(text.charAt(0)-('A'-'a')) + text.substring(1);

        text =  Arrays.stream(text.split(" "))
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.joining(" "));

        return (char)(text.charAt(0)+('A'-'a')) + text.substring(1);

    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){

        System.out.println(arrangeWords("Elephant is big"));
    }
}
