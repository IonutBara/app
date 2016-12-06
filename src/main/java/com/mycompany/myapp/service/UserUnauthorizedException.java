package com.mycompany.myapp.service;

/**
 * Created by ibara on 12/6/2016.
 */
public class UserUnauthorizedException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserUnauthorizedException(String message) {
        super(message);
    }

    public UserUnauthorizedException(String message, Throwable t) {
        super(message, t);
    }
}
