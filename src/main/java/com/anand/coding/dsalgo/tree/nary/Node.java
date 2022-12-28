package com.anand.coding.dsalgo.tree.nary;

import java.util.Arrays;
import java.util.Objects;

/**
 * Nary Tree Node
 */
public class Node<T extends Comparable<T>>{

    public T data;
    public Node<T>[] child;

    // Additional fields to support O(H) lock/unlock operation.
    public Node<T> parent;
    public boolean isLocked;
    public int lockedDescendants;

    public Node(T data, int N, Node<T> parent){
        this.data = data;
        child = new Node[N];

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

    private boolean lockWithSynchronizedAncestor(final Node<T> parent){

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

    private boolean unlockWithSynchronizedAncestor(final Node<T> parent){

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
}
