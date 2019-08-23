package com.anand.coding.dsalgo.tree.arraybased;


import com.anand.coding.dsalgo.exception.HeapEmptyException;
import com.anand.coding.dsalgo.exception.HeapFullException;

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

    private T [] heapArr;
    private int size=0;

    /**
     *
     */
    public BinaryMinHeap(){
        this(1);
    }

    /**
     *
     * @param size
     */
    @SuppressWarnings("unchecked")
    public BinaryMinHeap(int size){
        heapArr = (T[])new Comparable[size];
    }

    /**
     *
     * @param newSize
     */
    @SuppressWarnings("unchecked")
    public void alterSize(int newSize){
        T[] heapArrTemp = (T[])new Comparable[newSize];

        System.arraycopy(heapArr, 0, heapArrTemp, 0, this.size);
        heapArr = heapArrTemp;
    }

    /**
     *
     * @return
     */
    public int getCapacity(){
        return heapArr.length;
    }

    /**
     *
     * @param i
     * @return
     */
    private int left(int i){
        return 2*i+1;
    }

    /**
     *
     * @param i
     * @return
     */
    private int right(int i){
        return 2*i+2;
    }

    /**
     *
     * @param i
     * @return
     */
    private int parent(int i){
        return (i-1)/2;
    }

    /**
     * fix MinHeap property by comparing with parent, bottom up
     * @param child
     */
    private void heapUp(int child){

        int parent;
        for(; child>0; child=parent){

            parent=parent(child);
            if(heapArr[parent].compareTo(heapArr[child])<=0){
                break;
            }
            T temp = heapArr[parent];
            heapArr[parent] = heapArr[child];
            heapArr[child] = temp;
        }
    }

    /**
     * fix MinHeap property by comparing with children, top down.
     * @param i
     */
    private void heapify(int i){

        int left = left(i);
        int right = right(i);
        int smallest = i;
        if(left<size && heapArr[left].compareTo(heapArr[smallest])<0){
            smallest = left;
        }

        if(right<size && heapArr[right].compareTo(heapArr[smallest])<0){
            smallest = right;
        }

        if(smallest!=i){
            T temp = heapArr[smallest];
            heapArr[smallest] = heapArr[i];
            heapArr[i] = temp;

            heapify(smallest);
        }
    }

    /**
     *
     * @param data
     */
    public void insert(T data){

        if(heapArr.length==size){
            // throw new HeapFullException();
            //Dynamic size increase.
            alterSize(heapArr.length*2);
        }

        int i = size++;
        heapArr[i]=data;
        heapUp(i);
    }

    /**
     *
     * @return
     */
    public T extractMin(){
        if(size==0){
            throw new HeapEmptyException();
        }

        T root = heapArr[0];
        heapArr[0]=heapArr[--size];
        heapArr[size]=null;
        heapify(0);

        //Dynamic size decrease.
        if(size == heapArr.length/4){
            alterSize(heapArr.length/2);
        }

        return root;
    }

    /**
     *
     * @param i
     */
    public T view(int i){
        if(i>=heapArr.length){
            throw new IllegalArgumentException("Index out of size: " + i);
        }

        return heapArr[i];
    }

    /**
     *
     * @param i
     */
    public T delete(int i){
        final T deletedNode = replace(i, heapArr[--size]);
        heapArr[size]=null;

        //Dynamic size decrease.
        if(size == heapArr.length/4){
            alterSize(heapArr.length/2);
        }

        return deletedNode;
    }

    /**
     * Replaces a key with a new data.
     * This can be used for both decreaseKey() or increaseKey() operations.
     *
     * Depending upo the operations, it implies heapUp or heapify to fix broken heap property.
     *
     * @param i
     * @param data
     * @return
     */
    public T replace(int i, T data){

        if(i>=heapArr.length){
            throw new IllegalArgumentException("Index out of size: " + i);
        }

        final T deletedNode = heapArr[i];
        heapArr[i]=data;

        if(i>0 && heapArr[parent(i)].compareTo(heapArr[i])>0){
            heapUp(i);
        } else {
            heapify(i);
        }

        return deletedNode;
    }

    /**
     *
     */
    public void display(){
        for(int i=0; i<size; i++){
            System.out.print(heapArr[i] + ", ");
        }
        System.out.println();
    }

    /**
     *
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     *
     * @return
     */
    public boolean isFull(){
        return size==heapArr.length;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return size==0;
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
