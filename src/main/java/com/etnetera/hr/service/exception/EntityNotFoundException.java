package com.etnetera.hr.service.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(long id) {
        super("EntityNotFoundException with id = " + id);
    }
}
