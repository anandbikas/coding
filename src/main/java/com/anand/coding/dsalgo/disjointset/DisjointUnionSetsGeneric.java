package com.anand.coding.dsalgo.disjointset;

import java.util.HashMap;

/**
 *
 */
public class DisjointUnionSetsGeneric<T> {

    HashMap<T, T> Parent = new HashMap<>();
    HashMap<T, Integer> Rank = new HashMap<>();

    public void insert(T obj){
        if(!Parent.containsKey(obj)){
            Parent.put(obj,obj);
            Rank.put(obj,0);
        }
    }

    /**
     *
     * @return
     */
    public int countSets(){
        return (int) Parent.keySet().stream().filter(k -> Parent.get(k)==k).count();
    }

    /**
     * Find the representative/parent of element obj
    */
    public T findRec(T obj) {
        if (Parent.get(obj) == obj) { // i itself is the representative
            return obj;
        }

        T rep = findRec(Parent.get(obj)); // Else recursively call find on its parent
        return Parent.put(obj, rep); // Path Compression:
    }

    /**
     * Find the representative of element obj
     */
    public T find(T obj) {

        T rep=obj;
        for(; rep!=Parent.get(obj); rep=Parent.get(rep));

        //Path Compression:
        for(T k=obj; k!=Parent.get(k);) {
            T oldParentOfK = Parent.get(k);
            Parent.put(k,rep);
            k = oldParentOfK;
        }
        return rep;
    }

    /**
     *  Unite two disjoint sets containing elements obj1 and obj2 respectively.
     *
     * @param obj1
     * @param obj2
     */
    public boolean union(T obj1, T obj2) {

        T iRep = this.find(obj1);
        T jRep = this.find(obj2);

        if(iRep==jRep) {
            return false;
        }

        if(Rank.get(iRep) < Rank.get(jRep)) {
            Parent.put(iRep, jRep);
        } else if (Rank.get(iRep) > Rank.get(jRep)) {
            Parent.put(jRep, iRep);
        } else {
            Parent.put(iRep, jRep);
            Rank.put(jRep,Rank.get(jRep)+1);
        }
        return true;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        // dus for 5 persons with ids: 0, 1, 2, 3 and 4
        DisjointUnionSetsGeneric<String> dus = new DisjointUnionSetsGeneric<>();

        for(int i=0; i< 5; i++) dus.insert("person" + i);

        dus.union("person0", "person2");
        dus.union("person4", "person2");
        dus.union("person3", "person1");

        // Check if 4 is a friend of 0
        System.out.println(dus.find("person4") == dus.find("person0"));

        // Check if 1 is a friend of 0
        System.out.println(dus.find("person1") == dus.find("person0"));

        System.out.println(dus.countSets());
    }
}
