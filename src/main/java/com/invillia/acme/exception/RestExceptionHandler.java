package com.invillia.acme.exception;

import com.invillia.acme.util.GlobalResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<GlobalResponseMessage> handleGenericException(Exception ex, WebRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("handling exception...");
        }
        return new ResponseEntity<>(new GlobalResponseMessage(GlobalResponseMessage.Type.danger, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoResultFoundException.class, NullPointerException.class})
    public ResponseEntity<GlobalResponseMessage> handleNoResultFoundException(Exception ex, WebRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("handling null pointer exception...");
        }
        return new ResponseEntity<>(new GlobalResponseMessage(GlobalResponseMessage.Type.info, ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<GlobalResponseMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("handling ResourceNotFoundException...");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
