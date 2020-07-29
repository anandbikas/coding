package com.anand.coding.os.memorymanagement.cache;

import com.anand.coding.dsalgo.list.doubly.Node;
import com.anand.coding.dsalgo.queue.DoublyLinkedQueue;

import java.util.HashMap;

/**
 * Least Recently Used Cache.
 *
 * get(key)         : O(1)
 * put(key,value)   : O(1)
 *
 */
public class LRUCache<K extends Comparable<K>,V> {

    private static class Pair<K extends Comparable<K>,V> implements Comparable<Pair<K,V>> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Pair<K,V> o) {
            return this.key.compareTo(o.key);
        }
    }

    private DoublyLinkedQueue<Pair<K,V>>  queue = new DoublyLinkedQueue<>();
    private HashMap<K, Node<Pair<K,V>>> cacheMap = new HashMap<>();

    private int capacity;

    /**
     *
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @param key
     * @return
     */
    public V get(K key){
        if(cacheMap.containsKey(key)){
            queue.moveToRear(cacheMap.get(key));
            return cacheMap.get(key).data.value;
        }
       return null;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void put(K key, V value){
        if(cacheMap.containsKey(key)){
            cacheMap.get(key).data.value = value;
            queue.moveToRear(cacheMap.get(key));
        } else {
            //If capacity is full, remove LRU element
            if(cacheMap.size() == capacity) {
                cacheMap.remove(queue.delete().key);
            }

            cacheMap.put(key, queue.insert(new Pair<>(key, value)));
        }
    }

    /**
     *
     */
    public static void main(String [] args){

        LRUCache<Integer, String> cache = new LRUCache<>(2);

        cache.put(1, "value1");
        cache.put(2, "value2");

        System.out.println(cache.get(1));    // returns 1

        cache.put(3, "value3");              // evicts key 2

        System.out.println(cache.get(2));    // returns null (not found)

        cache.put(4, "value4");              // evicts key 1

        System.out.println(cache.get(1));    // returns null (not found)
        System.out.println(cache.get(3));    // returns 3
        System.out.println(cache.get(4));    // returns 4

    }
}


