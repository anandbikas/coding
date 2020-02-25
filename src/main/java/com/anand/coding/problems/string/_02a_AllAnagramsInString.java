package com.anand.coding.problems.string;

import java.util.*;

/**
 * Find starting indexes of all the anagrams of string p in string s.
 *
 * Other problems to solve this way.
 *
 * 1. check if any permutation of p is present in s.
 */
public class _02a_AllAnagramsInString {


    /**
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> list = new ArrayList<>();

        if(s==null || s.length()==0 || p==null || p.length()==0 || s.length()<p.length()){
            return list;
        }

        int keyLength = p.length();
        int A[] = new int[26];
        for (char c : p.toCharArray()) {
            A[c-'a']++;
        }

        for(char c: s.substring(0,keyLength).toCharArray()){
            A[c-'a']--;
        }

        //boolean isEqual = !Arrays.stream(A).filter(x -> x!=0).findFirst().isPresent();
        boolean isEqual = true;
        for (int i=0; i<26; i++) {
            if (A[i] != 0) {
                isEqual=false;
                break;
            }
        }

        if(isEqual){
            list.add(0);
        }

        for(int l=1, r=keyLength; r<s.length();  l++,r++){
            char lc = s.charAt(l-1);
            char rc = s.charAt(r);

            //Sliding window technique
            A[lc-'a']++;
            A[rc-'a']--;

            //isEqual = isEqual ? (lc==rc) : !Arrays.stream(A).filter(x -> x!=0).findFirst().isPresent();
            if(isEqual){
                isEqual = (lc==rc);
            } else {
                isEqual = true;
                for (int i=0; i<26; i++) {
                    if (A[i] != 0) {
                        isEqual=false;
                        break;
                    }
                }
            }

            if(isEqual){
                list.add(l);
            }
        }

        return list;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(findAnagrams("abab", "ab"));
        System.out.println(findAnagrams("aa", "bb"));
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }
}
