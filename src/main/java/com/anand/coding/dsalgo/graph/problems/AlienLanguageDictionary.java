package com.anand.coding.dsalgo.graph.problems;

import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.graph.adjacencylist.usingHashMap.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Find order of characters using sorted list of alien language words. Use Graph topological sorting.
 *
 */
public class AlienLanguageDictionary {


    /**
     *
     * @param words
     * @return
     */
    public static List<Character> findAlphabetsInOrderUsingGenericGraph(String []words){

        int n = words.length;

        if(n<=1){
            return new ArrayList<>();
        }

        Graph<Character> graph = new Graph(GraphType.DIRECTED);

        for(int k=0; k<n-1; k++){

            String word1 = words[k];
            String word2 = words[k+1];

            int i=0,j=0;
            for(;i < word1.length() && j < word2.length(); i++,j++){

               if(word1.charAt(i)!=word2.charAt(j)){
                   graph.insert(word1.charAt(i));
                   graph.insert(word2.charAt(j));

                   graph.addEdge(word1.charAt(i), word2.charAt(j));

                   break;
               }
            }
        }

        return graph.topologicalSortingDfsRec();
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){

        String []wordList1 = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(findAlphabetsInOrderUsingGenericGraph(wordList1));


        String [] wordList2 = {"caa", "aaa", "aab"};
        System.out.println(findAlphabetsInOrderUsingGenericGraph(wordList2));
    }
}
