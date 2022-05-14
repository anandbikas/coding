package com.anand.coding.os.memorymanagement.cache;

import com.anand.coding.dsalgo.queue.DoublyLinkedQueue;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Least Frequently Used Cache. (LRU for tie)
 *
 * get(key)         : O(1)
 * put(key,value)   : O(1)
 *
 */
public class LFUCache<K,V> {

    private final HashMap<K, DoublyLinkedQueue.Node<Pair<K,V>>> cacheMap = new HashMap<>();
    private final ConcurrentHashMap<Integer, DoublyLinkedQueue<Pair<K,V>>> frequencyMap = new ConcurrentHashMap<>();
    private int leastFrequency = 1;
    private final int capacity;

    /**
     *
     * @param capacity
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        frequencyMap.put(1, new DoublyLinkedQueue<>());
    }

    /**
     *
     * @param key
     * @return
     */
    public V get(K key){
        if(cacheMap.containsKey(key)){
            DoublyLinkedQueue.Node<Pair<K,V>> node = cacheMap.get(key);

            frequencyMap.get(node.data.frequency).delete(node);
            if (node.data.frequency==leastFrequency
                    && frequencyMap.get(node.data.frequency).isEmpty()){
                leastFrequency++;
            }

            node.data.frequency++;
            if(!frequencyMap.containsKey(node.data.frequency)){
                frequencyMap.put(node.data.frequency, new DoublyLinkedQueue<>());
            }
            cacheMap.put(key, frequencyMap.get(node.data.frequency).insert(node.data));

            return node.data.value;
        }
       return null;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void put(K key, V value){
        if(capacity==0){
            return;
        }

        if(get(key)!=null){
            cacheMap.get(key).data.value = value;

        } else {
            //If capacity is full, remove LRU element
            if(cacheMap.size() == capacity) {
                cacheMap.remove(frequencyMap.get(leastFrequency).delete().key);
            }
            leastFrequency=1;
            cacheMap.put(key, frequencyMap.get(1).insert(new Pair<>(key, value)));

        }
    }

    /**
     *
     */
    public static void main(String [] args){

        LFUCache<Integer, String> cache = new LFUCache<>(2);

        cache.put(1, "value1");
        cache.put(2, "value2");

        System.out.println(cache.get(1));    // returns 1

        cache.put(3, "value3");              // evicts key 2

        System.out.println(cache.get(2));    // returns null (not found)

        cache.put(4, "value4");              // evicts key 3

        System.out.println(cache.get(3));    // returns null (not found)
        System.out.println(cache.get(3));    // returns null (not found)
        System.out.println(cache.get(1));    // returns 1
        System.out.println(cache.get(4));    // returns 4

    }

    private static class Pair<K, V>{
        K key;
        V value;
        int frequency=1;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}


