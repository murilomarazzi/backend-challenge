package com.invillia.acme.exception;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */
public class ResourceNotFoundException extends RuntimeException {

    private Long id;

    public ResourceNotFoundException(Long id, String origin, String entity) {
        super(String.format("%s - %s: id %s was not found", origin, entity, id));
        this.id = id;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public Long getId() {
        return id;
    }
}
