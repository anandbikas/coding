package com.anand.coding.problems.advanced;

/**
 * There is a bitstream (0 and 1) coming.
 * Determine whether the current value is divisible by 3.
 *
 * Answer:
 * If the number of even 1 bits minus the number of odd 1 bits is a multiple of 3, then the number is divisible by 3.
 */
public class _06_BinaryStreamDivisibleBy3 {


    /**
     *
     */
    public static class BitStream{

        private int oddEvenPositionCountDifferenceOfOne=0;

        private boolean isOdd=true;

        public void consume(char bit){
            if(bit=='1') {
                if (isOdd) {
                    oddEvenPositionCountDifferenceOfOne++;
                } else {
                    oddEvenPositionCountDifferenceOfOne--;
                }
            }
            isOdd = !isOdd;
        }

        public boolean isDivisibleBy3(){
            return oddEvenPositionCountDifferenceOfOne%3==0;
        }
    }

    /**
     *
     */
    public static void main(String []args){

        BitStream bitStream = new BitStream();

        String stream = "10101";

        for(char c: stream.toCharArray()){
            bitStream.consume(c);
            System.out.println(bitStream.isDivisibleBy3());
        }

    }
}
