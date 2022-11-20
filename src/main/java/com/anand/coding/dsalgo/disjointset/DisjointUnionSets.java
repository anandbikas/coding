package com.anand.coding.dsalgo.disjointset;

import java.util.stream.IntStream;

/**
 * N persons can become friends with one another.
 *
 * Operations:
 *  1. Find if x and y are direct friend.
 *  2. Find if x and y are indirect friend.
 *  3. Find if x and y doesn't relate to each other directly or indirectly.
 *  4. Make x and y friends.
 *
 *  Array Implementation.
 *  Linked List implementation is handy for adding a new member.
 *
 */
public class DisjointUnionSets {

    int [] Parent;
    int [] Rank;

    public DisjointUnionSets(int n){
        Parent = new int[n];
        Rank = new int[n];

        // Creates n sets with single item in each. Initially, all elements are in their own set.
        for (int i = 0; i < Parent.length; Parent[i]=i++);
    }

    /**
     *
     * @return
     */
    public int countSets(){
        return (int) IntStream.range(0, Parent.length).filter(i -> Parent[i]==i).count();
    }

    /**
     * Find the representative/parent of element i
    */
    public int findRec(int i) {
        if (this.Parent[i] == i) { // i itself is the representative
            return i;
        }

        int rep = findRec(this.Parent[i]); // Else recursively call find on its parent
        return this.Parent[i] = rep; // Path Compression:
    }

    /**
     * Find the representative of element i
     */
    public int find(int i) {

        int iRep=i;
        for(; iRep!=Parent[iRep]; iRep=Parent[iRep]);

        //Path Compression:
        for(int k=i; k!=Parent[k];) {
            int oldParentOfK = Parent[k];
            Parent[k] = iRep;
            k = oldParentOfK;
        }
        return iRep;
    }

    /**
     *  Unite two disjoint sets containing elements i and j respectively.
     *
     * @param i
     * @param j
     * @return false if union already present, else true
     */
    public boolean union(int i, int j) {

        int iRep = this.find(i);
        int jRep = this.find(j);

        if(iRep==jRep) {
            return false;
        }

        if(Rank[iRep] < Rank[jRep]) {
            Parent[iRep] = jRep;
        } else if (Rank[iRep] > Rank[jRep]) {
            Parent[jRep] = iRep;
        } else {
            Parent[iRep] = jRep;
            Rank[jRep]++;
        }
        return true;
    }

    /**
     * Find max depth of union in a group.
     * Recursively find the depth. Alternatively find max_of_rank + 1.
     *
     * @return
     */
    public int depth() {

        int maxDepth=0;
        boolean []visited = new boolean[Parent.length];

        for(int i=0; i<Parent.length; i++){
            if(visited[i]){
                continue;
            }
            int depth=1;
            for(int k=i; k!=Parent[k]; visited[k]=true, depth++, k=Parent[k]);

            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        // dus for 5 persons with ids: 0, 1, 2, 3 and 4
        DisjointUnionSets dus = new DisjointUnionSets(5);

        dus.union(0, 2);
        dus.union(4, 2);
        dus.union(3, 1);

        // Check if 4 is a friend of 0
        System.out.println(dus.find(4) == dus.find(0));

        // Check if 1 is a friend of 0
        System.out.println(dus.find(1) == dus.find(0));

        System.out.println(dus.countSets());

        System.out.println(dus.depth());
    }
}
