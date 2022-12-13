package com.anand.coding.problems.stack;

import java.util.Stack;

/**
 * Given two integer arrays pushed and popped each with distinct values,
 * return true if this could have been the result of a sequence of push and pop operations on an
 * initially empty stack, or false otherwise.
 *
 * leetcode.com/problems/validate-stack-sequences
 */
public class _04_ValidateStackSequences {

    public static boolean validateStackSequences(int[] pushed, int[] popped) {

        Stack<Integer> stack = new Stack<>();
        int i=0,j=0;
        while(i<pushed.length){
            stack.push(pushed[i++]);
            while(!stack.isEmpty() && j<popped.length && stack.peek()==popped[j]){
                stack.pop();
                j++;
            }
        }

        return j==popped.length;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));

    }
}
