package com.anand.coding.dsalgo.tree.quad;

/**
 *
 */
public class Point {
    public int x;
    public int y;

    /**
     *
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)",x,y);
    }
}
