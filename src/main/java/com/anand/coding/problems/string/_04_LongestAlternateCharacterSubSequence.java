package com.anand.coding.problems.string;

/**
 *
 * Find the longest alternate character subsequence in a string.
 *
 *Eg: s = abaacdabd
 *   result = abab (all other characters removed)
 *
 */
public class _04_LongestAlternateCharacterSubSequence {


    /**
     *
     * @param s
     */
    public static int calculate(String s){


        char prev,next;
        int maxLength=0;

        for(char x='a'; x<='z'; x++) {
            for (char y = 'a'; y <= 'z'; y++) {

                if(x==y){
                    continue;
                }

                prev=x; next=y;

                int len=0;
                int i=0;
                for(; i<s.length();i++){
                    if(s.charAt(i)==prev){
                        break;
                    }

                    if(s.charAt(i)==next){
                        len++;
                        char temp=prev; prev=next; next=temp;
                    }
                }
                if(i==s.length() && len>maxLength){
                    maxLength=len;
                }

            }
        }
        return maxLength;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(calculate("abaacdabd"));
        System.out.println(calculate("beabeefeab"));
        System.out.println(calculate("asdcbsdcagfsdbgdfanfghbsfdab"));
    }
}
