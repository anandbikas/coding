package com.anand.coding.dsalgo.array;

import com.anand.coding.dsalgo.tree.binary.BinarySearchTree;

import java.util.Arrays;
import java.util.List;

/**
 * ####### Sorting Algorithms #######
 */
public class ArraySort extends Array {

    /**
     *
     * @param A
     */
    public ArraySort(int [] A){
        super(A);
    }

    /**
     * Array of 1 element is sorted.
     * Insert all other elements in this sorted array one by one.
     */
    public void insertionSort() {
        for (int i=1; i < size ; i++) {

            int key = A[i];
            int j;
            for(j=i-1; j >= 0 && A[j] > key; j--) {
                A[j+1] = A[j];
            }
            A[j+1] = key;
        }
    }

    /**
     * Sort in bubble fashion.
     *
     */
    public void bubbleSort() {

        for (int i=0; i < size; i++) {
            for(int j=0; j < size-1-i; j++){
                if(A[j]>A[j+1]){
                    int temp = A[j];
                    A[j]=A[j+1];
                    A[j+1]=temp;
                }
            }
        }
    }

    /**
     *
     */
    public void selectionSort() {
        selectionSort(0, size-1);
    }

    /**
     *
     * @param left
     * @param right
     */
    public void selectionSort(final int left, final int right){

        for(int i=left; i<right; i++){

            int indexOfSmallestElement=i;
            for(int j=i+1; j<=right; j++){
                if (A[j] <A[indexOfSmallestElement]){
                    indexOfSmallestElement = j;
                }
            }
            int temp = A[i];
            A[i] = A[indexOfSmallestElement];
            A[indexOfSmallestElement] = temp;
        }
    }

    /**
     * Array has two parts each sorted.
     * Merge the two sub arrays into one in a sorted fashion.
     *
     * @param left
     * @param midIndex
     * @param right
     */
    private void mergeAsSorted(int left, int midIndex, int right){

        int A1[] = Arrays.copyOfRange(A, left, midIndex);
        int A2[] = Arrays.copyOfRange(A, midIndex+1, right);

        int i = 0;
        int j = 0;
        while(i<A1.length && j<A2.length){
            A[left++] = (A1[i] < A2[j]) ? A1[i++] : A2[j++];
        }
        while(i<A1.length){
            A[left++] = A1[i++];
        }
        while(j<A2.length){
            A[left++] = A2[j++];
        }
    }

    /**
     *
     */
    public void mergeSort() {
        mergeSort(0, size-1);
    }

    /**
     * Array of 1 element is sorted,
     * Sort array of 2 elements onwards using mergeAsSorted recursively
     *
     * Time complexity using recurrence relation
     *
     * T(n) = 2 * T(n/2) + n
     *      = 4 * T(n/4) + 2n
     *      = 2^i * T(n/2^i) + in
     *
     * Say  n = 2^i
     * Then, log n = log 2^i
     *             = i
     *
     * Now,
     * T(n) = n * T(n/n) + logn * n
     *      = n + nLog n
     *      = O(nLog n)         ~~~In Big O notation we leave the lower terms.
     *
     *
     * @param left
     * @param right
     */
    public void mergeSort(final int left, final int right){
        if(left>=right){
            return;
        }

        int midIndex = (left + right) / 2;
        mergeSort(left, midIndex);
        mergeSort(midIndex+1, right);
        mergeAsSorted(left, midIndex, right);
    }

    /**
     *
     */
    public void quickSort() {
        quickSort(0, size-1);
    }

    /**
     * Choose one element (leftmost, rightmost, middle or even random) and put in in the right place as in
     * the sorted array in O(n) time by keeping all smaller element left and larger to the right.
     *
     * Now there are two partitions in the array, repeat the procedure until all partitions are processed.
     *
     *
     * Time complexity
     *      T(n) = T(k) + T(n-k-1) + Θ(n)
     *
     * Worst Case: (when smallest or greatest element is always chosen as pivot element, where k = 0;
     *      T(n) = T(0) + T(n-1) + Θ(n)
     *           = T(n-1) + Θ(n)
     *           =  Θ(n2) , by solving the above recurrence relation.
     *
     *
     * Best Case: (when the pivot is always picked the middle element in sorted array)
     *      T(n) = 2 * T(n/2) + Θ(n)
     *           = 4 * T(n/4) + 2Θ(n)
     *           = 2^i * T(n/2^i) + iΘ(n)
     *
     * Say  n = 2^i
     * Then, log n = log 2^i
     *             = i
     *
     * Now,
     *      T(n) = n * T(n/n) + logn * Θ(n)
     *           = n + nLog n
     *           = O(nLog n)         ~~~In Big O notation we leave the lower terms.
     *
     *  INFO:
     *  1. QuickSort is preferred over MergeSort for arrays due to its in-place sorting technique.
     *  2. MergeSort is preferred over QuickSort for linked list due to linked data structure.
     *     No extra space is required for linked list and it is always is O(nlog n)
     *
     * @param left
     * @param right
     */
    public void quickSort(final int left, final int right){
        if(left>=right){
            return;
        }

        int partitionIndex = partition(left, right);
        quickSort(left, partitionIndex-1);
        quickSort(partitionIndex+1, right);
    }

    /**
     * T
     *
     * @param left
     * @param right
     * @return
     */
    private int partition(int left, int right){

        //Smaller array index
        int i = left;
        for(int j=left; j<right; j++){
            if(A[j]<=A[right]){

                //Swap i and j;
                int temp = A[i];
                A[i]=A[j];
                A[j] = temp;

                i++;
            }
        }
        int temp = A[i];
        A[i]=A[right];
        A[right]=temp;

        return i;
    }

    private void swap(int i, int j){ int temp = A[i]; A[i] = A[j]; A[j] = temp;}

    /**
     * Sort using Binary Max Heap
     * It takes O(nLog n) time even if the array is already sorted due to creating a heap at the start.
     *
     */
    public void heapSort(){
        
        //Create Max Binary Heap inline existing array.
        for(int i=size/2-1; i>=0; i--){
            heapify(i, size);
        }

        //Top element is the largest, replace it with last and heapify the new element upto size -1.
        for(int x=size-1; x>0;x--){
            swap(0,x);
            heapify(0, x);
        }
    }

    /**
     * fix MaxHeap property by comparing with children
     *
     * @param i
     */
    private void heapify(int i, int currentSize){

        int left=2*i+1, right=2*i+2, largest = i;

        if(left<currentSize && A[left] > A[largest]){
            largest = left;
        }
        if(right<currentSize && A[right] > A[largest]){
            largest = right;
        }

        if(largest!=i){
            swap(i, largest);
            heapify(largest, currentSize);
        }
    }

    /**
     *
     */
    public void bstSort(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for(int x: A){
            bst.insert(x);
        }

        List<Integer> sortedList = bst.getSortedList();

        for(int i=0; i<sortedList.size(); i++){
            A[i] = sortedList.get(i);
        }
    }


    /**
     * Main function to test Array
     * @param args
     */
    public static void main(String[] args) {

        int B[] = new int[]{7, 3, 2, 5, 1, 6, 4};
        ArraySort array = new ArraySort(B);
        System.out.println("Array: " + array);
        System.out.println();


        array.insertionSort();
        array.bubbleSort();
        array.selectionSort();
        array.mergeSort();
        array.quickSort();
        array.bstSort();

        System.out.println("Sorted array: " + array);
    }
}
