package com.anand.coding.java.java8.defaultMethod;

import java.util.*;

/**
 *
 */
public class SortByRankThenSuitComparator implements Comparator<Card> {

    public int compare(Card firstCard, Card secondCard) {

        int compVal =
                firstCard.getRank().value() - secondCard.getRank().value();
        return (compVal != 0) ? compVal : firstCard.getSuit().value() - secondCard.getSuit().value();
    }
}