package org.imd.jaxrs.sample1.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class UserNotUpdatedException extends Exception {
    public UserNotUpdatedException(Exception causedBy) {
        super(causedBy);
    }
}
