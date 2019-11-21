package com.anand.coding.problems.dp;

/**
 * Given a string A, find the longest palindromic sub-string.
 */
public class _10_LongestPalindromicSubStringNonDP {

    /**
     * Expand around centre
     *
     * O(n2)
     * S(1)
     *
     * @param s
     * @return
     */
    public static String lps(String s) {

        if(s==null || s.length()<2){
            return s;
        }

        int maxLength = 1;
        int centre =0;
        for(int c=1; c<s.length(); c++){

            //Expand for even length;
            int lenght=0;
            for(int l=c-1,r=c; l>=0 && r<s.length() && s.charAt(l)==s.charAt(r); l--,r++, lenght+=2);
            if(lenght>maxLength){
                maxLength = lenght;
                centre = c;
            }

            //Expand for odd length;
            lenght=1;
            for(int l=c-1,r=c+1; l>=0 && r<s.length() && s.charAt(l)==s.charAt(r); l--,r++, lenght+=2);
            if(lenght>maxLength){
                maxLength = lenght;
                centre = c;
            }
        }

        int startIndex = centre-(maxLength/2);
        return s.substring(startIndex, startIndex+maxLength);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        //Even length
        System.out.println(lps("abcdef"));

        //Even length
        System.out.println(lps("axannax"));

        //Odd length
        System.out.println(lps("axanjnax"));
    }
}
