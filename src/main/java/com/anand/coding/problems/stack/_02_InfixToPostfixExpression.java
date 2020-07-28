package com.anand.coding.problems.stack;

import java.util.Stack;

/**
 * Infix: a * (b+c) / d
 *
 * Postfix: abc+*d/   or abc+d/*
 * It does not require scanning and brackets
 */
public class _02_InfixToPostfixExpression {

    final static String SPACE_REGEX = "\\s+";
    final static String OPERATORS = "+ - * /";

    final static String OPENING_BRACKETS = "(";
    final static String CLOSING_BRACKETS = ")";


    /**
     *
     * @param infixExpression
     */
    public static String infixToPostfix(String infixExpression){

        final String [] infixExpressionElements = infixExpression.split(SPACE_REGEX);

        Stack<String> stack = new Stack<>();

        String postfixExpression = "";

        for(String s : infixExpressionElements){
            if(OPENING_BRACKETS.contains(s) || OPERATORS.contains(s)){
                stack.push(s);
            } else if (CLOSING_BRACKETS.contains(s)) {
                while(!OPENING_BRACKETS.contains(stack.peek())) {
                    postfixExpression += stack.pop() + " ";
                }
                stack.pop();
            } else {
                postfixExpression += s + " ";
            }
        }

        return postfixExpression;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(infixToPostfix("( a * ( b + c ) / d )"));

        System.out.println(infixToPostfix("( ( ( a + b ) * ( c $ ( d - e ) + f ) / g ) $ ( h - j ) )"));

    }
}
