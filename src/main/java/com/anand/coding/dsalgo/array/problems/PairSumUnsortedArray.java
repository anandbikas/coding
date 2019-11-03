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
            int pairSum = list1.get(i)+list1.get(j);
            if( pairSum == sum){
                indexList.add(list.indexOf(list1.get(i)));
                indexList.add(list.indexOf(list1.get(j)));
                break;
            }

            if (pairSum < sum){
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

        //list takes care for duplicate values
        Map<Integer, ArrayList<Integer>> valueIndexesMap = new HashMap<>();

        for(int i=0; i<list.size(); i++){
            int value = list.get(i);
            if(!valueIndexesMap.containsKey(value)){
                valueIndexesMap.put(value, new ArrayList<>());
            }
            valueIndexesMap.get(value).add(i);
        }

        for(int i=0; i<list.size(); i++){
            int n1= list.get(i);
            int n2 = sum-list.get(i);

            if(valueIndexesMap.containsKey(n2) && valueIndexesMap.get(n2)!=null){

                // Skip if same index is found.
                if(valueIndexesMap.get(n2).size()==1 && valueIndexesMap.get(n2).get(0)==i){
                    continue;
                }

                if(indexList.size()==0 ||
                        Math.max(list.get(indexList.get(0)), list.get(indexList.get(1)))
                            < Math.max(n1, n2)
                ) {
                    indexList = Arrays.asList(i, valueIndexesMap.get(n2).get(0));
                    valueIndexesMap.remove(n1);
                    valueIndexesMap.remove(n2);

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
