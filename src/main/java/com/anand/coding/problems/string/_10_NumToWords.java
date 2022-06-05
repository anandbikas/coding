package com.anand.coding.problems.string;

/**
 * Convert number to words
 *
 */
public class _10_NumToWords {

    private String [] s1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    private String [] s2 = {"","", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private String [] place = {"", "Thousand", "Million", "Billion", "Trillion", "Quadrillion"};


    public String numberToWords(int x){
        if(x==0){
            return "Zero";
        }

        return toString(x);
    }

    public String toString(int x){

        if(x<20){
            return s1[x];
        } else if(x<100){
            return String.format("%s %s", s2[x/10], s1[x%10]).trim();
        } else if(x<1000){
            return String.format("%s Hundred %s", s1[x/100], toString(x%100)).trim();
        }

        String res = "";
        for(int i=0; x>0; x/=1000, i++){
            int num = x%1000;
            if(num>0){
                res = toString(num) + " " + place[i] + " " + res;
            }
        }

        return res.trim();
    }

    public static void main(String[] args) {
        //Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
        System.out.println(new _10_NumToWords().toString(1234567));

    }
}
