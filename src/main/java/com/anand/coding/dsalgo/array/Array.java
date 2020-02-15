package com.anand.coding.dsalgo.array;

import com.anand.coding.dsalgo.exception.ArrayEmptyException;
import com.anand.coding.dsalgo.exception.ArrayFullException;
import com.anand.coding.dsalgo.tree.BinarySearchTree;

import java.util.*;

/**
 *
 */
public class Array {

    protected static final int DEFAULT_CAPACITY = 100;

    protected int [] A;
    protected int size=0;

    /**
     *
     */
    public Array(){
        this(DEFAULT_CAPACITY);
    }

    /**
     *
     * @param capacity
     */
    public Array(int capacity){
        A = new int[capacity];
    }

    /**
     *
     * @param A
     */
    public Array(int [] A){
        this.A = A;
        size=A.length;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param data
     */
    public void insertEnd(final int data){
        insert(data, size);
    }

    /**
     *
     * @param data
     */
    public void insertStart(final int data){
        insert(data, 0);
    }

    /**
     *
     * @param data
     */
    public void insert(final int data, int index){
        if( index<0 || index>=A.length){
            throw new ArrayIndexOutOfBoundsException(index);
        }

        if(size==A.length){
            throw new ArrayFullException();
        }

        //Make space for index position by shifting all elements to right;
        for(int i = size-1; i>=index; i--){
            A[i+1] = A[i];
        }

        A[index]= data;
        size++;
    }

    /**
     *
     * @param data
     */
    public void insertAsSorted(int data) {
        insertAsSorted(0, size-1, data);
    }

    /**
     * Inserts a key in a sorted array in sorted fashion.
     *
     * @param left
     * @param right
     * @param data
     */
    public void insertAsSorted(int left, int right, int data) {

        if(right==size-1){
            throw new ArrayFullException();
        }

        int j;
        for(j=right; j >= left && A[j] > data; j--) {
            A[j+1] = A[j];
        }
        A[j+1] = data;
    }


    /**
     *
     */
    public int deleteLast(){
        return delete(size-1);
    }

    /**
     *
     */
    public int deleteFirst(){
        return delete(0);
    }

    /**
     *
     * @param index
     */
    public int delete(int index){
        if( index<0 || index>=A.length){
            throw new ArrayIndexOutOfBoundsException(index);
        }

        if(size==0){
            throw new ArrayEmptyException();
        }

        int data = A[index];
        size--;
        //Delete the index position by shifting all elements to left;
        for(int i=index; i<size; i++){
            A[i] = A[i+1];
        }

        return data;
    }

    /**
     *
     */
    public void deleteDuplicates(){

        if(size==0){
            return;
        }

        int k=1;
        for(int i=1; i<size; i++){

            int j=0;
            for(; j<k && A[j]!=A[i]; j++);
            if(j==k){
                A[k++]=A[i];
            }
        }
        size = k;
    }

    /**
     *
     */
    public void deleteAllOccurrence(int x){

        int k=0;
        for(int i=0; i<size; i++){
            if(A[i]!=x){
                A[k++]=A[i];
            }
        }
        size = k;
    }


    /**
     * equivalent to Arrays.toString
     * @return
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[ ");

        for (int i = 0; i<size; i++) {
            sb.append(A[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     */
    public void display(){
        System.out.println(toString());
    }

    /**
     *  -ve means left rotate
     *  +ve means right rotate
     *
     * @param n
     */
    public void rotate(int n){

        if(size==0 || n==0){
            return;
        }
        if(n<0){
            n = n*-1;
            n = size - n%size;
        } else {
            n %= size;
        }

        int [] tempArr = new int[n];
        System.arraycopy(A, size-n, tempArr, 0, n);

        int k=size-1;
        for(int i=size-n-1; i>=0; i--){
            A[k--] = A[i];
        }

        System.arraycopy(tempArr, 0, A, 0, n);
    }

    /**
     * Main function to test Array
     * @param args
     */
    public static void main(String[] args) {

        // Array initialization
        int A[] = {7, 9, 1, 2, 3, 4, 5};
        int C[] = Arrays.copyOf(A, 3);

        int D[] = new int[10];
        System.arraycopy(A,2, D, 0, 2);

        int B[] = new int[]{7, 3, 2, 5, 3, 1, 6, 4};
        Array array = new Array(B);
        System.out.println("Array: " + array);

        array.rotate(-2);
        System.out.println("Array rotated by -2 elements: " + array);
        System.out.println();

        array.deleteDuplicates();
        System.out.println("array.deleteDuplicates() : " + array);

        array = new Array(B);
        array.deleteAllOccurrence(3);
        System.out.println("array.deleteAllOccurrence() : " + array);
    }


    /**
     *
     * @param A
     */
    public static void display(int []A){
        System.out.println(Arrays.toString(A));
    }
}
