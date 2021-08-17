package com.anand.coding.dsalgo.tree.arraybased;

import com.anand.coding.dsalgo.exception.HeapEmptyException;

import java.util.Comparator;

/**
 * Generic BinaryHeap with Comparator
 *
 * @param <T>
 */
public class BinaryHeapWithComparator<T>{

    private T [] A;
    private Comparator<T> comparator;

    private int size=0;

    public BinaryHeapWithComparator(){
        this(1, null);
    }

    public BinaryHeapWithComparator(int initialSize){
        this(initialSize, null);
    }

    public BinaryHeapWithComparator(Comparator<T> comparator){
        this(1, comparator);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeapWithComparator(int initialSize, Comparator<T> comparator){
        this.A = (T[])new Object[initialSize];

        if(comparator==null){
            comparator = Comparator.comparingInt(Object::hashCode);
        }
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    public void alterSize(int newSize){
        T[] T = (T[])new Object[newSize];

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
     * fix Heap property by comparing with parent, bottom up
     * @param child
     */
    private void heapUp(int child){

        while (child>0 && comparator.compare(A[child],A[parent(child)])<0){
            swap(child, parent(child));
            child = parent(child);
        }
    }

    /**
     * fix Heap property by comparing with children, top down.
     * @param i
     */
    private void heapify(int i){

        int left=left(i), right=right(i), smallest = i;

        if(left<size && comparator.compare(A[left],A[smallest])<0){
            smallest = left;
        }
        if(right<size && comparator.compare(A[right],A[smallest])<0){
            smallest = right;
        }

        if(smallest!=i){
            swap(i, smallest);
            heapify(smallest);
        }
    }

    /**
     *
     * @param data
     */
    public void insert(T data){
        //Dynamic size increase.
        if(A.length==size){
            alterSize(A.length*2);
        }

        A[size]=data;
        heapUp(size++);
    }

    /**
     *
     * @param i
     */
    public T view(int i){
        if(i>=size){
            throw new IllegalArgumentException("Index out of size: " + i);
        }

        return A[i];
    }

    /**
     *
     * @return
     */
    public T extract(){
        if(size==0){
            throw new HeapEmptyException();
        }

        final T deletedNode=A[0];   A[0]=A[--size];     A[size]=null;
        heapify(0);

        //Dynamic size decrease.
        if(size==A.length/4)
            alterSize(A.length/2);

        return deletedNode;
    }

    /**
     *
     * @param i
     */
    public T delete(int i){
        if(i>=size){
            throw new IllegalArgumentException("Index out of size: " + i);
        }

        final T deletedNode=A[i];   A[i]=A[--size];     A[size]=null;
        if(i>0 && comparator.compare(A[i], A[parent(i)])<0) heapUp(i); else heapify(i);

        //Dynamic size decrease.
        if(size==A.length/4)
            alterSize(A.length/2);

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
            throw new IllegalArgumentException("Index out of size: " + i);
        }

        final T oldData=A[i];   A[i]=data;
        if(i>0 && comparator.compare(A[i],A[parent(i)])<0) heapUp(i); else heapify(i);

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

        final BinaryHeapWithComparator<Integer> binaryHeap =
                new BinaryHeapWithComparator<>(Comparator.comparingInt(Integer::intValue).reversed());

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
