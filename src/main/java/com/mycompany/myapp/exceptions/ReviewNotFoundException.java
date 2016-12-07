package com.mycompany.myapp.exceptions;

/**
 * Created by ibara on 12/6/2016.
 */
public class ReviewNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
