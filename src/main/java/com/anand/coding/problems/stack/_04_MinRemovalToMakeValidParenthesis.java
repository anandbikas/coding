package com.anand.coding.problems.stack;

import java.util.Stack;

/**
 * Remove minimum parenthesis in a string to make valid parenthesis.
 */
public class _04_MinRemovalToMakeValidParenthesis {

    public static String minRemoveToMakeValidParenthesis(String s) {

        StringBuilder sb = new StringBuilder(s);

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<sb.length(); i++){
            if(sb.charAt(i) =='('){
                stack.push(i);
            } else if (sb.charAt(i)==')'){
                if(stack.isEmpty()){
                    sb.setCharAt(i, '.');
                } else {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()){
            sb.setCharAt(stack.pop(), '.');
        }

        return sb.toString().replaceAll("\\.", "");
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(minRemoveToMakeValidParenthesis("a)b(c)d"));
        System.out.println(minRemoveToMakeValidParenthesis("lee(t(c)o)de)"));

    }
}
