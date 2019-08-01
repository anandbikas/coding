package com.anand.coding.games.sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class Sudoku {

    int[][]S = new int[9][9];

    boolean [][] verticalValueMap = new boolean[9][10];
    boolean [][] horizontalValueMap = new boolean[9][10];
    boolean [][] squareValueMap = new boolean[9][10];

    /**
     *
     * @param A
     */
    public Sudoku(String A[]){

        System.out.println("*********   SUDOKU   **********");

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                S[i][j] = Integer.valueOf(A[i*9+j]);

                //valueMap calculation
                verticalValueMap[j][S[i][j]] = true;
                horizontalValueMap[i][S[i][j]] = true;

                //squareValueMap calculation
                //Square rows start from 0 to 8
                int row = (i/3) *3 + (j/3);
                squareValueMap[row][S[i][j]] = true;
            }
        }
        display();
        System.out.println("Sudoku constructed.");
    }

    /**
     *
     */
    public void display(){

        for(int i=0; i<9; i++){
            if(i%3==0){
                System.out.println("|---------|---------|---------|");
            }
            for(int j=0; j<9; j++){
                if(j%3==0){
                    System.out.print("|");
                }
                System.out.print(String.format(" %d ", S[i][j]));
            }
            System.out.println("|");
        }
        System.out.println("|---------|---------|---------|");
    }

    /**
     *
     */
    public void display1(){

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(String.format("%d ", S[i][j]));
            }
            System.out.println();
        }
    }

    /**
     *
     */
    public void solve(){
        System.out.println("Sudoku solving...");

        int round = 1;
        boolean solved = false;
        while(!solved){
            solved = solveRound();

            System.out.println("\nAfter a round: " + round++);
            display();
        }
        System.out.println();
    }

    private boolean solveRound(){

        boolean solved = true;

        for(int i=0; i<9;i++){
            for(int j=0; j<9;j++){
                if(S[i][j]!=0){
                    continue;
                }

                // 1. Check if only one value is possible
                int row = (i/3) *3 + (j/3);

                int count =0;

                int missingNumber=0;
                for(int n=1; n<=9; n++){

                    if(squareValueMap[row][n] || verticalValueMap[j][n] || horizontalValueMap[i][n]){
                        count++;
                    } else {
                        missingNumber = n;
                    }
                }

                if(count==8){
                    System.out.println(String.format("(%s,%s) -> %s",i,j,missingNumber));
                    S[i][j] = missingNumber;
                    squareValueMap[row][missingNumber] = verticalValueMap[j][missingNumber] = horizontalValueMap[i][missingNumber] = true;

                    continue;
                }


                // 2. Check if a value can not be inserted elsewhere
                for(int n=1; n<=9; n++){

                    if(squareValueMap[row][n] || verticalValueMap[j][n] || horizontalValueMap[i][n]){
                        continue;
                    }

                    //verticalValueMap
                    boolean verticalPossible = false;
                    for(int ii=0; ii<9 && !verticalPossible; ii++){
                        if(ii==i || S[ii][j]!=0){
                            continue;
                        }
                        int rowLocal = (ii/3) *3 + (j/3);

                        verticalPossible = !(squareValueMap[rowLocal][n] || verticalValueMap[j][n] || horizontalValueMap[ii][n]);
                    }

                    //horizontalPossible
                    boolean horizontalPossible = false;
                    for(int jj=0; jj<9 && !horizontalPossible; jj++){
                        if(jj==j || S[i][jj]!=0){
                            continue;
                        }
                        int rowLocal = (i/3) *3 + (jj/3);

                        horizontalPossible = !(squareValueMap[rowLocal][n] || verticalValueMap[jj][n] || horizontalValueMap[i][n]);
                    }

                    //squareValueMap
                    boolean squarePossible = false;
                    for(int ii=0; ii<3 && !squarePossible; ii++){
                        for(int jj=0; jj<3 && !squarePossible; jj++){
                            int iii = (i/3)*3 + ii;
                            int jjj = (j/3)*3 + jj;

                            if(iii==i && jjj==j || S[iii][jjj]!=0){
                                continue;
                            }
                            squarePossible = !(squareValueMap[row][n] || verticalValueMap[jjj][n] || horizontalValueMap[iii][n]);
                        }
                    }

                    if(!(verticalPossible && horizontalPossible && squarePossible)){
                        System.out.println(String.format("(%s,%s) -> %s",i,j,n));
                        S[i][j] = n;
                        squareValueMap[row][n] = verticalValueMap[j][n] = horizontalValueMap[i][n] = true;
                        break;
                    }
                }

                if(S[i][j]==0){
                    solved = false;
                }
            }
        }
        return solved;
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args) throws IOException {
        final String SPACE_REGEX = "\\s+";

        String[] strs = "3 0 6 5 0 8 4 0 0 5 2 0 0 0 0 0 0 0 0 8 7 0 0 0 0 3 1  0 0 3 0 1 0 0 8 0 9 0 0 8 6 3 0 0 5 0 5 0 0 9 0 6 0 0 1 3 0 0 0 0 2 5 0 0 0 0 0 0 0 0 7 4 0 0 5 2 0 6 3 0 0".split(SPACE_REGEX);
        Sudoku sudoku = new Sudoku(strs);

        sudoku.solve();
    }

    /**
     *
     * @param args
     */
    public static void main1(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs;
        final String SPACE_REGEX = "\\s+";

        int t = Integer.parseInt(br.readLine());

        while(t-->0) {
            strs = br.readLine().split(SPACE_REGEX);
            Sudoku sudoku = new Sudoku(strs);

            sudoku.solve();
            sudoku.display1();

        }
    }


}
