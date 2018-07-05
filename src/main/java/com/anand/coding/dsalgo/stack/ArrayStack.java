package com.anand.coding.dsalgo.stack;
import com.anand.coding.dsalgo.exception.StackEmptyException;
import com.anand.coding.dsalgo.exception.StackFullException;

/**
 * ArrayStack implementation
 *
 * @param <T>
 */
public class ArrayStack<T> implements Stack {

    private static final int DEFAULT_SIZE = 100;

    public Object [] stackArr;
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
    public ArrayStack(int size){
        stackArr = new Object[size];
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
    public void push(Object data){
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
        T data =  peek();
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
        return(top==stackArr.length-1);
    }


    /**
     *
     */
    public void display(){
        for(int i=0; i<=top; i++){
            System.out.print(stackArr[i] + " ");
        }
        System.out.println();
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
