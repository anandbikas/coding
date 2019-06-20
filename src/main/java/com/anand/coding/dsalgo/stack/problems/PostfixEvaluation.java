package com.anand.coding.dsalgo.stack.problems;

import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

/**
 * Infix: a * (b+c) / d
 *
 * Postfix: abc+*d/     It does not require scanning and brackets
 */
public class PostfixEvaluation {

    final static String SPACE_REGEX = "\\s+";
    final static String OPERATORS = "+ - * /";

    /**
     *
     * @param postfixExpression
     */
    public static double evaluatePostfix(String postfixExpression){

        final String [] postfixExpressionElements = postfixExpression.split(SPACE_REGEX);

        Stack<String> stack = new ArrayStack<>();

        for(String s : postfixExpressionElements){
            if(OPERATORS.contains(s)){
                double Y = Double.parseDouble(stack.pop());
                double X = Double.parseDouble(stack.pop());

                stack.push(String.valueOf(calculate(X, Y, s )));
            } else {
                stack.push(s);
            }
        }

        return Double.parseDouble(stack.pop());
    }

    /**
     *
     * @param x
     * @param y
     * @param operand
     * @return
     */
    public static double calculate(double x, double y, String operand){

        switch (operand){
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(evaluatePostfix("10 20 30 + * 2 /"));

        System.out.println(evaluatePostfix(
                InfixToPostfixExpression.infixToPostfix("( ( 5 / 1 ) + ( 10 * 5 ) )")));

    }
}
