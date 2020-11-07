package org.imd.jaxrs.sample1.controller.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.imd.jaxrs.sample1.exception.UserAlreadyExistsException;
import org.imd.jaxrs.sample1.exception.UserNotFoundException;
import org.imd.jaxrs.sample1.exception.UserNotUpdatedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper;

    // user controller
    @ExceptionHandler(value = {UserNotUpdatedException.class})
    protected ResponseEntity<Object> handleUserNotFound(UserNotUpdatedException exc, WebRequest request) {
        String bodyOfResponse = "User is not updated. ";
        return prepareResponseEntity(exc, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { UserNotFoundException.class })
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException exc, WebRequest request) {
        final String bodyOfResponse = String.format("User with id %s not found: ", exc.getId());
        return prepareResponseEntity(exc, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { UserAlreadyExistsException.class })
    protected ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExistsException exc, WebRequest request) {
        final String bodyOfResponse = String.format("User with icn %s and type %s already exists", exc.getIcn(), exc.getType());
        return prepareResponseEntity(exc, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleRuntimeExceptions(RuntimeException re, WebRequest request) {
        String bodyOfResponse = "Internal error has occurred. ";
        return prepareResponseEntity(re, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // validation related
    @ExceptionHandler(value = { ConstraintViolationException.class })
    protected ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException exc, WebRequest request) {
        final ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        for (ConstraintViolation violation : exc.getConstraintViolations()) {
            validationErrorResponse.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        String bodyOfResponse = buildValidationBodyResponse(validationErrorResponse);
        return prepareResponseEntity(exc, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exc, HttpHeaders headers, HttpStatus status, WebRequest request) {

        final ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        for (FieldError fieldError : exc.getBindingResult().getFieldErrors()) {
            validationErrorResponse.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        String bodyOfResponse = buildValidationBodyResponse(validationErrorResponse);
        return prepareResponseEntity(exc, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    private String buildValidationBodyResponse(ValidationErrorResponse validationErrorResponse) {
        String bodyOfResponse = null;
        try {
            bodyOfResponse = objectMapper.writeValueAsString(validationErrorResponse);
        } catch (JsonProcessingException jpe) {
            logger.error("Unable to produce validation error response. ", jpe);
            bodyOfResponse = "";
        }
        return bodyOfResponse;
    }


    // common
    protected ResponseEntity<Object> prepareResponseEntity(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Unable to produce rest response.", ex);
        return new ResponseEntity<>(body, headers, status);
    }
}
