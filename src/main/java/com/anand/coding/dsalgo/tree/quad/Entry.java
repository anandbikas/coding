package com.anand.coding.dsalgo.tree.quad;

/**
 *
 * @param <T>
 */
public class Entry<T extends Comparable<T>> implements Comparable<Entry<T>> {

    public T object;
    public Point location;

    /**
     *
     * @param x
     * @param y
     * @param object
     */
    public Entry(int x, int y, T object) {
        this.object = object;
        this.location = new Point(x,y);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return object + " " + location;
    }

    /**
     *
     * @param entry
     * @return
     */
    @Override
    public int compareTo(Entry<T> entry) {
        return this.object.compareTo(entry.object);
    }

    /**
     *
     */
    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return String.format("(%d,%d)",x,y);
        }
    }
}
