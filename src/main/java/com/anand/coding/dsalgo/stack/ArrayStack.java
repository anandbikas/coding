package com.anand.coding.dsalgo.stack;
import java.util.EmptyStackException;

/**
 * ArrayStack implementation
 *
 * @param <T>
 */
public class ArrayStack<T> implements Stack {

    private static final int DEFAULT_SIZE = 100;

    private Object [] stackArr;
    private int size;
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
