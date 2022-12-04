package com.anand.coding.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * RandomizedCollection with on average O(1) time complexity for the operations.
 *
 * leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 */
public class _07_RandomizedCollectionEqualProbabilityWithDuplicates {

    public static Random random = new Random();

    List<Integer> list = new ArrayList<>();
    Map<Integer, Set<Integer>> keyMap = new HashMap<>();  //Store all indexes of the value in the list

    public boolean insert(int val) {

        boolean existed = keyMap.containsKey(val);
        if (!existed) {
            keyMap.put( val, new HashSet<>());
        }

        keyMap.get(val).add(list.size());
        list.add(val);

        return !existed;
    }

    public boolean remove(int val) {
        if(!keyMap.containsKey(val)) {
            return false;
        }

        int i = keyMap.get(val).iterator().next();
        keyMap.get(val).remove(i);

        int last = list.size()-1;
        if (i<last) {
            int v = list.get(last);
            list.set(i, v);
            keyMap.get(v).remove(last);
            keyMap.get(v).add(i);
        }
        list.remove(last);

        if (keyMap.get(val).isEmpty()) {
            keyMap.remove(val);
        }
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}