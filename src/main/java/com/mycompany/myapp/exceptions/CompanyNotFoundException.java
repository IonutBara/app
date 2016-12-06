package com.mycompany.myapp.exceptions;

/**
 * Created by ibara on 12/6/2016.
 */
public class CompanyNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CompanyNotFoundException(String message) {
        super(message);
    }

    public CompanyNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
