package com.anand.coding.dsalgo.array;

/**
 * ######## Searching Algorithms ########
 */
public class ArraySearch extends Array {

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
            if (key > A[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left==right && (A[left] == key) ? left : -1;
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
            if (key < A[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return left==right && (A[left] == key) ? left : -1;
    }

    /**
     *
     * @param key
     * @return
     */
    public int binarySearchRec(int key) {
        return bsRec(0, size-1, key);
    }

    /**
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int bsRec(int left, int right, int key) {

        if (left > right) {
            return -1;
        }

        int mid = left + (right-left)/2;
        if(A[mid]==key){
            return mid;
        }

        return (key < A[mid]) ? bsRec(left, mid - 1, key) : bsRec(mid + 1, right, key);
    }

    /**
     *
     * @return
     */
    public int getRotatedArrayPivotIndex() {
        return getRotatedArrayPivotIndex(0, size-1);
    }

    /**
     * Find Minimum in Rotated Sorted Array.
     * Hint: Find the left most element which is less than the first element.
     *       If not rotated, return 0
     *
     * Complexity:
     *      O(lon n) if elements are unique or firstElement>lastElement
     *      O(n) if elements are not unique
     *          For duplicate elements:
     *          *      3 4 3 3 3 3 ==> pivotIndex = 2
     *          *      3 3 3 3 3 3 ==> pivotIndex = 0
     *          *
     *          * To make this algorithm work for duplicate elements, we ensure A[left]>A[right] by adjusting left index.
     *          * which may take O(n) in worst case.
     *
     * @param left
     * @param right
     * @return
     */
    public int getRotatedArrayPivotIndex(int left, int right) {

        // Duplicate elements workaround.
        for (; left<right && A[right] == A[left]; left++);

        //Binary search
        int firstElement = A[left];
        while (left <= right) {
            int mid = left + (right-left)/2;
            if(A[mid]<firstElement) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return left%A.length;
    }

    /**
     *
     * @param key
     * @return
     */
    public int binarySearchRotatedArrayPivotBifurcation(final int key) {
        return binarySearchRotatedArrayPivotBifurcation(0, size-1, key);
    }

    /**
     * Complexity: O(lon n)
     *
     * @param left
     * @param right
     * @param key
     * @return
     */
    public int binarySearchRotatedArrayPivotBifurcation(final int left, final int right, final int key) {

        final int pivotElementIndex = getRotatedArrayPivotIndex(left, right);

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
    public int binarySearchRotatedArray(int left, int right, final int key) {

        //Workaround for duplicate elements. Say A={4412344}, search 4 results into index 5.
        //If we want index from a particular side of the pivot, we can truncate duplicates from the other side.
        for (; left<right && A[right] == A[left]; right--);

        while(left<=right) {
            int mid = left + (right-left)/2;

            if(A[mid]==key) {
                return mid;
            }

            if(A[left]<=A[mid]) {
                if (key>=A[left] && key<A[mid] ) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else {
                if(key>A[mid] && key<=A[right]) {
                    left = mid +1;
                } else {
                    right = mid-1;
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

        for(int x: A) {
            if(x==key) {
                return key;
            }
            if(x<key && (floor==null || floor<x)) {
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
                right = mid-1;
            } else if(key > A[mid]){
                left = mid+1;
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

        ArraySearch array1 = new ArraySearch(new int[]{5,7,7,8,8,10});
        System.out.println("binarySearchFirstOccurence 8: " + array1.binarySearchFirstOccurrence(8));
        System.out.println("binarySearchLastOccurrence 8: " + array1.binarySearchLastOccurrence(8));
        System.out.println();

        array1 = new ArraySearch(new int[]{});
        System.out.println("binarySearchFirstOccurence 8: " + array1.binarySearchFirstOccurrence(8));
        System.out.println("binarySearchLastOccurrence 8: " + array1.binarySearchLastOccurrence(8));
        System.out.println();

        array.rotate(-2);
        System.out.println("Array rotated by -2 elements: " + array);
        System.out.println("getRotatedArrayPivotElementIndex: " + array.getRotatedArrayPivotIndex());

        System.out.print("binarySearchRotatedArrayPivotBifercation: ");
        for (int number : B) {
            System.out.print(array.binarySearchRotatedArrayPivotBifurcation(number) + " ");
        }
        System.out.println();

        System.out.print("binarySearchRotatedArray: ");
        for (int number : B) {
            System.out.print(array.binarySearchRotatedArray(number) + " ");
        }
    }
}
