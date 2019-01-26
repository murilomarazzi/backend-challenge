package com.invillia.acme.exception;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */
public class NoResultFoundException extends RuntimeException {
    public NoResultFoundException(String message) {
        super(message);
    }
}
