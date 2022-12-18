package com.anand.coding.dsalgo.tree.quad;

/**
 *
 * @param <T>
 */
public class Entry<T extends Comparable<T>> {

    public T obj;
    public Point loc;

    public Entry(int x, int y, T obj) {
        this.obj = obj;
        this.loc = new Point(x,y);
    }

    public String toString() {
        return obj + " " + loc;
    }

    public static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x; this.y = y;
        }

        public String toString() {
            return String.format("(%d,%d)",x,y);
        }
    }
}
