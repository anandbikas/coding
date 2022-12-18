package com.anand.coding.dsalgo.tree.quad;

import java.util.ArrayList;
import java.util.List;
import com.anand.coding.dsalgo.tree.quad.Entry.Point;

/**
 * Quad Node with entryList and four children t1, t2, t3 and t4 nodes.
 */
public class QuadNode<T extends Comparable<T>>{

    public List<Entry<T>> entryList = new ArrayList<>();
    public Integer threshold;

    public Point ll, ur;

    public QuadNode<T> t1,t2,t3,t4;
    public QuadNode<T> parent;

    public QuadNode(Point ll, Point ur , int threshold){
        this.ll = ll;
        this.ur = ur;
        this.threshold = threshold;
    }

    public QuadNode(Point ll, Point ur , int threshold, QuadNode<T> parent){
       this(ll,ur,threshold);
       this.parent = parent;
    }

    /**
     *
     * @return
     */
    public boolean isLeafNode(){
        return entryList != null;
    }

    /**
     *
     * @param entry
     */
    public void addEntry(Entry<T> entry) {
        this.entryList.add(entry);

        if(entryList.size()>threshold){
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

        for(Entry<T> entry : entryList){
            if(entry.loc.x <= t1.ur.x){
                if(entry.loc.y <=t1.ur.y){
                    t1.addEntry(entry);
                } else {
                    t4.addEntry(entry);
                }
            } else {
                if(entry.loc.y <= t2.ur.y){
                    t2.addEntry(entry);
                } else {
                    t3.addEntry(entry);
                }
            }
        }
        //Clear this nodes data
        this.threshold=null;
        this.entryList=null;
    }

    @Override
    public String toString() {
        return String.format("{%s/%s} : %s",ll,ur, entryList);
    }
}
