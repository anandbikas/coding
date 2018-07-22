package com.anand.coding.dsalgo.exception;

public class HeapFullException extends RuntimeException {

    /**
     *
     */
    public HeapFullException() {
        super();
    }

    /**
     *
     * @param message
     */
    public HeapFullException(String message) {
        super(message);
    }
}
