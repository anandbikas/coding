package com.anand.coding.dsalgo.graph.adjacencylist;

/**
 *
 * @param <K>
 * @param <V>
 */
public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K,V>> {

    public K key;
    public V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + (value==null ? "" : "=" + value);
    }

    @Override
    public boolean equals(Object o) {
        return o!=null
                && this.getClass() == o.getClass()
                && key.equals(((Pair<?, ?>) o).key);
    }

    @Override
    public int compareTo(Pair<K,V> o) {
        return this.key.compareTo(o.key);
    }
}
