package com.anand.coding.dsalgo.tree.quad;

/**
 *
 * @param <T>
 */
public class QuadData<T extends Comparable<T>> implements Comparable<QuadData<T>> {

    private Point location;
    private T data;

    /**
     *
     * @param x
     * @param y
     * @param data
     */
    public QuadData(int x, int y, T data) {
        this.data = data;
        this.location = new Point(x,y);
    }

    /**
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public Point getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(data) + " " + location;
    }

    /**
     *
     * @param quadData
     * @return
     */
    @Override
    public int compareTo(QuadData<T> quadData) {
        return this.data.compareTo(quadData.data);
    }
}
