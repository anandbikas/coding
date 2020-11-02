package com.anand.coding.java.java8.defaultMethod;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StandardDeck implements Deck {

    private List<Card> entireDeck;


    @Override
    public List<Card> getCards() {
        return entireDeck;
    }


    @Override
    public int size() {
        return entireDeck.size();
    }

    @Override
    public void addCard(Card card) {
        entireDeck.add(card);

    }

    @Override
    public void addCards(List<Card> cards) {
        entireDeck.addAll(cards);
    }

    @Override
    public void addDeck(Deck deck) {
        entireDeck.addAll(deck.getCards());
    }

    @Override
    public void shuffle() {

    }

    @Override
    public Map<Integer, Deck> deal(int players, int numberOfCards) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void sort() {
        Collections.sort(entireDeck);
    }

    @Override
    public void sort(Comparator<Card> c) {
        Collections.sort(entireDeck, c);
    }

    @Override
    public String toString() {
        return null;
    }

}