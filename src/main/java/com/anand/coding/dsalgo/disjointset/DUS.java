package com.anand.coding.dsalgo.disjointset;

import java.util.stream.IntStream;

/**
 * Disjoint Union Set
 */
public class DUS {

    int [] Parent;

    public DUS(int n){
        Parent = new int[n];
        for (int i = 0; i < Parent.length; Parent[i]=i++);
    }

    public int countSets(){
        return (int) IntStream.range(0, Parent.length).filter(i -> Parent[i]==i).count();
    }

    public int find(int i) {

        int iRep=i;
        for(; iRep!=Parent[iRep]; iRep=Parent[iRep]);
        return iRep;
    }

    public boolean union(int i, int j) {

        int iRep = this.find(i);
        int jRep = this.find(j);

        if(iRep==jRep) {
            return false;
        }

        this.Parent[iRep] = jRep;
        return true;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        // dus for 5 persons with ids: 0, 1, 2, 3 and 4
        DUS dus = new DUS(5);

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
