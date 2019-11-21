package com.anand.coding.problems.dp;

/**
 * Given a string A, find the count of all the palindromic sub-strings.
 */
public class _10_PalindromicSubStringCountNonDP {

    /**
     * Expand around centre
     *
     * O(n2)
     * S(1)
     *
     * @param s
     * @return
     */
    public static int lpsCount(String s) {

        if(s==null){
            return 0;
        }

        int count = s.length();
        for(int c=1; c<s.length(); c++){

            //Expand for even length;
            for(int l=c-1,r=c; l>=0 && r<s.length() && s.charAt(l)==s.charAt(r); l--,r++, count++);

            //Expand for odd length;
            for(int l=c-1,r=c+1; l>=0 && r<s.length() && s.charAt(l)==s.charAt(r); l--,r++, count++);
        }

        return count;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(lpsCount("axanjnax"));
    }
}
