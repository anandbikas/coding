package com.anand.coding.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * RandomizedCollection with on average O(1) time complexity for the operations.
 * Duplicate element not supported.
 */
public class _08_RandomizedCollectionEqualProbability {

    public static Random random = new Random();

    List<Integer> list = new ArrayList<>();
    Map<Integer, Integer> keyMap = new HashMap<>();

    public boolean insert(int val) {

        if(keyMap.containsKey(val)) {
            return false;
        }
        keyMap.put( val, list.size());
        list.add(val);

        return true;
    }

    public boolean remove(int val) {
        if(!keyMap.containsKey(val)) {
            return false;
        }

        int i = keyMap.remove(val);

        int last = list.size()-1;
        if (i<last) {
            int v = list.get(last);
            list.set(i, v);
            keyMap.put(v,i);
        }
        list.remove(last);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}