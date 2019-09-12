package com.anand.coding.dsalgo.problems.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Time based key->value store.
 */
class TimeMap {

    private Map<String, List<TimeValue>> map = new HashMap<>();

    /**
     *
     * @param key
     * @param value
     * @param timestamp
     */
    public void set(String key, String value, int timestamp) {
        List<TimeValue> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new TimeValue(timestamp, value));
        map.put(key, list);
    }

    /**
     * get a value which is stored at a recent time <=timestamp.
     *
     * @param key
     * @param timestamp
     * @return
     */
    public String get(String key, int timestamp) {
        List<TimeValue> list = map.get(key);
        return list==null ? "" : binarySearch(list, timestamp);
    }

    /**
     *
     * @param list
     * @param timestamp
     * @return
     */
    private String binarySearch(List<TimeValue> list, int timestamp) {

        if (list.get(0).timestamp > timestamp) {
            return "";
        }
        if (list.get(list.size()-1).timestamp <= timestamp) {
            return list.get(list.size()-1).value;
        }

        int left = 0;
        int right = list.size()-1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if(timestamp<=list.get(mid).timestamp){
                right = mid;
            } else{
                left=mid+1;
            }
        }

        return (list.get(left).timestamp == timestamp) ? list.get(left).value : list.get(left-1).value;
    }

    /**
     *  class to store time-> value pair
     */
    private static class TimeValue {
        private int timestamp;
        private String value;

        /**
         *
         * @param timestamp
         * @param value
         */
        public TimeValue(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        //Example 1
        TimeMap timeMap = new TimeMap();
        timeMap.set("key1","value1",1);

        System.out.println(timeMap.get("key1",1));
        System.out.println(timeMap.get("key1",3));
        timeMap.set("key1","value2",4);
        System.out.println(timeMap.get("key1",4));
        System.out.println(timeMap.get("key1",5));
        System.out.println();

        //Example 2
        TimeMap timeMap1 = new TimeMap();
        timeMap1.set("key1","value1",10);
        timeMap1.set("key1","value2",20);

        System.out.println(timeMap1.get("key1",5));
        System.out.println(timeMap1.get("key1",10));
        System.out.println(timeMap1.get("key1",15));
        System.out.println(timeMap1.get("key1",20));
        System.out.println(timeMap1.get("key1",25));



    }
}