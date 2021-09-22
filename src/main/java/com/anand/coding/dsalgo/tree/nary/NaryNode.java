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
        synchronized (this){
            return !isLocked && lockedDescendants==0 && lockWithSynchronizedAncestor(this.parent);
        }
    }

    private boolean lockWithSynchronizedAncestor(final NaryNode<T>parent){

        if(parent==null){
            return this.isLocked = true;
        }
        synchronized (parent) {
            if(parent.isLocked) return false;
            if(lockWithSynchronizedAncestor(parent.parent)){
                parent.lockedDescendants++;
                return true;
            }
        }
        return false;
    }

    /**
     * O(h) unlock node
     *
     * @return
     */
    public boolean unLock(){
        synchronized (this){
            return isLocked && unlockWithSynchronizedAncestor(this.parent);
        }
    }

    private boolean unlockWithSynchronizedAncestor(final NaryNode<T>parent){

        if(parent==null){
            this.isLocked = false;
            return true;
        }
        synchronized (parent) {
            unlockWithSynchronizedAncestor(parent.parent);
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
