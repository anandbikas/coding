package com.anand.coding.problems.string;

/**
 * Reverse words in a string
 */
public class _06_ReverseWordsOfString {

    /**
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {

        String[] strs = s.split("\\s+");

        System.out.println(strs);

        StringBuilder sb = new StringBuilder();
        for(int i=strs.length-1; i>-1; i--){
            sb.append(strs[i]).append(' ');
        }

        return sb.toString().trim();
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(reverseWords("apple boy cat"));
    }
}
