package com.anand.coding.java.java8.defaultMethod;


import java.util.Comparator;

/**
 * Methods in interface can have implementation, called default methods.
 * Implementation class can further override default methods.
 * In case of multiple inheritance with same default method signature, the implementing class needs to specify
 * one by overriding using MyInterface1.super.show();
 *
 * Reference: https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
 *
 */
public class CardDemo {

    /**
     *
     * @param args
     */
    public static void main(String [] args){
        StandardDeck myDeck = new StandardDeck();
        myDeck.shuffle();

        //Sort by rank using lamda expression
        myDeck.sort((card1, card2) -> card1.getRank().value() - card2.getRank().value());

        // Static methods comparing, comparingDouble, comparingLong etc in Comparator interface
        //
        // It would be simpler if we create a Comparator instance by invoking the method Card.getRank only.
        // In particular, create a Comparator instance that compares any object that can return a numerical value
        // from a method such as getValue or hashCode.

        // Using lamda expression
        myDeck.sort(Comparator.comparing((card) -> card.getRank()));
        // Using method reference
        myDeck.sort(Comparator.comparing(Card::getRank));


        // Sort By multiple criteria: by rank then by suit

        // using comparator class
        myDeck.sort(new SortByRankThenSuitComparator());

        // using lambda expression
        myDeck.sort(
                (card1, card2) -> {
                    int compVal =
                            card1.getRank().value() - card2.getRank().value();
                    return (compVal != 0) ? compVal : card1.getSuit().value() - card2.getSuit().value();
                }
        );


        // Default methods thenComparing, thenComparingDouble, thenComparingLong etc in Comparator interface
        //
        // It would be simpler if we build a Comparator instance from a series of Comparator instances.
        myDeck.sort(Comparator
                        .comparing(Card::getRank)
                            .thenComparing(Card::getSuit));

        // reversed method
        myDeck.sort(Comparator
                        .comparing(Card::getRank)
                            .reversed()
                                .thenComparing(Card::getSuit));

    }
}
