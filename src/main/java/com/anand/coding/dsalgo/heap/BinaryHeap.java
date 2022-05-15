package com.anand.coding.dsalgo.heap;

import java.util.Comparator;

/**
 * Generic BinaryHeap with Comparator
 *
 * @param <T>
 */
public class BinaryHeap<T>{

    private T [] A;
    private int size=0;
    private Comparator<T> comparator;

    public BinaryHeap(){
        this(1, null);
    }

    public BinaryHeap(int initialSize){
        this(initialSize, null);
    }

    public BinaryHeap(Comparator<T> comparator){
        this(1, comparator);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(int initialSize, Comparator<T> comparator){
        A = (T[])new Comparable[initialSize];
        this.comparator = (comparator==null) ? Comparator.comparingInt(Object::hashCode) : comparator;
    }

    @SuppressWarnings("unchecked")
    public void alterSize(int newSize){
        T[] T = (T[])new Comparable[newSize];

        System.arraycopy(A, 0, T, 0, this.size);
        A = T;
    }

    public int getCapacity(){ return A.length;}
    public int getSize(){ return size;}
    public boolean isFull(){ return size==A.length;}
    public boolean isEmpty(){ return size==0;}

    private int left(int i){ return 2*i+1;}
    private int right(int i){ return 2*i+2;}
    private int parent(int i){ return (i-1)/2;}
    private void swap(int i, int j){ T temp = A[i]; A[i] = A[j]; A[j] = temp;}

    /**
     * Fix Heap property.
     * @param i
     */
    private void heapify(int i){

        if(i>0 && comparator.compare(A[i], A[parent(i)])<0){
            // compare with parent, bottom up.
            for (int child=i; child > 0 && comparator.compare(A[child], A[parent(child)]) < 0; child = parent(child)) {
                swap(child, parent(child));
            }

        } else {
            // compare with children, top down.
            while(true) {
                int lc = left(i), rc = right(i), smallest = i;
                if (lc < size && comparator.compare(A[lc], A[smallest]) < 0) smallest = lc;
                if (rc < size && comparator.compare(A[rc], A[smallest]) < 0) smallest = rc;
                if(smallest==i) {
                    break;
                }
                swap(i, smallest);
                i = smallest;
            }
        }
    }

    /**
     *
     * @param data
     */
    public T insert(T data){
        //Dynamic size increase.
        if(A.length==size) alterSize(A.length*2);

        A[size]=data;
        heapify(size++);
        return data;
    }

    /**
     *
     * @param i
     */
    public T view(int i){
        return (i<size) ? A[i] : null;
    }

    /**
     *
     * @return
     */
    public T extract(){
        if(size==0){
            return null;
        }

        final T deletedNode=A[0];   A[0]=A[--size];     A[size]=null;
        heapify(0);

        //Dynamic size decrease.
        if(size==A.length/4) alterSize(A.length/2);

        return deletedNode;
    }

    /**
     *
     * @param i
     */
    public T delete(int i){
        if(i>=size){
            return null;
        }

        final T deletedNode=A[i];   A[i]=A[--size];     A[size]=null;
        heapify(i);

        //Dynamic size decrease.
        if(size==A.length/4) alterSize(A.length/2);

        return deletedNode;
    }

    /**
     * Replaces a key with a new data.
     * This can be used for both decreaseKey() and increaseKey() operations.
     *
     * @param i
     * @param data
     */
    public T replace(int i, T data){
        if(i>=size){
            return null;
        }

        final T oldData=A[i];   A[i]=data;
        heapify(i);

        return oldData;
    }

    public void display(){
        for(int i=0; i<size; i++){
            System.out.print(A[i] + ", ");
        }
        System.out.println();
    }

    /**
     *  Main function to test BinaryHeap
     * @param args
     */
    public static void main(String args[]){

        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(Comparator.comparingInt(Integer::intValue));

        binaryHeap.insert(5);
        binaryHeap.insert(4);
        binaryHeap.insert(2);
        binaryHeap.insert(3);
        binaryHeap.insert(6);


        // Heap now: 2, 3, 4, 5, 6
        binaryHeap.replace(2, 1);

        // Heap now: 1, 3, 2, 5, 6
        binaryHeap.delete(1);

        // Heap now: 1, 5, 2, 6
        binaryHeap.display();

        System.out.println(binaryHeap.extract());
        System.out.println("Capacity: " + binaryHeap.getCapacity());

        // Heap now: 2, 5, 6
        binaryHeap.display();

        binaryHeap.extract();

        // Heap now: 5, 6
        binaryHeap.display();
        System.out.println("Capacity: " + binaryHeap.getCapacity());
    }
}
