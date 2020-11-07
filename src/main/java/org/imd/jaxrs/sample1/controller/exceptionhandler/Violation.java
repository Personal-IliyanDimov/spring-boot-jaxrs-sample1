package org.imd.jaxrs.sample1.controller.exceptionhandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Violation {
    private final String fieldName;
    private final String message;
}
