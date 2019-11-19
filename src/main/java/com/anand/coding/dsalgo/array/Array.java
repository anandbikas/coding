package com.anand.coding.dsalgo.array;

import com.anand.coding.dsalgo.exception.ArrayEmptyException;
import com.anand.coding.dsalgo.exception.ArrayFullException;
import com.anand.coding.dsalgo.tree.BinarySearchTree;

import java.util.*;

/**
 *
 */
public class Array {

    private static final int DEFAULT_SIZE = 100;

    private int [] A;
    private int size=0;

    /**
     *
     */
    public Array(){
        this(DEFAULT_SIZE);
    }

    /**
     *
     * @param size
     */
    public Array(int size){
        A = new int[size];
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
        //Delete the index position by shifting all elements to left;
        for(int i=index; i>=index; i--){
            A[i] = A[i+1];
        }

        size--;
        return data;
    }

    /**
     * Delete all the duplicate elements. -- Negative thinking. Complexity O(n3)
     * Or, keep only unique elements in the array -- Positive thinking. Complexity O(n2)
     */
    public void deleteDuplicates(){

        if(size==0){
            return;
        }

        int k=1;
        for(int i=1; i<size; i++){

            int j=0;
            for(; j<k; j++) {
                if (A[j]==A[i]) {
                    break;
                }
            }
            if(j==k){
                A[k++]=A[i];
            }
        }
        size = k;
    }

    /**
     * Deletes all occurrence of an element. -- Negative thinking. Complexity O(n2)
     * Or, Keep all the elements except the given one -- Positive thinking. Complexity O(n)
     */
    public void deleteAllOccurrence(int valueToDelete){

        if(size==0){
            return;
        }

        int k=0;
        for(int i=0; i<size; i++){
            if(A[i]==valueToDelete){
                continue;
            }
            A[k++]=A[i];
        }
        size = k;
    }


    /**
     * equivalent to Arrays.toString
     * @return
     */
    @Override
    public String toString() {
        String s = "[ ";

        for (int i = 0; i<size; i++) {
            s+=A[i] + " ";
        }
        s+="]";
        return s;
    }

    /**
     *
     */
    public void display(){
        System.out.println(toString());
    }


    //################################ Searching Algorithms ################################
    /**
     *
     * Complexity: O(lon n)
     *
     * @param key
     * @return
     */
    public int binarySearch(int key) {
        return binarySearch(0, size-1, key);
    }

    /**
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearch(int left, int right, int key) {

        while (left <= right) {
            int mid = left + (right-left)/2;

            if(key==A[mid]){
                return mid;
            }

            if (key < A[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    /**
     *
     * @param key
     * @return
     */
    public int binarySearchFirstOccurrence(int key) {
        return binarySearchFirstOccurrence(0, size-1, key);
    }

    /**
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearchFirstOccurrence(int left, int right, int key) {

        while (left < right) {
            int mid = left + (right-left) / 2;
            if (key <= A[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return (A[left] == key) ? left : -1;
    }

    /**
     *
     * @param key
     * @return
     */
    public int binarySearchLastOccurrence(int key) {
        return binarySearchLastOccurrence(0, size-1, key);
    }

    /**
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearchLastOccurrence(int left, int right, int key) {

        while (left < right) {
            int mid = (int)Math.ceil(left + (right-left) / 2.0);
            if (key >= A[mid]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return (A[left] == key) ? left : -1;
    }

    /**
     *
     * @param key
     * @return
     */
    public int binarySearchRec(int key) {
        return binarySearchRec(0, size-1, key);
    }

    /**
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearchRec(int left, int right, int key) {

        if (left > right) {
            return -1;
        }

        int mid = left + (right-left)/2;

        if (key < A[mid]) {
            return binarySearchRec(left, mid - 1, key);
        }
        if (key > A[mid]) {
            return binarySearchRec(mid + 1, right, key);
        }

        return mid;
    }

    /**
     *  -ve means left rotate
     *  +ve means right rotate
     *
     * @param n
     */
    public void rotateByNElements(int n){

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
     *
     * @return
     */
    public int getRotatedArrayPivotElementIndex() {
        return getRotatedArrayPivotElementIndex(0, size-1);
    }

    /**
     * Find the left most element which is less than the first element.
     * If the array is not rotated, pivot element index will be right+1.
     *
     * Complexity:
     *      O(lon n) if elements are unique or firstElement>lastElement
     *      O(n) if elements are not unique
     *
     * @param left
     * @param right
     * @return
     */
    public int getRotatedArrayPivotElementIndex(int left, int right) {

        /**
         * For duplicate elements:
         *      3 3 3 3 4 3 ==> pivotIndex = 5
         *      3 4 3 3 3 3 ==> pivotIndex = 2
         *      3 3 3 3 3 3 ==> pivotIndex = 6 (not found)
         *
         * To make this algorithm work for duplicate elements, we ensure A[left]>A[right] by adjusting right index.
         * which may take O(n) in worst case.
         */

        //Workaround for duplicate elements starts.
        int newRightIndex = right;
        for (; left<newRightIndex && A[newRightIndex] == A[left]; newRightIndex--);
        if(left<newRightIndex){
            right = newRightIndex;
        }
        //Workaround for duplicate elements ends.

        //Binary search operation
        int firstElement = A[left];
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (firstElement<=A[mid]) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return left;
    }

    /**
     *
     * @param key
     * @return
     */
    public int binarySearchInRotatedArray(final int key) {
        return binarySearchInRotatedArray(0, size-1, key);
    }

    /**
     * Complexity: O(lon n)
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearchInRotatedArray(final int left, final int right, final int key) {

        final int pivotElementIndex = getRotatedArrayPivotElementIndex(left, right);

        return key>=A[left] ?
                binarySearch(left, pivotElementIndex-1, key):
                    binarySearch(pivotElementIndex, right, key);
    }

    /**
     *
     * @param key
     * @return
     */
    public int binarySearchInRotatedArrayWithoutPivot(final int key) {
        return binarySearchInRotatedArrayWithoutPivot(0, size-1, key);
    }
    /**
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearchInRotatedArrayWithoutPivot(int left, int right, final int key){

        //Workaround for duplicate elements starts.
        int newRightIndex = right;
        for (; left<newRightIndex && A[newRightIndex] == A[left]; newRightIndex--);
        if(left<newRightIndex){
            right = newRightIndex;
        }
        //Workaround for duplicate elements ends.

        while(left<=right){
            int mid = left + (right-left)/2;

            if(A[mid]==key){
                return mid;
            }

            if(A[left]<=A[mid]){
                if (key>=A[left] && key<A[mid] ){
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else {
                if(key>A[mid] && key<=A[right]){
                    left = mid +1;
                } else {
                    right = mid -1;
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param key
     * @return
     */
    public Integer floor(int key) {
        return floor(0, size-1, key);
    }

    /**
     * Floor in unsorted array
     *
     * @param key
     * @return
     */
    public Integer floor(int left, int right, int key) {

        Integer floor = null;

        for(int x: A){
            if(x==key){
                return x;
            }
            if(x>key){
                continue;
            }
            if(floor==null || floor<x){
                floor = x;
            }
        }

        return floor;
    }

    /**
     *
     * @param key
     * @return
     */
    public Integer floorSortedArray(int key) {
        return floorSortedArray(0, size-1, key);
    }

    /**
     * Floor in sorted array
     *
     * @param key
     * @return
     */
    public Integer floorSortedArray(int left, int right, int key) {

        if (key < A[left]) {
            return null;
        }
        if (key >= A[right]) {
            return A[right];
        }

        //Operate as binarySearchFirstOccurrence
        while (left <= right) {
            int mid = left + (right-left)/2;

            if(key==A[mid]){
                return A[mid];
            }

            if (key < A[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return A[right];
    }



    //################################ Sorting Algorithms ################################
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

        int A1[] = Arrays.copyOfRange(A,left, midIndex);
        int A2[] = Arrays.copyOfRange(A, midIndex+1, right);

        int i = 0;
        int j = midIndex+1;
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
            int temp = A[0];
            A[0] = A[x];
            A[x] = temp;

            heapify(0, x);
        }
    }

    /**
     * fix MaxHeap property by comparing with children
     *
     * @param i
     */
    private void heapify(int i, int currentSize){

        int left = 2*i+1;
        int right = 2*i+2;
        int largest = i;

        if(left<currentSize && A[left] > A[largest]){
            largest = left;
        }

        if(right<currentSize && A[right] > A[largest]){
            largest = right;
        }

        if(largest!=i){
            int temp = A[largest];
            A[largest] = A[i];
            A[i] = temp;

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

        for(int i=0; i<A.length; i++){
            A[i] = sortedList.get(i);
        }
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
        System.out.println("floor 8: " + array.floor(8));
        System.out.println();


        array.insertionSort();
        array.bubbleSort();
        array.selectionSort();
        array.mergeSort();
        array.quickSort();
        array.bstSort();

        System.out.println("Sorted array: " + array);
        System.out.println("floor 8: " + array.floor(8));
        System.out.println("floorSortedArray 8: " + array.floorSortedArray(8));
        System.out.println("binarySearch 3: " + array.binarySearch(3));
        System.out.println("binarySearchFirstOccurence 3: " + array.binarySearchFirstOccurrence(3));
        System.out.println("binarySearchLastOccurrence 3: " + array.binarySearchLastOccurrence(3));
        System.out.println();


        array.rotateByNElements(-2);
        System.out.println("Array rotated by -2 elements: " + array);
        System.out.println("getRotatedArrayPivotElementIndex: " + array.getRotatedArrayPivotElementIndex());
        System.out.print("binarySearchInRotatedArray: ");
        for (int number : B) {
            System.out.print(array.binarySearchInRotatedArray(number) + " ");
        }
        System.out.println();
        System.out.print("binarySearchInRotatedArrayWithoutPivot: ");
        for (int number : B) {
            System.out.print(array.binarySearchInRotatedArrayWithoutPivot(number) + " ");
        }
        System.out.println();
        System.out.println();


        int [] E = {7, 3, 2, 5, 1, 6, 4, 3, 5, 2, 1};
        Array array3 = new Array(E);
        System.out.println("Array3: " + array3);

        array3.deleteDuplicates();
        System.out.println("array3.deleteDuplicates() : " + array3);

        array3 = new Array(E);
        array3.deleteAllOccurrence(3);
        System.out.println("array3.deleteAllOccurrence() : " + array3);
    }

    public static void display(int []A){
        System.out.println(Arrays.toString(A));
    }
}
