package com.anand.coding.problems.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Sort characters in a string by frequency.
 *
 * Ex: tree ->> eert
 */
public class _09_SortCharsByFrequency {

    public static String frequencySort(String s) {

        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Character> pq =
                new PriorityQueue<>(Comparator.comparing(map::get).reversed());

        for(char c: s.toCharArray()){
            map.put(c, map.containsKey(c) ? map.get(c)+1 : 1);
        }
        pq.addAll(map.keySet());

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            char c = pq.remove();
            sb.append(new String(new char[map.get(c)]).replace('\0', c));
        }

        return sb.toString();
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

       System.out.println(frequencySort("tree"));
    }
}
