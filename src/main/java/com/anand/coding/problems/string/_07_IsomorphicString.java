package com.anand.coding.problems.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters should map to the same character, a character may map to itself.
 *
 * Eg:
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 *
 */
public class _07_IsomorphicString {

    public static boolean isIsomorphic(String s, String t) {

        if(s.length()!=t.length()){
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> reverseMap = new HashMap<>();


        for(int i=0; i<s.length(); i++){

            if(map.containsKey(s.charAt(i))){
                char mapping = map.get(s.charAt(i));
                if(mapping != t.charAt(i)){
                    return false;
                }
            } else {
                if(reverseMap.containsKey(t.charAt(i))){
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
                reverseMap.put(t.charAt(i), s.charAt(i));
            }
        }

        return true;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("badc", "baba"));
        System.out.println(isIsomorphic("egg", "add"));

    }
}
