package org.imd.jaxrs.sample1.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserNotFoundException extends Exception {
    private final Long id;

}
