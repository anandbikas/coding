package com.anand.coding.problems.string.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find minimum deletion of characters in a string (lower case a-z) such that all characters have unique number of count.
 *
 * Example
 *
 * s = "aaabbb"
 * deletion =1 (either delete a or b);
 *
 */
public class _01_MinDeletionUniqueCharCount {

    public static int solution(String s) {

        int [] charCountMap = new int[26];
        char charA = 'a';
        for(char c: s.toCharArray()){
            charCountMap[c-charA]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(charIndex -> charCountMap[(int)charIndex]).reversed());

        for(int charIndex=0; charIndex<26; charIndex++){
            if(charCountMap[charIndex]>0)
                pq.offer(charIndex);
        }

        int deletion=0;
        int previousCharCount=0;
        while (!pq.isEmpty()){
            int charIndex = pq.remove();
            if(previousCharCount==charCountMap[charIndex]){
                deletion++;
                if(--charCountMap[charIndex]>0)
                    pq.offer(charIndex);
            } else {
                previousCharCount=charCountMap[charIndex];
            }
        }

        return deletion;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(solution("aaabbb"));
        System.out.println(solution(""));
    }
}
