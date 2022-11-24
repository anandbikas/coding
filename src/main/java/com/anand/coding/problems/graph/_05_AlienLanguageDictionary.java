package com.anand.coding.problems.graph;
import java.util.*;

/**
 * 1. Find order of characters using sorted list of alien language words. Use Graph topological sorting.
 * 2. Given order or characters, Validate whether words are sorted.
 */
public class _05_AlienLanguageDictionary {


    public static List<Character> findAlphabetsOrder(String []words){

        HashMap<Character, Set<Character>> vertices = new HashMap<>();
        int n = words.length;

        if(n<=1){
            return new ArrayList<>();
        }

        // Create graph.
        for(int k=0; k<n-1; k++){
            String w1=words[k], w2 = words[k+1];

            for(int i=0; i<w1.length() && i<w2.length(); i++){
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
        List<Character> sortedVertices = new ArrayList<>();

        Map<Character,Integer> inDegrees = new HashMap<>();
        for(Character u: vertices.keySet()) {
            inDegrees.put(u, 0);
        }

        for(Character u: vertices.keySet()){
            for(Character v: vertices.get(u)) {
                inDegrees.put(v, inDegrees.get(v)+1);
            }
        }

        vertices.keySet().stream().filter(u->inDegrees.get(u)==0).forEach(queue::add);

        while(!queue.isEmpty()){
            Character u = queue.remove();
            sortedVertices.add(u);

            for(Character v: vertices.get(u)) {
                //Reduce inDegree once its parent is processed.
                inDegrees.put(v,inDegrees.get(v)-1);
                if(inDegrees.get(v) == 0){
                    queue.add(v);
                }
            }
        }

        return sortedVertices;
    }

    public static boolean isAlienWordsSorted(String[] words, List<Character> order) {

        Map<Character, Integer> orderMap = new HashMap<>();
        for(int i=0; i<order.size(); i++){
            orderMap.put(order.get(i), i);
        }

        for(int k=0; k<words.length-1; k++){
            String x = words[k], y = words[k+1];

            int i=0;
            for(; i<x.length() && i<y.length() && x.charAt(i)==y.charAt(i); i++);

            if(i<x.length() && (i==y.length() || orderMap.get(x.charAt(i))>orderMap.get(y.charAt(i)))){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        String []wordList1 = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(findAlphabetsOrder(wordList1));
        System.out.println(isAlienWordsSorted(wordList1, findAlphabetsOrder(wordList1)));

        String [] wordList2 = {"caa", "aaa", "aab"};
        System.out.println(findAlphabetsOrder(wordList2));
        System.out.println(isAlienWordsSorted(wordList2, findAlphabetsOrder(wordList2)));
    }
}
