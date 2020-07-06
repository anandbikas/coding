package com.anand.coding.dsalgo.array;

/**
 * ######## Searching Algorithms ########
 */
public class ArraySearch extends Array {


    /**
     *
     * @param A
     */
    public ArraySearch(int [] A){
        super(A);
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
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearch(int left, int right, int key) {

        while (left <= right) {
            int mid = left + (right-left)/2;

            if (key < A[mid]) {
                right = mid - 1;
            } else if(key > A[mid]){
                left = mid + 1;
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

//        while (left <= right) {
//            int mid = left + (right-left)/2;
//
//            if (key < A[mid]) {
//                right = mid - 1;
//            } else if(key > A[mid]){
//                left = mid + 1;
//            } else {
//                if(mid>0 && key>A[mid-1]){
//                    return mid;
//                }
//                right = mid-1;
//            }
//        }
//        return -1;

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
         * To make this algorithm work for duplicate elements, we ensure A[left]>A[right] by adjusting left index.
         * which may take O(n) in worst case.
         */
        for (; left<right && A[right] == A[left]; left++);

        //Binary search
        int firstElement = A[left];
        while (left <= right) {
            int mid = left + (right-left)/2;
            if(A[mid]<firstElement){
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return left;
    }

    /**
     *
     * @param key
     * @return
     */
    public int binarySearchRotatedArrayPivotBifercation(final int key) {
        return binarySearchRotatedArrayPivotBifercation(0, size-1, key);
    }

    /**
     * Complexity: O(lon n)
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearchRotatedArrayPivotBifercation(final int left, final int right, final int key) {

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
    public int binarySearchRotatedArray(final int key) {
        return binarySearchRotatedArray(0, size-1, key);
    }
    /**
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearchRotatedArray(int left, int right, final int key){

        //Workaround for duplicate elements. Say A={4412344}, search 4 results into index 5.
        //If we want index from a particular side of the pivot, we can truncate duplicates from the other side.
        for (; left<right && A[right] == A[left]; right--);

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
     * Floor in unsorted array
     *
     * @param key
     * @return
     */
    public Integer floor(int key) {

        Integer floor = null;

        for(int x: A){
            if(x==key){
                return key;
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

        //binarySearch
        while (left <= right) {
            int mid = left + (right-left)/2;

            if (key < A[mid]) {
                right = mid - 1;
            } else if(key > A[mid]){
                left = mid + 1;
            } else {
                return key;
            }
        }
        return A[right];
    }


    /**
     * Main function to test Array
     * @param args
     */
    public static void main(String[] args) {

        int B[] = new int[]{7, 3, 2, 5, 3, 1, 6, 4};
        ArraySearch array = new ArraySearch(B);
        System.out.println("Array: " + array);
        System.out.println("floor 8: " + array.floor(8));
        System.out.println();

        //take a sorted array.
        int C[] = new int[]{1, 2, 3, 3, 4, 5, 6, 7};
        array = new ArraySearch(C);

        System.out.println("Sorted array: " + array);
        System.out.println("floor 8: " + array.floor(8));
        System.out.println("floorSortedArray 8: " + array.floorSortedArray(8));
        System.out.println("binarySearch 3: " + array.binarySearch(3));
        System.out.println("binarySearchFirstOccurence 3: " + array.binarySearchFirstOccurrence(3));
        System.out.println("binarySearchLastOccurrence 3: " + array.binarySearchLastOccurrence(3));
        System.out.println();


        array.rotate(-2);
        System.out.println("Array rotated by -2 elements: " + array);
        System.out.println("getRotatedArrayPivotElementIndex: " + array.getRotatedArrayPivotElementIndex());

        System.out.print("binarySearchRotatedArrayPivotBifercation: ");
        for (int number : B) {
            System.out.print(array.binarySearchRotatedArrayPivotBifercation(number) + " ");
        }
        System.out.println();

        System.out.print("binarySearchRotatedArray: ");
        for (int number : B) {
            System.out.print(array.binarySearchRotatedArray(number) + " ");
        }
    }
}
