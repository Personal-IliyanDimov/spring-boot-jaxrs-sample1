package org.imd.jaxrs.sample1.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.imd.jaxrs.sample1.model.domain.UserType;

@Getter
@ToString
@RequiredArgsConstructor
public class UserAlreadyExistsException extends Exception {
    private final String icn;
    private final UserType type;
}
