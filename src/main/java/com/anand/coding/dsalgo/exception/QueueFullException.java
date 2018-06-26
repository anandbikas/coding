package com.anand.coding.dsalgo.exception;

public class QueueFullException extends RuntimeException {

    /**
     *
     */
    public QueueFullException() {
        super();
    }

    /**
     *
     * @param message
     */
    public QueueFullException(String message) {
        super(message);
    }
}
