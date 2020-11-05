package org.imd.jaxrs.sample1.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAlreadyExistsException extends Exception {
    private final Long id;
}
