package org.imd.jaxrs.sample1.controller.exceptionhandler;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorResponse {
    private final List<Violation> violations = new ArrayList<>();
}
