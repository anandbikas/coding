package com.anand.coding.java.java8.defaultMethod;

import java.util.*;
import java.lang.*;

/**
 *
 */
public interface Deck {

    List<Card> getCards();
    //Deck deckFactory();
    int size();
    void addCard(Card card);
    void addCards(List<Card> cards);
    void addDeck(Deck deck);
    void shuffle();
    void sort();
    void sort(Comparator<Card> c);

    Map<Integer, Deck> deal(int players, int numberOfCards)
            throws IllegalArgumentException;

}
