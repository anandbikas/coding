package com.anand.coding.dsalgo.tree.trie;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class EnglishAlphabet implements Alphabet {

    private final static int A = 'A';

    private final static Set<Character> charSet = new HashSet<>();
    static {
        for(char c = 'A'; c<='Z'; c++){
            charSet.add(c);
        }
    }

    public Set<Character> charSet() {
        return charSet;
    }

    public int size(){
        return charSet.size();
    }

    public int charToIndex(char c){
        c = Character.toUpperCase(c);

        if(charSet.contains(c)){
            return c-A;
        }
        return -1;
    }
}
