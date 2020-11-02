package com.anand.coding.java.java8.defaultMethod;

public class PlayingCard implements Card  {

    private Suit suit;
    private Rank rank;

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    public int hashCode() {
        return ((suit.value()-1)*13)+rank.value();
    }

    public int compareTo(Card o) {
        //Compare by suit then rank
        return this.hashCode() - o.hashCode();
    }

}