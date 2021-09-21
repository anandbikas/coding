package com.anand.coding.dsalgo.tree.nary;

import java.util.Arrays;
import java.util.Objects;

/**
 * Nary Node with data and all children nodes.
 */
public class NaryNode <T extends Comparable<T>> implements Comparable<NaryNode<T>>{

    public T data;
    public NaryNode<T> [] child;

    // Extra fields to support O(H) lock/unlock operation.
    public NaryNode<T> parent;
    public boolean isLocked;
    public int lockedDescendants;

    public NaryNode(T data, int N, NaryNode<T> parent){
        this.data = data;
        child = new NaryNode[N];

        this.parent = parent;
        lockedDescendants = 0;
    }

    public boolean isLeafNode(){
        return Arrays.stream(child).filter(Objects::nonNull).findFirst().orElse(null) == null;
    }

    /**
     * O(h) lock node
     * An unlocked node can be locked provided no ancestors and descendants nodes are locked.
     *
     * @return
     */
    public boolean lock(){

        if(isLocked){
            return false;
        }

        //Ancestors lock check
        for(NaryNode<T> parent = this.parent; parent!=null; parent=parent.parent){
            if(parent.isLocked) return false;
        }

        //Descendant lock check
        if(lockedDescendants>0){
            return false;
        }

        isLocked=true;
        //Provide lock details to all the ancestors
        for(NaryNode<T> parent = this.parent; parent!=null ; parent=parent.parent){
            parent.lockedDescendants++;
        }

        return true;
    }

    /**
     * O(h) unlock node
     *
     * @return
     */
    public boolean unLock(){

        if(!isLocked){
            return false;
        }

        isLocked=false;
        //Provide unlock details to all the ancestors
        for(NaryNode<T> parent = this.parent; parent!=null ; parent=parent.parent){
            parent.lockedDescendants--;
        }

        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    @Override
    public int compareTo(NaryNode<T> node) {
        return data.compareTo(node.data);
    }
}
