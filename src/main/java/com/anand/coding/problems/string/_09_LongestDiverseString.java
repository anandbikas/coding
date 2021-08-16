package com.anand.coding.problems.string;

import java.util.PriorityQueue;

/**
 * In an alphabet of n characters, a string is called diverse if it does not have n adjacent similar characters.
 *
 * Given an alphabet has n characters {a,b,c,..} and max_chars for all the alphabet,
 * find the any longest possible diverse string.
 *
 * Example:
 * Alphabet {a,b,c}
 * max_chars {1,3,1}
 *
 * Result: abbcb, bcbab, bacbb etc.
 *
 */
public class _09_LongestDiverseString {

    public static String longestDiverseString(int []charCount) {

        char charA='a';
        int size=charCount.length;
        int [] recentCharConsumption = new int[size];

        PriorityQueue<Integer> queue
                = new PriorityQueue<>( (x, y) -> charCount[y] - charCount[x]);
        for (int i=0; i<size; i++) {
            queue.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = sb.length();
            int currCharacterIndex = queue.remove();

            if (n>=size-1 && recentCharConsumption[currCharacterIndex]==size-1) {
                int next = queue.remove();
                queue.add(currCharacterIndex);
                currCharacterIndex = next;
            }
            if (charCount[currCharacterIndex] == 0) {
                break;
            }
            recentCharConsumption[currCharacterIndex]++;
            if(n>=size-1) {
                recentCharConsumption[sb.charAt(n-(size-1))-charA]--;
            }

            sb.append((char) (charA + currCharacterIndex));
            charCount[currCharacterIndex]--;
            queue.add(currCharacterIndex);
        }
        return sb.toString();
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(longestDiverseString(new int[] {6,1,1}));
        System.out.println(longestDiverseString(new int[]{1,3,1}));
        System.out.println(longestDiverseString(new int[]{0,1,8}));
        System.out.println(longestDiverseString(new int[] {6,1,1,5}));
    }
}
