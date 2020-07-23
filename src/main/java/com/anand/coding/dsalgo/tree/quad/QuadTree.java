package com.anand.coding.dsalgo.tree.quad;

import com.anand.coding.dsalgo.tree.quad.Entry.Point;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class QuadTree<T extends Comparable<T>> {

    private QuadNode<T> root;

    /**
     *
     * @param threshold;
     */
    public QuadTree(int x1, int y1, int x2, int y2, int threshold) {
        this.root = new QuadNode<>(new Point(x1,y1), new Point(x2,y2), threshold);
    }

    /**
     *
     * @param x
     * @param y
     * @param object
     */
    public void insert(int x, int y, T object){

        QuadNode<T> quadNode = findQuadNode(x,y);
        if(quadNode!=null){
            quadNode.addEntry(new Entry<>(x,y,object));
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public QuadNode<T> findQuadNode(int x, int y) {

        if (x < root.ll.x || x > root.ur.x
                || y < root.ll.y || y > root.ur.y) {
            System.out.println("Location not supported");
            return null;
        }

        // Find the quad node, location (x,y) is lying in.
        QuadNode<T> quadNode = root;
        while (!quadNode.isLeafNode()) {

            //Check if this quad
            if (x <= quadNode.t1.ur.x) {
                if (y <= quadNode.t1.ur.y) {
                    quadNode = quadNode.t1;
                } else {
                    quadNode = quadNode.t4;
                }
            } else {
                if (y <= quadNode.t2.ur.y) {
                    quadNode = quadNode.t2;
                } else {
                    quadNode = quadNode.t3;
                }
            }
        }
        return quadNode;
    }


    /**
     *
     * @param x
     * @param y
     * @param radius
     * @return
     */
    public List<Entry<T>> search(int x, int y, int radius){

        if (x < root.ll.x || x > root.ur.x
                || y < root.ll.y || y > root.ur.y) {
            System.out.println("Location not supported");
            return null;
        }

        // Square co-ordinates enclosing the circle of the given radius
        Point ll = new Point(x-radius, y-radius);
        Point ur = new Point(x+radius, y+radius);

        // Find the quad node where squareOfCircle ll-ur is lies.
        // In case squareOfCircle expands beyond root quadNode, then use root,
        // else find through the tree path
        QuadNode<T> quadNode = root;
        if(quadNode.ll.x<=ll.x && quadNode.ll.y<=ll.y
                && quadNode.ur.x>=ur.x && quadNode.ur.y>=ur.y){

            while (!quadNode.isLeafNode()) {

               //Check if any child node can contain squareOfCircle
                QuadNode[] childQuadNodes = {quadNode.t1, quadNode.t2, quadNode.t3, quadNode.t4};
                QuadNode<T> newQuadNode=null;
                for(QuadNode child : childQuadNodes) {
                    if (child.ll.x <= ll.x && child.ll.y <= ll.y
                            && child.ur.x >= ur.x && child.ur.y >= ur.y) {
                        newQuadNode = child;
                        break;
                    }
                }
                if(newQuadNode==null){
                    break;
                } else {
                    quadNode=newQuadNode;
                }
            }
        }

        //Find all leaf quad nodes intersecting the squareOfCircle;
        List<QuadNode<T>> intersectingQuadNodes = new ArrayList<>();

        Queue<QuadNode<T>> queue = new ArrayDeque<>(); //new LinkedList<>();

        queue.add(quadNode);

        while (!queue.isEmpty()) {

            quadNode = queue.remove();
            if(quadNode.isLeafNode()){
                intersectingQuadNodes.add(quadNode);
                continue;
            }

            QuadNode[] childQuadNodes = {quadNode.t1, quadNode.t2, quadNode.t3, quadNode.t4};
            for(QuadNode child : childQuadNodes) {

                //find ll(x1,y1) and ur(x2,y2) intersecting rectangle with squareOfCircle.
                long x1=Math.max(ll.x, child.ll.x);    long x2=Math.min(ur.x, child.ur.x);
                long y1=Math.max(ll.y, child.ll.y);    long y2=Math.min(ur.y, child.ur.y);

                //Is not overlapping
                if(x1>x2 || y1>y2){
                    continue;
                }
                //Else
                queue.add(child);
            }
        }

        List<Entry<T>> entryList = new ArrayList<>();
        for(QuadNode<T> qN : intersectingQuadNodes){
            for(Entry<T> entry : qN.entryList){
                if(isOnCircle(x, y, radius, entry.location)){
                    entryList.add(entry);
                }
            }
        }
        return entryList;
    }

    /**
     *
     * @param x
     * @param y
     * @param radius
     * @param p
     * @return
     */
    private boolean isOnCircle(int x, int y, int radius, Point p) {
        return (x-p.x)*(x-p.x) + (y-p.y)*(y-p.y) <= radius*radius;
    }

    @Override
    public String toString() {
        return String.valueOf(root);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        QuadTree<String> quadTree = new QuadTree<>(0,0,18,18, 4);

        //Insert places.
        quadTree.insert(2,2, "P1");
        quadTree.insert(12,9, "P2");
        quadTree.insert(3,16, "P3");
        quadTree.insert(3,16, "P4");
        quadTree.insert(3,16, "P5");
        quadTree.insert(1,14, "P6");
        quadTree.insert(0,17, "P7");


        System.out.println(quadTree.findQuadNode(1,2));
        System.out.println(quadTree.search(2,16, 2));
        System.out.println();
    }
}
