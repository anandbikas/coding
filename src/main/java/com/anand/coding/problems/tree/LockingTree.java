package com.anand.coding.problems.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leet: /operations-on-tree/
 *
 * parent[i] represents parent of node i. parent[0]=-1 as its the root.
 *
 * Operations:
 *   1. lock: locks a node if it is not locked.
 *   2. unlock: unlock a node if it's locked.
 *   3. upgrade: Locks a node and unlocks all of its descendants.
 *         provided: a. node is unlocked
 *                   b. has at least one locked descendant
 *                   c. no any locked ancestors.
 */
class LockingTree {

    private Map<Integer, Integer> lock = new HashMap<>();

    private int[] parent;

    public LockingTree(int[] parent) {
        this.parent = parent;
    }

    public boolean lock(int num, int user) {

        synchronized(lock) {
            if (lock.get(num) == null) {
                lock.put(num, user);
                return true;
            }
        }
        return false;
    }

    public boolean unlock(int num, int user) {

        synchronized(lock) {
            if (lock.containsKey(num) && lock.get(num) == user) {
                lock.remove(num);
                return true;
            }
        }
        return false;
    }

    public boolean upgrade(int num, int user) {

        synchronized(lock){
            List<Integer> lockedDescendants = new ArrayList<>();

            if(lock.get(num)==null && isAllAncestorsUnlocked(num) ){
                for(int node: lock.keySet()){
                    if(isThereMatchingParent(node, num)){
                        lockedDescendants.add(node);
                    }
                }
            }
            if(!lockedDescendants.isEmpty()){
                lockedDescendants.forEach(node -> lock.remove(node));
                lock.put(num, user);
                return true;
            }
        }

        return false;
    }

    private boolean isAllAncestorsUnlocked(int num){
        int node;
        for(node=num; node!=-1 && lock.get(node)==null; node = parent[node]);
        return node==-1;
    }

    private boolean isThereMatchingParent(int node, int num){
        for(; node!=-1 && node!=num; node = parent[node]);
        return node==num;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        LockingTree lockingTree = new LockingTree(new int[]{-1,0,0,1,1,2,2});

        lockingTree.lock(2,5);
        lockingTree.lock(3,2);
        lockingTree.lock(1,3);
    }
}
