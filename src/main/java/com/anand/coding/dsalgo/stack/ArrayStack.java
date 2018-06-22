package com.anand.coding.dsalgo.stack;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack {
    private Object [] stackArr;
    private int size=100;
    private int top = -1;

    /**
     *
     */
    public ArrayStack(){
        stackArr = new Object[size];
    }

    /**
     *
     * @param size
     */
    public ArrayStack(int size){
        this.size = size;
        stackArr = new Object[size];
    }

    /**
     *
     * @param data
     */
    public void push(Object data){
        if(isFull()){
            throw new StackOverflowError();
        }
        stackArr[++top] = data;
    }

    /**
     *
     * @return
     */
    public T pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return elementData(top--);
    }

    /**
     *
     * @return
     */
    public T peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return elementData(top);
    }

    /**
     *
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) stackArr[index];
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return(top==-1);
    }

    /**
     *
     * @return
     */
    public boolean isFull(){
        return(top==size-1);
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){
        Stack stack = new ArrayStack(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
