package com.anand.coding.problems.array;

import java.util.*;

/**
 * Find pair with given sum in an unsorted array. One number should be possibly the largest.
 *
 */
public class _03_PairMatchingSumUnsortedArray {

    /**
     * Using sort
     * Complexity: O(n Log(n))
     *
     * @param sum
     * @param list
     * @return
     */
    public static int[] pairSum(int sum, ArrayList<Integer> list){

        ArrayList<Integer> list1 = (ArrayList<Integer>) list.clone();
        Collections.sort(list1);

        for(int i=0, j=list1.size()-1; i<j ;) {

            int pairSum = list1.get(i)+list1.get(j);
            if( pairSum == sum) {
                int[] res = {list.indexOf(list1.get(i)), list.indexOf(list1.get(j))};
                Arrays.sort(res);
                return res;
            }

            if (pairSum < sum) i++; else j--;
        }

        return new int[]{-1,-1};
    }

    /**
     * using HashMap
     * Complexity: O(n)
     *
     * @param sum
     * @param list
     * @return
     */
    public static int[] pairSumUsingHashMap(int sum, ArrayList<Integer> list){

        int index1=-1,index2=-1;

        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        for(int i=0; i<list.size(); i++){
            int value = list.get(i);
            if(!indexMap.containsKey(value)){
                indexMap.put(value, new ArrayList<>());
            }
            indexMap.get(value).add(i);
        }

        // Workaround for n1==n2
        if(sum%2==0){
            int n=sum/2;
            if(indexMap.containsKey(n) && indexMap.get(n).size()>1) {
                index1 = indexMap.get(n).get(0);
                index2 = indexMap.get(n).get(1);
                indexMap.remove(n);
            }
        }

        for(int n1: indexMap.keySet()) {
            int n2 = sum-n1;

            if(indexMap.containsKey(n2)) {
                if(index1==-1 || Math.max(list.get(index1), list.get(index2))<Math.max(n1, n2)) {
                    index1 = indexMap.get(n1).get(0);
                    index2 = indexMap.get(n2).get(0);
                }
            }
        }

        return new int[]{index1, index2};
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        ArrayList<Integer> list = new ArrayList<>();

        list.add(100);
        list.add(180);
        list.add(40);
        list.add(120);
        list.add(10);


        System.out.println(Arrays.toString(pairSum(220, list)));
        System.out.println(Arrays.toString(pairSumUsingHashMap(220, list)));
    }
}
