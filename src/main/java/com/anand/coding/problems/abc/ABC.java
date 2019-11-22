package com.anand.coding.problems.abc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class ABC {

    final static String SPACE_REGEX = "\\s+";
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {

        int t = Integer.parseInt(br.readLine());
        String[] strs;

        strs = br.readLine().split(SPACE_REGEX);

        System.out.println(strs);
        Arrays.stream(strs).forEach(System.out::println);

    }
}
