package com.anand.coding.dsalgo.tree.arraybased;

import com.anand.coding.dsalgo.exception.HeapEmptyException;

/**
 * A BinaryHeap is a complete BinaryTree.
 * (all levels completely filled except the last level where all the nodes are as left as possible).
 *
 * BinaryHeap can be a Min Heap or a Max Heap.
 *
 * In a BinaryMinHeap, the root node is the minimum for all the possible sub trees.
 *
 *            5                     10
 *        10    30            15        20
 *     20                  40   35   50   40
 *
 * Array represents level order traversal of the tree.
 *
 * TODO: Application:
 *      1. HeapSort O(nLog n).
 *
 *      2. Sort an almost sorted array.
 *
 *      3. Merge K sorted arrays
 *
 *      4. Priority Queue: insert(), delete(), extractMax(), decreaseKey() operations in O(log n) time.
 *
 *      5. Variations: Binomial Heap, Fibonacci Heap perform union in O(long n) which in O(n) in Binary Heap.
 *
 *      6. Heap implemented Priority queue is used in Graph Prim's Minimum Spanning Tree and Dijkstra's Shortest Path algorithms.
 *
 *      7. Efficiently find kth smallest or largest element in an array.
 *
 * @param <T>
 */
public class BinaryMinHeap<T extends Comparable<T>>{

    private T [] A;
    private int size=0;

    public BinaryMinHeap(){
        this(1);
    }

    @SuppressWarnings("unchecked")
    public BinaryMinHeap(int initialSize){
        A = (T[])new Comparable[initialSize];
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
     * fix MinHeap property by comparing with parent, bottom up
     * @param child
     */
    private void heapUp(int child){

        while (child>0 && A[child].compareTo(A[parent(child)])<0){
            swap(child, parent(child));
            child = parent(child);
        }
    }

    /**
     * fix MinHeap property by comparing with children, top down.
     * @param i
     */
    private void heapify(int i){

        int left=left(i), right=right(i), smallest = i;

        if(left<size && A[left].compareTo(A[smallest])<0){
            smallest = left;
        }
        if(right<size && A[right].compareTo(A[smallest])<0){
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
    public T extractMin(){
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
        if(i>0 && A[i].compareTo(A[parent(i)])<0) heapUp(i); else heapify(i);

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
        if(i>0 && A[i].compareTo(A[parent(i)])<0) heapUp(i); else heapify(i);

        return oldData;
    }

    public void display(){
        for(int i=0; i<size; i++){
            System.out.print(A[i] + ", ");
        }
        System.out.println();
    }

    /**
     *  Main function to test BinaryMinHeap
     * @param args
     */
    public static void main(String args[]){

        final BinaryMinHeap<Integer> binaryMinHeap = new BinaryMinHeap<>();

        binaryMinHeap.insert(5);
        binaryMinHeap.insert(4);
        binaryMinHeap.insert(2);
        binaryMinHeap.insert(3);
        binaryMinHeap.insert(6);


        // Heap now: 2, 3, 4, 5, 6
        binaryMinHeap.replace(2, 1);

        // Heap now: 1, 3, 2, 5, 6
        binaryMinHeap.delete(1);

        // Heap now: 1, 5, 2, 6
        binaryMinHeap.display();

        System.out.println(binaryMinHeap.extractMin());
        System.out.println("Capacity: " + binaryMinHeap.getCapacity());

        // Heap now: 2, 5, 6
        binaryMinHeap.display();

        binaryMinHeap.extractMin();

        // Heap now: 5, 6
        binaryMinHeap.display();
        System.out.println("Capacity: " + binaryMinHeap.getCapacity());
    }
}
