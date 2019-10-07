package com.anand.coding.dsalgo.tree.quad;

import java.util.ArrayList;
import java.util.List;

/**
 * Quad Node with dataList and four children t1, t2, t3 and t4 nodes.
 */
public class QuadNode<T extends Comparable<T>>{

    private List<QuadData<T>> dataList = new ArrayList<>();
    private Integer threshold;

    private Point ll;
    private Point ur;

    private QuadNode<T> t1;
    private QuadNode<T> t2;
    private QuadNode<T> t3;
    private QuadNode<T> t4;

    private QuadNode<T> parent;

    /**
     *
     */
    public QuadNode(Point ll, Point ur , int threshold){
        super();

        this.ll = ll;
        this.ur = ur;
        this.threshold = threshold;
    }

    /**
     *
     */
    public QuadNode(Point ll, Point ur , int threshold, QuadNode<T> parent){
       this(ll,ur,threshold);
       this.parent = parent;
    }

    /**
     *
     * @return
     */
    public boolean isLeafNode(){
        return dataList != null;
    }

    /**
     *
     * @param quadData
     */
    public void addQuadData(QuadData<T> quadData) {
        this.dataList.add(quadData);

        if(dataList.size()>threshold){
            divide();
        }
    }

    /**
     *
     */
    private void divide(){

        int midX = ll.x+(ur.x-ll.x)/2;
        int midY = ll.y+(ur.y-ll.y)/2;

        if(midX==ll.x && midY==ll.y){
            //Granular Quad reached, no division allowed.
            return;
        }

        Point middlePoint = new Point(midX, midY);

        t1 = new QuadNode<>(ll, middlePoint, threshold, this);
        t3 = new QuadNode<>(middlePoint, ur, threshold, this);
        t2 = new QuadNode<>(new Point(midX, ll.y), new Point(ur.x, midY), threshold, this);
        t4 = new QuadNode<>(new Point(ll.x, midY), new Point(midX, ur.y), threshold, this);

        for(QuadData<T> quadData: dataList){
            if(quadData.getLocation().x <=t1.ur.x){
                if(quadData.getLocation().y <=t1.ur.y){
                    t1.addQuadData(quadData);
                } else {
                    t4.addQuadData(quadData);
                }
            } else {
                if(quadData.getLocation().y <=t2.ur.y){
                    t2.addQuadData(quadData);
                } else {
                    t3.addQuadData(quadData);
                }
            }
        }
        //Clear this nodes data
        this.threshold=null;
        this.dataList=null;
    }

    /**
     *
     * @return
     */
    public QuadNode<T> getT1() {
        return t1;
    }

    /**
     *
     * @return
     */
    public QuadNode<T> getT2() {
        return t2;
    }

    /**
     *
     * @return
     */
    public QuadNode<T> getT3() {
        return t3;
    }

    /**
     *
     * @return
     */
    public QuadNode<T> getT4() {
        return t4;
    }

    /**
     *
     * @return
     */
    public Point getLl() {
        return ll;
    }

    /**
     *
     * @return
     */
    public Point getUr() {
        return ur;
    }

    /**
     *
     * @return
     */
    public QuadNode<T> getParent() {
        return parent;
    }

    /**
     *
     * @return
     */
    public List<QuadData<T>> getDataList() {
        return dataList;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(dataList) + String.format(" {%s/%s}",ll,ur);
    }
}
