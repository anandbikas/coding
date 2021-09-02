package com.anand.coding.dsalgo.disjointset;

import java.util.HashMap;

/**
 *
 */
public class DisjointUnionSetsGeneric<T> {

    //TODO: parent and rank can be put in one hash map using object.
    HashMap<T, T> parentMap = new HashMap<>();
    HashMap<T, Integer> rankMap = new HashMap<>();

    public void insert(T obj){
        if(!parentMap.containsKey(obj)){
            parentMap.put(obj,obj);
            rankMap.put(obj,0);
        }
    }

    /**
     *
     * @return
     */
    public int countSets(){
        return (int)parentMap.keySet().stream().filter(k -> parentMap.get(k)==k).count();
    }

    /**
     * Find the representative of element obj
    */
    public T findRec(T obj) {

        if (parentMap.get(obj) == obj) { // i itself is the representative
            return obj;
        } else { // Else recursively call find on its parent
            T rep = findRec(parentMap.get(obj));

            parentMap.put(obj, rep); // Path Compression:
            return rep;
        }
    }

    /**
     * Find the representative of element i
     */
    public T find(T obj) {

        T rep=obj;
        for(; rep!=parentMap.get(obj); rep=parentMap.get(rep));

        //Path Compression:
        for(T k=obj; k!=parentMap.get(k);) {
            T oldParentOfK = parentMap.get(k);
            parentMap.put(k,rep);
            k = oldParentOfK;
        }
        return rep;
    }

    /**
     *  Unite two disjoint sets containing elements i and j respectively.
     *
     * @param obj1
     * @param obj2
     */
    public void union(T obj1, T obj2) {

        T iRep = this.find(obj1);
        T jRep = this.find(obj2);

        if(iRep==jRep){
            return;
        }

        if(rankMap.get(iRep) < rankMap.get(jRep)){
            parentMap.put(iRep, jRep);
        } else if (rankMap.get(iRep)>rankMap.get(jRep)){
            parentMap.put(jRep, iRep);
        } else {
            parentMap.put(iRep, jRep);
            rankMap.put(jRep,rankMap.get(jRep)+1);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        // dus for 5 persons with ids: 0, 1, 2, 3 and 4
        DisjointUnionSetsGeneric<String> dus = new DisjointUnionSetsGeneric<>();

        for(int i=0; i< 5; i++){
            dus.insert("person" + i);
        }

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
