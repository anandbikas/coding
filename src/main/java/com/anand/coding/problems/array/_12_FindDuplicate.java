package com.anand.coding.problems.array;

import java.util.HashSet;
import java.util.Set;


/**
 * An array has n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in this, find that number.
 *
 */
public class _12_FindDuplicate {

    public static int findDuplicate1(int[] A) {
        //1. Map to index, (modify array)
        while (A[0] != A[A[0]]) {
            int temp = A[A[0]];
            A[A[0]] = A[0];
            A[0] = temp;
        }
        return A[0];

    }

    public static int findDuplicate2(int[] A) {

        // 2. Using map
        Set<Integer> set = new HashSet<>();
        for (int x : A) {
            if (!set.add(x)) {
                return x;
            }
        }
        return -1;
    }

    public static int findDuplicate3(int[] A) {

        // 3. Like LinkedList loop detection
        int slow = A[0], fast = A[0];
        do {
            slow = A[slow];
            fast = A[A[fast]];
        } while (slow != fast);

        for (slow = A[0]; slow != fast; slow = A[slow], fast = A[fast]) ;
        return slow;
    }

    public static int findDuplicate4(int[] A) {

        // 4. Negate index, (modify array)
        for (int x : A) {
            if (A[Math.abs(x)] < 0) {
                return Math.abs(x);
            } else {
                A[Math.abs(x)] *= -1;
            }
        }
        return -1;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int []A = {2,4,8,7,3,4,5,6,1};
        System.out.println(findDuplicate1(A));
        System.out.println(findDuplicate2(A));
        System.out.println(findDuplicate3(A));
        System.out.println(findDuplicate4(A));
    }
}
