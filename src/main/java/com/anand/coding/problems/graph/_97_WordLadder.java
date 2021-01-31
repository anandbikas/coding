package com.anand.coding.problems.graph;

import java.util.*;

/**
 *  Given two words (beginWord and endWord), and a dictionary's word list,
 *  find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time. Each transformed word must exist in the word list.
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 */
public class _97_WordLadder {

    HashMap<String, Set<String>> vertices = new HashMap<>();
    Map<String, Integer> distFromSrc = new HashMap<>(); //this works as visited too
    int size;

    public List<List<String>> wordLadder(String beginWord, String endWord, List<String> wordList){
        wordList.add(beginWord);
        size = wordList.size();

        for(String word: wordList){
            vertices.put(word, new HashSet<>());
        }
        if(!vertices.containsKey(endWord)){
            return new ArrayList<>();
        }

        // Create graph.
        for(int k=0; k<size; k++){
            for(int p=k+1; p<size; p++) {
                String w1 = wordList.get(k);
                String w2 = wordList.get(p);

                if(oneCharDifference(w1,w2)){
                    vertices.get(w1).add(w2);
                    vertices.get(w2).add(w1);
                }
            }
        }

        // BFS to populate distance for all the nodes falling in the path of endWord.
        Queue<String> queue = new LinkedList<>();

        queue.add(beginWord);
        distFromSrc.put(beginWord,0);

        while(!queue.isEmpty()){
            String u = queue.remove();
            if(u.equals(endWord)){
                break;
            }

            for(String v: vertices.get(u)) {
                if(!distFromSrc.containsKey(v)) {
                    queue.add(v);
                    distFromSrc.put(v,distFromSrc.get(u)+1);
                }
            }
        }

        if(!distFromSrc.containsKey(endWord)){
            return new ArrayList<>();
        }

        // DFS to get all the shortest paths from begin to end.
        LinkedList<String> pathStack = new LinkedList<>();
        List<List<String>> pathList= new ArrayList<>();

        findAllPathsDFSRec(beginWord, endWord, pathStack, pathList, distFromSrc.get(endWord));
        return pathList;
    }

    private void findAllPathsDFSRec(String u, String v, LinkedList<String> pathStack, List<List<String>> pathList, int length){

        if(length<0){
            return;
        }
        pathStack.addLast(u);

        if(u.equals(v)){
            pathList.add((List<String>)pathStack.clone());

            pathStack.removeLast();
            return;
        }

        for(String vNode: vertices.get(u)){
            if(distFromSrc.containsKey(vNode) && distFromSrc.get(vNode)==distFromSrc.get(u)+1) // Most important to save TLE
                findAllPathsDFSRec(vNode, v, pathStack, pathList, length-1);
        }

        pathStack.removeLast();
    }

    public static boolean oneCharDifference(String s1, String s2){

        if(s1.length()!=s2.length()){
            return false;
        }

        int count =0;
        for(int i=0; i<s1.length() && count<2; i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
            }
        }
        return count<2;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        List<String> wordList = Arrays.asList("cog");
        System.out.println(new _97_WordLadder().wordLadder("hog", "cog", wordList));

        List<String> wordList0 = Arrays.asList("hot","dot","tog","cog");
        System.out.println(new _97_WordLadder().wordLadder("hit", "cog", wordList0));

        List<String> wordList1 = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(new _97_WordLadder().wordLadder("hit", "cog", wordList1));

        List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
        System.out.println(new _97_WordLadder().wordLadder("hit", "cog", wordList2));

        List<String> wordList3 = Arrays.asList("a","b","c");
        System.out.println(new _97_WordLadder().wordLadder("a", "c", wordList3));
    }
}
