package com.anand.coding.problems.string;

import java.util.HashSet;
import java.util.Set;

/**
 * A sentence is pangram if it contains all the alphabet characters.
 */
public class _11_Pangram {

    public static boolean isPangram(String sentence) {
        Set<Character> set = new HashSet<>();
        for(char c ='a'; c<='z'; c++){
            set.add(c);
        }

        for(char c: sentence.toCharArray()) {
            set.remove(c);
            if(set.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPangram("thequickbrownfoxjumpsoverthelazydog"));

    }
}
