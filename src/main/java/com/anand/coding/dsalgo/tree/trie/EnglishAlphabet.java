package com.anand.coding.dsalgo.tree.trie;

import com.anand.coding.dsalgo.tree.trie.exception.TrieCharacterNotSupportedException;

/**
 *
 */
public class EnglishAlphabet implements Alphabet {

    private final static int ALPHABET_SIZE = 26;
    private final static int A = 'A';

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
