package com.anand.coding.dsalgo.array.problems;

import java.util.*;

/**
 * Find pair with given sum in an unsorted array. One number should be possibly the largest.
 *
 */
public class PairSumUnsortedArray {

    /**
     *
     * Using sort
     *
     * Complexity: O(n Log(n))
     *
     * @param sum
     * @param list
     * @return
     */
    public static List<Integer> pairSum(int sum, ArrayList<Integer> list){

        ArrayList<Integer> indexList = new ArrayList<>();

        ArrayList<Integer> list1 = (ArrayList<Integer>) list.clone();
        Collections.sort(list1);


        Integer i=0;
        Integer j = list1.size()-1;

        while(i<j){
            if(list1.get(i)+list1.get(j) == sum){
                indexList.add(list.indexOf(list1.get(i)));
                indexList.add(list.indexOf(list1.get(j)));
                break;
            }

            if (list1.get(i)+list1.get(j) < sum){
                i++;
            } else {
                j--;
            }
        }

        Collections.sort(indexList);
        return indexList;
    }

    /**
     * using HashMap
     *
     * Complexity: O(n)
     *
     * @param sum
     * @param list
     * @return
     */
    public static List<Integer> pairSumUsingHashMap(int sum, ArrayList<Integer> list){

        List<Integer> indexList = new ArrayList<>();

        Map<Integer, Integer> valueIndexMap = new HashMap<>();

        for(int i=0; i<list.size(); i++){
            valueIndexMap.put(list.get(i), i);
        }

        for(int i=0; i<list.size(); i++){
            Integer delta = sum-list.get(i);

            if(valueIndexMap.containsKey(delta) && valueIndexMap.get(delta)!=i){

                if(indexList.size()==0 ||
                        Math.max(list.get(indexList.get(0)), list.get(indexList.get(1)))
                            < Math.max(list.get(i), list.get(valueIndexMap.get(delta)))

                ) {
                    indexList = Arrays.asList(i, valueIndexMap.get(delta));
                    valueIndexMap.remove(i);
                    valueIndexMap.remove(list.get(i));
                }
            }
        }

        return indexList;
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


        System.out.println(pairSum(220, list));
        System.out.println(pairSumUsingHashMap(220, list));
    }
}
