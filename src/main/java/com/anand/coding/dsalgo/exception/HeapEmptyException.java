package com.anand.coding.dsalgo.exception;

public class HeapEmptyException extends RuntimeException {

    /**
     *
     */
    public HeapEmptyException() {
        super();
    }

    /**
     *
     * @param message
     */
    public HeapEmptyException(String message) {
        super(message);
    }
}
