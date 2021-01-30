package com.anand.coding.problems.graph;
import java.util.*;

/**
 * Find order of characters using sorted list of alien language words. Use Graph topological sorting.
 *
 */
public class _05_AlienLanguageDictionary {


    /**
     *
     * @param words
     * @return
     */
    public static List<Character> findAlphabetsOrder(String []words){

        HashMap<Character, Set<Character>> vertices = new HashMap<>();

        int n = words.length;

        if(n<=1){
            return new ArrayList<>();
        }

        // Create graph.
        for(int k=0; k<n-1; k++){

            String w1=words[k], w2 = words[k+1];
            for(int i=0 ;i < w1.length() && i < w2.length(); i++){
                char u=w1.charAt(i), v=w2.charAt(i);
               if(u!=v){
                    if(!vertices.containsKey(u)){
                        vertices.put(u, new HashSet<>());
                    }
                    if(!vertices.containsKey(v)){
                        vertices.put(v, new HashSet<>());
                    }
                    vertices.get(u).add(v);
                    break;
               }
            }
        }

        //Topological Sorting

        Queue<Character> queue = new ArrayDeque<>(vertices.size());
        List<Character> topologicallySortedVertices = new ArrayList<>();

        Map<Character,Integer> inDegrees = new HashMap<>();
        for(Character u: vertices.keySet()) {
            inDegrees.put(u, 0);
        }

        for(Character u: vertices.keySet()){
            for(Character v: vertices.get(u)) {
                inDegrees.put(v, inDegrees.get(v)+1);
            }
        }

        for(Character u: vertices.keySet()){
            if(inDegrees.get(u)==0){
                queue.add(u);
            }
        }

        while(!queue.isEmpty()){
            Character u = queue.remove();
            topologicallySortedVertices.add(u);

            for(Character v: vertices.get(u)) {
                //Reduce indegree once its parent is processed.
                inDegrees.put(v,inDegrees.get(v)-1);
                if(inDegrees.get(v) == 0){
                    queue.add(v);
                }
            }
        }

        return topologicallySortedVertices;
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){

        String []wordList1 = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(findAlphabetsOrder(wordList1));

        String [] wordList2 = {"caa", "aaa", "aab"};
        System.out.println(findAlphabetsOrder(wordList2));
    }
}
