package com.anand.coding.dsalgo.tree.trie;

import com.anand.coding.dsalgo.tree.trie.exception.TrieCharacterNotSupportedException;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class EnglishAlphabet implements Alphabet {

    private final static int ALPHABET_SIZE = 26;
    private final static int A = 'A';

    private final static Set<Character> charSet = new HashSet<>();
    static
    {
        for(char c = 'A'; c<='Z'; c++){
            charSet.add(c);
        }
    }

    /**
     *
     * @return
     */
    public Set<Character> getCharSet() {
        return charSet;
    }

    /**
     *
     * @return
     */
    public int getSize(){
        return ALPHABET_SIZE;
    }

    /**
     *
     * @param c
     * @return
     * @throws TrieCharacterNotSupportedException
     */
    public int charToIndex(char c){
        c = Character.toUpperCase(c);
        if(c>='A' && c<='Z'){
            return Character.toUpperCase(c)-A;
        } else {
            throw new TrieCharacterNotSupportedException(String.format("Character %s not supported", c));
        }
    }
}
