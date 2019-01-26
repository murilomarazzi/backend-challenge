package com.invillia.acme.exception;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
