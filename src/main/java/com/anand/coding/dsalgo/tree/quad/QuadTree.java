package com.anand.coding.dsalgo.tree.quad;

import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;

import java.util.ArrayList;
import java.util.List;

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
     * @param data
     */
    public void insert(int x, int y, T data){

        QuadNode<T> quadNode = findQuadNode(x,y);
        if(quadNode!=null){
            quadNode.addQuadData(new QuadData<>(x,y,data));
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public QuadNode<T> findQuadNode(int x, int y) {

        if (x < root.getLl().x || x > root.getUr().x
                || y < root.getLl().y || y > root.getUr().y) {
            System.out.println("Location not supported");
            return null;
        }

        // Find the quad node, location (x,y) is lying in.
        QuadNode<T> quadNode = root;
        while (!quadNode.isLeafNode()) {

            //Check if this quad
            if (x <= quadNode.getT1().getUr().x) {
                if (y <= quadNode.getT1().getUr().y) {
                    quadNode = quadNode.getT1();
                } else {
                    quadNode = quadNode.getT4();
                }
            } else {
                if (y <= quadNode.getT2().getUr().y) {
                    quadNode = quadNode.getT2();
                } else {
                    quadNode = quadNode.getT3();
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
    public List<QuadData<T>> search(int x, int y, int radius){

        if (x < root.getLl().x || x > root.getUr().x
                || y < root.getLl().y || y > root.getUr().y) {
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
        if(quadNode.getLl().x<=ll.x && quadNode.getLl().y<=ll.y
                && quadNode.getUr().x>=ur.x && quadNode.getUr().y>=ur.y){

            while (!quadNode.isLeafNode()) {

                //Check if any child node can contain squareOfCircle
               QuadNode[] childQuadNodes = {quadNode.getT1(), quadNode.getT2(), quadNode.getT3(), quadNode.getT4()};

               QuadNode<T> newQuadNode=null;
               for(QuadNode child : childQuadNodes) {
                    if (child.getLl().x <= ll.x && child.getLl().y <= ll.y
                            && child.getUr().x >= ur.x && child.getUr().y >= ur.y) {
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
        List<QuadNode<T>> quadNodeList = new ArrayList<>();

        Queue<QuadNode<T>> queue = new ArrayCircularQueue<>();
        queue.insert(quadNode);

        while (!queue.isEmpty()) {

            quadNode = queue.delete();
            if(quadNode.isLeafNode()){
                quadNodeList.add(quadNode);
                continue;
            }

            QuadNode[] childQuadNodes = {quadNode.getT1(), quadNode.getT2(), quadNode.getT3(), quadNode.getT4()};
            for(QuadNode child : childQuadNodes) {

                //find ll(x1,y1) and ur(x2,y2) intersecting rectangle with squareOfCircle.
                long x1=Math.max(ll.x, child.getLl().x);    long x2=Math.min(ur.x, child.getUr().x);
                long y1=Math.max(ll.y, child.getLl().y);    long y2=Math.min(ur.y, child.getUr().y);

                //Is not overlapping
                if(x1>x2 || y1>y2){
                    continue;
                }
                //Else
                queue.insert(child);
            }
        }

        List<QuadData<T>> quadDataList = new ArrayList<>();
        for(QuadNode<T> qN : quadNodeList){
            for(QuadData<T> quadData: qN.getDataList()){
                if(isOnCircle(x, y, radius, quadData.getLocation())){
                    quadDataList.add(quadData);
                }
            }
        }

        return quadDataList;

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
