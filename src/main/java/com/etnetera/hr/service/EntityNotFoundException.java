package com.etnetera.hr.service;

/**
 * Custom exceptions for http 404 error to the clients.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(long id) {
        super("EntityNotFoundException with id = " + id);
    }
}
