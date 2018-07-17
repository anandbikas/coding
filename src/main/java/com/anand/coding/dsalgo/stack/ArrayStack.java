package com.anand.coding.dsalgo.stack;
import com.anand.coding.dsalgo.exception.StackEmptyException;
import com.anand.coding.dsalgo.exception.StackFullException;

/**
 * ArrayStack implementation
 *
 * @param <T>
 */
public class ArrayStack<T> implements Stack<T> {

    private static final int DEFAULT_SIZE = 100;

    public T [] stackArr;
    private int top=-1;

    /**
     *
     */
    public ArrayStack(){
        this(DEFAULT_SIZE);
    }

    /**
     *
     * @param size
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int size){
        stackArr = (T[])new Object[size];
    }

    /**
     *
     * @return
     */
    public int size(){
        return top+1;
    }

    /**
     *
     * @param data
     */
    public void push(T data){
        if(isFull()){
            throw new StackFullException();
        }
        stackArr[++top] = data;
    }

    /**
     *
     * @return
     */
    public T pop(){
        if(isEmpty()){
            throw new StackEmptyException();
        }
        T data =  stackArr[top];
        stackArr[top--]=null;
        return data;
    }

    /**
     *
     * @return
     */
    public T peek(){
        if(isEmpty()){
            throw new StackEmptyException();
        }
        return stackArr[top];
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
        return(top==stackArr.length-1);
    }

    /**
     *
     */
    public void display(){
        for(int i=0; i<=top; i++){
            System.out.print(stackArr[i] + ", ");
        }
        System.out.println();
    }

    /**
     *
     * @return
     */
    public String getAsWord(){
        String word = "";

        for(int i=0; i<=top; i++){
            word += stackArr[i];
        }
        return word;
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){
        Stack<Integer> stack = new ArrayStack<>(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        stack.display();
        System.out.println(((ArrayStack<Integer>) stack).getAsWord());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
