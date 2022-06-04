package com.anand.coding.problems.list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Store frequency of keys and support all the below operations in O(1);
 *
 * inc(key)         : O(1)
 * dec(key)         : O(1)
 * getMinKey()      : O(1)
 * getMaxKey()      : O(1)
 *
 */
public class AllO1FrequencyStore {

    public static class Node {
        int frequency;
        Node prev, next;
        Set<String> keys = new HashSet<>();

        public Node(int frequency) {
            this.frequency = frequency;
        }
    }

    private Node start, end;
    private final Map<String, Node> cacheMap = new HashMap<>();

    public AllO1FrequencyStore() {
        start = new Node(0);
        end   = new Node(Integer.MAX_VALUE);
        start.next=end;
        end.prev=start;
    }

    public void inc(String key) {

        Node node = cacheMap.getOrDefault(key, start);
        node.keys.remove(key);

        if(node.next.frequency!=node.frequency+1){
            Node tNode = new Node(node.frequency+1);
            tNode.next = node.next;
            tNode.prev = node;

            node.next.prev = tNode;
            node.next = tNode;
        }

        cacheMap.put(key, node.next);
        node.next.keys.add(key);

        if(node !=start && node.keys.size()==0) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void dec(String key) {
        if(!cacheMap.containsKey(key)) {
            return;
        }

        Node node = cacheMap.remove(key);
        node.keys.remove(key);

        if (node.prev.frequency != node.frequency-1) {
            Node tNode = new Node(node.frequency - 1);
            tNode.next = node;
            tNode.prev = node.prev;

            node.prev.next = tNode;
            node.prev = tNode;
        }

        if(node.prev!=start) {
            node.prev.keys.add(key);
            cacheMap.put(key, node.prev);
        }

        if(node.keys.size()==0) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public String getMinKey(){
        return  start.next==end ? "" : start.next.keys.iterator().next();
    }

    public String getMaxKey(){
        return  end.prev==start ? "" : end.prev.keys.iterator().next();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        AllO1FrequencyStore allOne = new AllO1FrequencyStore();

        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");

        allOne.dec("b");
        allOne.dec("b");

        System.out.println(allOne.getMinKey());

        allOne.dec("a");

        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }
}