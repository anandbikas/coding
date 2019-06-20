package com.anand.coding.dsalgo.stack.problems;

import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

/**
 * Infix: (a * (b+c) / d
 * In infix, provide as much extra bracket as required for precedence.
 *
 * Postfix: abc+*d/   or abc+d/*
 * It does not require scanning and brackets
 */
public class InfixToPrefixExpression {

    final static String SPACE_REGEX = "\\s+";
    final static String OPERATORS = "+ - * /";

    final static String OPENING_BRACKETS = "(";
    final static String CLOSING_BRACKETS = ")";


    /**
     *
     * @param infixExpression
     */
    public static String infixToPrefix(String infixExpression){

        final String [] infixExpressionElements = infixExpression.split(SPACE_REGEX);

        Stack<String> stack = new ArrayStack<>();

        String prefixExpression = "";

        for(int i= infixExpressionElements.length-1; i>=0;  i--){

            String s = infixExpressionElements[i];

            if(CLOSING_BRACKETS.contains(s) || OPERATORS.contains(s)){
                stack.push(s);
            } else if (OPENING_BRACKETS.contains(s)) {
                while(!CLOSING_BRACKETS.contains(stack.peek())) {
                    prefixExpression = stack.pop() + " " + prefixExpression;
                }
                stack.pop();
            } else {
                prefixExpression = s + " " + prefixExpression;
            }
        }

        return prefixExpression;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(infixToPrefix("( a * ( b + c ) / d )"));
        System.out.println(infixToPrefix("( ( a - b ) * ( c / d ) )"));

        System.out.println(infixToPrefix("( ( a * ( b + d ) / e ) - ( f * ( g + ( h / k ) ) ) )"));
    }
}
