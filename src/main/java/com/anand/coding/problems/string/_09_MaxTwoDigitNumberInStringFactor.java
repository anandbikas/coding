package com.anand.coding.problems.string;

/**
 * Find max two digit number in a string factors.
 *
 * Example:
 * S = "328780"
 * 2Digit String factors: 32, 28, 87, 78, 80
 *
 * Max = 87
 *
 */
public class _09_MaxTwoDigitNumberInStringFactor {

    public static int solution(String s) {

        char maxFirstDigit=s.charAt(0);
        for(int i=1; i<s.length()-1; i++){
            maxFirstDigit = (char)Math.max(maxFirstDigit, s.charAt(i));
        }

        char maxSecondDigit=0;
        for(int i=0; i<s.length()-1; i++){
            if(s.charAt(i)==maxFirstDigit){
                maxSecondDigit=(char)Math.max(maxSecondDigit, s.charAt(i+1));
            }
        }

        return Integer.parseInt(String.format("%c%c",maxFirstDigit,maxSecondDigit));
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(solution("32878023"));

    }
}
