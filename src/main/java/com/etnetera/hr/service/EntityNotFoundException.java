package com.etnetera.hr.service;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(long id) {
        super("EntityNotFoundException with id = " + id);
    }
}
