package com.anand.coding.problems.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group anagrams
 */
public class _02_GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> keyStringsMap = new HashMap<>();
        for (String str : strs) {
            String key = generateStringKey(str);

            if(!keyStringsMap.containsKey(key)){
                keyStringsMap.put(key, new ArrayList<>());
            }
            keyStringsMap.get(key).add(str);
        }

        return new ArrayList<>(keyStringsMap.values());
    }

    /**
     * String key: string of sorted characters with shorten length.
     *
     * @param str
     * @return
     */
    private static String generateStringKey(String str) {

        int[] A = new int[26];

        for (char c : str.toCharArray()) {
            A[c-'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<26; i++) {
            if (A[i] > 0) {
                sb.append((char) ('a' + i)).append(A[i]);
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        String [] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> groupAnagrams = groupAnagrams(strs);

        System.out.println(groupAnagrams);
    }
}
