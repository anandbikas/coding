package com.anand.coding.dsalgo.graph.adjacencylist;

/**
 *
 * @param <K>
 * @param <V>
 */
public class Pair<K,V> {

    private K key;
    private V value;

    /**
     * @param key
     * @param value
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     *
     * @return
     */
    public K getKey() {
        return key;
    }

    /**
     *
     * @param key
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     *
     * @return
     */
    public V getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return key + "=" + value;
    }


    /**
     * check only key
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return key.equals(pair.key);
    }

}
