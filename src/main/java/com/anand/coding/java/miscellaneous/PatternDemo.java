package com.anand.coding.java.miscellaneous;

/**
 *
 */
public class PatternDemo {

    /**
     * 123456789
     * 12345678
     * 1234567
     * 123456
     * 12345
     * 1234
     * 123
     * 12
     * 1
     */
    public void pattern1() {
        int n =123456789;

        for(int d=n%10; n>0; n/=10) {
            System.out.println(n);
        }
        System.out.println();
    }

    /**
     * *
     * **
     * ***
     * ****
     * *****
     * ******
     * *******
     * ********
     * *********
     * @param rows
     */
    public void pattern2(final int rows) {
        for(int i=1; i<=rows; i++) {
            for(int j=1; j<=i; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     *         *
     *        **
     *       ***
     *      ****
     *     *****
     *    ******
     *   *******
     *  ********
     * *********
     * @param rows
     */
    public void pattern3(final int rows) {
        for(int i=1; i<=rows; i++) {
            for(int j=1; j<=rows-i; j++) {
                System.out.print(" ");
            }
            for(int j=1; j<=i; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
        System.out.println();
    }


    /**
     *   1
     *
     *   1  1
     *
     *   1  2  1
     *
     *   1  3  3  1
     *
     *   1  4  6  4  1
     * @param rows
     */
    public void pascalTriangle(final int rows) {
        System.out.println("Pascal Triangle, rows = " + rows);

        int [][] pascalTriangle = new int[rows][];

        for(int i=0; i<rows; i++) {
            pascalTriangle[i] = new int [i+1];
            for(int j=0; j<pascalTriangle[i].length; j++) {
                pascalTriangle[i][j] = (j == 0 || i == j)
                        ? 1 : pascalTriangle[i-1][j-1] + pascalTriangle[i-1][j];
            }
        }

        for(int i=0; i<pascalTriangle.length; i++) {
            for(int j=0; j<pascalTriangle[i].length; j++) {
                System.out.print(String.format("%4d", pascalTriangle[i][j]));
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){

        final PatternDemo patternDemo = new PatternDemo();
        patternDemo.pattern1();
        patternDemo.pattern2(10);
        patternDemo.pattern3(10);

        patternDemo.pascalTriangle(7);
    }
}
