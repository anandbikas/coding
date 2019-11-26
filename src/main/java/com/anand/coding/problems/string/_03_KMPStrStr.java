package com.anand.coding.problems.string;

import com.anand.coding.dsalgo.array.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple strStr(): O(n*m)
 *
 * KMP optimization:
 *      O(n) using LPP[] pre-calculation (longest proper prefix which is also suffix for all sub-strings in pattern string)
 *
 *      Note: length(proper_prefix) < length(string)
 */
public class _03_KMPStrStr {


    /**
     * Return starting indexes of all matching pattern in the string.
     *
     * @param str
     * @param pat
     * @return
     */
    public static List<Integer> strStr(String str, String pat){

        List<Integer> indexes = new ArrayList<>();

        if(str==null || pat == null || pat.length()==0 || str.length()<pat.length()){
            return indexes;
        }

        // Length of longest proper prefix which is suffix too.
        int []LPP = new int[pat.length()];
        LPP[0]=0;
        for(int i=1; i<pat.length(); i++){

            LPP[i]=0; //Store default value;
            int len;
            for (len= LPP[i-1]; pat.charAt(i)!=pat.charAt(len) && len!=0; len=LPP[len-1]);

            if(pat.charAt(i)==pat.charAt(len)){
                LPP[i]=len+1;
            }
        }

        // Pattern matching
        int i=0,j=0;
        while(i<str.length()){

            for(;j<pat.length() && i<str.length() && str.charAt(i) == pat.charAt(j); i++,j++);

            if(pat.length()==j){
                indexes.add(i-pat.length());
            }
            if(j==0){
                i++;
            } else {
                j = LPP[j - 1];
            }
        }

        Array.display(LPP);
        return indexes;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        for(int x: strStr("AAAAABAAABAAAABAAAAC","AAABAAA")){
            System.out.print(x + " ");
        }
        System.out.println();

        for(int x: strStr("ababcaababcaabc","ababcaabc")){
            System.out.print(x + " ");
        }
    }
}
