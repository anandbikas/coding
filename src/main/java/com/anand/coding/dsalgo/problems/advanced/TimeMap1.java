package com.anand.coding.dsalgo.problems.advanced;

import java.util.*;

/**
 *  Time based key->value store using HashMap and TreeMap.
 *
 *  Note: treeMap is single value map.
 */
class TimeMap1 {

    private Map<String, TreeMap<Integer, String>> map = new HashMap<>();

    /**
     *
     * @param key
     * @param value
     * @param timestamp
     */
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)){
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    /**
     * get a value which is stored at a recent time <=timestamp.
     *
     * @param key
     * @param timestamp
     * @return
     */
    public String get(String key, int timestamp) {
        TreeMap<Integer, String> treeMap = map.get(key);

        if(treeMap==null){
            return "";
        }

        Integer floorKey =  treeMap.floorKey(timestamp);

        if(floorKey==null){
            return "";
        } else {
            return treeMap.get(floorKey);
        }
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){

        //Example 1
        TimeMap1 timeMap = new TimeMap1();
        timeMap.set("key1","value1",1);

        System.out.println(timeMap.get("key1",1));
        System.out.println(timeMap.get("key1",3));
        timeMap.set("key1","value2",4);
        System.out.println(timeMap.get("key1",4));
        System.out.println(timeMap.get("key1",5));
        System.out.println();

        //Example 2
        TimeMap1 timeMap1 = new TimeMap1();
        timeMap1.set("key1","value1",10);
        timeMap1.set("key1","value2",20);

        System.out.println(timeMap1.get("key1",5));
        System.out.println(timeMap1.get("key1",10));
        System.out.println(timeMap1.get("key1",15));
        System.out.println(timeMap1.get("key1",20));
        System.out.println(timeMap1.get("key1",25));

    }
}