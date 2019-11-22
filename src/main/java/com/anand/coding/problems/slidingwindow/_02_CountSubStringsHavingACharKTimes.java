package com.anand.coding.problems.slidingwindow;

/**
 *
 */
public class _02_CountSubStringsHavingACharKTimes {

    public static int countSubStringsHavingACharKTimes(String str, char c, int k){

        if(str.length()<k){
            return 0;
        }
        int result = 0;

        //Sliding Window

        //Calculate count of the character c in the initial window of p=k-1 characters
        int initialWindowCharCount=0;
        for(int i=0; i<k-1; i++) {
            if (str.charAt(i) == c) {
                initialWindowCharCount++;
            }
        }

        for(int p=k; p<=str.length(); p++){

            // count of the character c in the initial window of p characters
            if (str.charAt(p-1) == c) {
                initialWindowCharCount++;
            }
            int charCount=initialWindowCharCount;

            if(charCount==k){
                result++;
            }

            //Slide the window one by one to the right
            for(int i=p; i<str.length(); i++){
                if (str.charAt(i) == c) {
                    charCount++;
                }
                if (str.charAt(i-p) == c) {
                    charCount--;
                }
                if(charCount==k){
                    result++;
                }
            }
        }
        return result;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(countSubStringsHavingACharKTimes("bikasanand", 'n', 2));
    }
}
