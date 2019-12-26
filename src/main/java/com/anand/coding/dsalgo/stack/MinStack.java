package com.anand.coding.dsalgo.stack;

import java.util.Stack;
/**
 *
 */
public class MinStack {

    private Stack<Integer> stack = new Stack<>();
    private Integer  min = Integer.MAX_VALUE;


    public void push(int x) {
        if(x<=min){
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public int pop() {
        int item =  stack.pop();

        if(item==min){
            min = stack.pop();
        }
        return item;
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    public boolean isEmpty(){
        return(stack.isEmpty());
    }


    /**
     *
     */
    public void display(){
        System.out.print(stack.toString());
        System.out.println();
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){
        MinStack stack = new MinStack();

        stack.push(3);
        stack.push(2);
        stack.push(7);
        stack.push(5);

        stack.display();

        while (!stack.isEmpty()){
            System.out.println(stack.getMin());
            stack.pop();
        }
    }
}
