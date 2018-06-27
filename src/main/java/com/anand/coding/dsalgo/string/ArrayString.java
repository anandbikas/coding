package com.anand.coding.dsalgo.string;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ArrayString {

    private char [] strArr;

    /**
     *
     * @param str
     */
    public ArrayString(String str){
        this.strArr = str.toCharArray();
    }

    /**
     *
     * @return
     */
    public char getMaxOccuringChar(){
        short A[] = new short[256];
        for(char c: strArr){
            A[c]++;
        }

        int maxValueIndex = 0;
        for(int i = 1; i < A.length; i++) {
            if(A[i] > A[maxValueIndex]) {
                maxValueIndex = i;
            }
        }
        return (char)maxValueIndex;
    }

    /**
     *
     * @return
     */
    public boolean isPalindrome() {
        return isPalindrome(0, strArr.length-1);
    }

    /**
     *
     * @param startIndex
     * @param endIndex
     * @return
     */
    public boolean isPalindrome(int startIndex, int endIndex){

        while(startIndex<endIndex
                && strArr[startIndex++] == strArr[endIndex--]);

        return (startIndex >= endIndex);
    }

    /**
     *
     * @return
     */
    public boolean isPalindromeRec() {
        return isPalindromeRec(0, strArr.length-1);
    }

    /**
     *
     * @param startIndex
     * @param endIndex
     * @return
     */
    public boolean isPalindromeRec(int startIndex, int endIndex){

        if(startIndex>=endIndex){
            return true;
        }
        if(strArr[startIndex] == strArr[endIndex]){
            return isPalindromeRec(startIndex+1,endIndex-1);
        } else{
            return false;
        }
    }

    /**
     * Brute Force algorithm: Check palindrome for all possible substrings.
     * Find DP algorithm in DynamicProgramming.java
     *
     * @return
     */
    public List<String> longestPalindromicSubstrings(){

        List<String> allLPSubStrings = new ArrayList<>();

        for(int subStrLength=strArr.length; subStrLength>0; subStrLength--){

            for(int subStrStartIndex=0; subStrStartIndex<=strArr.length-subStrLength ; subStrStartIndex++){

                if(isPalindromeRec(subStrStartIndex, subStrStartIndex+subStrLength-1)){
                    allLPSubStrings.add(new String(strArr, subStrStartIndex, subStrLength));
                }
            }
            if(!allLPSubStrings.isEmpty()){
                // Longest palindromic substrings found
                break;
            }
        }
        return allLPSubStrings;
    }


    /**
     * main function to test ArrayString
     *
     * @param args
     */
    public static void main (String args[]){

        final ArrayString arrayString = new ArrayString("anandanjnaanandanbnabbb");

        System.out.println(arrayString.getMaxOccuringChar());

        List<String> allLongestPalindromicSubStrings = arrayString.longestPalindromicSubstrings();

        allLongestPalindromicSubStrings.forEach(System.out::println);
    }
}
