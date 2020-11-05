package org.imd.jaxrs.sample1.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserNotFoundException extends Exception {
    private final Long id;

}
