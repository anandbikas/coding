package com.anand.coding.dsalgo.disjointset;

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

        // Creates n sets with single item in each
        for (int i = 0; i < Parent.length; i++) {
            // Initially, all elements are in their own set.
            Parent[i] = i;
        }
    }

    /**
     *
     * @return
     */
    public int countSets(){
        int count = 0;
        for(int i=0; i<Parent.length; i++){
            if(Parent[i] == i){
                count++;
            }
        }
        return count;
    }

    /**
     * Find the representative of element i
    */
    public int find(int i) {

        // i itself is the representative
        if (this.Parent[i] == i){
            return i;
        }
        // Else recursively call find on its parent
        else {
            int rep = find(this.Parent[i]);

            // Path Compression:
            return this.Parent[i] = rep;
        }
    }

    /**
     *  Unite two disjoint sets containing elements i and j respectively.
     *
     * @param i
     * @param j
     */
    public void union(int i, int j) {

        // Find representative of of the set containing i
        int iRep = this.find(i);

        // Find representative of of the set containing i
        int jRep = this.find(j);

        if(iRep==jRep){
            return;
        }

        if(Rank[iRep] < Rank[jRep]){
            this.Parent[iRep] = jRep;
        } else if (Rank[iRep] > Rank[jRep]){
            this.Parent[jRep] = iRep;
        } else {
            this.Parent[iRep] = jRep;
            Rank[jRep]++;
        }
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
    }
}
