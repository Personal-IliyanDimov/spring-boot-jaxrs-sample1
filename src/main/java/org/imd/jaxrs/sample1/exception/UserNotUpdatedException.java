package org.imd.jaxrs.sample1.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserNotUpdatedException extends Exception {
    private final Exception nestedException;
}
