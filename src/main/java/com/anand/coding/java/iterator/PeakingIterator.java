package com.anand.coding.java.iterator;

import java.util.Iterator;

/**
 * Extend existing iterator to add peek functionality.
 */
class PeekingIterator implements Iterator<Integer> {
    Integer next;
    Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        next = iterator.next();
    }

    public Integer peek() {
        return next;
    }

    @Override
    public Integer next() {
        int item = next;
        this.next = iterator.hasNext() ? iterator.next() : null;

        return item;
    }

    @Override
    public boolean hasNext() {
        return next!=null;
    }
}
