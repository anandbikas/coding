package com.anand.coding.problems.heap;

import java.util.*;

/**
 * k frequent words. Use lexicographical order for same frequency words.
 */
public class _08_KFrequentWords {

    public static List<String> topKFrequentNumbers(String[] words, int k) {

        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<String> pq =
                new PriorityQueue<>(Comparator.comparing(map::get).reversed().thenComparing(Object::toString));

        Arrays.stream(words).forEach(x -> map.put(x, map.containsKey(x) ? map.get(x)+1 : 1));
        pq.addAll(map.keySet());

        List<String> list = new ArrayList<>();;
        for(int i=0; i<k && !pq.isEmpty(); i++){
            list.add(pq.remove());
        }

        return list;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

       System.out.println(topKFrequentNumbers(new String[]{"i","love","competitive","i","love","coding"}, 2));
    }
}
