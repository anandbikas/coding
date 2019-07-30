package com.anand.coding.dsalgo.array;

import com.anand.coding.dsalgo.exception.ArrayEmptyException;
import com.anand.coding.dsalgo.exception.ArrayFullException;
import com.anand.coding.dsalgo.tree.multivalue.BinarySearchTree;

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
    public void insertAtEnd(final int data){
        insert(data, size);
    }

    /**
     *
     * @param data
     */
    public void insertAtStart(final int data){
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

            for(int j=0; j<k; j++){
                if(A[j]==A[i]){
                    break;
                }
                A[k++]=A[i];
            }
        }
        size = k;
    }

    /**
     * Deletes all occurrence of an element. -- Negative thinking. Complexity O(n2)
     * Or, Keep all the elements except the given one -- Positive thinking. Complexity O(n)
     */
    public void deleteAllOccurence(int valueToDelete){

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
     * @param startIndex
     * @param endIndex
     * @param key
     * @return
     */
    public int binarySearch(int startIndex, int endIndex, int key) {

        while (startIndex <= endIndex) {
            int mid = (startIndex + endIndex) / 2;
            if (key < A[mid]) {
                endIndex = mid - 1;
            } else if (key > A[mid]) {
                startIndex = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     *
     * @param key
     * @return
     */
    public int binarySearchFirstOccurence(int key) {
        return binarySearchFirstOccurence(0, size-1, key);
    }

    /**
     * *TWISTED* algorithm.
     *
     * @param startIndex
     * @param endIndex
     * @param key
     * @return
     */
    public int binarySearchFirstOccurence(int startIndex, int endIndex, int key) {

        while (startIndex < endIndex) {
            int mid = (startIndex + endIndex) / 2;
            if (key <= A[mid]) {
                endIndex = mid;
            } else {
                startIndex = mid + 1;
            }
        }

        if (A[startIndex] == key) {
            return startIndex;
        }
        return -1;
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
     * @param startIndex
     * @param endIndex
     * @param key
     * @return
     */
    public int binarySearchRec(int startIndex, int endIndex, int key) {

        if (startIndex > endIndex) {
            return -1;
        }

        int mid = (startIndex + endIndex) / 2;
        if (key < A[mid]) {
            return binarySearchRec(startIndex, mid - 1, key);
        } else if (key > A[mid]) {
            return binarySearchRec(mid + 1, endIndex, key);
        } else {
            return mid;
        }
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
        }
        if(n>0){
            n %= size;
        }

        int [] tempArr = new int[n];
        System.arraycopy(A, size-n, tempArr, 0, n);

        for(int i=size-1; i>=n; i--){
            A[i] = A[i-n];
        }

        System.arraycopy(tempArr, 0, A, 0, n);
    }

    public int getRotatedArrayPivotElementIndex() {
        return getRotatedArrayPivotElementIndex(0, size-1);
    }

    /**
     * Find the left most element which is less than the first element.
     * If the array is not rotated, pivot element index will be endIndex+1.
     * Works only for unique element array
     *
     * Complexity: O(lon n)
     *
     * @param startIndex
     * @param endIndex
     * @return
     */
    public int getRotatedArrayPivotElementIndex(int startIndex, int endIndex) {

        int firstElementIndex = startIndex;
        while (startIndex <= endIndex) {
            int mid = (startIndex + endIndex) / 2;
            if (A[mid] < A[firstElementIndex]) {
                endIndex = mid-1;
            } else {
                startIndex = mid+1;
            }
        }
        return startIndex;
    }

    /**
     * *TWISTED* algorithm
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
     * @param startIndex
     * @param endIndex
     * @param key
     * @return
     */
    public int binarySearchInRotatedArray(final int startIndex, final int endIndex, final int key) {

        final int pivotElementIndex = getRotatedArrayPivotElementIndex(startIndex, endIndex);

        return key>=A[startIndex] ?
                binarySearch(startIndex, pivotElementIndex-1, key):
                    binarySearch(pivotElementIndex, endIndex, key);
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
     * @param startIndex
     * @param endIndex
     * @param key
     * @return
     */
    public int binarySearchInRotatedArrayWithoutPivot(int startIndex, int endIndex, final int key){

        while(startIndex<=endIndex){
            int mid = (startIndex + endIndex) / 2;

            if(A[mid]==key){
                return mid;
            }

            if(A[startIndex]<=A[mid]){
                if (key>=A[startIndex] && key<A[mid] ){
                    endIndex = mid-1;
                } else {
                    startIndex = mid+1;
                }
            } else {
                if(key>A[mid] && key<=A[endIndex]){
                    startIndex = mid +1;
                } else {
                    endIndex = mid -1;
                }
            }
        }
        return -1;
    }

    /**
     * 
     * @param data
     */
    private void insertAsSorted(int data) {
        insertAsSorted(0, size-1, data);
    }
    
    /**
     * Inserts a key in a sorted array in sorted fashion.
     *
     * @param startIndex
     * @param endIndex
     * @param data
     */
    public void insertAsSorted(int startIndex, int endIndex, int data) {

        int j;
        for(j=endIndex; j >= startIndex && A[j] > data; j--) {
            A[j+1] = A[j];
        }
        A[j+1] = data;
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
     * Array has two parts each sorted.
     * Merge the two sub arrays into one in a sorted fashion.
     *
     * @param startIndex
     * @param midIndex
     * @param endIndex
     */
    private void mergeAsSorted(int startIndex, int midIndex, int endIndex){

        int A1[] = Arrays.copyOfRange(A,startIndex, midIndex);
        int A2[] = Arrays.copyOfRange(A, midIndex+1, endIndex);

        int i = 0;
        int j = midIndex+1;
        while(i<A1.length && j<A2.length){
            A[startIndex++] = (A1[i] < A2[j]) ? A1[i++] : A2[j++];
        }
        while(i<A1.length){
            A[startIndex++] = A1[i++];
        }
        while(j<A2.length){
            A[startIndex++] = A2[j++];
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
     * @param startIndex
     * @param endIndex
     */
    public void mergeSort(final int startIndex, final int endIndex){
        if(startIndex>=endIndex){
            return;
        }

        int midIndex = (startIndex + endIndex) / 2;
        mergeSort(startIndex, midIndex);
        mergeSort(midIndex+1, endIndex);
        mergeAsSorted(startIndex, midIndex, endIndex);
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
     * @param startIndex
     * @param endIndex
     */
    public void quickSort(final int startIndex, final int endIndex){
        if(startIndex>=endIndex){
            return;
        }

        int partitionIndex = partition(startIndex, endIndex);
        quickSort(startIndex, partitionIndex-1);
        quickSort(partitionIndex+1, endIndex);
    }

    /**
     * T
     *
     * @param startIndex
     * @param endIndex
     * @return
     */
    private int partition(int startIndex, int endIndex){

        //Smaller array index
        int i = startIndex-1;

        int j;
        for(j=startIndex; j<endIndex; j++){
            if(A[j]<=A[endIndex]){

                //Swap i+1 and j;
                i++;
                int temp = A[i];
                A[i]=A[j];
                A[j] = temp;
            }
        }
        i++;
        int temp = A[i];
        A[i]=A[endIndex];
        A[endIndex]=temp;

        return i;
    }

    /**
     *
     */
    public void selectionSort() {
        selectionSort(0, size-1);
    }

    /**
     *
     * @param startIndex
     * @param endIndex
     */
    public void selectionSort(final int startIndex, final int endIndex){

        for(int i=startIndex; i<endIndex; i++){

            int indexOfSmallestElement=i;
            for(int j=i+1; j<=endIndex; j++){
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
     *
     * @param sum
     * @return
     */
    public List<int []> findAllPairsWithMatchingSum(final int sum) {
        return findAllPairsWithMatchingSum(0, size-1, sum);
    }

    /**
     * Complexity O(n2)
     *
     * @param startIndex
     * @param endIndex
     * @param sum
     * @return
     */
    public List<int []> findAllPairsWithMatchingSum(final int startIndex, final int endIndex, final int sum){

        List<int []> list = new ArrayList<>();

        for(int i=startIndex; i<=endIndex; i++){
            for(int j=i+1; j<=endIndex; j++){
                if(A[i]+A[j] == sum){
                    list.add(new int[]{A[i],A[j]});
                }
            }
        }
        return list;
    }

    /**
     *
     * @param sum
     * @return
     */
    public List<int []> findAllPairsWithMatchingSumNLogN(final int sum) {
        return findAllPairsWithMatchingSumNLogN(0, size-1, sum);
    }

    /**
     * Complexity O(n log n) using binarySearch
     *
     * @param startIndex
     * @param endIndex
     * @param sum
     * @return
     */
    public List<int []> findAllPairsWithMatchingSumNLogN(final int startIndex, final int endIndex, final int sum){

        Arrays.sort(A);
        List<int []> list = new ArrayList<>();

        for(int i=startIndex; i<=endIndex-1; i++){
            int j = Arrays.binarySearch(A, i+1, endIndex, sum-A[i]);
            if( j >= 0){
                list.add(new int[]{A[i],A[j]});
            }
        }
        return list;
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

        int B[] = new int[]{7, 3, 2, 5, 1, 6, 4};
        Array array = new Array(B);
        array.insertionSort();
        System.out.println(array);
        System.out.println();

        array.rotateByNElements(-2);
        System.out.println(array);

        System.out.println(array.getRotatedArrayPivotElementIndex());
        System.out.println();

        for (int number : B) {
            System.out.println(array.binarySearchInRotatedArray(number));
        }
        System.out.println();
        for (int number : B) {
            System.out.println(array.binarySearchInRotatedArrayWithoutPivot(number));
        }


        final int matchingSum =9;
        final List<int []> matchingSumPairs = array.findAllPairsWithMatchingSumNLogN(matchingSum);
        System.out.println("matchingSumPairs for sum  = " + matchingSum);
        matchingSumPairs.forEach(Array::display);

        array.mergeSort();
        System.out.println(array);
        System.out.println(array.binarySearch(5));

        Array array1 = new Array(new int[]{1, 2, 3, 3, 7, 7, 8});

        array1.display();
        System.out.println(array1.binarySearchFirstOccurence(8));

        Array array2 = new Array(new int[]{7, 3, 2, 5, 1, 6, 4, 3, 5, 2, 1});
        array2.bstSort();
        System.out.println(array2);

    }

    public static void display(int A[]){
        System.out.println(Arrays.toString(A));
    }
}
