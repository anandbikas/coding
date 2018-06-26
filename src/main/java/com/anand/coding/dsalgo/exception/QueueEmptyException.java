package com.anand.coding.dsalgo.exception;

public class QueueEmptyException extends RuntimeException {

    /**
     *
     */
    public QueueEmptyException() {
        super();
    }

    /**
     *
     * @param message
     */
    public QueueEmptyException(String message) {
        super(message);
    }
}
