package com.anand.coding.problems.string;

import java.util.*;

/**
 *
 * Remove duplicate letters in a string such that every letter appears once and only once.
 * Result should be the smallest in lexicographical order among all possible results.
 *
 *Eg:   Input: "cbacdcbc"
 *      Output: "acdb"
 *
 */
public class _05_RemoveDuplicateLetters {


    /**
     *
     * @param s
     */
    public static String removeDuplicateLetters(String s){

        if(s.length()==0){
            return "";
        }

        int []count = new int[26];
        boolean []visited = new boolean[26];

        for(int i=0; i<s.length(); i++){
            count[s.charAt(i)-'a']++;
        }

        Stack<Character> stack = new Stack<>();

        for(char c: s.toCharArray()){

            if(visited[c-'a']){
                count[c-'a']--;
                continue;
            }

            while(!stack.isEmpty() && stack.peek()>c && count[stack.peek()-'a']>0){
                visited[stack.peek()-'a'] = false;
                stack.pop();
            }

            stack.push(c);
            visited[c-'a'] = true;
            count[c-'a']--;
        }

        String res = "";

        while (!stack.isEmpty()){
            res = stack.pop() + res;
        }
        return res;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(removeDuplicateLetters("cbacdcbc"));
        System.out.println(removeDuplicateLetters("bcabc"));
        System.out.println(removeDuplicateLetters("bbcaac"));
        System.out.println(removeDuplicateLetters("abacb"));
    }
}

