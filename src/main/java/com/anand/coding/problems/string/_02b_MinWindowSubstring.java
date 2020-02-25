package com.anand.coding.problems.string;

/**
 * Find minimum window substring of s which contains all the characters of the given string p.
 *
 */
public class _02b_MinWindowSubstring {

    public static String minWindow(String s, String p) {
        if(s==null || s.length()==0 || p==null || p.length()==0 || s.length()<p.length()){
            return "";
        }

        int A[] = new int[128];
        for (char c : p.toCharArray()) {
            A[c]++;
        }
        int n = p.length();

        int head=0, start=0, end=0, windowLength=Integer.MAX_VALUE;

        for(; end<s.length(); end++){
            if(A[s.charAt(end)]-->0){
                n--;
            }

            for(; n==0 ; start++){
                if(end-start+1<windowLength){
                    windowLength = end-start+1;
                    head=start;
                }

                if(A[s.charAt(start)]++==0){
                    n++;
                }
            }
        }

        return windowLength==Integer.MAX_VALUE ? "" : s.substring(head, head+windowLength);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
    }
}
