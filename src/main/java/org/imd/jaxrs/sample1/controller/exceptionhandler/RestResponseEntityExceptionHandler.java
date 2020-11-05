package org.imd.jaxrs.sample1.controller.exceptionhandler;

import org.imd.jaxrs.sample1.exception.UserAlreadyExistsException;
import org.imd.jaxrs.sample1.exception.UserNotFoundException;
import org.imd.jaxrs.sample1.exception.UserNotUpdatedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotUpdatedException.class})
    protected ResponseEntity<Object> handleUserNotFound(UserNotUpdatedException exc, WebRequest request) {
        String bodyOfResponse = "User is not updated. ";
        return prepareResponseEntity(exc, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { UserNotFoundException.class })
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException exc, WebRequest request) {
        String bodyOfResponse = "User not found. ";
        return prepareResponseEntity(exc, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { UserAlreadyExistsException.class })
    protected ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExistsException exc, WebRequest request) {
        String bodyOfResponse = "User already exists. ";
        return prepareResponseEntity(exc, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleRuntimeExceptions(RuntimeException re, WebRequest request) {
        String bodyOfResponse = "Internal error has occurred. ";
        return prepareResponseEntity(re, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    protected ResponseEntity<Object> prepareResponseEntity(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, body, headers, status, request);
    }
}
